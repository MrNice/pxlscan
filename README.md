# PxLScan

LAN scanner for Android: finds the devices on your network, pings them, checks their
services, and watches for devices that join or leave. Kotlin + Jetpack Compose over a Rust
core built with the NDK.

Package: `com.pixelicious.pxlscan`. Target: Google Play (personal developer account).

## Status

This repo is a rebuild. The original app, **PixelScan 0.1.1** (`com.mrnice.pixelscan`), was
built on 2026-09-30 in a Claude cloud session that no longer exists, from a folder that was
never under git. Only the APK on the captain's phone survived.

`reference/pixelscan-0.1.1/` holds what was recovered from that APK on 2026-10-11:

| Path | What it is |
|---|---|
| `pixelscan-0.1.1.apk` | The original APK, pulled from the phone with `adb` |
| `kotlin-decompiled/` | jadx 1.5.3 output for `com.mrnice.pixelscan`. Not obfuscated, so names are real. Java syntax, not Kotlin |
| `resources/` | Decoded manifest and resources, including the launcher icon (`res/mipmap-*`) |

The Rust core (`libpixelscan_core.so`) cannot be decompiled usefully. What is known about it:

- JNI exports on `com.mrnice.pixelscan.Native`: `startScan`, `poll`, `cancel`, `ping`, `monitorDiff`.
- Modules: `discover`, `scan`, `icmp`, `dns`, `http`, `monitor`, `model`.
- Crates: `tokio`, `serde_json`, `socket2`, `jni`.

Reference material is read-only. Rebuilt code goes in `android/` and `android/rust/`.

## Signing

The original APK was signed with TrowlLord's committed dev key (`CN=TrowlLord Dev`). That key
and its password are public, so **PxLScan never uses it.** The Play upload key lives outside
this repo and is never committed.
