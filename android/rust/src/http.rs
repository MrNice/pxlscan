//! Tiny HTTP/1.0 client: `GET /` for the `Server` header and `<title>`, and a body fetch for
//! SSDP LOCATION documents.

use std::net::Ipv4Addr;
use std::time::Duration;

use tokio::io::{AsyncReadExt, AsyncWriteExt};
use tokio::net::TcpStream;

pub const USER_AGENT: &str = "PxLScan/1.0";

/// Decode the entities the contract names (`&amp; &lt; &gt; &quot; &#39; &apos;`) plus numeric
/// `&#NN;` / `&#xHH;`. One pass, so `&amp;lt;` becomes `&lt;`.
pub fn decode_entities(s: &str) -> String {
    let mut out = String::with_capacity(s.len());
    let mut rest = s;
    while let Some(i) = rest.find('&') {
        out.push_str(&rest[..i]);
        let tail = &rest[i..];
        // an entity is at most ~10 chars
        let semi = tail
            .char_indices()
            .take(12)
            .find(|(_, c)| *c == ';')
            .map(|(j, _)| j);
        let decoded = semi.and_then(|j| {
            let body = &tail[1..j];
            let ch = match body {
                "amp" => Some('&'),
                "lt" => Some('<'),
                "gt" => Some('>'),
                "quot" => Some('"'),
                "apos" => Some('\''),
                _ => {
                    if let Some(h) = body.strip_prefix("#x").or_else(|| body.strip_prefix("#X")) {
                        u32::from_str_radix(h, 16).ok().and_then(char::from_u32)
                    } else if let Some(d) = body.strip_prefix('#') {
                        d.parse::<u32>().ok().and_then(char::from_u32)
                    } else {
                        None
                    }
                }
            };
            ch.map(|c| (c, j))
        });
        match decoded {
            Some((c, j)) => {
                out.push(c);
                rest = &tail[j + 1..];
            }
            None => {
                out.push('&');
                rest = &tail[1..];
            }
        }
    }
    out.push_str(rest);
    out
}

/// Case-insensitive search that keeps byte offsets valid for the original string.
fn find_ci(hay: &str, needle: &str, from: usize) -> Option<usize> {
    let h = hay.as_bytes();
    let n = needle.as_bytes();
    if n.is_empty() || h.len() < n.len() {
        return None;
    }
    (from..=h.len() - n.len()).find(|&i| h[i..i + n.len()].eq_ignore_ascii_case(n))
}

/// Text of the first `<title>` element: whitespace collapsed, entities decoded.
pub fn extract_title(html: &str) -> Option<String> {
    let open = find_ci(html, "<title", 0)?;
    let gt = html[open..].find('>')? + open + 1;
    let close = find_ci(html, "</title", gt).unwrap_or_else(|| html.len().min(gt + 300));
    let raw = html.get(gt..close)?;
    let decoded = decode_entities(raw);
    let t = decoded.split_whitespace().collect::<Vec<_>>().join(" ");
    if t.is_empty() {
        None
    } else {
        Some(t)
    }
}

/// Split a raw response into `(headers, body)`.
pub fn split_response(raw: &[u8]) -> (String, Vec<u8>) {
    let pos = raw.windows(4).position(|w| w == b"\r\n\r\n");
    match pos {
        Some(p) => (
            String::from_utf8_lossy(&raw[..p]).into_owned(),
            raw[p + 4..].to_vec(),
        ),
        None => (String::from_utf8_lossy(raw).into_owned(), vec![]),
    }
}

/// `(server header, title)` from a raw HTTP response.
pub fn parse_response(raw: &[u8]) -> (Option<String>, Option<String>) {
    let (head, body) = split_response(raw);
    let server = head.lines().skip(1).find_map(|l| {
        let (k, v) = l.split_once(':')?;
        if k.trim().eq_ignore_ascii_case("server") {
            let v = v.trim();
            (!v.is_empty()).then(|| v.to_string())
        } else {
            None
        }
    });
    let title = extract_title(&String::from_utf8_lossy(&body));
    (server, title)
}

