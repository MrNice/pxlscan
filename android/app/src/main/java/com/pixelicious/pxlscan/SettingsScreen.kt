package com.pixelicious.pxlscan

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(vm: ScanViewModel) {
    val context = LocalContext.current
    val settings = remember { Settings(context) }
    var monitor by remember { mutableStateOf(settings.monitorEnabled) }
    var interval by remember { mutableIntStateOf(settings.intervalMinutes) }
    var alertNew by remember { mutableStateOf(settings.alertAllNew) }
    var alertWatched by remember { mutableStateOf(settings.alertWatchedMovement) }

    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp)) {
        Text(
            "Settings",
            modifier = Modifier.padding(vertical = 10.dp),
            color = Ink.Text,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )

        Card {
            ToggleRow("Monitor mode", "Scan in the background and alert on changes", monitor) { on ->
                monitor = on
                settings.monitorEnabled = on
                Monitor.ensureChannels(context)
                Monitor.schedule(context)
            }
            if (monitor) {
                HorizontalDivider(color = Ink.Hairline)
                Text(
                    "Check every",
                    modifier = Modifier.padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 4.dp),
                    color = Ink.TextDim,
                    fontSize = 13.sp,
                )
                Row(
                    Modifier.padding(start = 12.dp, top = 0.dp, end = 12.dp, bottom = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    listOf(15, 30, 60).forEach { minutes ->
                        Chip("$minutes min", interval == minutes, Modifier.weight(1f)) {
                            interval = minutes
                            settings.intervalMinutes = minutes
                            Monitor.schedule(context)
                        }
                    }
                }
            }
        }
        Spacer(Modifier.height(12.dp))

        Card {
            ToggleRow("Alert on any new device", "Not just untrusted ones", alertNew) {
                alertNew = it
                settings.alertAllNew = it
            }
            HorizontalDivider(color = Ink.Hairline)
            ToggleRow("Alert on watched devices", "When a starred device joins or leaves", alertWatched) {
                alertWatched = it
                settings.alertWatchedMovement = it
            }
        }
        Spacer(Modifier.height(12.dp))

        Card {
            Column(Modifier.padding(16.dp)) {
                Text("How PxLScan finds devices", color = Ink.Text, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(6.dp))
                Text(
                    "It pings the subnet, tries common service ports, and asks each device its name over " +
                        "mDNS/Bonjour, NetBIOS, SSDP/UPnP and your router's DNS — then classifies it. Android " +
                        "hides the ARP table from apps, so MAC addresses only show when a device announces one.",
                    color = Ink.TextDim,
                    fontSize = 13.sp,
                )
            }
        }
        Spacer(Modifier.height(12.dp))

        Card {
            Column(Modifier.padding(16.dp)) {
                Text("Engine", color = Ink.Text, fontWeight = FontWeight.Medium)
                Spacer(Modifier.height(4.dp))
                Text(
                    if (Native.available) "Rust core loaded ✓" else "Rust core not loaded",
                    color = if (Native.available) Ink.TextDim else Ink.Red,
                    fontSize = 13.sp,
                )
                vm.state.lan?.let { lan ->
                    Text(
                        "This device: ${lan.ip}/${lan.prefixLen} (${lan.transport}${if (lan.vpnActive) ", VPN up" else ""})",
                        color = Ink.TextDim,
                        fontSize = 13.sp,
                        fontFamily = Mono,
                    )
                    lan.gateway?.let {
                        Text("Router: $it", color = Ink.TextDim, fontSize = 13.sp, fontFamily = Mono)
                    }
                }
                Text(
                    "Last scan",
                    modifier = Modifier.padding(top = 10.dp),
                    color = Ink.Text,
                    fontWeight = FontWeight.Medium,
                )
                Text(diagText(vm.state, context), color = Ink.TextDim, fontSize = 11.sp, fontFamily = Mono)
                TextButton(onClick = {
                    val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    cm.setPrimaryClip(ClipData.newPlainText("pxlscan diag", diagText(vm.state, context)))
                }) { Text("Copy diagnostics", color = Ink.Red) }
                Text(
                    "PxLScan ${BuildConfigLite.VERSION}",
                    modifier = Modifier.padding(top = 6.dp),
                    color = Ink.TextFaint,
                    fontSize = 12.sp,
                )
            }
        }
        Spacer(Modifier.height(30.dp))
    }
}

@Composable
private fun Card(content: @Composable ColumnScope.() -> Unit) {
    Column(Modifier.fillMaxWidth().clip(RoundedCornerShape(14.dp)).background(Ink.Surface), content = content)
}

@Composable
private fun ToggleRow(title: String, subtitle: String, checked: Boolean, onChange: (Boolean) -> Unit) {
    Row(
        Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(Modifier.weight(1f)) {
            Text(title, color = Ink.Text, fontSize = 16.sp)
            Text(subtitle, color = Ink.TextDim, fontSize = 12.sp)
        }
        Switch(
            checked = checked,
            onCheckedChange = onChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Ink.Text,
                checkedTrackColor = Ink.Red,
                checkedBorderColor = Ink.Red,
                uncheckedThumbColor = Ink.TextDim,
                uncheckedTrackColor = Ink.SurfaceHi,
                uncheckedBorderColor = Ink.Hairline,
            ),
        )
    }
}

@Composable
private fun Chip(label: String, selected: Boolean, modifier: Modifier, onClick: () -> Unit) {
    Box(
        modifier
            .clip(RoundedCornerShape(10.dp))
            .background(if (selected) Ink.Red else Ink.SurfaceHi)
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            label,
            color = if (selected) Ink.Text else Ink.TextDim,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
        )
    }
}
