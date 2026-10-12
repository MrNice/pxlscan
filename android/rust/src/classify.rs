//! Kind + vendor + display-name selection by keyword tables (CORE-CONTRACT "Classify"),
//! and the stable device id key (must match Kotlin `Store.keyOf`).

use crate::model::Host;

/// Kotlin `Store.keyOf`: mac (lowercased) > upnp uuid (as is) > mdns > netbios > dns (lowercased) > ip.
pub fn id_key(
    mac: Option<&str>,
    uuid: Option<&str>,
    mdns: Option<&str>,
    netbios: Option<&str>,
    dns: Option<&str>,
    ip: &str,
) -> String {
    if let Some(m) = mac {
        return format!("mac:{}", m.to_lowercase());
    }
    if let Some(u) = uuid {
        return format!("upnp:{}", u);
    }
    if let Some(m) = mdns {
        return format!("mdns:{}", m.to_lowercase());
    }
    if let Some(n) = netbios {
        return format!("nb:{}", n.to_lowercase());
    }
    if let Some(d) = dns {
        return format!("dns:{}", d.to_lowercase());
    }
    format!("ip:{}", ip)
}

/// (keyword, kind, vendor). Order matters: first matching row picks the kind. A keyword made of
/// letters and digits only must match the START of a name token ("iphone" hits "Nicks-iPhone15");
/// anything with a space or punctuation is a plain substring match.
const KEYWORDS: &[(&str, &str, &str)] = &[
    // phones / tablets / wearables
    ("iphone", "phone", "Apple"),
    ("ipad", "tablet", "Apple"),
    ("apple watch", "watch", "Apple"),
    ("galaxy watch", "watch", "Samsung"),
    ("watch", "watch", ""),
    ("galaxy tab", "tablet", "Samsung"),
    ("galaxy", "phone", "Samsung"),
    ("pixel", "phone", "Google"),
    ("oneplus", "phone", "OnePlus"),
    ("xiaomi", "phone", "Xiaomi"),
    ("redmi", "phone", "Xiaomi"),
    ("moto", "phone", "Motorola"),
    // Apple home gear before generic computer words
    ("apple tv", "tv", "Apple"),
    ("homepod", "speaker", "Apple"),
    ("airport", "router", "Apple"),
    ("macbook", "computer", "Apple"),
    ("imac", "computer", "Apple"),
    ("mac mini", "computer", "Apple"),
    ("mac pro", "computer", "Apple"),
    // TVs / streamers
    ("chromecast", "tv", "Google"),
    ("google home", "speaker", "Google"),
    ("webos", "tv", "LG"),
    ("tizen", "tv", "Samsung"),
    ("bravia", "tv", "Sony"),
    ("vizio", "tv", "Vizio"),
    ("fire tv", "tv", "Amazon"),
    ("shield", "tv", "NVIDIA"),
    ("roku", "tv", "Roku"),
    // speakers
    ("sonos", "speaker", "Sonos"),
    ("bose", "speaker", "Bose"),
    // consoles
    ("playstation", "console", "Sony"),
    ("ps4", "console", "Sony"),
    ("ps5", "console", "Sony"),
    ("nintendo", "console", "Nintendo"),
    ("xbox", "console", "Microsoft"),
    // printers
    ("laserjet", "printer", "HP"),
    ("officejet", "printer", "HP"),
    ("epson", "printer", "Epson"),
    ("brother", "printer", "Brother"),
    ("canon", "printer", "Canon"),
    // cameras
    ("hikvision", "camera", "Hikvision"),
    ("dahua", "camera", "Dahua"),
    ("reolink", "camera", "Reolink"),
    ("wyze", "camera", "Wyze"),
    ("onvif", "camera", ""),
    ("ipcam", "camera", ""),
    ("camera", "camera", ""),
    ("nvr", "camera", ""),
    ("ring", "camera", "Ring"),
    // NAS
    ("synology", "nas", "Synology"),
    ("diskstation", "nas", "Synology"),
    ("qnap", "nas", "QNAP"),
    ("truenas", "nas", "iXsystems"),
    ("unraid", "nas", "Lime Technology"),
    // smart home
    ("esp32", "iot", "Espressif"),
    ("espressif", "iot", "Espressif"),
    ("esphome", "iot", "Espressif"),
    ("tasmota", "iot", ""),
    ("shelly", "iot", "Shelly"),
    ("hue", "iot", "Philips Hue"),
    ("wiz", "iot", "WiZ"),
    ("kasa", "iot", "TP-Link"),
    ("tuya", "iot", "Tuya"),
    ("meross", "iot", "Meross"),
    ("ecobee", "iot", "ecobee"),
    ("nest", "iot", "Google"),
    ("smartthings", "iot", "Samsung"),
    ("home assistant", "server", ""),
    // network gear
    ("openwrt", "router", ""),
    ("unifi", "network", "Ubiquiti"),
    ("ubiquiti", "network", "Ubiquiti"),
    ("mikrotik", "router", "MikroTik"),
    ("fritz", "router", "AVM"),
    ("netgear", "router", "Netgear"),
    ("tp-link", "router", "TP-Link"),
    ("tplink", "router", "TP-Link"),
    ("linksys", "router", "Linksys"),
    ("asus", "router", "ASUS"),
    ("eero", "router", "eero"),
    ("router", "router", ""),
    ("switch", "network", ""),
    // generic printer brand last: "hp" is short and common
    ("hp", "printer", "HP"),
    // computers / servers
    ("raspberry", "server", "Raspberry Pi"),
    ("windows", "computer", "Microsoft"),
    ("desktop", "computer", ""),
    ("laptop", "computer", ""),
    ("workstation", "computer", ""),
];

