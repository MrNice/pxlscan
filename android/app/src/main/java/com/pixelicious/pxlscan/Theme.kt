package com.pixelicious.pxlscan

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

/** The app palette: near-black greys with a single red accent. */
object Ink {
    val Bg = Color(0xFF1A1A1A)
    val Surface = Color(0xFF242424)
    val SurfaceHi = Color(0xFF2E2E2E)
    val Hairline = Color(0xFF3A3A3A)
    val Text = Color(0xFFFFFFFF)
    val TextDim = Color(0xFFA6A6A6)
    val TextFaint = Color(0xFF6E6E6E)
    val Red = Color(0xFFFF0000)
    val RedDim = Color(0xFF3A0E0E)
    val Online = Color(0xFFFF1A1A)
    val Offline = Color(0xFF555555)
}

private val scheme = darkColorScheme(
    primary = Ink.Red,
    onPrimary = Ink.Text,
    background = Ink.Bg,
    onBackground = Ink.Text,
    surface = Ink.Surface,
    onSurface = Ink.Text,
    surfaceVariant = Ink.SurfaceHi,
    onSurfaceVariant = Ink.TextDim,
    error = Ink.Red,
    outline = Ink.Hairline,
)

val Mono = FontFamily.Monospace

private val typography = Typography(
    bodyLarge = TextStyle(fontSize = 16.sp),
    bodyMedium = TextStyle(fontSize = 14.sp),
    labelSmall = TextStyle(fontSize = 11.sp),
)

/** The app is always dark; the system theme is ignored. */
@Composable
fun PxLScanTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = scheme, typography = typography, content = content)
}
