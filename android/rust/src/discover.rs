//! Multicast / broadcast discovery: mDNS, SSDP (+ LOCATION XML) and NetBIOS node-status.
//! Each `pub async fn` runs from scan start with its own deadline and writes into `Shared`.

use std::collections::HashMap;
use std::io;
use std::net::{Ipv4Addr, SocketAddr, SocketAddrV4};
use std::sync::Arc;
use std::time::Duration;

use socket2::{Domain, Protocol, Socket, Type};
use tokio::net::UdpSocket;
use tokio::time::Instant;

use crate::dns::{self, RData, Record};
use crate::http;
use crate::scan::Shared;

pub const SSDP_USER_AGENT: &str = "Android/15 UPnP/1.1 PxLScan/1.0";

const MDNS_TCP: &[&str] = &[
    "_airplay",
    "_googlecast",
    "_ipp",
    "_ipps",
    "_printer",
    "_pdl-datastream",
    "_hap",
    "_companion-link",
    "_apple-mobdev2",
    "_device-info",
    "_smb",
    "_afpovertcp",
    "_adisk",
    "_ssh",
    "_sftp-ssh",
    "_workstation",
    "_spotify-connect",
    "_sonos",
    "_matter",
    "_esphomelib",
    "_hue",
    "_amzn-wplay",
    "_androidtvremote2",
    "_nvstream",
    "_homekit",
    "_rdlink",
    "_scanner",
    "_uscan",
    "_raop",
    "_http",
];
const MDNS_UDP: &[&str] = &["_matterc", "_meshcop", "_sleep-proxy"];

/// UDP socket pinned to the LAN interface: bound to `self_ip`, multicast leaves through it.
fn bind_udp(self_ip: Ipv4Addr, mcast_ttl: u32) -> io::Result<UdpSocket> {
    let s = Socket::new(Domain::IPV4, Type::DGRAM, Some(Protocol::UDP))?;
    if s.bind(&SocketAddrV4::new(self_ip, 0).into()).is_err() {
        s.bind(&SocketAddrV4::new(Ipv4Addr::UNSPECIFIED, 0).into())?;
    }
    let _ = s.set_multicast_if_v4(&self_ip);
    let _ = s.set_multicast_ttl_v4(mcast_ttl);
    let _ = s.set_multicast_loop_v4(false);
    let _ = s.set_broadcast(true);
    s.set_nonblocking(true)?;
    UdpSocket::from_std(s.into())
}

// ---------------------------------------------------------------- mDNS

/// Full list of `<svc>._tcp.local` / `._udp.local` names plus the service enumeration name.
pub fn mdns_query_names() -> Vec<String> {
    let mut v = vec!["_services._dns-sd._udp.local".to_string()];
    v.extend(MDNS_TCP.iter().map(|s| format!("{s}._tcp.local")));
    v.extend(MDNS_UDP.iter().map(|s| format!("{s}._udp.local")));
    v
}

/// One mDNS packet with a PTR question per name. `unicast` sets the QU bit.
pub fn build_mdns_query(names: &[String], unicast: bool) -> Vec<u8> {
    let mut p = vec![0u8; 12];
    p[4..6].copy_from_slice(&(names.len() as u16).to_be_bytes());
    for n in names {
        dns::push_name(&mut p, n);
        p.extend_from_slice(&dns::TYPE_PTR.to_be_bytes());
        let class: u16 = if unicast { 0x8001 } else { 0x0001 };
        p.extend_from_slice(&class.to_be_bytes());
    }
    p
}

/// What one datagram taught us about one host.
#[derive(Debug, Default, Clone, PartialEq)]
pub struct MdnsInfo {
    pub name: Option<String>,
    pub services: Vec<String>,
    pub model: Option<String>,
    pub friendly: Option<String>,
}

