package com.pixelicious.pxlscan

import android.app.Application
import android.os.Build
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/** A scanned device joined with the user's overrides for it. */
data class Row(val device: Device, val override: DeviceOverride) {
    val id: String get() = device.ip

    val displayName: String
        get() = override.nickname
            ?: device.name.ifEmpty { device.vendor?.let { "$it device" } ?: "Device ${device.ip}" }

    val kind: String get() = override.typeOverride ?: device.kind
    val watched: Boolean get() = override.watched
    val known: Boolean get() = override.known
}

data class ScanState(
    val running: Boolean = false,
    val phase: String = "idle",
    val progress: Float = 0f,
    val rows: List<Row> = emptyList(),
    val lan: LanInfo? = null,
    val error: String? = null,
    val elapsedMs: Long = 0L,
    val lastScanAt: Long = 0L,
    val diag: ScanDiag = ScanDiag(),
    val boundToLan: Boolean = false,
)

class ScanViewModel(app: Application) : AndroidViewModel(app) {
    var state by mutableStateOf(ScanState())
        private set

    private var handle = -1L
    private var pollJob: Job? = null
    private var store: Store? = null

    /** Re-reads the network without scanning (shows the subnet or "not on Wi-Fi"). */
    fun refreshNetwork() {
        val lan = NetInfo.current(getApplication())
        state = state.copy(lan = lan, error = if (lan == null) "Not on a Wi-Fi network" else null)
        if (lan != null) store = Store(getApplication(), Store.networkKey(lan))
    }

    fun startScan() {
        val lan = NetInfo.current(getApplication())
        if (lan == null) {
            state = state.copy(running = false, lan = null, error = "Join a Wi-Fi network to scan")
            return
        }
        store = Store(getApplication(), Store.networkKey(lan))
        if (!Native.ensureLoaded()) {
            state = state.copy(lan = lan, error = "Scan engine failed to load")
            return
        }
        stopPolling()
        val bound = NetInfo.bindTo(getApplication(), lan)
        handle = Native.start(
            ScanConfig(lan.ip, lan.prefixLen, lan.gateway, lan.dnsServers, 700L, Build.MODEL),
        )
        if (handle < 0) {
            NetInfo.unbind(getApplication())
            state = state.copy(lan = lan, error = "Could not start scan")
            return
        }
        state = state.copy(running = true, phase = "starting", progress = 0f, lan = lan, error = null, boundToLan = bound)
        pollJob = viewModelScope.launch {
            while (true) {
                val snap = Native.snapshot(handle)
                state = state.copy(
                    running = !snap.done,
                    phase = snap.phase,
                    progress = snap.progress,
                    rows = merge(snap.devices),
                    elapsedMs = snap.elapsedMs,
                    diag = snap.diag,
                )
                if (snap.done) {
                    NetInfo.unbind(getApplication())
                    state = state.copy(lastScanAt = System.currentTimeMillis())
                    return@launch
                }
                delay(250)
            }
        }
    }

    fun stopScan() {
        stopPolling()
        NetInfo.unbind(getApplication())
        if (handle >= 0) Native.stop(handle)
        handle = -1L
        state = state.copy(running = false)
    }

    private fun stopPolling() {
        pollJob?.cancel()
        pollJob = null
    }

    /** Attaches overrides to scanned devices, sorted by IP. */
    private fun merge(devices: List<Device>): List<Row> {
        val s = store
        return if (s != null) {
            devices.map { Row(it, s.overrideFor(it)) }.sortedBy { it.device.ipSortKey }
        } else {
            devices.map { Row(it, DeviceOverride()) }
        }
    }

    /** Writes one override field (null removes it) and refreshes the matching row. */
    fun setOverride(row: Row, field: String, value: Any?) {
        val s = store ?: return
        val key = Store.identityKey(row.device)
        s.setOverride(key, field, value)
        state = state.copy(
            rows = state.rows.map {
                if (Store.identityKey(it.device) == key) it.copy(override = s.overrideFor(it.device)) else it
            },
        )
    }

    fun rowFor(id: String): Row? = state.rows.firstOrNull { it.id == id }

    fun currentStore(): Store? = store

    override fun onCleared() {
        stopScan()
        super.onCleared()
    }
}
