package com.pixelicious.pxlscan

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.FiberNew
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private val fmt = SimpleDateFormat("MMM d, HH:mm", Locale.getDefault())

/** Monitor tab: summary tiles plus the activity log of joins, leaves and new devices. */
@Composable
fun MonitorScreen(vm: ScanViewModel) {
    val context = LocalContext.current
    val settings = remember { Settings(context) }
    val store = vm.currentStore()
    var refresh by remember { mutableIntStateOf(0) }
    val log = remember(refresh, store) { store?.log()?.reversed() ?: emptyList() }
    val rows = vm.state.rows

    LazyColumn(Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        item {
            Text(
                "Monitor",
                modifier = Modifier.padding(top = 14.dp, bottom = 4.dp),
                color = Ink.Text,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                if (settings.monitorEnabled) {
                    "Checking every ${settings.intervalMinutes} min on Wi-Fi"
                } else {
                    "Monitoring is off — turn it on in Settings to get alerts when devices join or leave."
                },
                modifier = Modifier.padding(bottom = 8.dp),
                color = Ink.TextDim,
                fontSize = 13.sp,
            )
        }
        item {
            val newToday = log.count { it.type == "new" && isToday(it.t) }
            val eventsToday = log.count { isToday(it.t) }
            Row(
                Modifier.fillMaxWidth().padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                StatTile("Online now", rows.size.toString(), Modifier.weight(1f))
                StatTile("Known", rows.count { it.known }.toString(), Modifier.weight(1f))
                StatTile("New today", newToday.toString(), Modifier.weight(1f), highlight = newToday > 0)
                StatTile("Events today", eventsToday.toString(), Modifier.weight(1f))
            }
        }
        item {
            Row(
                Modifier.fillMaxWidth().padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    "Activity log",
                    Modifier.weight(1f),
                    color = Ink.TextFaint,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                )
                if (log.isNotEmpty()) {
                    TextButton(onClick = {
                        store?.clearLog()
                        refresh++
                    }) { Text("Clear", color = Ink.TextDim, fontSize = 12.sp) }
                }
            }
        }
        if (log.isEmpty()) {
            item {
                Box(Modifier.fillMaxWidth().padding(30.dp), contentAlignment = Alignment.Center) {
                    Text("No events yet", color = Ink.TextDim)
                }
            }
        } else {
            items(log) { LogRow(it) }
        }
        item { Spacer(Modifier.height(24.dp)) }
    }
}

@Composable
private fun StatTile(label: String, value: String, modifier: Modifier = Modifier, highlight: Boolean = false) {
    Column(
        modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Ink.Surface)
            .padding(12.dp),
    ) {
        Text(
            value,
            color = if (highlight) Ink.Red else Ink.Text,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(label, color = Ink.TextDim, fontSize = 11.sp)
    }
}

@Composable
fun LogRow(entry: LogEntry) {
    val (icon, tint) = when (entry.type) {
        "joined" -> Icons.Filled.Login to Ink.Text
        "ip_changed" -> Icons.Filled.SwapHoriz to Ink.TextDim
        "new" -> Icons.Filled.FiberNew to Ink.Red
        "left" -> Icons.Filled.Logout to Ink.TextDim
        else -> Icons.Filled.Circle to Ink.TextDim
    }
    Column {
        Row(
            Modifier.fillMaxWidth().padding(vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(icon, entry.type, Modifier.size(20.dp), tint = tint)
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(
                    entry.name.ifEmpty { entry.ip },
                    color = Ink.Text,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                )
                Text(
                    entry.detail + if (entry.ip.isNotEmpty()) "  ·  ${entry.ip}" else "",
                    color = Ink.TextDim,
                    fontSize = 12.sp,
                    fontFamily = Mono,
                )
            }
            Text(timeAgo(entry.t), color = Ink.TextFaint, fontSize = 11.sp)
        }
        HorizontalDivider(thickness = 1.dp, color = Ink.Hairline)
    }
}

private fun isToday(t: Long): Boolean {
    val now = Calendar.getInstance()
    val then = Calendar.getInstance().apply { timeInMillis = t }
    return now.get(Calendar.YEAR) == then.get(Calendar.YEAR) && now.get(Calendar.DAY_OF_YEAR) == then.get(Calendar.DAY_OF_YEAR)
}

/** "now", "5m", "3h", or a date for anything older than a day. */
private fun timeAgo(t: Long): String {
    val d = System.currentTimeMillis() - t
    return when {
        d < 60_000L -> "now"
        d < 3_600_000L -> "${d / 60_000L}m"
        d < 86_400_000L -> "${d / 3_600_000L}h"
        else -> fmt.format(Date(t))
    }
}