/// `_googlecast._tcp.local` or `Foo._googlecast._tcp.local` -> `_googlecast._tcp`.
pub fn service_of(name: &str) -> Option<String> {
    let name = name.trim_end_matches('.');
    let name = name.strip_suffix(".local").unwrap_or(name);
    let labels: Vec<&str> = name.split('.').collect();
    let i = labels.iter().position(|l| l.starts_with('_'))?;
    let rest = &labels[i..];
    if rest.len() < 2 {
        return None;
    }
    let last = rest[rest.len() - 1];
    if last != "_tcp" && last != "_udp" {
        return None;
    }
    Some(rest.join("."))
}

/// Group a reply's records by the host they describe. A records describe the host in their data;
/// everything else describes the sender.
pub fn summarize_mdns(recs: &[Record], from: Ipv4Addr) -> HashMap<Ipv4Addr, MdnsInfo> {
    let mut out: HashMap<Ipv4Addr, MdnsInfo> = HashMap::new();
    for r in recs {
        match &r.rdata {
            RData::A(ip) => {
                let e = out.entry(*ip).or_default();
                if e.name.is_none() {
                    e.name = Some(r.name.clone());
                }
            }
            RData::Ptr(target) => {
                let svc = if r.name.eq_ignore_ascii_case("_services._dns-sd._udp.local") {
                    service_of(target)
                } else {
                    service_of(&r.name)
                };
                if let Some(s) = svc {
                    let e = out.entry(from).or_default();
                    if !e.services.contains(&s) {
                        e.services.push(s);
                    }
                }
            }
            RData::Srv { target, .. } => {
                let e = out.entry(from).or_default();
                if e.name.is_none() && target.to_lowercase().ends_with(".local") {
                    e.name = Some(target.clone());
                }
                if let Some(s) = service_of(&r.name) {
                    if !e.services.contains(&s) {
                        e.services.push(s);
                    }
                }
            }
            RData::Txt(items) => {
                let e = out.entry(from).or_default();
                for it in items {
                    let Some((k, v)) = it.split_once('=') else {
                        continue;
                    };
                    let v = v.trim();
                    if v.is_empty() {
                        continue;
                    }
                    match k.to_ascii_lowercase().as_str() {
                        "md" | "model" => {
                            if e.model.is_none() {
                                e.model = Some(v.to_string());
                            }
                        }
                        "fn" => {
                            if e.friendly.is_none() {
                                e.friendly = Some(v.to_string());
                            }
                        }
                        _ => {}
                    }
                }
            }
            RData::Other => {}
        }
    }
    out
}

pub async fn mdns(sh: Arc<Shared>) {
    let sock = match bind_udp(sh.self_ip, 255) {
        Ok(s) => s,
        Err(e) => {
            sh.error(format!("mdns socket: {e}"));
            return;
        }
    };
    let mcast = SocketAddr::from((Ipv4Addr::new(224, 0, 0, 251), 5353));
    let names = mdns_query_names();
    let multicast_pkts: Vec<Vec<u8>> = names
        .chunks(12)
        .map(|c| build_mdns_query(c, true))
        .collect();

    let send_multicast = |first: bool| {
        let pkts = &multicast_pkts;
        let sock = &sock;
        let sh = &sh;
        async move {
            for p in pkts {
                match sock.send_to(p, mcast).await {
                    Ok(_) => sh.diag(|d| d.udp_sent += 1),
                    Err(e) => {
                        sh.diag(|d| d.udp_send_errors += 1);
                        if first {
                            sh.error(format!("mdns multicast send: {e}"));
                        }
                    }
                }
            }
        }
    };
    send_multicast(true).await;

    // Direct unicast service enumeration to every address (legacy-unicast replies come back to us).
    let uq = build_mdns_query(&["_services._dns-sd._udp.local".to_string()], false);
    let mut logged = false;
    for (i, ip) in sh.targets().into_iter().enumerate() {
        if sh.is_cancelled() {
            return;
        }
        match sock.send_to(&uq, SocketAddr::from((ip, 5353))).await {
            Ok(_) => sh.diag(|d| d.udp_sent += 1),
            Err(e) => {
                sh.diag(|d| d.udp_send_errors += 1);
                if !logged {
                    logged = true;
                    sh.error(format!("mdns unicast send: {e}"));
                }
            }
        }
        if i % 32 == 31 {
            tokio::task::yield_now().await;
        }
    }

    let start = Instant::now();
    let deadline = start + Duration::from_millis(5000);
    let resend_at = start + Duration::from_millis(1800);
    let mut resent = false;
    let mut buf = vec![0u8; 9000];
    loop {
        let wake = if resent {
            deadline
        } else {
            resend_at.min(deadline)
        };
        match tokio::time::timeout_at(wake, sock.recv_from(&mut buf)).await {
            Ok(Ok((n, SocketAddr::V4(from)))) => {
                let Some(((_, flags), recs)) = dns::parse_message(&buf[..n]) else {
                    continue;
                };
                if flags & 0x8000 == 0 || recs.is_empty() {
                    continue;
                }
                sh.diag(|d| d.replies_mdns += 1);
                for (ip, info) in summarize_mdns(&recs, *from.ip()) {
                    sh.with_host(ip, |h| {
                        h.add_seen("mdns");
                        if h.mdns_name.is_none() {
                            h.mdns_name = info.name.clone();
                        }
                        for s in &info.services {
                            h.add_service(s);
                        }
                        if h.model.is_none() {
                            h.model = info.model.clone();
                        }
                        if h.friendly_name.is_none() {
                            h.friendly_name = info.friendly.clone();
                        }
                    });
                }
            }
            Ok(Ok(_)) => {}
            Ok(Err(_)) => break,
            Err(_) => {
                if Instant::now() >= deadline {
                    break;
                }
                resent = true;
                send_multicast(false).await;
            }
        }
        if sh.is_cancelled() {
            return;
        }
    }
}

