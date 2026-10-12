//! Background presence tracking: a pure function from `MonitorInput` to `MonitorOutput`.

use std::collections::{HashMap, HashSet};

use crate::classify::id_key;
use crate::model::{Event, KnownDevice, MonitorInput, MonitorOutput, ScanHost};

fn nonempty(o: &Option<String>) -> Option<&str> {
    o.as_deref().filter(|s| !s.is_empty())
}

/// Same key Kotlin computes with `Store.keyOf` for a scan host.
pub fn host_id(h: &ScanHost) -> String {
    id_key(
        nonempty(&h.mac),
        nonempty(&h.ssdp_uuid),
        nonempty(&h.mdns_name),
        nonempty(&h.netbios_name),
        nonempty(&h.dns_name),
        &h.ip,
    )
}

fn fill(dst: &mut Option<String>, src: &Option<String>) {
    if let Some(s) = nonempty(src) {
        *dst = Some(s.to_string());
    }
}

fn from_host(id: String, h: &ScanHost) -> KnownDevice {
    KnownDevice {
        id,
        name: h.name.clone(),
        kind: if h.kind.is_empty() {
            "unknown".into()
        } else {
            h.kind.clone()
        },
        mac: h.mac.clone().filter(|s| !s.is_empty()),
        ssdp_uuid: h.ssdp_uuid.clone().filter(|s| !s.is_empty()),
        mdns_name: h.mdns_name.clone().filter(|s| !s.is_empty()),
        netbios_name: h.netbios_name.clone().filter(|s| !s.is_empty()),
        dns_name: h.dns_name.clone().filter(|s| !s.is_empty()),
        last_ip: h.ip.clone(),
        known: false,
        was_online: true,
        missed: 0,
        vendor: h.vendor.clone().filter(|s| !s.is_empty()),
        watched: None,
    }
}

pub fn diff(input: MonitorInput) -> MonitorOutput {
    let MonitorInput {
        known,
        scan,
        leave_after,
        alert_all_new,
    } = input;
    let mut devices = known;
    let mut by_id: HashMap<String, usize> = HashMap::new();
    for (i, d) in devices.iter().enumerate() {
        by_id.entry(d.id.clone()).or_insert(i);
    }

    let hosts: Vec<(String, &ScanHost)> = scan
        .iter()
        .filter(|h| !h.is_self)
        .map(|h| (host_id(h), h))
        .collect();
    let scan_ids: HashSet<&str> = hosts.iter().map(|(id, _)| id.as_str()).collect();

    let mut events: Vec<Event> = Vec::new();
    let mut matched: HashSet<usize> = HashSet::new();
    let mut online_ids: Vec<String> = Vec::new();

    for (id, h) in &hosts {
        let idx = by_id
            .get(id)
            .copied()
            // A device first seen by ip alone, now with a real identity at the same ip.
            .or_else(|| {
                devices.iter().enumerate().find_map(|(i, d)| {
                    (!matched.contains(&i) && d.id.starts_with("ip:") && d.last_ip == h.ip)
                        .then_some(i)
                })
            })
            // The reverse: identity missing this round, but a known device sat at this ip.
            .or_else(|| {
                if !id.starts_with("ip:") {
                    return None;
                }
                devices.iter().enumerate().find_map(|(i, d)| {
                    (!matched.contains(&i)
                        && d.last_ip == h.ip
                        && !scan_ids.contains(d.id.as_str()))
                    .then_some(i)
                })
            });

        match idx {
            Some(i) if matched.contains(&i) => continue, // two scan rows for one device
            Some(i) => {
                matched.insert(i);
                let d = &mut devices[i];
                let was_online = d.was_online;
                let old_ip = d.last_ip.clone();
                // A richer identity replaces an ip-only id; a poorer scan never downgrades.
                if d.id.starts_with("ip:") && !id.starts_with("ip:") {
                    by_id.remove(&d.id);
                    d.id = id.clone();
                    by_id.insert(d.id.clone(), i);
                }
                if !h.name.is_empty() {
                    d.name = h.name.clone();
                }
                if !h.kind.is_empty() && (h.kind != "unknown" || d.kind.is_empty()) {
                    d.kind = h.kind.clone();
                }
                fill(&mut d.mac, &h.mac);
                fill(&mut d.ssdp_uuid, &h.ssdp_uuid);
                fill(&mut d.mdns_name, &h.mdns_name);
                fill(&mut d.netbios_name, &h.netbios_name);
                fill(&mut d.dns_name, &h.dns_name);
                fill(&mut d.vendor, &h.vendor);
                d.last_ip = h.ip.clone();
                d.was_online = true;
                d.missed = 0;
                online_ids.push(d.id.clone());

                if !was_online {
                    events.push(Event {
                        kind: "joined".into(),
                        id: d.id.clone(),
                        name: d.name.clone(),
                        ip: Some(h.ip.clone()),
                        ..Default::default()
                    });
                }
                if !old_ip.is_empty() && old_ip != h.ip {
                    events.push(Event {
                        kind: "ip_changed".into(),
                        id: d.id.clone(),
                        name: d.name.clone(),
                        ip: Some(h.ip.clone()),
                        from: Some(old_ip),
                        to: Some(h.ip.clone()),
                        ..Default::default()
                    });
                }
            }
            None => {
                let dev = from_host(id.clone(), h);
                if alert_all_new {
                    events.push(Event {
                        kind: "new".into(),
                        id: dev.id.clone(),
                        name: dev.name.clone(),
                        ip: Some(h.ip.clone()),
                        unknown: Some(dev.kind == "unknown"),
                        ..Default::default()
                    });
                }
                let i = devices.len();
                by_id.insert(dev.id.clone(), i);
                online_ids.push(dev.id.clone());
                devices.push(dev);
                matched.insert(i);
            }
        }
    }

    for (i, d) in devices.iter_mut().enumerate() {
        if matched.contains(&i) || !d.was_online {
            continue;
        }
        d.missed += 1;
        if d.missed >= leave_after {
            d.was_online = false;
            events.push(Event {
                kind: "left".into(),
                id: d.id.clone(),
                name: d.name.clone(),
                last_ip: Some(d.last_ip.clone()),
                ..Default::default()
            });
        }
    }

    MonitorOutput {
        events,
        devices,
        online_ids,
    }
}

