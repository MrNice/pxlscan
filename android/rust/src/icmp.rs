//! Unprivileged ICMP echo: `socket(AF_INET, SOCK_DGRAM, IPPROTO_ICMP)` works on Android without
//! root. The kernel owns the echo id and checksum; we only choose the sequence number.
//!
//! The socket code is gated to Linux/Android. Other hosts (the Windows test machine) get a stub
//! whose `open()` always fails, so everything above it still compiles and the callers take their
//! "ICMP unavailable" path.

use std::collections::HashMap;
use std::io;
use std::net::{Ipv4Addr, SocketAddr, TcpStream};
use std::time::{Duration, Instant};

use crate::scan::Shared;

/// One parsed ICMP reply.
#[derive(Debug, Clone, Copy)]
pub struct Reply {
    pub ip: Ipv4Addr,
    pub seq: u16,
    pub ttl: Option<u8>,
}

/// RFC 1071 internet checksum.
pub fn checksum(data: &[u8]) -> u16 {
    let mut sum: u32 = 0;
    let mut chunks = data.chunks_exact(2);
    for c in &mut chunks {
        sum += u16::from_be_bytes([c[0], c[1]]) as u32;
    }
    if let [last] = chunks.remainder() {
        sum += (*last as u32) << 8;
    }
    while sum >> 16 != 0 {
        sum = (sum & 0xffff) + (sum >> 16);
    }
    !(sum as u16)
}

/// 16-byte echo request (type 8): id 0 (kernel fills it), `seq`, 8 payload bytes.
pub fn echo_packet(seq: u16) -> [u8; 16] {
    let mut p = [0u8; 16];
    p[0] = 8;
    p[6..8].copy_from_slice(&seq.to_be_bytes());
    p[8..16].copy_from_slice(b"PxLScan!");
    let c = checksum(&p);
    p[2..4].copy_from_slice(&c.to_be_bytes());
    p
}

#[cfg(any(target_os = "android", target_os = "linux"))]
mod imp {
    use super::{echo_packet, Reply};
    use socket2::{Domain, Protocol, SockAddr, Socket, Type};
    use std::io;
    use std::mem;
    use std::net::{Ipv4Addr, SocketAddrV4};
    use std::os::fd::AsRawFd;
    use std::time::Duration;

    pub struct IcmpSock {
        sock: Socket,
    }

    impl IcmpSock {
        pub fn open() -> io::Result<Self> {
            let sock = Socket::new(Domain::IPV4, Type::DGRAM, Some(Protocol::ICMPV4))?;
            // Ask for the TTL of each reply as ancillary data. Failure only loses the TTL.
            let one: libc::c_int = 1;
            unsafe {
                libc::setsockopt(
                    sock.as_raw_fd(),
                    libc::IPPROTO_IP,
                    libc::IP_RECVTTL,
                    &one as *const libc::c_int as *const libc::c_void,
                    mem::size_of::<libc::c_int>() as libc::socklen_t,
                );
            }
            Ok(IcmpSock { sock })
        }

        pub fn send(&self, seq: u16, ip: Ipv4Addr) -> io::Result<()> {
            let pkt = echo_packet(seq);
            self.sock
                .send_to(&pkt, &SockAddr::from(SocketAddrV4::new(ip, 0)))
                .map(|_| ())
        }

        /// Wait up to `wait` for one echo reply. Anything else (timeouts, other ICMP types,
        /// transient errors) is `None`.
        pub fn recv(&self, wait: Duration) -> Option<Reply> {
            let _ = self
                .sock
                .set_read_timeout(Some(wait.max(Duration::from_millis(1))));
            let mut buf = [0u8; 256];
            let mut cbuf = [0u8; 64];
            // SAFETY: plain C structs, zero is a valid initial state; every pointer stored in
            // `msg` points at a local that outlives the recvmsg call.
            unsafe {
                let mut src: libc::sockaddr_in = mem::zeroed();
                let mut iov = libc::iovec {
                    iov_base: buf.as_mut_ptr() as *mut libc::c_void,
                    iov_len: buf.len() as _,
                };
                let mut msg: libc::msghdr = mem::zeroed();
                msg.msg_name = &mut src as *mut libc::sockaddr_in as *mut libc::c_void;
                msg.msg_namelen = mem::size_of::<libc::sockaddr_in>() as _;
                msg.msg_iov = &mut iov;
                msg.msg_iovlen = 1 as _;
                msg.msg_control = cbuf.as_mut_ptr() as *mut libc::c_void;
                msg.msg_controllen = cbuf.len() as _;
                let n = libc::recvmsg(self.sock.as_raw_fd(), &mut msg, 0);
                if n < 8 {
                    return None;
                }
                if buf[0] != 0 {
                    return None; // not an echo reply
                }
                let seq = u16::from_be_bytes([buf[6], buf[7]]);
                let ip = Ipv4Addr::from(u32::from_be(src.sin_addr.s_addr));
                let mut ttl = None;
                let mut c = libc::CMSG_FIRSTHDR(&msg);
                while !c.is_null() {
                    if (*c).cmsg_level == libc::IPPROTO_IP && (*c).cmsg_type == libc::IP_TTL {
                        let v = *(libc::CMSG_DATA(c) as *const libc::c_int);
                        ttl = Some(v.clamp(0, 255) as u8);
                    }
                    c = libc::CMSG_NXTHDR(&msg, c);
                }
                Some(Reply { ip, seq, ttl })
            }
        }
    }
}