/// mDNS service type (without `._tcp`/`.local`) to kind. First hit wins, order is priority.
const SERVICE_KINDS: &[(&str, &str)] = &[
    ("_googlecast", "tv"),
    ("_androidtvremote2", "tv"),
    ("_airplay", "tv"),
    ("_ipp", "printer"),
    ("_ipps", "printer"),
    ("_printer", "printer"),
    ("_pdl-datastream", "printer"),
    ("_scanner", "printer"),
    ("_uscan", "printer"),
    ("_sonos", "speaker"),
    ("_spotify-connect", "speaker"),
    ("_raop", "speaker"),
    ("_hap", "iot"),
    ("_matter", "iot"),
    ("_hue", "iot"),
    ("_esphomelib", "iot"),
    ("_meshcop", "iot"),
    ("_adisk", "nas"),
    ("_afpovertcp", "nas"),
    ("_smb", "computer"),
    ("_workstation", "computer"),
    ("_companion-link", "computer"),
    ("_ssh", "server"),
    ("_sftp-ssh", "server"),
];

fn tokens(hay: &str) -> Vec<&str> {
    hay.split(|c: char| !c.is_ascii_alphanumeric())
        .filter(|t| !t.is_empty())
        .collect()
}

fn kw_matches(hay: &str, toks: &[&str], kw: &str) -> bool {
    if kw.chars().all(|c| c.is_ascii_alphanumeric()) {
        toks.iter().any(|t| t.starts_with(kw))
    } else {
        hay.contains(kw)
    }
}

fn service_kind(services: &[String]) -> Option<&'static str> {
    for (svc, kind) in SERVICE_KINDS {
        for s in services {
            let s = s.to_lowercase();
            let base = s.split('.').next().unwrap_or("");
            if base == *svc {
                return Some(kind);
            }
            // "_matter" also covers "_matterc"
            if *svc == "_matter" && base == "_matterc" {
                return Some(kind);
            }
        }
    }
    None
}

fn lower(o: &Option<String>) -> String {
    o.as_deref().unwrap_or("").to_lowercase()
}

fn base_name(s: &str) -> &str {
    s.strip_suffix(".local").unwrap_or(s)
}

