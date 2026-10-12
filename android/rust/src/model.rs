//! Serde structs for the Kotlin <-> Rust JSON contract (docs/CORE-CONTRACT.md).

use serde::{Deserialize, Serialize};

fn d_timeout() -> u64 {
    700
}
fn d_prefix() -> u8 {
    24
}
fn d_leave_after() -> u32 {
    2
}
fn d_true() -> bool {
    true
}

/// `startScan` input.
#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct ScanConfig {
    pub self_ip: String,
    #[serde(default = "d_prefix")]
    pub prefix_len: u8,
    #[serde(default)]
    pub gateway: Option<String>,
    #[serde(default)]
    pub dns_servers: Vec<String>,
    #[serde(default = "d_timeout")]
    pub timeout_ms: u64,
    #[serde(default)]
    pub self_name: Option<String>,
}

/// One discovered host (22 fields). Absent values serialize as `null`.
#[derive(Debug, Clone, Default, Serialize, Deserialize, PartialEq)]
pub struct Host {
    pub ip: String,
    pub name: String,
    pub kind: String,
    pub seen_by: Vec<String>,
    pub rtt_ms: Option<f32>,
    pub ttl: Option<u32>,
    pub open_ports: Vec<u16>,
    pub mdns_name: Option<String>,
    pub netbios_name: Option<String>,
    pub dns_name: Option<String>,
    pub ssdp_uuid: Option<String>,
    pub friendly_name: Option<String>,
    pub mac: Option<String>,
    pub vendor: Option<String>,
    pub model: Option<String>,
    pub http_server: Option<String>,
    pub http_title: Option<String>,
    pub ssdp_server: Option<String>,
    pub ssdp_type: Option<String>,
    pub is_self: bool,
    pub is_gateway: bool,
    pub services: Vec<String>,
}

impl Host {
    pub fn new(ip: &str) -> Host {
        Host {
            ip: ip.to_string(),
            kind: "unknown".into(),
            ..Default::default()
        }
    }

    pub fn add_seen(&mut self, tag: &str) {
        if !self.seen_by.iter().any(|t| t == tag) {
            self.seen_by.push(tag.to_string());
        }
    }

    pub fn add_service(&mut self, svc: &str) {
        if !self.services.iter().any(|t| t == svc) {
            self.services.push(svc.to_string());
        }
    }
}

/// Probe counters. `lanLooksBlocked` on the Kotlin side reads these.
#[derive(Debug, Clone, Default, Serialize, Deserialize, PartialEq)]
pub struct Diag {
    pub icmp_sent: u32,
    pub icmp_replies: u32,
    pub icmp_send_errors: u32,
    pub tcp_attempts: u32,
    pub tcp_open: u32,
    pub tcp_refused: u32,
    pub tcp_timeout: u32,
    pub tcp_denied: u32,
    pub tcp_unreachable: u32,
    pub udp_sent: u32,
    pub udp_send_errors: u32,
    pub replies_mdns: u32,
    pub replies_netbios: u32,
    pub replies_ssdp: u32,
    pub replies_dns: u32,
    pub errors: Vec<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct Snapshot {
    pub progress: f32,
    pub phase: String,
    pub done: bool,
    pub elapsed_ms: u64,
    pub total_addresses: u32,
    pub hosts: Vec<Host>,
    pub diag: Diag,
}

/// Exact JSON for an unknown / cancelled handle.
pub const GONE_JSON: &str =
    r#"{"done":true,"hosts":[],"progress":1.0,"phase":"gone","elapsed_ms":0,"total_addresses":0}"#;

/// Persisted device record (12 fields; `vendor` / `watched` are optional extras).
#[derive(Debug, Clone, Default, Serialize, Deserialize, PartialEq)]
pub struct KnownDevice {
    #[serde(default)]
    pub id: String,
    #[serde(default)]
    pub name: String,
    #[serde(default)]
    pub kind: String,
    #[serde(default)]
    pub mac: Option<String>,
    #[serde(default)]
    pub ssdp_uuid: Option<String>,
    #[serde(default)]
    pub mdns_name: Option<String>,
    #[serde(default)]
    pub netbios_name: Option<String>,
    #[serde(default)]
    pub dns_name: Option<String>,
    #[serde(default)]
    pub last_ip: String,
    #[serde(default)]
    pub known: bool,
    #[serde(default)]
    pub was_online: bool,
    #[serde(default)]
    pub missed: u32,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub vendor: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub watched: Option<bool>,
}

/// Kotlin `deviceToJson` shape. Everything optional: Kotlin may omit nulls.
#[derive(Debug, Clone, Default, Serialize, Deserialize)]
pub struct ScanHost {
    #[serde(default)]
    pub ip: String,
    #[serde(default)]
    pub name: String,
    #[serde(default)]
    pub kind: String,
    #[serde(default)]
    pub seen_by: Vec<String>,
    #[serde(default)]
    pub mac: Option<String>,
    #[serde(default)]
    pub mdns_name: Option<String>,
    #[serde(default)]
    pub netbios_name: Option<String>,
    #[serde(default)]
    pub dns_name: Option<String>,
    #[serde(default)]
    pub ssdp_uuid: Option<String>,
    #[serde(default)]
    pub is_self: bool,
    #[serde(default)]
    pub is_gateway: bool,
    #[serde(default)]
    pub vendor: Option<String>,
    #[serde(default)]
    pub open_ports: Vec<u16>,
    #[serde(default)]
    pub services: Vec<String>,
}

#[derive(Debug, Clone, Serialize, Deserialize)]
pub struct MonitorInput {
    #[serde(default)]
    pub known: Vec<KnownDevice>,
    #[serde(default)]
    pub scan: Vec<ScanHost>,
    #[serde(default = "d_leave_after")]
    pub leave_after: u32,
    #[serde(default = "d_true")]
    pub alert_all_new: bool,
}

#[derive(Debug, Clone, Default, Serialize, Deserialize, PartialEq)]
pub struct Event {
    #[serde(rename = "type")]
    pub kind: String,
    pub id: String,
    pub name: String,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub ip: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub unknown: Option<bool>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub from: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub to: Option<String>,
    #[serde(default, skip_serializing_if = "Option::is_none")]
    pub last_ip: Option<String>,
}

#[derive(Debug, Clone, Default, Serialize, Deserialize)]
pub struct MonitorOutput {
    pub events: Vec<Event>,
    pub devices: Vec<KnownDevice>,
    pub online_ids: Vec<String>,
}

#[cfg(test)]
mod tests {
    use super::*;
    use serde_json::Value;