/// JSON entry point used by the JNI layer.
pub fn diff_json(input: &str) -> String {
    match serde_json::from_str::<MonitorInput>(input) {
        Ok(i) => serde_json::to_string(&diff(i)).unwrap_or_else(|e| {
            format!("{{\"error\":{}}}", serde_json::Value::String(e.to_string()))
        }),
        Err(e) => format!(
            "{{\"error\":{}}}",
            serde_json::Value::String(format!("bad monitor input: {e}"))
        ),
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn scan(ip: &str, name: &str, kind: &str, mdns: Option<&str>) -> ScanHost {
        ScanHost {
            ip: ip.into(),
            name: name.into(),
            kind: kind.into(),
            mdns_name: mdns.map(String::from),
            ..Default::default()
        }
    }

    fn input(known: Vec<KnownDevice>, scan: Vec<ScanHost>) -> MonitorInput {
        MonitorInput {
            known,
            scan,
            leave_after: 2,
            alert_all_new: true,
        }
    }

    fn types(o: &MonitorOutput) -> Vec<&str> {
        o.events.iter().map(|e| e.kind.as_str()).collect()
    }

    #[test]
    fn new_device_is_added_and_announced() {
        let o = diff(input(
            vec![],
            vec![scan("10.0.0.5", "Den TV", "tv", Some("Den.LOCAL"))],
        ));
        assert_eq!(types(&o), vec!["new"]);
        assert_eq!(o.events[0].id, "mdns:den.local");
        assert_eq!(o.events[0].unknown, Some(false));
        assert_eq!(o.devices.len(), 1);
        assert!(o.devices[0].was_online);
        assert_eq!(o.online_ids, vec!["mdns:den.local".to_string()]);
    }

    #[test]
    fn unknown_kind_flags_unknown() {
        let o = diff(input(
            vec![],
            vec![scan("10.0.0.6", "Device 6", "unknown", None)],
        ));
        assert_eq!(o.events[0].id, "ip:10.0.0.6");
        assert_eq!(o.events[0].unknown, Some(true));
    }

    #[test]
    fn alert_all_new_false_suppresses_event_but_adds_device() {
        let mut i = input(vec![], vec![scan("10.0.0.6", "X", "unknown", None)]);
        i.alert_all_new = false;
        let o = diff(i);
        assert!(o.events.is_empty());
        assert_eq!(o.devices.len(), 1);
    }

    #[test]
    fn self_is_skipped() {
        let mut me = scan("10.0.0.2", "Me", "phone", None);
        me.is_self = true;
        let o = diff(input(vec![], vec![me]));
        assert!(o.events.is_empty() && o.devices.is_empty() && o.online_ids.is_empty());
    }

    #[test]
    fn leaves_only_after_leave_after_misses() {
        let first = diff(input(
            vec![],
            vec![scan("10.0.0.5", "TV", "tv", Some("tv.local"))],
        ));
        let mut devs = first.devices;
        let o1 = diff(input(devs.clone(), vec![]));
        assert!(o1.events.is_empty());
        assert_eq!(o1.devices[0].missed, 1);
        assert!(o1.devices[0].was_online);
        devs = o1.devices;
        let o2 = diff(input(devs, vec![]));
        assert_eq!(types(&o2), vec!["left"]);
        assert_eq!(o2.events[0].last_ip.as_deref(), Some("10.0.0.5"));
        assert!(!o2.devices[0].was_online);
        // already offline: no repeat event
        let o3 = diff(input(o2.devices, vec![]));
        assert!(o3.events.is_empty());
    }

    #[test]
    fn offline_device_seen_again_joins() {
        let mut d = diff(input(
            vec![],
            vec![scan("10.0.0.5", "TV", "tv", Some("tv.local"))],
        ))
        .devices;
        d[0].was_online = false;
        d[0].missed = 2;
        let o = diff(input(
            d,
            vec![scan("10.0.0.5", "TV", "tv", Some("tv.local"))],
        ));
        assert_eq!(types(&o), vec!["joined"]);
        assert_eq!(o.devices[0].missed, 0);
        assert!(o.devices[0].was_online);
    }

    #[test]
    fn ip_change_is_reported() {
        let d = diff(input(
            vec![],
            vec![scan("10.0.0.5", "TV", "tv", Some("tv.local"))],
        ))
        .devices;
        let o = diff(input(
            d,
            vec![scan("10.0.0.9", "TV", "tv", Some("tv.local"))],
        ));
        assert_eq!(types(&o), vec!["ip_changed"]);
        assert_eq!(o.events[0].from.as_deref(), Some("10.0.0.5"));
        assert_eq!(o.events[0].to.as_deref(), Some("10.0.0.9"));
        assert_eq!(o.devices[0].last_ip, "10.0.0.9");
    }

    #[test]
    fn ip_only_device_upgrades_to_real_identity() {
        let d = diff(input(
            vec![],
            vec![scan("10.0.0.7", "Device 7", "unknown", None)],
        ))
        .devices;
        let o = diff(input(
            d,
            vec![scan("10.0.0.7", "Fridge", "iot", Some("fridge.local"))],
        ));
        assert!(o.events.is_empty(), "no new/joined noise: {:?}", o.events);
        assert_eq!(o.devices.len(), 1);
        assert_eq!(o.devices[0].id, "mdns:fridge.local");
        assert_eq!(o.devices[0].kind, "iot");
    }

    #[test]
    fn json_entry_point() {
        let out = diff_json(
            r#"{"known":[],"scan":[{"ip":"10.0.0.5","name":"A","kind":"tv","seen_by":["icmp"],"is_self":false,"is_gateway":false,"open_ports":[],"services":[]}],"leave_after":2,"alert_all_new":true}"#,
        );
        let v: serde_json::Value = serde_json::from_str(&out).unwrap();
        assert_eq!(v["events"][0]["type"], "new");
        assert!(v["devices"][0]["known"].is_boolean());
        assert!(v["online_ids"].is_array());
        assert!(diff_json("not json").contains("\"error\""));
    }
}
