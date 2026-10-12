package com.pixelicious.pxlscan

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceDetailScreen(vm: ScanViewModel, id: String, onBack: () -> Unit) {
    val row = vm.rowFor(id)
    if (row == null) {
        // The device vanished from the list (new scan): leave the detail screen.
        LaunchedEffect(Unit) { onBack() }
        return
    }
    val device = row.device
    val context = LocalContext.current
    var editing by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Ink.Bg,
        topBar = {
            TopAppBar(
                title = { Text(row.displayName, color = Ink.Text, maxLines = 1) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "back", tint = Ink.Text)
                    }
                },
                actions = {
                    IconButton(onClick = { vm.setOverride(row, "watched", !row.watched) }) {
                        Icon(
                            if (row.watched) Icons.Filled.Star else Icons.Filled.StarBorder,
                            "watch",
                            tint = if (row.watched) Ink.Red else Ink.TextDim,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Ink.Surface),
            )
        },
    ) { pad ->
        Column(Modifier.padding(pad).fillMaxSize().verticalScroll(rememberScrollState())) {
            Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                Box(
                    Modifier.size(56.dp).clip(RoundedCornerShape(12.dp)).background(Ink.SurfaceHi),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Kinds.icon(row.kind),
                        row.kind,
                        Modifier.size(30.dp),
                        tint = if (device.isGateway) Ink.Red else Ink.TextDim,
                    )
                }
                Spacer(Modifier.width(14.dp))
                Column(Modifier.weight(1f)) {
                    Text(
                        device.ip,
                        color = Ink.Text,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Mono,
                    )
                    Text(
                        Kinds.label(row.kind) + (device.vendor?.let { " · $it" } ?: ""),
                        color = Ink.TextDim,
                        fontSize = 14.sp,
                    )
                }
                IconButton(onClick = { editing = true }) {
                    Icon(Icons.Filled.Edit, "rename", tint = Ink.TextDim)
                }
            }

            PingCard(device.ip)

            Row(
                Modifier.fillMaxWidth().padding(horizontal = 12.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                if (device.openPorts.any { it == 80 || it == 443 || it == 8080 }) {
                    ToolButton("Open web UI", Icons.Filled.Language, Modifier.weight(1f)) { openWebUi(context, device) }
                }
                ToolButton("Copy IP", Icons.Filled.ContentCopy, Modifier.weight(1f)) {
                    val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    cm.setPrimaryClip(ClipData.newPlainText("ip", device.ip))
                }
                ToolButton(
                    if (row.known) "Known ✓" else "Mark known",
                    Icons.Filled.VerifiedUser,
                    Modifier.weight(1f),
                ) { vm.setOverride(row, "known", !row.known) }
            }

            SectionTitle("Identity")
            Fact("Display name", row.displayName)
            device.mdnsName?.let { Fact("mDNS / Bonjour", it) }
            device.netbiosName?.let { Fact("NetBIOS name", it) }
            device.dnsName?.let { Fact("DNS name", it) }
            device.model?.let { Fact("Model", it) }
            device.vendor?.let { Fact("Maker", it) }
            device.mac?.let { Fact("MAC (announced)", it) }
            device.ssdpUuid?.let { Fact("UPnP UUID", it) }
            device.httpServer?.let { Fact("Web server", it) }
            device.httpTitle?.let { Fact("Web page title", it) }

            if (device.openPorts.isNotEmpty()) {
                SectionTitle("Open ports")
                Text(
                    device.openPorts.joinToString("  ") { portLabel(it) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    color = Ink.Text,
                    fontSize = 14.sp,
                    fontFamily = Mono,
                )
            }

            val services = device.services.filter { !it.startsWith("upnp:") }
            if (services.isNotEmpty()) {
                SectionTitle("Services")
                Text(
                    services.joinToString("\n"),
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                    color = Ink.TextDim,
                    fontSize = 13.sp,
                    fontFamily = Mono,
                )
            }

            SectionTitle("Signal")
            Fact("Discovered via", device.seenBy.filter { it != "self" }.joinToString(", ").ifEmpty { "—" })
            device.rttMs?.let { Fact("Latency", if (it < 1f) "<1 ms" else "${it.roundToInt()} ms") }
            device.ttl?.let { Fact("TTL", "$it  (${ttlHint(it)})") }
            Spacer(Modifier.height(40.dp))
        }
    }

    if (editing) {
        RenameDialog(
            name = row.displayName,
            kind = row.kind,
            onDismiss = { editing = false },
            onSave = { name, kind ->
                vm.setOverride(row, "nickname", name.ifBlank { null })
                vm.setOverride(row, "type", if (kind == device.kind) null else kind)
                editing = false
            },
        )
    }
}

/** Opens the device's web page: https if 443 is open, else http on 80/8080. */
private fun openWebUi(context: Context, device: Device) {
    val port = device.openPorts.firstOrNull { it == 443 }
        ?: device.openPorts.first { it == 80 || it == 8080 }
    val scheme = if (port == 443) "https" else "http"
    runCatching {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("$scheme://${device.ip}:$port")))
    }
}

