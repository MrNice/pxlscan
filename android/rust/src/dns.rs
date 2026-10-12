//! Hand-built DNS packets: reverse PTR lookups against the configured resolvers, plus the record
//! parser that mDNS discovery shares.

use std::net::{IpAddr, Ipv4Addr, SocketAddr};
use std::sync::atomic::{AtomicU16, Ordering};
use std::time::Duration;

use tokio::net::UdpSocket;

/// Decoded resource record data (only the types discovery cares about).
#[derive(Debug, Clone, PartialEq)]
pub enum RData {
    A(Ipv4Addr),
    Ptr(String),
    Srv { target: String, port: u16 },
    Txt(Vec<String>),
    Other,
}

#[derive(Debug, Clone, PartialEq)]
pub struct Record {
    pub name: String,
    pub rtype: u16,
    pub rdata: RData,
}

pub const TYPE_A: u16 = 1;
pub const TYPE_PTR: u16 = 12;
pub const TYPE_TXT: u16 = 16;
pub const TYPE_SRV: u16 = 33;

fn u16_at(b: &[u8], p: usize) -> Option<u16> {
    Some(u16::from_be_bytes([*b.get(p)?, *b.get(p + 1)?]))
}

/// Append a dotted name as DNS labels.
pub fn push_name(out: &mut Vec<u8>, name: &str) {
    for label in name.split('.').filter(|l| !l.is_empty()) {
        let bytes = label.as_bytes();
        let len = bytes.len().min(63);
        out.push(len as u8);
        out.extend_from_slice(&bytes[..len]);
    }
    out.push(0);
}

/// `d.c.b.a.in-addr.arpa` for an IPv4 address.
pub fn reverse_name(ip: Ipv4Addr) -> String {
    let o = ip.octets();
    format!("{}.{}.{}.{}.in-addr.arpa", o[3], o[2], o[1], o[0])
}

/// Standard recursive PTR query for `ip`.
pub fn build_ptr_query(id: u16, ip: Ipv4Addr) -> Vec<u8> {
    let mut p = Vec::with_capacity(48);
    p.extend_from_slice(&id.to_be_bytes());
    p.extend_from_slice(&0x0100u16.to_be_bytes()); // RD
    p.extend_from_slice(&1u16.to_be_bytes()); // QDCOUNT
    p.extend_from_slice(&[0, 0, 0, 0, 0, 0]);
    push_name(&mut p, &reverse_name(ip));
    p.extend_from_slice(&TYPE_PTR.to_be_bytes());
    p.extend_from_slice(&1u16.to_be_bytes()); // IN
    p
}

/// Read a (possibly compressed) name. Returns the name and the offset just after it in `buf`.
pub fn read_name(buf: &[u8], start: usize) -> Option<(String, usize)> {
    let mut pos = start;
    let mut out = String::new();
    let mut end: Option<usize> = None;
    let mut jumps = 0;
    loop {
        let len = *buf.get(pos)? as usize;
        if len == 0 {
            pos += 1;
            break;
        }
        if len & 0xC0 == 0xC0 {
            let b2 = *buf.get(pos + 1)? as usize;
            if end.is_none() {
                end = Some(pos + 2);
            }
            pos = ((len & 0x3F) << 8) | b2;
            jumps += 1;
            if jumps > 16 {
                return None;
            }
            continue;
        }
        if len & 0xC0 != 0 {
            return None;
        }
        let label = buf.get(pos + 1..pos + 1 + len)?;
        if !out.is_empty() {
            out.push('.');
        }
        out.push_str(&String::from_utf8_lossy(label));
        pos += 1 + len;
    }
    Some((out, end.unwrap_or(pos)))
}

