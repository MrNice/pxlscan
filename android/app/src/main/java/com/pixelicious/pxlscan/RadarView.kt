package com.pixelicious.pxlscan

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.ln
import kotlin.math.min
import kotlin.math.sin

/** A device dot as last drawn, kept so taps can be matched to the nearest device. */
private class Blip(val row: Row, val at: Offset)

/**
 * Radar view: rings and a rotating sweep, one dot per device. A device's angle comes from its
 * identity hash (stable between scans); its distance from the centre grows with latency.
 */
@Composable
fun RadarView(rows: List<Row>, self: Row?, onOpen: (String) -> Unit) {
    val sweep by rememberInfiniteTransition(label = "sweep").animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(3600, easing = LinearEasing), RepeatMode.Restart),
        label = "angle",
    )
    val others = remember(rows) {
        rows.filter { !it.device.isSelf }.map { r ->
            val h = Store.identityKey(r.device).hashCode().toDouble()
            r to ((h % 360.0) + 360.0) % 360.0
        }
    }
    // Filled in by the draw pass; read by the tap handler.
    val blips = remember { ArrayList<Blip>() }

    Box(
        Modifier
            .fillMaxSize()
            .background(Ink.Bg)
            .pointerInput(Unit) {
                detectTapGestures { tap ->
                    val nearest = blips.minByOrNull { hypot(tap.x - it.at.x, tap.y - it.at.y) }
                    if (nearest != null && hypot(tap.x - nearest.at.x, tap.y - nearest.at.y) < 60f) {
                        onOpen(nearest.row.id)
                    }
                }
            },
    ) {
        Canvas(Modifier.fillMaxSize()) {
            val cx = size.width / 2
            val cy = size.height / 2
            val maxR = min(cx, cy) * 0.86f
            val center = Offset(cx, cy)

            listOf(0.25f, 0.5f, 0.75f, 1f).forEach {
                drawCircle(Ink.Hairline, maxR * it, center, style = Stroke(1.5f))
            }
            drawLine(Ink.Hairline, Offset(cx - maxR, cy), Offset(cx + maxR, cy), strokeWidth = 1.5f)
            drawLine(Ink.Hairline, Offset(cx, cy - maxR), Offset(cx, cy + maxR), strokeWidth = 1.5f)

            rotate(sweep, center) {
                drawArc(
                    brush = Brush.sweepGradient(
                        0f to Color.Transparent,
                        0.12f to Ink.Red.copy(alpha = 0.28f),
                        0.16f to Color.Transparent,
                        center = center,
                    ),
                    startAngle = -90f,
                    sweepAngle = 60f,
                    useCenter = true,
                    topLeft = Offset(cx - maxR, cy - maxR),
                    size = Size(maxR * 2, maxR * 2),
                )
                drawLine(Ink.Red, center, Offset(cx, cy - maxR), strokeWidth = 2.5f)
            }
            drawCircle(Ink.Red, 7f, center)

            blips.clear()
            for ((row, angleDeg) in others) {
                val dist = (ln((row.device.rttMs ?: 120f) + 1f) / ln(201f)).coerceIn(0.12f, 1f) * maxR
                val a = angleDeg / 180.0 * PI
                val at = Offset((cos(a) * dist).toFloat() + cx, (sin(a) * dist).toFloat() + cy)
                val gateway = row.device.isGateway
                drawCircle(if (gateway) Ink.Red else Ink.Text, if (gateway) 6f else 5f, at)
                if (row.watched) drawCircle(Ink.Red, 9f, at, style = Stroke(1.5f))
                blips.add(Blip(row, at))
            }
        }
        Text(
            "${others.size} devices · distance ≈ latency",
            modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp),
            color = Ink.TextDim,
            fontSize = 12.sp,
        )
        if (self != null) {
            Text(
                "YOU",
                modifier = Modifier.align(Alignment.Center).padding(top = 22.dp),
                color = Ink.Red,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}
