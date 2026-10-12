# PxLScan — Kotlin ↔ Rust core contract

Recovered 2026-10-11 from PixelScan 0.1.1 (`reference/pixelscan-0.1.1/`): JSON keys and call
shapes from the decompiled Kotlin (`kotlin-decompiled/Native.java`, `ScanSnapshot.java`,
`Device.java`, `ScanDiag.java`, `MonitorOutput.java`, `NetEvent.java`, `Monitor.java`), struct
shapes and probe payloads from the strings in `resources/lib/arm64-v8a/libpixelscan_core.so`.
**This file is the interface.** Both halves are written against it; neither may change a key
without changing it here.

## Renames from PixelScan

| Thing | PixelScan 0.1.1 | PxLScan |
|---|---|---|
| Package / namespace / applicationId | `com.mrnice.pixelscan` | `com.pixelicious.pxlscan` |
| Native library | `libpixelscan_core.so` (`System.loadLibrary("pixelscan_core")`) | `libpxlscan_core.so` (`"pxlscan_core"`) |
| Rust crate | `pixelscan_core` | `pxlscan_core` (cdylib) |
| JNI symbols | `Java_com_mrnice_pixelscan_Native_*` | `Java_com_pixelicious_pxlscan_Native_*` |
| HTTP `User-Agent` | `PixelScan/1.0` | `PxLScan/1.0` |
| SSDP `USER-AGENT` | `Android/15 UPnP/1.1 PixelScan/1.0` | `Android/15 UPnP/1.1 PxLScan/1.0` |
| App label / theme | `PixelScan` / `Theme.PixelScan` | `PxLScan` / `Theme.PxLScan` |

## JNI surface — `object Native` in `com.pixelicious.pxlscan`

```kotlin
private external fun startScan(configJson: String): Long   // handle ≥ 0, or -1 on bad input
private external fun poll(handle: Long): String            // ScanSnapshot JSON, never throws
private external fun cancel(handle: Long)                  // stop + free; poll after = "gone"
private external fun monitorDiff(inputJson: String): String // MonitorOutput JSON
private external fun ping(ip: String, timeoutMs: Long): Float // RTT ms, or -1 on no reply
```

