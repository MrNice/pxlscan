package com.pixelicious.pxlscan

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.Locale

/** User edits to one device: nickname, type, watched star, known mark, note. */
data class DeviceOverride(
    val nickname: String? = null,
    val typeOverride: String? = null,
    val watched: Boolean = false,
    val known: Boolean = false,
    val note: String? = null,
)

/** One line of the monitor activity log. */
data class LogEntry(
    val t: Long,
    val type: String,
    val name: String,
    val ip: String,
    val detail: String,
) {
    companion object {
        fun parse(o: JSONObject) = LogEntry(
            t = o.optLong("t"),
            type = o.optString("type"),
            name = o.optString("name"),
            ip = o.optString("ip"),
            detail = o.optString("detail"),
        )
    }
}

/** App-wide monitor settings, kept in `settings.json`. */
class Settings(ctx: Context) {
    private val file = File(ctx.filesDir, "settings.json")

    private fun read(): JSONObject = try {
        JSONObject(if (file.exists()) file.readText() else "{}")
    } catch (_: Exception) {
        JSONObject()
    }

    private fun write(o: JSONObject) = file.writeText(o.toString())

    var monitorEnabled: Boolean
        get() = read().optBoolean("monitor", false)
        set(v) = write(read().put("monitor", v))

    var intervalMinutes: Int
        get() = read().optInt("interval", 30)
        set(v) = write(read().put("interval", v))

    var alertAllNew: Boolean
        get() = read().optBoolean("alert_all_new", true)
        set(v) = write(read().put("alert_all_new", v))

    var alertWatchedMovement: Boolean
        get() = read().optBoolean("alert_watched", true)
        set(v) = write(read().put("alert_watched", v))
}

/** Per-network storage: known devices, activity log, user overrides, presence history. */
class Store(ctx: Context, networkKey: String) {
    private val dir = File(ctx.filesDir, "nets").also { it.mkdirs() }
    private val safeKey = Regex("[^A-Za-z0-9_.-]").replace(networkKey, "_")
    private val devicesFile = File(dir, "$safeKey.devices.json")
    private val logFile = File(dir, "$safeKey.log.json")
    private val overridesFile = File(dir, "$safeKey.overrides.json")
    private val presenceFile = File(dir, "$safeKey.presence.json")

    fun knownDevicesJson(): String = if (devicesFile.exists()) devicesFile.readText() else "[]"

    fun saveKnownDevices(json: String) = devicesFile.writeText(json)

    fun knownCount(): Int = try {
        JSONArray(knownDevicesJson()).length()
    } catch (_: Exception) {
        0
    }

    fun overrides(): JSONObject = try {
        JSONObject(if (overridesFile.exists()) overridesFile.readText() else "{}")
    } catch (_: Exception) {
        JSONObject()
    }

    /** Sets one override field for [key]; a null [value] removes the field. */
    fun setOverride(key: String, field: String, value: Any?) {
        val all = overrides()
        val entry = all.optJSONObject(key) ?: JSONObject()
        if (value == null) entry.remove(field) else entry.put(field, value)
        all.put(key, entry)
        overridesFile.writeText(all.toString())
    }

    fun overrideFor(device: Device): DeviceOverride {
        val o = overrides().optJSONObject(identityKey(device)) ?: return DeviceOverride()
        return DeviceOverride(
            nickname = o.optStringOrNull("nickname"),
            typeOverride = o.optStringOrNull("type"),
            watched = o.optBoolean("watched"),
            known = o.optBoolean("known"),
            note = o.optStringOrNull("note"),
        )
    }

    fun log(): List<LogEntry> = try {
        JSONArray(if (logFile.exists()) logFile.readText() else "[]").mapObjects { LogEntry.parse(it) }
    } catch (_: Exception) {
        emptyList()
    }

    /** Appends events to the log, keeping only the newest 500. */
    fun appendEvents(events: List<NetEvent>, at: Long = System.currentTimeMillis()) {
        if (events.isEmpty()) return
        val existing = try {
            JSONArray(if (logFile.exists()) logFile.readText() else "[]")
        } catch (_: Exception) {
            JSONArray()
        }
        for (e in events) {
            existing.put(
                JSONObject().apply {
                    put("t", at)
                    put("type", e.type)
                    put("name", e.name)
                    put("ip", e.ip)
                    put("detail", e.detail)
                },
            )
        }
        val trimmed = JSONArray()
        for (i in maxOf(0, existing.length() - 500) until existing.length()) trimmed.put(existing.get(i))
        logFile.writeText(trimmed.toString())
    }

    fun clearLog() = logFile.writeText("[]")

    /** Records that [onlineIds] were online at [at]; presence older than 26 hours is dropped. */
    fun recordPresence(onlineIds: List<String>, at: Long = System.currentTimeMillis()) {
        val all = try {
            JSONObject(if (presenceFile.exists()) presenceFile.readText() else "{}")
        } catch (_: Exception) {
            JSONObject()
        }
        val cutoff = at - 93_600_000L
        for (id in onlineIds) {
            val times = all.optJSONArray(id) ?: JSONArray()
            times.put(at)
            val kept = JSONArray()
            for (i in 0 until times.length()) {
                val t = times.optLong(i)
                if (t >= cutoff) kept.put(t)
            }
            all.put(id, kept)
        }
        presenceFile.writeText(all.toString())
    }

    fun presenceFor(id: String): List<Long> = try {
        val text = if (presenceFile.exists()) presenceFile.readText() else "{}"
        val times = JSONObject(text).optJSONArray(id)
        if (times == null) emptyList() else (0 until times.length()).map { times.optLong(it) }
    } catch (_: Exception) {
        emptyList()
    }

    companion object {
        fun identityKey(d: Device): String =
            keyOf(d.mac, d.ssdpUuid, d.mdnsName, d.netbiosName, d.dnsName, d.ip)

        /** Stable id for a device: the best identifier it announced, falling back to its IP. */
        fun keyOf(mac: String?, uuid: String?, mdns: String?, netbios: String?, dns: String?, ip: String): String =
            mac?.let { "mac:" + it.lowercase(Locale.ROOT) }
                ?: uuid?.let { "upnp:$it" }
                ?: mdns?.let { "mdns:" + it.lowercase(Locale.ROOT) }
                ?: netbios?.let { "nb:" + it.lowercase(Locale.ROOT) }
                ?: dns?.let { "dns:" + it.lowercase(Locale.ROOT) }
                ?: "ip:$ip"

        fun networkKey(lan: LanInfo): String = (lan.gateway ?: "nogw") + "_" + lan.subnetLabel
    }
}