    fn keys(v: &Value) -> Vec<String> {
        let mut k: Vec<String> = v.as_object().unwrap().keys().cloned().collect();
        k.sort();
        k
    }

    fn sorted(list: &[&str]) -> Vec<String> {
        let mut v: Vec<String> = list.iter().map(|s| s.to_string()).collect();
        v.sort();
        v
    }

    #[test]
    fn host_has_the_22_contract_keys() {
        let v = serde_json::to_value(Host::new("192.168.1.40")).unwrap();
        let want = sorted(&[
            "ip",
            "name",
            "kind",
            "seen_by",
            "rtt_ms",
            "ttl",
            "open_ports",
            "mdns_name",
            "netbios_name",
            "dns_name",
            "ssdp_uuid",
            "friendly_name",
            "mac",
            "vendor",
            "model",
            "http_server",
            "http_title",
            "ssdp_server",
            "ssdp_type",
            "is_self",
            "is_gateway",
            "services",
        ]);
        assert_eq!(want.len(), 22);
        assert_eq!(keys(&v), want);
        assert!(v["mac"].is_null());
        assert!(v["seen_by"].is_array());
    }

    #[test]
    fn snapshot_and_diag_keys_match_contract() {
        let snap = Snapshot {
            progress: 0.42,
            phase: "scanning".into(),
            done: false,
            elapsed_ms: 3120,
            total_addresses: 254,
            hosts: vec![],
            diag: Diag::default(),
        };
        let v = serde_json::to_value(&snap).unwrap();
        assert_eq!(
            keys(&v),
            sorted(&[
                "progress",
                "phase",
                "done",
                "elapsed_ms",
                "total_addresses",
                "hosts",
                "diag"
            ])
        );
        assert_eq!(
            keys(&v["diag"]),
            sorted(&[
                "icmp_sent",
                "icmp_replies",
                "icmp_send_errors",
                "tcp_attempts",
                "tcp_open",
                "tcp_refused",
                "tcp_timeout",
                "tcp_denied",
                "tcp_unreachable",
                "udp_sent",
                "udp_send_errors",
                "replies_mdns",
                "replies_netbios",
                "replies_ssdp",
                "replies_dns",
                "errors",
            ])
        );
        let back: Snapshot = serde_json::from_value(v).unwrap();
        assert_eq!(back.total_addresses, 254);
    }

    #[test]
    fn host_round_trips() {
        let mut h = Host::new("10.0.0.5");
        h.name = "X".into();
        h.open_ports = vec![80, 443];
        h.rtt_ms = Some(3.2);
        let s = serde_json::to_string(&h).unwrap();
        let back: Host = serde_json::from_str(&s).unwrap();
        assert_eq!(back, h);
    }

    #[test]
    fn known_device_has_the_12_contract_keys() {
        let v = serde_json::to_value(KnownDevice::default()).unwrap();
        assert_eq!(
            keys(&v),
            sorted(&[
                "id",
                "name",
                "kind",
                "mac",
                "ssdp_uuid",
                "mdns_name",
                "netbios_name",
                "dns_name",
                "last_ip",
                "known",
                "was_online",
                "missed",
            ])
        );
    }

    #[test]
    fn config_accepts_nulls_and_defaults() {
        let c: ScanConfig = serde_json::from_str(
            r#"{"self_ip":"192.168.1.23","prefix_len":24,"gateway":null,"dns_servers":["192.168.1.1"],"timeout_ms":700,"self_name":null}"#,
        )
        .unwrap();
        assert!(c.gateway.is_none() && c.self_name.is_none());
        assert_eq!(c.dns_servers.len(), 1);
    }

    #[test]
    fn gone_json_is_exact_and_valid() {
        assert_eq!(
            GONE_JSON,
            r#"{"done":true,"hosts":[],"progress":1.0,"phase":"gone","elapsed_ms":0,"total_addresses":0}"#
        );
        let v: Value = serde_json::from_str(GONE_JSON).unwrap();
        assert_eq!(v["phase"], "gone");
    }
}