// ---------------------------------------------------------------- SSDP

pub fn msearch(st: &str) -> String {
    format!(
        "M-SEARCH * HTTP/1.1\r\nHOST: 239.255.255.250:1900\r\nMAN: \"ssdp:discover\"\r\nMX: 1\r\nST: {st}\r\nUSER-AGENT: {SSDP_USER_AGENT}\r\n\r\n"
    )
}

#[derive(Debug, Default, Clone, PartialEq)]
pub struct SsdpReply {
    pub location: Option<String>,
    pub server: Option<String>,
    pub st: Option<String>,
    pub uuid: Option<String>,
}

/// Parse an SSDP response / NOTIFY. Header names are case-insensitive.
pub fn parse_ssdp(text: &str) -> Option<SsdpReply> {
    let mut lines = text.lines();
    let first = lines.next()?;
    if first.starts_with("M-SEARCH") {
        return None; // somebody else's query
    }
    let mut r = SsdpReply::default();
    for l in lines {
        let Some((k, v)) = l.split_once(':') else {
            continue;
        };
        let v = v.trim();
        if v.is_empty() {
            continue;
        }
        match k.trim().to_ascii_uppercase().as_str() {
            "LOCATION" => r.location = Some(v.to_string()),
            "SERVER" => r.server = Some(v.to_string()),
            "ST" | "NT" => {
                if r.st.is_none() {
                    r.st = Some(v.to_string());
                }
            }
            "USN" => {
                if r.uuid.is_none() && v.starts_with("uuid:") {
                    r.uuid = Some(v.split("::").next().unwrap_or(v).to_string());
                }
            }
            _ => {}
        }
    }
    if r.location.is_none() && r.server.is_none() && r.st.is_none() && r.uuid.is_none() {
        None
    } else {
        Some(r)
    }
}

#[derive(Debug, Default, Clone, PartialEq)]
pub struct DeviceXml {
    pub friendly_name: Option<String>,
    pub manufacturer: Option<String>,
    pub model_name: Option<String>,
    pub device_type: Option<String>,
}

fn tag_text(xml: &str, tag: &str) -> Option<String> {
    let open = format!("<{tag}>");
    let i = xml.find(&open)? + open.len();
    let j = xml[i..].find(&format!("</{tag}>"))? + i;
    let t = http::decode_entities(xml[i..j].trim());
    (!t.is_empty()).then_some(t)
}

