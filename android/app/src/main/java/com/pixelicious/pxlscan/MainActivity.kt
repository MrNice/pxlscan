package com.pixelicious.pxlscan

import android.Manifest
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sensors
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.Locale

/** Single-activity host; the whole UI is Compose. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Native.ensureLoaded()
        enableEdgeToEdge()
        setContent {
            PxLScanTheme { App() }
        }
    }
}

object BuildConfigLite {
    const val VERSION = "0.2.2"
}

private enum class Tab(val label: String, val icon: ImageVector) {
    Devices("Devices", Icons.Filled.Wifi),
    Monitor("Monitor", Icons.Filled.Notifications),
    Settings("Settings", Icons.Filled.Settings),
}

@Composable
fun App(vm: ScanViewModel = viewModel()) {
    var tab by remember { mutableStateOf(Tab.Devices) }
    var detailId by remember { mutableStateOf<String?>(null) }
    val notifPerm = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { }

    // First launch: show the network, start a scan, and ask for the notification permission.
    LaunchedEffect(Unit) {
        vm.refreshNetwork()
        vm.startScan()
        if (Build.VERSION.SDK_INT >= 33 &&
            vm.getApplication<android.app.Application>().checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != 0
        ) {
            notifPerm.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    val id = detailId
    // System back (gesture or button) closes the detail screen instead of leaving the app. With
    // targetSdk 36, predictive back is on by default and only a registered handler is honoured.
    BackHandler(enabled = id != null) { detailId = null }
    if (id != null) {
        DeviceDetailScreen(vm, id, onBack = { detailId = null })
        return
    }

    Scaffold(
        containerColor = Ink.Bg,
        bottomBar = {
            NavigationBar(containerColor = Ink.Surface, tonalElevation = 0.dp) {
                Tab.entries.forEach { t ->
                    NavigationBarItem(
                        selected = tab == t,
                        onClick = { tab = t },
                        icon = { Icon(t.icon, t.label) },
                        label = { Text(t.label, fontSize = 11.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Ink.Red,
                            selectedTextColor = Ink.Red,
                            indicatorColor = Ink.RedDim,
                            unselectedIconColor = Ink.TextDim,
                            unselectedTextColor = Ink.TextDim,
                        ),
                    )
                }
            }
        },
    ) { pad ->
        Box(Modifier.padding(pad).fillMaxSize().background(Ink.Bg)) {
            when (tab) {
                Tab.Devices -> DevicesScreen(vm, onOpen = { detailId = it })
                Tab.Monitor -> MonitorScreen(vm)
                Tab.Settings -> SettingsScreen(vm)
            }
        }
    }
}

@Composable
private fun DevicesScreen(vm: ScanViewModel, onOpen: (String) -> Unit) {
    val state = vm.state
    var radar by rememberSaveable { mutableStateOf(false) }
    var query by rememberSaveable { mutableStateOf("") }

    val list = remember(state.rows, query) {
        if (query.isBlank()) {
            state.rows
        } else {
            val q = query.lowercase(Locale.ROOT)
            state.rows.filter { r ->
                r.displayName.lowercase(Locale.ROOT).contains(q) ||
                    r.device.ip.contains(q) ||
                    r.device.vendor?.lowercase(Locale.ROOT)?.contains(q) == true ||
                    Kinds.label(r.kind).lowercase(Locale.ROOT).contains(q)
            }
        }
    }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 8.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("PxLScan", color = Ink.Text, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    val lan = state.lan
                    val subtitle = if (lan != null) {
                        "${lan.subnetLabel} · ${state.rows.size} online" + if (lan.vpnActive) " · VPN on" else ""
                    } else {
                        state.error ?: "No network"
                    }
                    Text(subtitle, color = Ink.TextDim, fontSize = 13.sp, fontFamily = Mono)
                }
                IconButton(onClick = { radar = !radar }) {
                    Icon(
                        if (radar) Icons.Filled.ViewList else Icons.Filled.Sensors,
                        contentDescription = "toggle view",
                        tint = Ink.TextDim,
                    )
                }
                ScanButton(state.running, state.progress) {
                    if (state.running) vm.stopScan() else vm.startScan()
                }
            }
            if (state.running) {
                Spacer(Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = { state.progress.coerceIn(0f, 1f) },
                    modifier = Modifier.fillMaxWidth().height(2.dp),
                    color = Ink.Red,
                    trackColor = Ink.Hairline,
                )
            }
        }

        val error = state.error
        if (error != null && state.rows.isEmpty()) {
            EmptyState(error)
            return@Column
        }

        // A finished scan that found only this phone usually means Android blocked the traffic.
        if (!state.running && state.lastScanAt > 0 && state.rows.none { !it.device.isSelf }) {
            ScanProblemCard(state)
        }

        if (radar) {
            RadarView(list, state.rows.firstOrNull { it.device.isSelf }, onOpen)
        } else {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 0.dp),
                placeholder = { Text("Search name, IP, maker…", color = Ink.TextFaint) },
                leadingIcon = { Icon(Icons.Filled.Search, null, tint = Ink.TextDim) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                singleLine = true,
                colors = fieldColors(),
            )
            Spacer(Modifier.height(4.dp))
            LazyColumn(Modifier.fillMaxSize()) {
                items(list, key = { it.id }) { row ->
                    DeviceRow(row, onOpen)
                    HorizontalDivider(thickness = 1.dp, color = Ink.Hairline)
                }
                if (list.isEmpty() && !state.running) {
                    item {
                        Box(Modifier.fillMaxWidth().padding(40.dp), contentAlignment = Alignment.Center) {
                            Text("No devices found", color = Ink.TextDim)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DeviceRow(row: Row, onOpen: (String) -> Unit) {
    val device = row.device
    Row(
        Modifier
            .fillMaxWidth()
            .clickable { onOpen(row.id) }
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)).background(Ink.SurfaceHi),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Kinds.icon(row.kind),
                contentDescription = row.kind,
                modifier = Modifier.size(22.dp),
                tint = if (device.isGateway) Ink.Red else Ink.TextDim,
            )
        }
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    row.displayName,
                    modifier = Modifier.weight(1f, fill = false),
                    color = Ink.Text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (row.watched) {
                    Spacer(Modifier.width(6.dp))
                    Icon(Icons.Filled.Star, "watched", Modifier.size(14.dp), tint = Ink.Red)
                }
                if (device.isSelf) {
                    Spacer(Modifier.width(6.dp))
                    Tag("THIS PHONE")
                }
                if (device.isGateway) {
                    Spacer(Modifier.width(6.dp))
                    Tag("ROUTER")
                }
            }
            Text(
                device.ip + "  ·  " + (device.vendor ?: Kinds.label(row.kind)),
                color = Ink.TextDim,
                fontSize = 13.sp,
                fontFamily = Mono,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Spacer(Modifier.width(8.dp))
        device.rttMs?.let { rtt ->
            Text(
                if (rtt < 1f) "<1ms" else "${rtt.toInt()}ms",
                color = Ink.TextFaint,
                fontSize = 12.sp,
                fontFamily = Mono,
            )
        }
        Spacer(Modifier.width(8.dp))
        Box(Modifier.size(8.dp).clip(CircleShape).background(Ink.Online))
    }
}

@Composable
fun Tag(text: String) {
    Box(
        Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(Ink.RedDim)
            .padding(horizontal = 5.dp, vertical = 2.dp),
    ) {
        Text(text, color = Ink.Red, fontSize = 9.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun ScanButton(running: Boolean, progress: Float, onClick: () -> Unit) {
    Box(
        Modifier
            .size(48.dp)
            .clip(CircleShape)
            .background(if (running) Ink.SurfaceHi else Ink.Red)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        if (running) {
            CircularProgressIndicator(Modifier.size(24.dp), color = Ink.Red, strokeWidth = 2.dp)
            Icon(Icons.Filled.Stop, "stop", Modifier.size(16.dp), tint = Ink.Text)
        } else {
            Icon(Icons.Filled.Refresh, "scan", tint = Ink.Text)
        }
    }
}

@Composable
private fun EmptyState(msg: String) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(msg, color = Ink.TextDim)
        }
    }
}

/** Text-field colors shared by the search box and the rename dialog. */
@Composable
fun fieldColors(): TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedTextColor = Ink.Text,
    unfocusedTextColor = Ink.Text,
    focusedContainerColor = Ink.Surface,
    unfocusedContainerColor = Ink.Surface,
    cursorColor = Ink.Red,
    focusedBorderColor = Ink.Red,
    unfocusedBorderColor = Ink.Hairline,
)