All five are synchronous from Kotlin's view. `startScan` returns at once; the scan runs on a
background tokio runtime owned by the core, keyed by handle. `poll` is called every ~300 ms and
returns the *current* snapshot (hosts so far). Errors are never thrown into Java: a bad JSON input
returns `{"error":"..."}` (Kotlin's parsers then yield empty results). `ping` blocks up to
`timeoutMs`.

## `startScan` input — ScanConfig (6 fields)

```json
{"self_ip":"192.168.1.23","prefix_len":24,"gateway":"192.168.1.1",
 "dns_servers":["192.168.1.1"],"timeout_ms":700,"self_name":"Pixel 8 Pro"}
```
`gateway` and `self_name` may be `null`. The UI scan uses its own timeout; the background
monitor uses 700.

## `poll` output — ScanSnapshot

```json
{"progress":0.42,"phase":"scanning","done":false,"elapsed_ms":3120,"total_addresses":254,
 "hosts":[ Host, ... ], "diag": Diag}
```
- `phase` strings seen in the binary: `"resolving"`, `"classifying"`, plus a sweep phase
  (use `"scanning"`, which is Kotlin's default). Final snapshot: `done:true`, `progress:1.0`.
- Unknown / cancelled handle returns exactly
  `{"done":true,"hosts":[],"progress":1.0,"phase":"gone","elapsed_ms":0,"total_addresses":0}`.

### Host (22 fields in the original struct)

```json
{"ip":"192.168.1.40","name":"Living Room TV","kind":"tv","seen_by":["icmp","mdns","ssdp"],
 "rtt_ms":3.2,"ttl":64,"open_ports":[80,8008,8009],"mdns_name":"Living-Room.local",
 "netbios_name":null,"dns_name":"tv.lan","ssdp_uuid":"uuid:...","friendly_name":"Living Room TV",
 "mac":null,"vendor":"Google","model":"Chromecast","http_server":"...","http_title":"...",
 "ssdp_server":"...","ssdp_type":"...","is_self":false,"is_gateway":false,
 "services":["_googlecast._tcp"]}
```
Absent values are `null` (Kotlin treats null and `""` the same). `mac` is usually null: Android
gives apps no ARP table. `name` is never empty: best of friendly_name → mdns (without `.local`) →
netbios → dns → model → vendor + kind → `"Device <last octet>"` (the binary has the string
`"Device "`). Self is always included (`is_self`, `name` = `self_name`), gateway is flagged.

### Diag

```json
{"icmp_sent":254,"icmp_replies":12,"icmp_send_errors":0,"tcp_attempts":1270,"tcp_open":30,
 "tcp_refused":200,"tcp_timeout":1000,"tcp_denied":0,"tcp_unreachable":40,"udp_sent":600,
 "udp_send_errors":0,"replies_mdns":9,"replies_netbios":2,"replies_ssdp":5,"replies_dns":11,
 "errors":["icmp socket: Permission denied (os error 13)"]}
```
`tcp_denied` counts EACCES/EPERM on connect (the "local network permission" case). Kotlin's
`lanLooksBlocked` = tcp_attempts>0 && tcp_open==0 && tcp_refused==0 && icmp_replies≤1 && no
mdns/netbios/ssdp replies. Error tags seen in the binary: `icmp send`, `icmp socket`,
`dns socket`, `dns send`, `mdns unicast send`, `mdns multicast send`, `mdns socket`, `ssdp send`,
`ssdp socket`, `netbios send`, `netbios socket`.

## Discovery the core performs (from the binary's strings)

- **ICMP echo** sweep of every address in the subnet (cap /22 → ~1022 hosts; larger nets are cut
  to the /24 around self). Unprivileged ICMP: `socket(AF_INET, SOCK_DGRAM, IPPROTO_ICMP)` — works
  on Android without root. Record `rtt_ms`, `ttl`.
- **TCP connect** probes on a short port list (e.g. 22, 53, 80, 139, 443, 445, 548, 554, 631, 1883,
  3389, 5000, 5353?, 7000, 8008, 8009, 8080, 8443, 9100, 62078) — open or *refused* both prove a
  host exists.
- **mDNS** (224.0.0.251:5353): query `_services._dns-sd._udp.local` plus these service types:
  `_airplay _googlecast _ipp _ipps _printer _pdl-datastream _hap _companion-link _apple-mobdev2
  _device-info _smb _afpovertcp _adisk _ssh _sftp-ssh _workstation _spotify-connect _sonos
  _matter(_tcp) _matterc(_udp) _esphomelib _hue _amzn-wplay _androidtvremote2 _nvstream
  _meshcop(_udp) _homekit _sleep-proxy(_udp) _rdlink _scanner _uscan _raop _http` — `._tcp.local`
  unless marked `_udp`. Both multicast and unicast-response queries. Collect host names (`.local`),
  service types into `services`, TXT `md=`/`model=`/`fn=` into model/friendly name.
- **SSDP** (239.255.255.250:1900):
  ```
  M-SEARCH * HTTP/1.1\r\nHOST: 239.255.255.250:1900\r\nMAN: "ssdp:discover"\r\nMX: 1\r\n
  ST: ssdp:all\r\nUSER-AGENT: Android/15 UPnP/1.1 PxLScan/1.0\r\n\r\n
  ```
  also `ST: upnp:rootdevice`. Keep `SERVER` → `ssdp_server`, `ST`/`NT` → `ssdp_type`, `USN` uuid →
  `ssdp_uuid`; fetch `LOCATION` XML for `<friendlyName>`, `<manufacturer>`, `<modelName>`.
- **NetBIOS** node-status query (UDP 137, name `*`) → `netbios_name`.
- **Reverse DNS** PTR (`d.c.b.a.in-addr.arpa`) against `dns_servers` → `dns_name`.
- **HTTP** `GET / HTTP/1.0` (`Host:`, `User-Agent: PxLScan/1.0`, `Accept: */*`,
  `Connection: close`) to hosts with 80/8080 open → `http_server` (Server header) and
  `http_title` (`<title>`, HTML entities `&amp; &lt; &gt; &quot; &#39; &apos;` decoded).
- **Classify** into `kind` and `vendor` by keyword over names/services/server strings. Keywords in
  the binary include: iphone ipad macbook imac "mac mini" "mac pro" "apple tv" homepod watch airport,
  galaxy pixel oneplus xiaomi redmi moto, chromecast webos tizen bravia vizio "fire tv" shield roku,
  sonos bose, playstation ps4 ps5 switch nintendo xbox, laserjet officejet epson brother canon hp,
  hikvision dahua reolink nvr onvif ipcam camera ring wyze, synology diskstation qnap truenas unraid,
  esp32 espressif tasmota shelly hue wiz kasa tuya meross ecobee nest "google home" smartthings
  "home assistant" esphome, openwrt unifi ubiquiti mikrotik fritz netgear tp-link linksys asus eero
  router, raspberry, windows desktop laptop workstation, and service types (`_ipp`→printer,
  `_googlecast`/`_airplay`/`_androidtvremote2`→tv, `_sonos`/`_spotify-connect`→speaker,
  `_hap`/`_matter`/`_hue`/`_esphomelib`/`_meshcop`→iot, `_adisk`/`_afpovertcp`→nas,
  `_smb`/`_workstation`/`_companion-link`→computer, `_ssh`→server, `_scanner`/`_uscan`→printer).
  `InternetGatewayDevice` (UPnP) or `ip == gateway` → `router`.

### `kind` values (Kotlin `Kinds` maps these to labels and icons)

`phone tablet computer server tv speaker console printer camera nas iot watch router network
unknown` — label strings: Phone, Tablet, Computer, Server / Pi, TV / Streamer, Speaker,
Game console, Printer, Camera, NAS / Storage, Smart home, Watch, Router, Network gear, Unknown.

## `monitorDiff` — background presence tracking

Input (built in `Monitor.runOnce`):
```json
{"known":[KnownDevice...], "scan":[ScanHost...], "leave_after":2, "alert_all_new":true}
```
`ScanHost` is the Kotlin `deviceToJson`: `ip name kind seen_by mac? mdns_name? netbios_name?
dns_name? ssdp_uuid? is_self is_gateway vendor open_ports services`.

`KnownDevice` (12 fields, persisted by Kotlin verbatim from the previous output's `devices`):
```json
{"id":"mdns:living-room.local","name":"Living Room TV","kind":"tv","mac":null,"ssdp_uuid":null,
 "mdns_name":"Living-Room.local","netbios_name":null,"dns_name":null,"last_ip":"192.168.1.40",
 "known":true,"was_online":true,"missed":0}
```
(The 12th field is `watched` *or* `vendor` — Kotlin reads `watched` from its own overrides, not
from here, so carry `vendor` and accept/ignore `watched`.)

Rules:
- `id` = first of `mac:<lower>`, `upnp:<uuid>`, `mdns:<lower>`, `nb:<lower>`, `dns:<lower>`,
  `ip:<ip>` — **must match Kotlin `Store.keyOf`** so overrides line up.
- Match each scan host to a known device by id; fall back to the same ip if the known one has only
  an `ip:` id.
- Not known before → event `new` (`unknown:true` when kind is `unknown`), add with `was_online`.
  Emit `new` events only when `alert_all_new` is true, but always add the device.
- Known, was offline, now seen → `joined`. Seen at a different ip → `ip_changed` (`from`,`to`).
- Known, online, not seen → `missed += 1`; when `missed >= leave_after` → `left`, `was_online=false`.
- Skip `is_self` hosts entirely.

Output:
```json
{"events":[{"type":"new","id":"...","name":"...","ip":"...","unknown":false},
           {"type":"ip_changed","id":"...","name":"...","from":"a","to":"b"},
           {"type":"left","id":"...","name":"...","last_ip":"..."}],
 "devices":[KnownDevice...], "online_ids":["..."]}
```

## `ping`

One unprivileged ICMP echo (fall back to a TCP connect to 80/443 timing if ICMP sockets are
refused). Returns RTT in ms as `f32`, or `-1.0`.