/** Repeated one-per-second ping with a rolling graph of the last 40 samples. */
@Composable
private fun PingCard(ip: String) {
    var samples by remember { mutableStateOf<List<Float>>(emptyList()) }
    val scope = rememberCoroutineScope()
    var running by remember { mutableStateOf(false) }

    Column(
        Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Ink.Surface)
            .padding(14.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Live ping", Modifier.weight(1f), color = Ink.Text, fontWeight = FontWeight.Medium)
            samples.lastOrNull()?.let { last ->
                Text(
                    if (last < 0f) "timeout" else "${last.roundToInt()} ms",
                    color = if (last < 0f) Ink.Red else Ink.TextDim,
                    fontFamily = Mono,
                )
            }
            Spacer(Modifier.width(8.dp))
            TextButton(onClick = {
                if (running) {
                    running = false
                } else {
                    running = true
                    scope.launch {
                        while (running && samples.size < 60) {
                            val ms = withContextIO { Native.pingMs(ip, 1000L) }
                            samples = (samples + ms).takeLast(40)
                            delay(900)
                        }
                        running = false
                    }
                }
            }) { Text(if (running) "Stop" else "Start", color = Ink.Red) }
        }
        Spacer(Modifier.height(8.dp))
        Sparkline(samples)
    }
}

/** Latency line; negative samples are timeouts and break the line. */
@Composable
private fun Sparkline(samples: List<Float>) {
    Canvas(Modifier.fillMaxWidth().height(48.dp)) {
        if (samples.isEmpty()) return@Canvas
        val peak = samples.filter { it >= 0f }.maxOrNull()?.coerceAtLeast(1f) ?: 1f
        val dx = size.width / (samples.size.coerceAtLeast(2) - 1)
        var prev: Offset? = null
        samples.forEachIndexed { i, v ->
            if (v < 0f) {
                prev = null
            } else {
                val pt = Offset(i * dx, size.height - (v / peak) * size.height * 0.9f - 2f)
                prev?.let { drawLine(Ink.Red, it, pt, strokeWidth = 2f) }
                drawCircle(Ink.Red, radius = 2.5f, center = pt)
                prev = pt
            }
        }
    }
}

@Composable
private fun SectionTitle(text: String) {
    Text(
        text.uppercase(),
        modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 4.dp),
        color = Ink.TextFaint,
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun Fact(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp)) {
        Text(label, Modifier.width(130.dp), color = Ink.TextDim, fontSize = 14.sp)
        Text(
            value,
            color = Ink.Text,
            fontSize = 14.sp,
            fontFamily = if ("MAC" in label || "UUID" in label) Mono else FontFamily.Default,
        )
    }
}

@Composable
private fun ToolButton(label: String, icon: ImageVector, modifier: Modifier, onClick: () -> Unit) {
    Column(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Ink.Surface)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(icon, label, Modifier.size(20.dp), tint = Ink.Red)
        Spacer(Modifier.height(4.dp))
        Text(label, color = Ink.Text, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Clip)
    }
}

/** Dialog to set a nickname and override the detected device type. */
@Composable
private fun RenameDialog(name: String, kind: String, onDismiss: () -> Unit, onSave: (String, String) -> Unit) {
    var nick by remember { mutableStateOf(name) }
    var type by remember { mutableStateOf(kind) }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = { TextButton(onClick = { onSave(nick, type) }) { Text("Save", color = Ink.Red) } },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancel", color = Ink.TextDim) } },
        title = { Text("Edit device", color = Ink.Text) },
        text = {
            Column {
                OutlinedTextField(
                    value = nick,
                    onValueChange = { nick = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Nickname") },
                    singleLine = true,
                    colors = fieldColors(),
                )
                Spacer(Modifier.height(12.dp))
                Box {
                    OutlinedTextField(
                        value = Kinds.label(type),
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth().clickable { expanded = true },
                        readOnly = true,
                        label = { Text("Type") },
                        trailingIcon = {
                            IconButton(onClick = { expanded = true }) {
                                Icon(Icons.Filled.ArrowDropDown, null, tint = Ink.TextDim)
                            }
                        },
                        colors = fieldColors(),
                    )
                    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                        Kinds.order.forEach { k ->
                            DropdownMenuItem(
                                text = { Text(Kinds.label(k), color = Ink.Text) },
                                onClick = {
                                    type = k
                                    expanded = false
                                },
                            )
                        }
                    }
                }
            }
        },
        containerColor = Ink.Surface,
    )
}

/** Friendly "port·service" label for well-known ports. */
private fun portLabel(port: Int): String = when (port) {
    22 -> "22·ssh"
    80 -> "80·http"
    139, 445 -> "$port·smb"
    443 -> "443·https"
    631 -> "631·ipp"
    1883 -> "1883·mqtt"
    3389 -> "3389·rdp"
    5000, 5001 -> "$port·nas"
    8009 -> "8009·cast"
    9100 -> "9100·print"
    32400 -> "32400·plex"
    62078 -> "62078·ios"
    else -> port.toString()
}

/** Guess the OS family from the reply TTL. */
private fun ttlHint(ttl: Int): String = when {
    ttl > 128 -> "network gear"
    ttl > 64 -> "Windows"
    ttl > 32 -> "Linux/Apple/Android"
    else -> "many hops"
}

private suspend fun <T> withContextIO(block: () -> T): T = withContext(Dispatchers.IO) { block() }