/// Raw bytes of `GET <path> HTTP/1.0` (at most `max` bytes of response).
pub async fn get_raw(
    ip: Ipv4Addr,
    port: u16,
    host_header: &str,
    path: &str,
    timeout: Duration,
    max: usize,
) -> Option<Vec<u8>> {
    let work = async {
        let mut s = TcpStream::connect((ip, port)).await.ok()?;
        let req = format!(
            "GET {path} HTTP/1.0\r\nHost: {host_header}\r\nUser-Agent: {USER_AGENT}\r\nAccept: */*\r\nConnection: close\r\n\r\n"
        );
        s.write_all(req.as_bytes()).await.ok()?;
        let mut out = Vec::new();
        let mut buf = [0u8; 4096];
        while out.len() < max {
            match s.read(&mut buf).await {
                Ok(0) | Err(_) => break,
                Ok(n) => out.extend_from_slice(&buf[..n]),
            }
        }
        Some(out)
    };
    tokio::time::timeout(timeout, work).await.ok().flatten()
}

/// `GET /` on a host: `(Server, title)`.
pub async fn probe(
    ip: Ipv4Addr,
    port: u16,
    timeout: Duration,
) -> Option<(Option<String>, Option<String>)> {
    let raw = get_raw(ip, port, &ip.to_string(), "/", timeout, 32 * 1024).await?;
    if raw.is_empty() {
        return None;
    }
    Some(parse_response(&raw))
}

/// Split `http://ip:port/path` into its parts (IPv4 literal hosts only).
pub fn parse_url(url: &str) -> Option<(Ipv4Addr, u16, String)> {
    let rest = url.strip_prefix("http://")?;
    let (auth, path) = match rest.find('/') {
        Some(i) => (&rest[..i], &rest[i..]),
        None => (rest, "/"),
    };
    let (host, port) = match auth.rsplit_once(':') {
        Some((h, p)) => (h, p.parse::<u16>().ok()?),
        None => (auth, 80),
    };
    Some((host.parse().ok()?, port, path.to_string()))
}

/// Body of a document at an `http://` URL.
pub async fn fetch_body(url: &str, timeout: Duration) -> Option<String> {
    let (ip, port, path) = parse_url(url)?;
    let raw = get_raw(
        ip,
        port,
        &format!("{ip}:{port}"),
        &path,
        timeout,
        128 * 1024,
    )
    .await?;
    let (_, body) = split_response(&raw);
    Some(String::from_utf8_lossy(&body).into_owned())
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn title_extraction_and_entities() {
        assert_eq!(
            extract_title("<html><head><TITLE>  Tom &amp; Jerry&#39;s &quot;NAS&quot; &lt;1&gt; </TITLE></head>").as_deref(),
            Some("Tom & Jerry's \"NAS\" <1>")
        );
        assert_eq!(
            extract_title("<title lang=en>Router\n  Login</title>").as_deref(),
            Some("Router Login")
        );
        assert_eq!(
            extract_title("<title>It&apos;s &#x41;&#66;</title>").as_deref(),
            Some("It's AB")
        );
        assert_eq!(extract_title("<title>  </title>"), None);
        assert_eq!(extract_title("no title here"), None);
        // no double decoding
        assert_eq!(decode_entities("&amp;lt;"), "&lt;");
        // stray ampersands survive
        assert_eq!(decode_entities("a & b &unknown; c"), "a & b &unknown; c");
    }

    #[test]
    fn response_headers_and_title() {
        let raw = b"HTTP/1.0 200 OK\r\nserver: lighttpd/1.4\r\nContent-Type: text/html\r\n\r\n<html><title>Printer</title></html>";
        let (srv, title) = parse_response(raw);
        assert_eq!(srv.as_deref(), Some("lighttpd/1.4"));
        assert_eq!(title.as_deref(), Some("Printer"));
        let (s2, t2) = parse_response(b"HTTP/1.0 404 Not Found\r\n\r\n");
        assert!(s2.is_none() && t2.is_none());
    }

    #[test]
    fn url_parsing() {
        assert_eq!(
            parse_url("http://192.168.1.1:49152/desc.xml"),
            Some((
                Ipv4Addr::new(192, 168, 1, 1),
                49152,
                "/desc.xml".to_string()
            ))
        );
        assert_eq!(
            parse_url("http://10.0.0.2"),
            Some((Ipv4Addr::new(10, 0, 0, 2), 80, "/".to_string()))
        );
        assert_eq!(parse_url("https://10.0.0.2/"), None);
    }
}
