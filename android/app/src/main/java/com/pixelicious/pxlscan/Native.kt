package com.pixelicious.pxlscan

import org.json.JSONArray
import org.json.JSONObject

/**
 * JNI bridge to the Rust core (`libpxlscan_core.so`). The five externals follow
 * docs/CORE-CONTRACT.md exactly; everything crosses the boundary as JSON strings.
 */
object Native {
    @Volatile private var loaded = false

    @Volatile
    var available = false
        private set

    private external fun startScan(configJson: String): Long
    private external fun poll(handle: Long): String
    private external fun cancel(handle: Long)
    private external fun monitorDiff(inputJson: String): String
    private external fun ping(ip: String, timeoutMs: Long): Float

    /** Loads the library once. Returns whether the core is usable. */
    fun ensureLoaded(): Boolean {
        if (!loaded) {
            synchronized(this) {
                if (!loaded) {
                    available = try {
                        System.loadLibrary("pxlscan_core")
                        true
                    } catch (_: Throwable) {
                        false
                    }
                    loaded = true
                }
            }
        }
        return available
    }

    /** Starts a scan; returns a handle >= 0, or -1 when the core is missing or rejected the input. */
    fun start(config: ScanConfig): Long {
        if (!ensureLoaded()) return -1L
        return startScan(config.toJson().toString())
    }

    fun snapshot(handle: Long): ScanSnapshot =
        if (handle < 0 || !available) ScanSnapshot.empty() else ScanSnapshot.parse(poll(handle))

    fun stop(handle: Long) {
        if (handle < 0 || !available) return
        cancel(handle)
    }

    fun diff(input: String): MonitorOutput =
        if (ensureLoaded()) MonitorOutput.parse(monitorDiff(input)) else MonitorOutput(emptyList(), "[]", emptyList())

    /** Round-trip time in ms, or -1 on no reply. */
    fun pingMs(ip: String, timeoutMs: Long = 1000): Float =
        if (ensureLoaded()) ping(ip, timeoutMs) else -1f
}

// --- JSON helpers -------------------------------------------------------------------------------

/** A string value, or null when the key is missing, JSON null, or empty. */
fun JSONObject.optStringOrNull(key: String): String? {
    if (isNull(key) || !has(key)) return null
    return optString(key).takeIf { it.isNotEmpty() }
}

fun JSONArray?.toStringList(): List<String> =
    if (this == null) emptyList() else (0 until length()).map { optString(it) }

fun JSONArray?.toIntList(): List<Int> =
    if (this == null) emptyList() else (0 until length()).map { optInt(it) }

fun <T> JSONArray?.mapObjects(f: (JSONObject) -> T?): List<T> {
    if (this == null) return emptyList()
    return (0 until length()).mapNotNull { i -> optJSONObject(i)?.let(f) }
}

// --- models -------------------------------------------------------------------------------------

/** Input to `startScan`. */
data class ScanConfig(
    val selfIp: String,
    val prefixLen: Int,
    val gateway: String?,
    val dnsServers: List<String>,
    val timeoutMs: Long,
    val selfName: String?,
) {
    fun toJson(): JSONObject = JSONObject().apply {
        put("self_ip", selfIp)
        put("prefix_len", prefixLen)
        put("gateway", gateway ?: JSONObject.NULL)
        put("dns_servers", JSONArray(dnsServers))
        put("timeout_ms", timeoutMs)
        put("self_name", selfName ?: JSONObject.NULL)
    }
}

/** One host found by the scan. */
data class Device(
    val ip: String,
    val name: String,
    val kind: String,
    val vendor: String?,
    val model: String?,
    val rttMs: Float?,
    val ttl: Int?,
    val mac: String?,
    val mdnsName: String?,
    val netbiosName: String?,
    val dnsName: String?,
    val ssdpUuid: String?,
    val httpServer: String?,
    val httpTitle: String?,
    val openPorts: List<Int>,
    val services: List<String>,
    val seenBy: List<String>,
    val isSelf: Boolean,
    val isGateway: Boolean,
) {
    /** The IPv4 address as one number, for sorting. */
    val ipSortKey: Long
        get() = ip.split('.').fold(0L) { acc, part -> acc * 256 + (part.toLongOrNull() ?: 0L) }

    companion object {
        fun parse(o: JSONObject): Device = Device(
            ip = o.optString("ip"),
            name = o.optString("name"),
            kind = o.optString("kind", "unknown"),
            vendor = o.optStringOrNull("vendor"),
            model = o.optStringOrNull("model"),
            rttMs = if (o.isNull("rtt_ms")) null else o.optDouble("rtt_ms").toFloat(),
            ttl = if (o.isNull("ttl")) null else o.optInt("ttl"),
            mac = o.optStringOrNull("mac"),
            mdnsName = o.optStringOrNull("mdns_name"),
            netbiosName = o.optStringOrNull("netbios_name"),
            dnsName = o.optStringOrNull("dns_name"),
            ssdpUuid = o.optStringOrNull("ssdp_uuid"),
            httpServer = o.optStringOrNull("http_server"),
            httpTitle = o.optStringOrNull("http_title"),
            openPorts = o.optJSONArray("open_ports").toIntList(),
            services = o.optJSONArray("services").toStringList(),
            seenBy = o.optJSONArray("seen_by").toStringList(),
            isSelf = o.optBoolean("is_self"),
            isGateway = o.optBoolean("is_gateway"),
        )
    }
}

