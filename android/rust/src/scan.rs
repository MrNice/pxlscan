//! Scan orchestration: shared state, subnet math, the sweep and resolve phases.
//!
//! Timeline for a /24 (all phases are time-bounded; the whole run has a hard cap):
//!   0.00-0.55  sweep      ICMP + quick TCP probes of every address, while mDNS / SSDP / NetBIOS
//!                         listen in parallel
//!   0.55-0.70  scanning   full port list against hosts that answered
//!   0.70-0.90  resolving  reverse DNS + HTTP `GET /` for each live host
//!   0.90-1.00  classifying
//!
//! Progress is monotonic, so the UI never sees it move backwards.

use std::collections::BTreeMap;
use std::net::{IpAddr, Ipv4Addr};
use std::sync::atomic::{AtomicBool, AtomicUsize, Ordering};
use std::sync::{Arc, Mutex, MutexGuard};
use std::time::{Duration, Instant};

use tokio::sync::Semaphore;
use tokio::task::JoinSet;

use crate::model::{Diag, Host, ScanConfig, Snapshot};
use crate::{classify, discover, dns, http, icmp};

/// Ports probed on every address. A refusal proves the host exists just as an open port does.
pub const QUICK_PORTS: &[u16] = &[80, 443, 62078, 22, 445, 8009, 7000, 5000];
/// Every port we ever probe (hosts that answered get all of them).
pub const ALL_PORTS: &[u16] = &[
    22, 53, 80, 139, 443, 445, 548, 554, 631, 1883, 3389, 5000, 7000, 8008, 8009, 8080, 8443, 9100,
    62078,
];

const TCP_PERMITS: usize = 96;
const HARD_CAP: Duration = Duration::from_secs(22);
const MAX_ERRORS: usize = 24;

struct Inner {
    hosts: BTreeMap<Ipv4Addr, Host>,
    diag: Diag,
    progress: f32,
    phase: &'static str,
    done: bool,
    elapsed_ms: u64,
}

/// State shared by every probe task. All methods take `&self` and lock briefly.
pub struct Shared {
    pub cfg: ScanConfig,
    pub self_ip: Ipv4Addr,
    pub gateway: Option<Ipv4Addr>,
    net: u32,
    mask: u32,
    total: u32,
    cancel: AtomicBool,
    start: Instant,
    inner: Mutex<Inner>,
}

impl Shared {
    pub fn new(cfg: ScanConfig) -> Result<Shared, String> {
        let self_ip: Ipv4Addr = cfg
            .self_ip
            .trim()
            .parse()
            .map_err(|_| format!("bad self_ip: {}", cfg.self_ip))?;
        let gateway = cfg
            .gateway
            .as_deref()
            .and_then(|g| g.trim().parse::<Ipv4Addr>().ok());
        let mut prefix = cfg.prefix_len.min(32);
        if prefix < 22 {
            prefix = 24; // huge nets are cut to the /24 around self
        }
        let mask: u32 = if prefix == 0 {
            0
        } else {
            u32::MAX << (32 - prefix as u32)
        };
        let net = u32::from(self_ip) & mask;
        let size = (!mask) as u64 + 1;
        let total = match prefix {
            32 => 1,
            31 => 2,
            _ => (size - 2) as u32,
        };
        let mut hosts = BTreeMap::new();
        let mut me = Host::new(&self_ip.to_string());
        me.is_self = true;
        me.name = cfg
            .self_name
            .clone()
            .filter(|n| !n.trim().is_empty())
            .unwrap_or_default();
        me.add_seen("self");
        hosts.insert(self_ip, me);
        Ok(Shared {
            cfg,
            self_ip,
            gateway,
            net,
            mask,
            total,
            cancel: AtomicBool::new(false),
            start: Instant::now(),
            inner: Mutex::new(Inner {
                hosts,
                diag: Diag::default(),
                progress: 0.0,
                phase: "scanning",
                done: false,
                elapsed_ms: 0,
            }),
        })
    }