/// Returns `(kind, vendor)`. Existing `vendor` (for example from SSDP `<manufacturer>`) is kept.
pub fn classify(h: &Host, gateway: Option<&str>) -> (String, Option<String>) {
    let hay = [
        h.name.to_lowercase(),
        lower(&h.friendly_name),
        lower(&h.mdns_name),
        lower(&h.netbios_name),
        lower(&h.dns_name),
        lower(&h.model),
        lower(&h.vendor),
        lower(&h.http_server),
        lower(&h.http_title),
        lower(&h.ssdp_server),
        lower(&h.ssdp_type),
        h.services.join(" ").to_lowercase(),
    ]
    .join(" ");
    let toks = tokens(&hay);

    let mut vendor = h.vendor.clone().filter(|v| !v.is_empty());
    let mut kw_kind: Option<&str> = None;
    for (kw, kind, v) in KEYWORDS {
        if kw_matches(&hay, &toks, kw) {
            if kw_kind.is_none() {
                kw_kind = Some(kind);
            }
            if vendor.is_none() && !v.is_empty() {
                vendor = Some((*v).to_string());
            }
            if kw_kind.is_some() && vendor.is_some() {
                break;
            }
        }
    }

    let is_gw = h.is_gateway
        || gateway.map_or(false, |g| g == h.ip)
        || hay.contains("internetgatewaydevice");
    let kind = if is_gw {
        "router"
    } else if let Some(k) = kw_kind {
        k
    } else if let Some(k) = service_kind(&h.services) {
        k
    } else if h.open_ports.contains(&9100) || h.open_ports.contains(&631) {
        "printer"
    } else if h.open_ports.contains(&554) {
        "camera"
    } else if h.open_ports.contains(&62078) {
        "phone"
    } else if h.open_ports.contains(&3389)
        || h.open_ports.contains(&445)
        || h.open_ports.contains(&139)
    {
        "computer"
    } else if h.open_ports.contains(&8008) || h.open_ports.contains(&8009) {
        "tv"
    } else if h.open_ports.contains(&22) {
        "server"
    } else {
        "unknown"
    };
    (kind.to_string(), vendor)
}

fn kind_word(kind: &str) -> &'static str {
    match kind {
        "phone" => "phone",
        "tablet" => "tablet",
        "computer" => "computer",
        "server" => "server",
        "tv" => "TV",
        "speaker" => "speaker",
        "console" => "console",
        "printer" => "printer",
        "camera" => "camera",
        "nas" => "NAS",
        "iot" => "device",
        "watch" => "watch",
        "router" => "router",
        "network" => "network device",
        _ => "device",
    }
}

fn nonempty(o: &Option<String>) -> Option<&str> {
    o.as_deref().map(str::trim).filter(|s| !s.is_empty())
}

/// friendly_name > mdns (no `.local`) > netbios > dns > model > vendor + kind > "Device <octet>".
/// Self keeps the name it was given.
pub fn pick_name(h: &Host) -> String {
    if h.is_self && !h.name.trim().is_empty() {
        return h.name.clone();
    }
    if let Some(n) = nonempty(&h.friendly_name) {
        return n.to_string();
    }
    if let Some(n) = nonempty(&h.mdns_name) {
        let b = base_name(n);
        if !b.is_empty() {
            return b.to_string();
        }
    }
    if let Some(n) = nonempty(&h.netbios_name) {
        return n.to_string();
    }
    if let Some(n) = nonempty(&h.dns_name) {
        return n.to_string();
    }
    if let Some(n) = nonempty(&h.model) {
        return n.to_string();
    }
    if let Some(v) = nonempty(&h.vendor) {
        return format!("{} {}", v, kind_word(&h.kind));
    }
    let last = h.ip.rsplit('.').next().unwrap_or("?");
    format!("Device {}", last)
}

/// Fill `kind`, `vendor` and `name` on a host.
pub fn finish(h: &mut Host, gateway: Option<&str>) {
    let (kind, vendor) = classify(h, gateway);
    h.kind = kind;
    if vendor.is_some() {
        h.vendor = vendor;
    }
    h.name = pick_name(h);
}

#[cfg(test)]
mod tests {
    use super::*;

    fn host(ip: &str) -> Host {
        Host::new(ip)
    }