/// Parse a whole DNS message into its records (answers + authority + additional). Stops quietly at
/// the first malformed record and returns what it has. `None` only if the header is unusable.
/// The second value is `(id, flags)`.
pub fn parse_message(buf: &[u8]) -> Option<((u16, u16), Vec<Record>)> {
    if buf.len() < 12 {
        return None;
    }
    let id = u16_at(buf, 0)?;
    let flags = u16_at(buf, 2)?;
    let qd = u16_at(buf, 4)? as usize;
    let rr = u16_at(buf, 6)? as usize + u16_at(buf, 8)? as usize + u16_at(buf, 10)? as usize;
    let mut pos = 12;
    for _ in 0..qd {
        let (_, next) = match read_name(buf, pos) {
            Some(v) => v,
            None => return Some(((id, flags), vec![])),
        };
        pos = next + 4;
    }
    let mut recs = Vec::new();
    for _ in 0..rr {
        let Some((name, next)) = read_name(buf, pos) else {
            break;
        };
        let (Some(rtype), Some(rdlen)) = (u16_at(buf, next), u16_at(buf, next + 8)) else {
            break;
        };
        let rstart = next + 10;
        let rend = rstart + rdlen as usize;
        if rend > buf.len() {
            break;
        }
        let rdata = match rtype {
            TYPE_A if rdlen == 4 => RData::A(Ipv4Addr::new(
                buf[rstart],
                buf[rstart + 1],
                buf[rstart + 2],
                buf[rstart + 3],
            )),
            TYPE_PTR => match read_name(buf, rstart) {
                Some((n, _)) => RData::Ptr(n),
                None => RData::Other,
            },
            TYPE_SRV if rdlen >= 7 => {
                let port = u16_at(buf, rstart + 4).unwrap_or(0);
                match read_name(buf, rstart + 6) {
                    Some((t, _)) => RData::Srv { target: t, port },
                    None => RData::Other,
                }
            }
            TYPE_TXT => {
                let mut items = Vec::new();
                let mut p = rstart;
                while p < rend {
                    let l = buf[p] as usize;
                    if p + 1 + l > rend {
                        break;
                    }
                    items.push(String::from_utf8_lossy(&buf[p + 1..p + 1 + l]).into_owned());
                    p += 1 + l;
                }
                RData::Txt(items)
            }
            _ => RData::Other,
        };
        recs.push(Record { name, rtype, rdata });
        pos = rend;
    }
    Some(((id, flags), recs))
}

/// The first PTR target of a reply to `build_ptr_query(id, ..)`, trailing dot stripped.
pub fn parse_ptr_response(buf: &[u8], id: u16) -> Option<String> {
    let ((rid, flags), recs) = parse_message(buf)?;
    if rid != id || flags & 0x8000 == 0 || flags & 0x000F != 0 {
        return None;
    }
    recs.into_iter().find_map(|r| match r.rdata {
        RData::Ptr(n) if r.rtype == TYPE_PTR => {
            let n = n.trim_end_matches('.').to_string();
            if n.is_empty() {
                None
            } else {
                Some(n)
            }
        }
        _ => None,
    })
}

static NEXT_ID: AtomicU16 = AtomicU16::new(0x4a31);

/// Outcome of asking one resolver.
pub enum PtrResult {
    Name(String),
    NoName,
    Failed(String),
}