private const val PERMISSION_MSG =
    "Android is blocking this app's local-network traffic. Open App info → Permissions and allow Nearby devices / local network."

/** Shown when a finished scan saw only this phone: explains the likely cause and offers help. */
@Composable
private fun ScanProblemCard(s: ScanState) {
    val context = LocalContext.current
    val diag = s.diag
    val message = when {
        diag.tcpDenied > 0 -> PERMISSION_MSG
        diag.udpErrors > 0 && diag.errors.any { "ermission" in it || "not permitted" in it } -> PERMISSION_MSG
        diag.lanLooksBlocked ->
            "Nothing on the LAN answered at all — every connection timed out. That's what Android does when " +
                "local-network access is blocked (packets are dropped silently). Check App info → Permissions → " +
                "Nearby devices, and any VPN's \"allow LAN\" setting."
        else -> "Only this phone answered. Pull diagnostics below and send them to Claude."
    }
    Column(
        Modifier
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Ink.RedDim)
            .padding(14.dp),
    ) {
        Text("Scan found only this phone", color = Ink.Text, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(4.dp))
        Text(message, color = Ink.Text, fontSize = 13.sp)
        Spacer(Modifier.height(8.dp))
        s.lan?.let { lan ->
            Text(
                lan.transport + " " + lan.ip + "/" + lan.prefixLen + " via " + lan.gateway +
                    " · dns " + lan.dnsServers.joinToString() +
                    (if (lan.vpnActive) " · VPN up" else "") +
                    (if (s.boundToLan) " · bound" else " · not bound"),
                color = Ink.TextDim,
                fontSize = 11.sp,
                fontFamily = Mono,
            )
        }
        Text(
            "local-network permission: " + NetInfo.localNetworkPermission(context),
            color = Ink.TextDim,
            fontSize = 11.sp,
            fontFamily = Mono,
        )
        diag.lines().forEach { Text(it, color = Ink.TextDim, fontSize = 11.sp, fontFamily = Mono) }
        Row {
            TextButton(onClick = {
                runCatching {
                    context.startActivity(
                        Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.fromParts("package", context.packageName, null)),
                    )
                }
            }) { Text("App info", color = Ink.Red) }
            TextButton(onClick = {
                val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                cm.setPrimaryClip(ClipData.newPlainText("pxlscan diag", diagText(s, context)))
            }) { Text("Copy diagnostics", color = Ink.Red) }
        }
    }
}

/** Plain-text diagnostics for pasting into a bug report. */
fun diagText(s: ScanState, ctx: Context): String = buildString {
    append("PxLScan ${BuildConfigLite.VERSION} on Android ${Build.VERSION.RELEASE} (API ${Build.VERSION.SDK_INT}) ${Build.MODEL}\n")
    val lan = s.lan
    if (lan != null) {
        append("net: ${lan.transport} ${lan.ip}/${lan.prefixLen} gw=${lan.gateway} dns=${lan.dnsServers} " +
            "if=${lan.interfaceName} vpn=${lan.vpnActive} bound=${s.boundToLan}\n")
    } else {
        append("net: none\n")
    }
    append("local-network permission: ${NetInfo.localNetworkPermission(ctx)}\n")
    append("devices: ${s.rows.size}, scan ${s.elapsedMs} ms\n")
    s.diag.lines().forEach { append(it).append('\n') }
}