    fn lock(&self) -> MutexGuard<'_, Inner> {
        self.inner.lock().unwrap_or_else(|p| p.into_inner())
    }

    pub fn contains(&self, ip: Ipv4Addr) -> bool {
        u32::from(ip) & self.mask == self.net
    }

    /// Every usable address of the subnet (self included).
    pub fn addrs(&self) -> Vec<Ipv4Addr> {
        let first = self.net as u64;
        let last = first + (!self.mask) as u64;
        let (lo, hi) = if self.total <= 2 {
            (first, last)
        } else {
            (first + 1, last - 1)
        };
        (lo..=hi).map(|n| Ipv4Addr::from(n as u32)).collect()
    }

    /// Addresses to probe: the subnet minus self.
    pub fn targets(&self) -> Vec<Ipv4Addr> {
        self.addrs()
            .into_iter()
            .filter(|a| *a != self.self_ip)
            .collect()
    }

    pub fn is_cancelled(&self) -> bool {
        self.cancel.load(Ordering::Relaxed)
    }

    pub fn request_cancel(&self) {
        self.cancel.store(true, Ordering::Relaxed);
    }

    /// Edit (creating if needed) the host record for `ip`. Addresses outside the subnet are ignored.
    pub fn with_host<R>(&self, ip: Ipv4Addr, f: impl FnOnce(&mut Host) -> R) -> Option<R> {
        if !self.contains(ip) {
            return None;
        }
        let mut g = self.lock();
        let gw = self.gateway == Some(ip);
        let h = g.hosts.entry(ip).or_insert_with(|| {
            let mut h = Host::new(&ip.to_string());
            h.is_gateway = gw;
            h
        });
        Some(f(h))
    }

    pub fn diag(&self, f: impl FnOnce(&mut Diag)) {
        f(&mut self.lock().diag);
    }

    pub fn error(&self, msg: String) {
        let mut g = self.lock();
        if g.diag.errors.len() < MAX_ERRORS && !g.diag.errors.contains(&msg) {
            g.diag.errors.push(msg);
        }
    }

    /// Hosts with any evidence of life, excluding self.
    pub fn alive(&self) -> Vec<Ipv4Addr> {
        self.lock()
            .hosts
            .keys()
            .copied()
            .filter(|a| *a != self.self_ip)
            .collect()
    }

    pub fn is_alive(&self, ip: Ipv4Addr) -> bool {
        self.lock().hosts.contains_key(&ip)
    }

    pub fn open_ports(&self, ip: Ipv4Addr) -> Vec<u16> {
        self.lock()
            .hosts
            .get(&ip)
            .map(|h| h.open_ports.clone())
            .unwrap_or_default()
    }

    pub fn set_phase(&self, phase: &'static str) {
        self.lock().phase = phase;
    }

    /// Raise progress to `p` (never lowers it).
    pub fn bump(&self, p: f32) {
        let mut g = self.lock();
        if p > g.progress && !g.done {
            g.progress = p.min(0.99);
        }
    }

    pub fn finish(&self) {
        let mut g = self.lock();
        g.done = true;
        g.progress = 1.0;
        g.elapsed_ms = self.start.elapsed().as_millis() as u64;
    }

    /// Current snapshot. Hosts are classified on the way out so names are never empty.
    pub fn snapshot(&self) -> Snapshot {
        let g = self.lock();
        let gw = self.gateway.map(|g| g.to_string());
        let hosts = g
            .hosts
            .values()
            .map(|h| {
                let mut h = h.clone();
                classify::finish(&mut h, gw.as_deref());
                h
            })
            .collect();
        Snapshot {
            progress: g.progress,
            phase: if g.done {
                "done".to_string()
            } else {
                g.phase.to_string()
            },
            done: g.done,
            elapsed_ms: if g.done {
                g.elapsed_ms
            } else {
                self.start.elapsed().as_millis() as u64
            },
            total_addresses: self.total,
            hosts,
            diag: g.diag.clone(),
        }
    }
}

/// One TCP connect attempt, classified into the `tcp_*` counters.
async fn tcp_probe(sh: &Shared, ip: Ipv4Addr, port: u16, timeout: Duration) {
    sh.diag(|d| d.tcp_attempts += 1);
    let started = Instant::now();
    let res = tokio::time::timeout(timeout, tokio::net::TcpStream::connect((ip, port))).await;
    match res {
        Ok(Ok(_)) => {
            let rtt = started.elapsed().as_secs_f32() * 1000.0;
            sh.diag(|d| d.tcp_open += 1);
            sh.with_host(ip, |h| {
                h.add_seen("tcp");
                if !h.open_ports.contains(&port) {
                    h.open_ports.push(port);
                    h.open_ports.sort_unstable();
                }
                if h.rtt_ms.is_none() {
                    h.rtt_ms = Some(rtt);
                }
            });
        }
        Ok(Err(e)) => {
            let raw = e.raw_os_error();
            if e.kind() == std::io::ErrorKind::ConnectionRefused {
                sh.diag(|d| d.tcp_refused += 1);
                sh.with_host(ip, |h| h.add_seen("tcp"));
            } else if e.kind() == std::io::ErrorKind::PermissionDenied
                || matches!(raw, Some(1) | Some(13))
            {
                sh.diag(|d| d.tcp_denied += 1);
            } else if matches!(raw, Some(101) | Some(113) | Some(100)) {
                sh.diag(|d| d.tcp_unreachable += 1);
            } else if e.kind() == std::io::ErrorKind::TimedOut {
                sh.diag(|d| d.tcp_timeout += 1);
            } else {
                sh.diag(|d| d.tcp_unreachable += 1);
            }
        }
        Err(_) => sh.diag(|d| d.tcp_timeout += 1),
    }
}