/// Ask `server` (port 53) for the PTR of `ip`. Counts `udp_sent` / `replies_dns` into `diag`.
pub async fn ptr_lookup(
    shared: &crate::scan::Shared,
    server: IpAddr,
    ip: Ipv4Addr,
    timeout: Duration,
) -> PtrResult {
    let id = NEXT_ID.fetch_add(0x9e37, Ordering::Relaxed) ^ u16::from(ip.octets()[3]);
    let bind: SocketAddr = if server.is_ipv4() {
        "0.0.0.0:0".parse().unwrap()
    } else {
        "[::]:0".parse().unwrap()
    };
    let sock = match UdpSocket::bind(bind).await {
        Ok(s) => s,
        Err(e) => {
            shared.error(format!("dns socket: {e}"));
            return PtrResult::Failed(e.to_string());
        }
    };
    let pkt = build_ptr_query(id, ip);
    if let Err(e) = sock.send_to(&pkt, SocketAddr::new(server, 53)).await {
        shared.diag(|d| d.udp_send_errors += 1);
        shared.error(format!("dns send: {e}"));
        return PtrResult::Failed(e.to_string());
    }
    shared.diag(|d| d.udp_sent += 1);
    let mut buf = [0u8; 1500];
    let deadline = tokio::time::Instant::now() + timeout;
    loop {
        match tokio::time::timeout_at(deadline, sock.recv_from(&mut buf)).await {
            Err(_) => return PtrResult::NoName,
            Ok(Err(e)) => return PtrResult::Failed(e.to_string()),
            Ok(Ok((n, from))) => {
                if from.ip() != server {
                    continue;
                }
                shared.diag(|d| d.replies_dns += 1);
                return match parse_ptr_response(&buf[..n], id) {
                    Some(name) => PtrResult::Name(name),
                    None => PtrResult::NoName,
                };
            }
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn ptr_query_layout() {
        let q = build_ptr_query(0x1234, Ipv4Addr::new(192, 168, 1, 40));
        assert_eq!(&q[0..2], &[0x12, 0x34]);
        assert_eq!(&q[2..4], &[0x01, 0x00]);
        assert_eq!(&q[4..6], &[0, 1]);
        // 40.1.168.192.in-addr.arpa
        let (name, next) = read_name(&q, 12).unwrap();
        assert_eq!(name, "40.1.168.192.in-addr.arpa");
        assert_eq!(&q[next..next + 4], &[0, 12, 0, 1]);
        assert_eq!(q.len(), next + 4);
    }

    fn reply_for(q: &[u8], target: &str, rcode: u8) -> Vec<u8> {
        let mut r = q.to_vec();
        r[2] = 0x81; // QR + RD
        r[3] = 0x80 | rcode; // RA
        r[6] = 0;
        r[7] = 1; // ANCOUNT
        r.extend_from_slice(&[0xC0, 0x0C]); // name pointer to question
        r.extend_from_slice(&TYPE_PTR.to_be_bytes());
        r.extend_from_slice(&1u16.to_be_bytes());
        r.extend_from_slice(&300u32.to_be_bytes());
        let mut rd = Vec::new();
        push_name(&mut rd, target);
        r.extend_from_slice(&(rd.len() as u16).to_be_bytes());
        r.extend_from_slice(&rd);
        r
    }

    #[test]
    fn ptr_response_parses() {
        let q = build_ptr_query(7, Ipv4Addr::new(10, 0, 0, 9));
        let r = reply_for(&q, "tv.lan", 0);
        assert_eq!(parse_ptr_response(&r, 7).as_deref(), Some("tv.lan"));
        // wrong id and error rcode are rejected
        assert_eq!(parse_ptr_response(&r, 8), None);
        let nx = reply_for(&q, "tv.lan", 3);
        assert_eq!(parse_ptr_response(&nx, 7), None);
        // garbage never panics
        assert_eq!(parse_ptr_response(&[1, 2, 3], 7), None);
        assert_eq!(parse_ptr_response(&r[..20], 7), None);
    }

    #[test]
    fn compression_loop_is_rejected() {
        let mut b = vec![0u8; 12];
        b.extend_from_slice(&[0xC0, 12]); // points at itself
        assert!(read_name(&b, 12).is_none());
    }

    #[test]
    fn a_srv_txt_records_parse() {
        // header: id 0, response, 0 questions, 3 answers
        let mut m = vec![0, 0, 0x84, 0, 0, 0, 0, 3, 0, 0, 0, 0];
        // A  host.local -> 192.168.1.5
        push_name(&mut m, "host.local");
        m.extend_from_slice(&[0, 1, 0x80, 1, 0, 0, 0, 120, 0, 4, 192, 168, 1, 5]);
        // TXT x.local "md=Chromecast" "fn=Den"
        push_name(&mut m, "x.local");
        let txt: Vec<u8> = [&[13u8][..], b"md=Chromecast", &[6u8][..], b"fn=Den"].concat();
        m.extend_from_slice(&[0, 16, 0x80, 1, 0, 0, 0, 120]);
        m.extend_from_slice(&(txt.len() as u16).to_be_bytes());
        m.extend_from_slice(&txt);
        // SRV x.local -> host.local:8009
        push_name(&mut m, "x.local");
        let mut srv = vec![0, 0, 0, 0, 0x1f, 0x49];
        push_name(&mut srv, "host.local");
        m.extend_from_slice(&[0, 33, 0x80, 1, 0, 0, 0, 120]);
        m.extend_from_slice(&(srv.len() as u16).to_be_bytes());
        m.extend_from_slice(&srv);

        let (_, recs) = parse_message(&m).unwrap();
        assert_eq!(recs.len(), 3);
        assert_eq!(recs[0].rdata, RData::A(Ipv4Addr::new(192, 168, 1, 5)));
        assert_eq!(
            recs[1].rdata,
            RData::Txt(vec!["md=Chromecast".into(), "fn=Den".into()])
        );
        assert_eq!(
            recs[2].rdata,
            RData::Srv {
                target: "host.local".into(),
                port: 8009
            }
        );
    }
}