pub fn parse_device_xml(xml: &str) -> DeviceXml {
    DeviceXml {
        friendly_name: tag_text(xml, "friendlyName"),
        manufacturer: tag_text(xml, "manufacturer"),
        model_name: tag_text(xml, "modelName"),
        device_type: tag_text(xml, "deviceType"),
    }
}

pub async fn ssdp(sh: Arc<Shared>) {
    let sock = match bind_udp(sh.self_ip, 2) {
        Ok(s) => s,
        Err(e) => {
            sh.error(format!("ssdp socket: {e}"));
            return;
        }
    };
    let dest = SocketAddr::from((Ipv4Addr::new(239, 255, 255, 250), 1900));
    let probes = [msearch("ssdp:all"), msearch("upnp:rootdevice")];
    let send = |first: bool| {
        let sock = &sock;
        let sh = &sh;
        let probes = &probes;
        async move {
            for p in probes {
                match sock.send_to(p.as_bytes(), dest).await {
                    Ok(_) => sh.diag(|d| d.udp_sent += 1),
                    Err(e) => {
                        sh.diag(|d| d.udp_send_errors += 1);
                        if first {
                            sh.error(format!("ssdp send: {e}"));
                        }
                    }
                }
            }
        }
    };
    send(true).await;

    let start = Instant::now();
    let deadline = start + Duration::from_millis(4000);
    let resend_at = start + Duration::from_millis(1500);
    let mut resent = false;
    let mut locations: HashMap<Ipv4Addr, String> = HashMap::new();
    let mut buf = vec![0u8; 4096];
    loop {
        let wake = if resent {
            deadline
        } else {
            resend_at.min(deadline)
        };
        match tokio::time::timeout_at(wake, sock.recv_from(&mut buf)).await {
            Ok(Ok((n, SocketAddr::V4(from)))) => {
                let text = String::from_utf8_lossy(&buf[..n]).into_owned();
                let Some(r) = parse_ssdp(&text) else { continue };
                sh.diag(|d| d.replies_ssdp += 1);
                let ip = *from.ip();
                if let Some(loc) = &r.location {
                    locations.entry(ip).or_insert_with(|| loc.clone());
                }
                sh.with_host(ip, |h| {
                    h.add_seen("ssdp");
                    if h.ssdp_server.is_none() {
                        h.ssdp_server = r.server.clone();
                    }
                    if h.ssdp_uuid.is_none() {
                        h.ssdp_uuid = r.uuid.clone();
                    }
                    if let Some(st) = &r.st {
                        let generic = |s: &str| s == "upnp:rootdevice" || s == "ssdp:all";
                        let better = match &h.ssdp_type {
                            None => true,
                            Some(cur) => generic(cur) && !generic(st),
                        };
                        if better {
                            h.ssdp_type = Some(st.clone());
                        }
                    }
                });
            }
            Ok(Ok(_)) => {}
            Ok(Err(_)) => break,
            Err(_) => {
                if Instant::now() >= deadline {
                    break;
                }
                resent = true;
                send(false).await;
            }
        }
        if sh.is_cancelled() {
            return;
        }
    }

    // Fetch each device description once.
    let mut set = tokio::task::JoinSet::new();
    for (ip, url) in locations {
        let sh = sh.clone();
        set.spawn(async move {
            let Some(body) = http::fetch_body(&url, Duration::from_millis(2000)).await else {
                return;
            };
            let x = parse_device_xml(&body);
            sh.with_host(ip, |h| {
                if h.friendly_name.is_none() {
                    h.friendly_name = x.friendly_name.clone();
                }
                if h.vendor.is_none() {
                    h.vendor = x.manufacturer.clone();
                }
                if h.model.is_none() {
                    h.model = x.model_name.clone();
                }
                if let Some(dt) = &x.device_type {
                    if dt.contains("InternetGatewayDevice") {
                        h.ssdp_type = Some(dt.clone());
                    }
                }
            });
        });
    }
    while set.join_next().await.is_some() {}
}