/// Probe `(ip, port)` pairs with bounded concurrency; progress runs from `p0` to `p1`.
async fn probe_batch(
    sh: &Arc<Shared>,
    sem: &Arc<Semaphore>,
    targets: Vec<(Ipv4Addr, u16)>,
    skip_if_alive: bool,
    p0: f32,
    p1: f32,
) {
    if targets.is_empty() {
        sh.bump(p1);
        return;
    }
    let total = targets.len();
    let done = Arc::new(AtomicUsize::new(0));
    let timeout = Duration::from_millis(sh.cfg.timeout_ms.clamp(150, 500));
    let mut set = JoinSet::new();
    for (ip, port) in targets {
        let (sh, sem, done) = (sh.clone(), sem.clone(), done.clone());
        set.spawn(async move {
            if let Ok(_permit) = sem.acquire().await {
                // Stage A only needs one sign of life; ICMP often answers while probes are queued.
                let skip = (skip_if_alive && sh.is_alive(ip)) || sh.is_cancelled();
                if !skip {
                    tcp_probe(&sh, ip, port, timeout).await;
                }
            }
            let n = done.fetch_add(1, Ordering::Relaxed) + 1;
            if n % 16 == 0 || n == total {
                sh.bump(p0 + (p1 - p0) * n as f32 / total as f32);
            }
        });
    }
    while set.join_next().await.is_some() {}
    sh.bump(p1);
}

/// Reverse DNS + HTTP for every live host.
async fn resolve(sh: &Arc<Shared>) {
    let hosts = sh.alive();
    if hosts.is_empty() {
        sh.bump(0.9);
        return;
    }
    let servers: Vec<IpAddr> = sh
        .cfg
        .dns_servers
        .iter()
        .filter_map(|s| s.trim().parse::<IpAddr>().ok())
        .collect();
    let sem = Arc::new(Semaphore::new(48));
    let total = hosts.len();
    let done = Arc::new(AtomicUsize::new(0));
    let t = Duration::from_millis(sh.cfg.timeout_ms.clamp(300, 1500));
    let mut set = JoinSet::new();
    for ip in hosts {
        let (sh, sem, done, servers) = (sh.clone(), sem.clone(), done.clone(), servers.clone());
        set.spawn(async move {
            if let Ok(_p) = sem.acquire().await {
                if sh.is_cancelled() {
                    return;
                }
                // Reverse DNS: first resolver that names the host wins.
                for s in &servers {
                    match dns::ptr_lookup(&sh, *s, ip, t).await {
                        dns::PtrResult::Name(n) => {
                            sh.with_host(ip, |h| {
                                h.add_seen("dns");
                                h.dns_name = Some(n);
                            });
                            break;
                        }
                        _ => continue,
                    }
                }
                // HTTP banner.
                let ports = sh.open_ports(ip);
                for port in [80u16, 8080] {
                    if !ports.contains(&port) {
                        continue;
                    }
                    if let Some((server, title)) =
                        http::probe(ip, port, t.max(Duration::from_millis(800))).await
                    {
                        sh.with_host(ip, |h| {
                            h.add_seen("http");
                            if h.http_server.is_none() {
                                h.http_server = server;
                            }
                            if h.http_title.is_none() {
                                h.http_title = title;
                            }
                        });
                        break;
                    }
                }
            }
            let n = done.fetch_add(1, Ordering::Relaxed) + 1;
            sh.bump(0.7 + 0.2 * n as f32 / total as f32);
        });
    }
    while set.join_next().await.is_some() {}
    sh.bump(0.9);
}

