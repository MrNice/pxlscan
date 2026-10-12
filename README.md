# PxLScan

> **Help get PxLScan onto Google Play.** Play needs 12+ testers for 14 days before a new app can launch.
> 1. Join [pxlscan-testers](https://groups.google.com/g/pxlscan-testers) with the Google account on your phone.
> 2. Opt in: on your phone, open [PxLScan on Google Play](https://play.google.com/store/apps/details?id=com.pixelicious.pxlscan) and install. On a computer, use the [web opt-in page](https://play.google.com/apps/testing/com.pixelicious.pxlscan) and tap **Become a tester**.
>
> Please keep it installed for 14 days. Prefer no Google account? The APK is on the [Releases](https://github.com/MrNice/pxlscan/releases) page.

A LAN scanner for Android. It finds the devices on your Wi-Fi, works out what each one is, and can
watch the network in the background and tell you when something new shows up.

No account, no ads, no analytics, no server. The app talks to the devices it is scanning and to
your DNS server, and nothing else.

<!-- Screenshots: drop PNGs in docs/screenshots/ with these names. -->
<p>
  <img src="docs/screenshots/devices.png" width="240" alt="Devices list">
  <img src="docs/screenshots/radar.png" width="240" alt="Radar view">
  <img src="docs/screenshots/detail.png" width="240" alt="Device detail">
  <img src="docs/screenshots/monitor.png" width="240" alt="Monitor">
</p>

## Install

Two ways, both Android 8.0 or newer:

- **Google Play (testing).** Join the [tester group](https://groups.google.com/g/pxlscan-testers), then install [from Google Play](https://play.google.com/store/apps/details?id=com.pixelicious.pxlscan) on your phone, or opt in on the [web](https://play.google.com/apps/testing/com.pixelicious.pxlscan). Updates come through Play.
- **Download the APK** from the [Releases](../../releases) page and open it on your phone. Android
  asks you to allow installs from your browser or file manager the first time.

## What it does

### Devices

- **One tap scans the network.** A scan of a home network takes about 10 to 20 seconds. Devices
  appear as they answer, so the list fills in while the scan runs.
- **Names, not just addresses.** Each device gets the best name it gives out: its Bonjour name, its
  Windows name, its UPnP name, or its name in your router's DNS.
- **Sorted into kinds.** Phone, tablet, computer, server or Pi, TV or streamer, speaker, game
  console, printer, camera, NAS, smart home, watch, router, network gear. The maker shows next to
  the kind when the device says who made it.
- **List or radar.** The top bar switches between a list and a radar sweep of the same devices.
- **Search** by name, IP or maker.
- Your phone is marked `THIS PHONE` and your router `ROUTER`.

### Device detail

Tap a device to open it.

- **Live ping.** Start and stop a running ping to the device and watch its latency.
- **Identity.** Every name the device answered to: mDNS / Bonjour, NetBIOS, DNS, model, maker,
  UPnP UUID, and the server header and page title of its web interface.
- **Open ports and services**, with common ports labelled (`631·ipp`, `8009·cast`, `9100·print`).
- **Signal.** How the device was found, its latency, and its TTL with a guess at the OS family.
- **Open web UI** when the device serves a web page, and **Copy IP**.
- **Your own labels.** The pencil sets a nickname and a type. The star watches the device.
  **Mark known** tells PxLScan you trust it.

Labels are saved per network, so the same IP at home and at work are different devices.

### Monitor

- **Background checks.** Settings ▸ Monitor mode scans every 15, 30 or 60 minutes while the
  phone is on an unmetered network such as Wi-Fi, and keeps running after a reboot.
- **Alerts.** A notification when a new device joins. Starred devices also alert when they join or
  leave, if Settings ▸ Alert on watched devices is on.
- **Activity log.** The Monitor tab shows devices online now, known devices, new devices today,
  and a log of joins, leaves, IP changes and new arrivals.

## How it finds devices

Android does not let apps read the ARP table, so PxLScan asks the network instead. It needs no root.

- An ICMP ping sweep of your subnet.
- TCP connections to a short list of common ports. An open port and a refused connection both
  prove a device is there.
- mDNS / Bonjour queries for about 30 service types (AirPlay, Chromecast, printers, HomeKit,
  Matter, Sonos, SMB, SSH and more).
- SSDP / UPnP discovery, then the device's description for its name, maker and model.
- NetBIOS name queries and reverse DNS against your router.
- An HTTP request to devices with a web port, for the server name and page title.

The scanning runs in a Rust core. The app is Kotlin and Jetpack Compose.

## Limits

- **MAC addresses are usually missing.** Without the ARP table, PxLScan shows a MAC only when a
  device announces its own.
- **Some networks block it.** Guest networks and some mesh or business routers stop devices from
  seeing each other. If a scan finds only your phone, the app says so and offers its diagnostics.
- **Background checks are at least 15 minutes apart.** That is the shortest interval Android allows
  for a background job.
- **A VPN can hide the LAN.** Some VPNs send all traffic, including local traffic, through the
  tunnel. Turn the VPN off or allow local network access in its settings.

## PxLScan Privacy Policy

PxLScan collects no personal data and shares none. It has no account, no ads, no analytics and no
server.

- **What it sends.** Packets to addresses on your own subnet, and reverse-DNS lookups for those
  addresses to the DNS servers your phone uses. That is usually your router. If you set a public
  DNS server such as 1.1.1.1, those lookups go there.
- **What it keeps.** Scan results, your device labels and the activity log, in the app's private
  storage on your phone. Nothing is uploaded.
- **Deleting it.** Monitor ▸ Clear empties the activity log. Uninstalling the app, or clearing its
  storage in Android settings, deletes everything.

Questions about privacy: [nicholas@pixelicious.com](mailto:nicholas@pixelicious.com)

## Building

Needs JDK 17, the Android SDK (API 35) with NDK 27.2, Rust with the `aarch64-linux-android` and
`x86_64-linux-android` targets, and `cargo install cargo-ndk`.

```sh
cd android
./gradlew assembleDebug       # builds the Rust core with cargo-ndk first
# → app/build/outputs/apk/debug/app-debug.apk
```

The Rust core lives in `android/rust`. Its tests run on the host: `cargo test` in that folder.
The interface between the app and the core is in [docs/CORE-CONTRACT.md](docs/CORE-CONTRACT.md).

## Licence

Copyright 2026 Nicholas van de Walle. Licensed under the [Apache License 2.0](LICENSE).