/** Counters the core keeps while scanning, used to explain an empty result. */
data class ScanDiag(
    val icmpSent: Int = 0,
    val icmpReplies: Int = 0,
    val icmpErrors: Int = 0,
    val tcpAttempts: Int = 0,
    val tcpOpen: Int = 0,
    val tcpRefused: Int = 0,
    val tcpTimeout: Int = 0,
    val tcpDenied: Int = 0,
    val tcpUnreachable: Int = 0,
    val udpSent: Int = 0,
    val udpErrors: Int = 0,
    val mdns: Int = 0,
    val netbios: Int = 0,
    val ssdp: Int = 0,
    val dns: Int = 0,
    val errors: List<String> = emptyList(),
) {
    /** Nothing at all answered: what Android does when local-network access is blocked. */
    val lanLooksBlocked: Boolean
        get() = tcpAttempts > 0 && tcpOpen == 0 && tcpRefused == 0 && icmpReplies <= 1 &&
            mdns == 0 && netbios == 0 && ssdp == 0

    fun lines(): List<String> = listOf(
        "ping: $icmpSent sent, $icmpReplies replies, $icmpErrors send errors",
        "tcp: $tcpAttempts tries, $tcpOpen open, $tcpRefused refused, $tcpTimeout timed out, " +
            "$tcpDenied denied, $tcpUnreachable unreachable",
        "udp: $udpSent sent, $udpErrors errors",
        "replies: mdns $mdns · netbios $netbios · ssdp $ssdp · dns $dns",
    ) + errors.map { "error: $it" }

    companion object {
        fun parse(o: JSONObject?): ScanDiag {
            if (o == null) return ScanDiag()
            return ScanDiag(
                icmpSent = o.optInt("icmp_sent"),
                icmpReplies = o.optInt("icmp_replies"),
                icmpErrors = o.optInt("icmp_send_errors"),
                tcpAttempts = o.optInt("tcp_attempts"),
                tcpOpen = o.optInt("tcp_open"),
                tcpRefused = o.optInt("tcp_refused"),
                tcpTimeout = o.optInt("tcp_timeout"),
                tcpDenied = o.optInt("tcp_denied"),
                tcpUnreachable = o.optInt("tcp_unreachable"),
                udpSent = o.optInt("udp_sent"),
                udpErrors = o.optInt("udp_send_errors"),
                mdns = o.optInt("replies_mdns"),
                netbios = o.optInt("replies_netbios"),
                ssdp = o.optInt("replies_ssdp"),
                dns = o.optInt("replies_dns"),
                errors = o.optJSONArray("errors").toStringList(),
            )
        }
    }
}

/** What `poll` returns: progress plus the hosts found so far. */
data class ScanSnapshot(
    val progress: Float,
    val phase: String,
    val done: Boolean,
    val devices: List<Device>,
    val elapsedMs: Long,
    val totalAddresses: Int,
    val diag: ScanDiag = ScanDiag(),
) {
    companion object {
        fun empty() = ScanSnapshot(0f, "idle", false, emptyList(), 0L, 0)

        fun parse(json: String): ScanSnapshot = try {
            val o = JSONObject(json)
            ScanSnapshot(
                progress = o.optDouble("progress").toFloat(),
                phase = o.optString("phase", "scanning"),
                done = o.optBoolean("done"),
                devices = o.optJSONArray("hosts").mapObjects { Device.parse(it) },
                elapsedMs = o.optLong("elapsed_ms"),
                totalAddresses = o.optInt("total_addresses"),
                diag = ScanDiag.parse(o.optJSONObject("diag")),
            )
        } catch (_: Exception) {
            empty()
        }
    }
}

/** A presence change reported by `monitorDiff`. */
data class NetEvent(
    val type: String,
    val id: String,
    val name: String,
    val ip: String,
    val detail: String,
) {
    companion object {
        fun parse(o: JSONObject): NetEvent {
            val type = o.optString("type")
            val ip = o.optStringOrNull("ip") ?: o.optStringOrNull("to") ?: o.optStringOrNull("last_ip") ?: ""
            val detail = when (type) {
                "joined" -> "Joined the network"
                "ip_changed" -> o.optString("from") + " → " + o.optString("to")
                "new" -> if (o.optBoolean("unknown")) "New unknown device" else "New device"
                "left" -> "Left the network"
                else -> ""
            }
            return NetEvent(type, o.optString("id"), o.optString("name"), ip, detail)
        }
    }
}

/** Result of `monitorDiff`: events, the new known-device list (kept as raw JSON), and who is online. */
data class MonitorOutput(
    val events: List<NetEvent>,
    val devicesJson: String,
    val onlineIds: List<String>,
) {
    companion object {
        fun parse(json: String): MonitorOutput = try {
            val o = JSONObject(json)
            MonitorOutput(
                events = o.optJSONArray("events").mapObjects { NetEvent.parse(it) },
                devicesJson = o.optJSONArray("devices")?.toString() ?: "[]",
                onlineIds = o.optJSONArray("online_ids").toStringList(),
            )
        } catch (_: Exception) {
            MonitorOutput(emptyList(), "[]", emptyList())
        }
    }
}