async fn run_inner(sh: &Arc<Shared>) {
    sh.set_phase("scanning");
    let mut disc = JoinSet::new();
    disc.spawn(discover::mdns(sh.clone()));
    disc.spawn(discover::ssdp(sh.clone()));
    disc.spawn(discover::netbios(sh.clone()));
    let icmp_sh = sh.clone();
    let icmp_task = tokio::task::spawn_blocking(move || icmp::sweep(&icmp_sh));

    let sem = Arc::new(Semaphore::new(TCP_PERMITS));

    // Stage A: every address, quick ports.
    let targets: Vec<(Ipv4Addr, u16)> = sh
        .targets()
        .into_iter()
        .flat_map(|ip| QUICK_PORTS.iter().map(move |p| (ip, *p)))
        .collect();
    probe_batch(sh, &sem, targets, true, 0.0, 0.55).await;
    let _ = icmp_task.await;
    sh.bump(0.55);

    // Stage B: full port list, live hosts only.
    let mut targets = Vec::new();
    for ip in sh.alive() {
        let open = sh.open_ports(ip);
        for p in ALL_PORTS {
            if !open.contains(p) {
                targets.push((ip, *p));
            }
        }
    }
    probe_batch(sh, &sem, targets, false, 0.55, 0.7).await;

    sh.set_phase("resolving");
    resolve(sh).await;

    // Let discovery finish (each part has its own deadline of a few seconds).
    sh.set_phase("classifying");
    sh.bump(0.92);
    while disc.join_next().await.is_some() {}
    sh.bump(0.97);
}

/// Run a whole scan to completion (or the hard cap), then mark the state done.
pub async fn run(sh: Arc<Shared>) {
    if tokio::time::timeout(HARD_CAP, run_inner(&sh))
        .await
        .is_err()
    {
        sh.error("scan hit the time cap; results are partial".to_string());
    }
    sh.finish();
}

#[cfg(test)]
mod tests {
    use super::*;

    fn cfg(ip: &str, prefix: u8) -> ScanConfig {
        ScanConfig {
            self_ip: ip.into(),
            prefix_len: prefix,
            gateway: Some("192.168.1.1".into()),
            dns_servers: vec![],
            timeout_ms: 700,
            self_name: Some("Pixel 8 Pro".into()),
        }
    }

    #[test]
    fn slash24_has_254_targets_and_self_host() {
        let s = Shared::new(cfg("192.168.1.23", 24)).unwrap();
        let a = s.addrs();
        assert_eq!(a.len(), 254);
        assert_eq!(a[0], Ipv4Addr::new(192, 168, 1, 1));
        assert_eq!(a[253], Ipv4Addr::new(192, 168, 1, 254));
        assert_eq!(s.targets().len(), 253);
        let snap = s.snapshot();
        assert_eq!(snap.total_addresses, 254);
        assert_eq!(snap.hosts.len(), 1);
        assert!(snap.hosts[0].is_self);
        assert_eq!(snap.hosts[0].name, "Pixel 8 Pro");
    }

    #[test]
    fn big_nets_are_cut_to_the_slash24_and_slash22_is_kept() {
        let s = Shared::new(cfg("10.1.2.3", 16)).unwrap();
        assert_eq!(s.addrs().len(), 254);
        assert!(s.contains(Ipv4Addr::new(10, 1, 2, 200)));
        assert!(!s.contains(Ipv4Addr::new(10, 1, 3, 1)));
        let s22 = Shared::new(cfg("10.1.2.3", 22)).unwrap();
        assert_eq!(s22.addrs().len(), 1022);
    }

    #[test]
    fn gateway_is_flagged_when_seen_and_outsiders_ignored() {
        let s = Shared::new(cfg("192.168.1.23", 24)).unwrap();
        s.with_host(Ipv4Addr::new(192, 168, 1, 1), |h| h.add_seen("icmp"));
        assert!(s.with_host(Ipv4Addr::new(8, 8, 8, 8), |_| ()).is_none());
        let snap = s.snapshot();
        let gw = snap.hosts.iter().find(|h| h.ip == "192.168.1.1").unwrap();
        assert!(gw.is_gateway);
        assert_eq!(gw.kind, "router");
        assert!(!gw.name.is_empty());
    }

    #[test]
    fn progress_is_monotonic_and_finish_sets_done() {
        let s = Shared::new(cfg("192.168.1.23", 24)).unwrap();
        s.bump(0.5);
        s.bump(0.2);
        assert_eq!(s.snapshot().progress, 0.5);
        s.finish();
        let snap = s.snapshot();
        assert!(snap.done);
        assert_eq!(snap.progress, 1.0);
    }

    #[test]
    fn bad_self_ip_is_an_error() {
        assert!(Shared::new(cfg("nope", 24)).is_err());
    }
}
