package com.lajara.navlab.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val PurpleDark = Color(0xFF5B3E96)
val PurpleAccent = Color(0xFF7B52C7)
val PurpleLight = Color(0xFFE8DFF5)
val SurfaceGray = Color(0xFFF2F0F7)
val ErrorCoral = Color(0xFFE85D5D)
val ErrorContainerLight = Color(0xFFFBE4E4)

val GradientHome = Brush.verticalGradient(
    colorStops = arrayOf(
        0f to Color(0xFF5B3E96),
        0.55f to Color(0xFF9B7FD4),
        1f to Color(0xFFF5F0FA)
    )
)

val GradientHeader = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF5B3E96),
        Color(0xFF9B6FD1)
    )
)

val GradientLogin = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF6A3FA0),
        Color(0xFFEDE6F7)
    )
)

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