#[cfg(not(any(target_os = "android", target_os = "linux")))]
mod imp {
    use super::Reply;
    use std::io;
    use std::net::Ipv4Addr;
    use std::time::Duration;

    pub struct IcmpSock;

    impl IcmpSock {
        pub fn open() -> io::Result<Self> {
            Err(io::Error::new(
                io::ErrorKind::Unsupported,
                "unprivileged ICMP needs Linux/Android",
            ))
        }
        pub fn send(&self, _seq: u16, _ip: Ipv4Addr) -> io::Result<()> {
            Err(io::ErrorKind::Unsupported.into())
        }
        pub fn recv(&self, _wait: Duration) -> Option<Reply> {
            None
        }
    }
}

pub use imp::IcmpSock;

/// Blocking ICMP sweep of every scan target, two rounds (the first ping to a neighbour often only
/// triggers ARP). Records `rtt_ms`, `ttl`, `seen_by: icmp` and the `icmp_*` counters.
pub fn sweep(shared: &Shared) {
    let sock = match IcmpSock::open() {
        Ok(s) => s,
        Err(e) => {
            shared.error(format!("icmp socket: {e}"));
            return;
        }
    };
    let addrs = shared.targets();
    let index: HashMap<Ipv4Addr, usize> = addrs.iter().enumerate().map(|(i, a)| (*a, i)).collect();
    let mut replied = vec![false; addrs.len()];
    let mut sent_at: Vec<Option<Instant>> = vec![None; addrs.len()];
    let wait = Duration::from_millis(shared.cfg.timeout_ms.clamp(300, 1200));
    let mut send_err_logged = false;

    for _round in 0..2 {
        for (i, ip) in addrs.iter().enumerate() {
            if replied[i] {
                continue;
            }
            if shared.is_cancelled() {
                return;
            }
            match sock.send((i as u16).wrapping_add(1), *ip) {
                Ok(()) => {
                    sent_at[i] = Some(Instant::now());
                    shared.diag(|d| d.icmp_sent += 1);
                }
                Err(e) => {
                    shared.diag(|d| d.icmp_send_errors += 1);
                    if !send_err_logged {
                        send_err_logged = true;
                        shared.error(format!("icmp send: {e}"));
                    }
                }
            }
            if i % 16 == 15 {
                std::thread::sleep(Duration::from_millis(1));
            }
        }
        let deadline = Instant::now() + wait;
        while let Some(left) = deadline.checked_duration_since(Instant::now()) {
            if shared.is_cancelled() {
                return;
            }
            let Some(r) = sock.recv(left.min(Duration::from_millis(100))) else {
                continue;
            };
            let Some(&i) = index.get(&r.ip) else { continue };
            if replied[i] {
                continue;
            }
            replied[i] = true;
            let rtt = sent_at[i].map(|t| t.elapsed().as_secs_f32() * 1000.0);
            shared.diag(|d| d.icmp_replies += 1);
            shared.with_host(r.ip, |h| {
                h.add_seen("icmp");
                h.rtt_ms = rtt;
                h.ttl = r.ttl.map(u32::from);
            });
        }
        if replied.iter().all(|r| *r) {
            break;
        }
    }
}

/// One echo to `ip`. `Err` means ICMP sockets are unavailable; `Ok(None)` means no reply.
pub fn echo_once(ip: Ipv4Addr, timeout: Duration) -> io::Result<Option<(f32, Option<u8>)>> {
    let sock = IcmpSock::open()?;
    let start = Instant::now();
    sock.send(1, ip)?;
    while let Some(left) = timeout.checked_sub(start.elapsed()) {
        if let Some(r) = sock.recv(left) {
            if r.ip == ip {
                return Ok(Some((start.elapsed().as_secs_f32() * 1000.0, r.ttl)));
            }
        }
    }
    Ok(None)
}

/// Fallback RTT: time a TCP connect to 80 then 443. A refusal proves the host answered.
pub fn tcp_rtt(ip: Ipv4Addr, timeout: Duration) -> Option<f32> {
    let per = (timeout / 2).max(Duration::from_millis(50));
    for port in [80u16, 443] {
        let t = Instant::now();
        match TcpStream::connect_timeout(&SocketAddr::new(ip.into(), port), per) {
            Ok(_) => return Some(t.elapsed().as_secs_f32() * 1000.0),
            Err(e) if e.kind() == io::ErrorKind::ConnectionRefused => {
                return Some(t.elapsed().as_secs_f32() * 1000.0)
            }
            Err(_) => {}
        }
    }
    None
}

/// `Native.ping`: RTT in ms or -1.0.
pub fn ping_ms(ip: &str, timeout_ms: i64) -> f32 {
    let Ok(ip) = ip.trim().parse::<Ipv4Addr>() else {
        return -1.0;
    };
    let timeout = Duration::from_millis(timeout_ms.clamp(50, 10_000) as u64);
    match echo_once(ip, timeout) {
        Ok(Some((rtt, _))) => rtt,
        Ok(None) => -1.0,
        Err(_) => tcp_rtt(ip, timeout).unwrap_or(-1.0),
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn echo_packet_checksum_verifies() {
        let p = echo_packet(0x0102);
        assert_eq!(p[0], 8);
        assert_eq!(&p[6..8], &[1, 2]);
        // a correct internet checksum makes the whole packet sum to zero
        assert_eq!(checksum(&p), 0);
    }

    #[test]
    fn ping_rejects_bad_ip() {
        assert_eq!(ping_ms("not an ip", 100), -1.0);
    }
}