// ---------------------------------------------------------------- NetBIOS

/// NBSTAT query for the wildcard name `*`.
pub fn netbios_query() -> Vec<u8> {
    let mut p = vec![0x50, 0x58, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0];
    p.push(0x20);
    // "*" followed by 15 NULs, first-level encoded (each byte -> two letters)
    let mut raw = [0u8; 16];
    raw[0] = b'*';
    for b in raw {
        p.push(b'A' + (b >> 4));
        p.push(b'A' + (b & 0x0f));
    }
    p.push(0);
    p.extend_from_slice(&[0x00, 0x21, 0x00, 0x01]);
    p
}

/// First unique workstation name (suffix 0x00, group bit clear) of an NBSTAT reply.
pub fn parse_netbios(buf: &[u8]) -> Option<String> {
    // header 12 + name (1 + 32 + 1) + type/class 4 + ttl 4 + rdlength 2 = 56, then NUM_NAMES
    let n = *buf.get(56)? as usize;
    let mut pos = 57;
    for _ in 0..n {
        let e = buf.get(pos..pos + 18)?;
        pos += 18;
        let flags = u16::from_be_bytes([e[16], e[17]]);
        if e[15] == 0x00 && flags & 0x8000 == 0 {
            let name = String::from_utf8_lossy(&e[..15]).trim().to_string();
            if !name.is_empty() {
                return Some(name);
            }
        }
    }
    None
}

