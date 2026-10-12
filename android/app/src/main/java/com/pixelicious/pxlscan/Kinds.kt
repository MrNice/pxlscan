package com.pixelicious.pxlscan

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cast
import androidx.compose.material.icons.filled.DeviceUnknown
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Router
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material.icons.outlined.Laptop
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Smartphone
import androidx.compose.material.icons.outlined.Speaker
import androidx.compose.material.icons.outlined.Tablet
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.material.icons.outlined.Watch
import androidx.compose.ui.graphics.vector.ImageVector

/** Maps the core's `kind` strings to labels, icons and a sort order. */
object Kinds {
    /** Display order (also the order of the type picker). */
    val order = listOf(
        "router", "computer", "server", "phone", "tablet", "tv", "speaker",
        "console", "printer", "camera", "nas", "iot", "watch", "network", "unknown",
    )

    fun label(kind: String): String = when (kind) {
        "speaker" -> "Speaker"
        "camera" -> "Camera"
        "router" -> "Router"
        "server" -> "Server / Pi"
        "tablet" -> "Tablet"
        "computer" -> "Computer"
        "printer" -> "Printer"
        "tv" -> "TV / Streamer"
        "iot" -> "Smart home"
        "nas" -> "NAS / Storage"
        "phone" -> "Phone"
        "watch" -> "Watch"
        "console" -> "Game console"
        "network" -> "Network gear"
        else -> "Unknown"
    }

    fun icon(kind: String): ImageVector = when (kind) {
        "speaker" -> Icons.Outlined.Speaker
        "camera" -> Icons.Filled.Videocam
        "router" -> Icons.Filled.Router
        "server" -> Icons.Filled.Storage
        "tablet" -> Icons.Outlined.Tablet
        "computer" -> Icons.Outlined.Laptop
        "printer" -> Icons.Filled.Print
        "tv" -> Icons.Outlined.Tv
        "iot" -> Icons.Outlined.Lightbulb
        "nas" -> Icons.Filled.Storage
        "castable" -> Icons.Filled.Cast
        "phone" -> Icons.Outlined.Smartphone
        "watch" -> Icons.Outlined.Watch
        "console" -> Icons.Filled.SportsEsports
        "network" -> Icons.Filled.Router
        else -> Icons.Filled.DeviceUnknown
    }

    fun rank(kind: String): Int {
        val i = order.indexOf(kind)
        return if (i < 0) order.size else i
    }
}
