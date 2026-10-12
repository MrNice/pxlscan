package com.pixelicious.pxlscan

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobInfo
import android.app.job.JobParameters
import android.app.job.JobScheduler
import android.app.job.JobService
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import org.json.JSONArray
import org.json.JSONObject
import kotlin.concurrent.thread

/** Background presence tracking: a periodic job that rescans the LAN and raises notifications. */
object Monitor {
    const val JOB_ID = 4711
    private const val CH_NEW = "new_devices"
    private const val CH_WATCH = "watched_movement"

    /** (Re)schedules the periodic job according to [Settings]; cancels it when monitoring is off. */
    fun schedule(ctx: Context) {
        val settings = Settings(ctx)
        val js = ctx.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        js.cancel(JOB_ID)
        if (settings.monitorEnabled) {
            val everyMs = settings.intervalMinutes.coerceIn(15, 720) * 60_000L
            js.schedule(
                JobInfo.Builder(JOB_ID, ComponentName(ctx, MonitorJobService::class.java))
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                    .setPersisted(true)
                    .setPeriodic(everyMs)
                    .build(),
            )
        }
    }

    fun ensureChannels(ctx: Context) {
        val nm = ctx.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(
            NotificationChannel(CH_NEW, "New devices", NotificationManager.IMPORTANCE_HIGH).apply {
                description = "A device you haven't seen before joined the network"
            },
        )
        nm.createNotificationChannel(
            NotificationChannel(CH_WATCH, "Watched devices", NotificationManager.IMPORTANCE_DEFAULT).apply {
                description = "A starred device joined or left the network"
            },
        )
    }

    /** One scan + diff + notify pass. Returns the events it produced. */
    fun runOnce(ctx: Context): List<NetEvent> {
        val lan = NetInfo.current(ctx) ?: return emptyList()
        if (!Native.ensureLoaded()) return emptyList()
        val store = Store(ctx, Store.networkKey(lan))
        val settings = Settings(ctx)
        val config = ScanConfig(lan.ip, lan.prefixLen, lan.gateway, lan.dnsServers, 700L, Build.MODEL)

        NetInfo.bindTo(ctx, lan)
        try {
            val handle = Native.start(config)
            if (handle < 0) return emptyList()
            var snap = Native.snapshot(handle)
            val deadline = System.currentTimeMillis() + 20_000L
            while (!snap.done && System.currentTimeMillis() < deadline) {
                Thread.sleep(300)
                snap = Native.snapshot(handle)
            }
            Native.stop(handle)
            NetInfo.unbind(ctx)
            // A blocked LAN would make every known device look like it left: report nothing.
            if (snap.diag.lanLooksBlocked) return emptyList()

            val input = JSONObject().apply {
                put("known", JSONArray(store.knownDevicesJson()))
                put("scan", JSONArray(snap.devices.map { deviceToJson(it) }))
                put("leave_after", 2)
                put("alert_all_new", settings.alertAllNew)
            }
            val out = Native.diff(input.toString())
            store.saveKnownDevices(out.devicesJson)
            store.appendEvents(out.events)
            store.recordPresence(out.onlineIds)

            ensureChannels(ctx)
            val nm = NotificationManagerCompat.from(ctx)
            if (Build.VERSION.SDK_INT >= 33 &&
                ctx.checkSelfPermission("android.permission.POST_NOTIFICATIONS") != PackageManager.PERMISSION_GRANTED
            ) {
                return out.events
            }
            for (event in out.events) {
                val (channel, title) = when {
                    event.type == "new" -> CH_NEW to "New device on your network"
                    event.type == "joined" && settings.alertWatchedMovement -> CH_WATCH to "Device joined"
                    event.type == "left" && settings.alertWatchedMovement -> CH_WATCH to "Device left"
                    else -> continue
                }
                if (event.type == "new" || isWatched(store, out.devicesJson, event.id)) {
                    val notification = NotificationCompat.Builder(ctx, channel)
                        .setSmallIcon(android.R.drawable.stat_notify_more)
                        .setContentTitle(title)
                        .setContentText("${event.name} · ${event.ip}")
                        .setColor(0xFFFF0000.toInt())
                        .setAutoCancel(true)
                        .setContentIntent(openAppIntent(ctx))
                        .build()
                    runCatching { nm.notify(event.id.hashCode(), notification) }
                }
            }
            return out.events
        } finally {
            NetInfo.unbind(ctx)
        }
    }

    /** Whether the user starred the device with [id] in the known-device list. */
    private fun isWatched(store: Store, devicesJson: String, id: String): Boolean {
        val devices = runCatching { JSONArray(devicesJson) }.getOrNull() ?: return false
        for (i in 0 until devices.length()) {
            val d = devices.optJSONObject(i) ?: continue
            if (d.optString("id") == id) {
                val key = Store.keyOf(
                    d.optStringOrNull("mac"),
                    d.optStringOrNull("ssdp_uuid"),
                    d.optStringOrNull("mdns_name"),
                    d.optStringOrNull("netbios_name"),
                    d.optStringOrNull("dns_name"),
                    d.optString("last_ip"),
                )
                return store.overrides().optJSONObject(key)?.optBoolean("watched") == true
            }
        }
        return false
    }

    private fun openAppIntent(ctx: Context): PendingIntent =
        PendingIntent.getActivity(
            ctx,
            0,
            Intent(ctx, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),
            PendingIntent.FLAG_IMMUTABLE,
        )

    /** The host as the core's `monitorDiff` expects it. */
    private fun deviceToJson(d: Device): JSONObject = JSONObject().apply {
        put("ip", d.ip)
        put("name", d.name)
        put("kind", d.kind)
        put("seen_by", JSONArray(d.seenBy))
        d.mac?.let { put("mac", it) }
        d.mdnsName?.let { put("mdns_name", it) }
        d.netbiosName?.let { put("netbios_name", it) }
        d.dnsName?.let { put("dns_name", it) }
        d.ssdpUuid?.let { put("ssdp_uuid", it) }
        put("is_self", d.isSelf)
        put("is_gateway", d.isGateway)
        put("vendor", d.vendor ?: JSONObject.NULL)
        put("open_ports", JSONArray(d.openPorts))
        put("services", JSONArray(d.services))
    }
}

/** Runs [Monitor.runOnce] on a worker thread for each periodic job tick. */
class MonitorJobService : JobService() {
    @Volatile private var worker: Thread? = null

    override fun onStartJob(params: JobParameters): Boolean {
        worker = thread {
            runCatching { Monitor.runOnce(applicationContext) }
            jobFinished(params, false)
        }
        return true
    }

    override fun onStopJob(params: JobParameters): Boolean {
        worker?.interrupt()
        return true
    }
}

/** Re-arms the monitor job after a reboot. */
class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) Monitor.schedule(context)
    }
}