    #[test]
    fn id_key_priority_and_case() {
        assert_eq!(
            id_key(
                Some("AA:BB:CC:00:11:22"),
                Some("uuid:X"),
                Some("A.local"),
                None,
                None,
                "1.2.3.4"
            ),
            "mac:aa:bb:cc:00:11:22"
        );
        assert_eq!(
            id_key(
                None,
                Some("uuid:Abc"),
                Some("A.local"),
                None,
                None,
                "1.2.3.4"
            ),
            "upnp:uuid:Abc"
        );
        assert_eq!(
            id_key(
                None,
                None,
                Some("Living-Room.LOCAL"),
                Some("NB"),
                None,
                "1.2.3.4"
            ),
            "mdns:living-room.local"
        );
        assert_eq!(
            id_key(None, None, None, Some("NBNAME"), Some("d"), "1.2.3.4"),
            "nb:nbname"
        );
        assert_eq!(
            id_key(None, None, None, None, Some("TV.Lan"), "1.2.3.4"),
            "dns:tv.lan"
        );
        assert_eq!(
            id_key(None, None, None, None, None, "1.2.3.4"),
            "ip:1.2.3.4"
        );
    }

    #[test]
    fn services_map_to_kinds() {
        let mut h = host("192.168.1.40");
        h.services = vec!["_googlecast._tcp".into()];
        assert_eq!(classify(&h, None).0, "tv");
        let mut p = host("192.168.1.41");
        p.services = vec!["_ipp._tcp".into()];
        assert_eq!(classify(&p, None).0, "printer");
        let mut n = host("192.168.1.42");
        n.services = vec!["_adisk._tcp".into()];
        assert_eq!(classify(&n, None).0, "nas");
        let mut s = host("192.168.1.43");
        s.services = vec!["_spotify-connect._tcp".into()];
        assert_eq!(classify(&s, None).0, "speaker");
        let mut i = host("192.168.1.44");
        i.services = vec!["_matterc._udp".into()];
        assert_eq!(classify(&i, None).0, "iot");
    }

    #[test]
    fn gateway_ip_and_igd_are_routers() {
        let h = host("192.168.1.1");
        assert_eq!(classify(&h, Some("192.168.1.1")).0, "router");
        let mut g = host("192.168.1.2");
        g.ssdp_type = Some("urn:schemas-upnp-org:device:InternetGatewayDevice:1".into());
        assert_eq!(classify(&g, None).0, "router");
    }

    #[test]
    fn name_keywords_give_kind_and_vendor() {
        let mut h = host("192.168.1.50");
        h.mdns_name = Some("Nicks-iPhone15.local".into());
        let (k, v) = classify(&h, None);
        assert_eq!((k.as_str(), v.as_deref()), ("phone", Some("Apple")));

        let mut c = host("192.168.1.51");
        c.friendly_name = Some("Living Room Chromecast".into());
        assert_eq!(classify(&c, None).0, "tv");

        // "hp" must not match inside an unrelated word
        let mut x = host("192.168.1.52");
        x.dns_name = Some("shepherd.lan".into());
        assert_eq!(classify(&x, None).0, "unknown");
    }

    #[test]
    fn open_port_fallbacks() {
        let mut h = host("192.168.1.60");
        h.open_ports = vec![9100];
        assert_eq!(classify(&h, None).0, "printer");
        let mut p = host("192.168.1.61");
        p.open_ports = vec![62078];
        assert_eq!(classify(&p, None).0, "phone");
    }

    #[test]
    fn name_selection_order_and_fallbacks() {
        let mut h = host("192.168.1.77");
        assert_eq!(pick_name(&h), "Device 77");
        h.vendor = Some("Sonos".into());
        h.kind = "speaker".into();
        assert_eq!(pick_name(&h), "Sonos speaker");
        h.model = Some("Play:1".into());
        assert_eq!(pick_name(&h), "Play:1");
        h.dns_name = Some("kitchen.lan".into());
        assert_eq!(pick_name(&h), "kitchen.lan");
        h.netbios_name = Some("KITCHEN".into());
        assert_eq!(pick_name(&h), "KITCHEN");
        h.mdns_name = Some("Kitchen-Sonos.local".into());
        assert_eq!(pick_name(&h), "Kitchen-Sonos");
        h.friendly_name = Some("Kitchen".into());
        assert_eq!(pick_name(&h), "Kitchen");
    }

    #[test]
    fn self_keeps_its_name() {
        let mut h = host("192.168.1.23");
        h.is_self = true;
        h.name = "Pixel 8 Pro".into();
        finish(&mut h, None);
        assert_eq!(h.name, "Pixel 8 Pro");
        assert_eq!(h.kind, "phone");
    }
}