pub async fn netbios(sh: Arc<Shared>) {
    let sock = match bind_udp(sh.self_ip, 1) {
        Ok(s) => s,
        Err(e) => {
            sh.error(format!("netbios socket: {e}"));
            return;
        }
    };
    let q = netbios_query();
    let mut logged = false;
    for (i, ip) in sh.targets().into_iter().enumerate() {
        if sh.is_cancelled() {
            return;
        }
        match sock.send_to(&q, SocketAddr::from((ip, 137))).await {
            Ok(_) => sh.diag(|d| d.udp_sent += 1),
            Err(e) => {
                sh.diag(|d| d.udp_send_errors += 1);
                if !logged {
                    logged = true;
                    sh.error(format!("netbios send: {e}"));
                }
            }
        }
        if i % 32 == 31 {
            tokio::task::yield_now().await;
        }
    }
    let deadline = Instant::now() + Duration::from_millis(2500);
    let mut buf = [0u8; 1500];
    loop {
        match tokio::time::timeout_at(deadline, sock.recv_from(&mut buf)).await {
            Ok(Ok((n, SocketAddr::V4(from)))) => {
                if let Some(name) = parse_netbios(&buf[..n]) {
                    sh.diag(|d| d.replies_netbios += 1);
                    sh.with_host(*from.ip(), |h| {
                        h.add_seen("netbios");
                        if h.netbios_name.is_none() {
                            h.netbios_name = Some(name.clone());
                        }
                    });
                }
            }
            Ok(Ok(_)) => {}
            Ok(Err(_)) | Err(_) => break,
        }
        if sh.is_cancelled() {
            return;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn service_names() {
        assert_eq!(
            service_of("_googlecast._tcp.local").as_deref(),
            Some("_googlecast._tcp")
        );
        assert_eq!(
            service_of("Living Room._googlecast._tcp.local").as_deref(),
            Some("_googlecast._tcp")
        );
        assert_eq!(service_of("host.local"), None);
        assert_eq!(service_of("_ipp.local"), None);
    }

    #[test]
    fn mdns_query_has_all_names() {
        let names = mdns_query_names();
        assert!(names.contains(&"_services._dns-sd._udp.local".to_string()));
        assert!(names.contains(&"_matterc._udp.local".to_string()));
        assert!(names.contains(&"_matter._tcp.local".to_string()));
        let pkt = build_mdns_query(&names[..3], true);
        assert_eq!(&pkt[4..6], &[0, 3]);
        let (n, next) = dns::read_name(&pkt, 12).unwrap();
        assert_eq!(n, "_services._dns-sd._udp.local");
        assert_eq!(&pkt[next..next + 4], &[0, 12, 0x80, 0x01]);
    }

    #[test]
    fn mdns_summary() {
        let from = Ipv4Addr::new(192, 168, 1, 40);
        let recs = vec![
            Record {
                name: "_services._dns-sd._udp.local".into(),
                rtype: dns::TYPE_PTR,
                rdata: RData::Ptr("_googlecast._tcp.local".into()),
            },
            Record {
                name: "Den._googlecast._tcp.local".into(),
                rtype: dns::TYPE_TXT,
                rdata: RData::Txt(vec!["md=Chromecast".into(), "fn=Den TV".into()]),
            },
            Record {
                name: "Den-TV.local".into(),
                rtype: dns::TYPE_A,
                rdata: RData::A(from),
            },
        ];
        let m = summarize_mdns(&recs, from);
        let i = &m[&from];
        assert_eq!(i.name.as_deref(), Some("Den-TV.local"));
        assert_eq!(i.services, vec!["_googlecast._tcp".to_string()]);
        assert_eq!(i.model.as_deref(), Some("Chromecast"));
        assert_eq!(i.friendly.as_deref(), Some("Den TV"));
    }

    #[test]
    fn ssdp_parse_and_device_xml() {
        let r = parse_ssdp(
            "HTTP/1.1 200 OK\r\nCACHE-CONTROL: max-age=1800\r\nLocation: http://192.168.1.1:49152/d.xml\r\nServer: Linux/4.9 UPnP/1.0 MiniUPnPd/2.1\r\nST: urn:schemas-upnp-org:device:InternetGatewayDevice:1\r\nUSN: uuid:abc-123::urn:schemas-upnp-org:device:InternetGatewayDevice:1\r\n\r\n",
        )
        .unwrap();
        assert_eq!(
            r.location.as_deref(),
            Some("http://192.168.1.1:49152/d.xml")
        );
        assert_eq!(r.uuid.as_deref(), Some("uuid:abc-123"));
        assert!(r.st.unwrap().contains("InternetGatewayDevice"));
        assert!(parse_ssdp("M-SEARCH * HTTP/1.1\r\nST: x\r\n").is_none());

        let x = parse_device_xml(
            "<root><device><deviceType>urn:x:InternetGatewayDevice:1</deviceType><friendlyName>Home &amp; Away</friendlyName><manufacturer>ACME</manufacturer><modelName>R1</modelName></device></root>",
        );
        assert_eq!(x.friendly_name.as_deref(), Some("Home & Away"));
        assert_eq!(x.manufacturer.as_deref(), Some("ACME"));
        assert_eq!(x.model_name.as_deref(), Some("R1"));
    }

    #[test]
    fn msearch_payload_matches_contract() {
        let m = msearch("ssdp:all");
        assert!(m.starts_with("M-SEARCH * HTTP/1.1\r\nHOST: 239.255.255.250:1900\r\nMAN: \"ssdp:discover\"\r\nMX: 1\r\nST: ssdp:all\r\nUSER-AGENT: Android/15 UPnP/1.1 PxLScan/1.0\r\n\r\n"));
    }

    #[test]
    fn netbios_query_and_reply() {
        let q = netbios_query();
        assert_eq!(q.len(), 50);
        assert_eq!(&q[13..15], b"CK");
        assert_eq!(&q[15..45], &[b'A'; 30][..]);
        // build a reply with two names: a group name then the workstation name
        let mut r = vec![0u8; 56];
        r.push(2);
        let mut e1 = *b"WORKGROUP      \x00";
        e1[15] = 0x00;
        r.extend_from_slice(&e1);
        r.extend_from_slice(&0x8400u16.to_be_bytes());
        r.extend_from_slice(b"LIVINGROOM-PC  ");
        r.push(0x00);
        r.extend_from_slice(&0x0400u16.to_be_bytes());
        assert_eq!(parse_netbios(&r).as_deref(), Some("LIVINGROOM-PC"));
        assert_eq!(parse_netbios(&[0u8; 10]), None);
    }
}
