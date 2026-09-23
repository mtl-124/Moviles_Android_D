package com.lajara.tecsupfit.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val TecsupColorScheme = lightColorScheme(
    primary = VerdeTecsup,
    secondary = VerdeTecsup,
    background = Blanco,
    surface = Blanco,
    onPrimary = Blanco,
    onSecondary = Blanco,
    onBackground = NegroTexto,
    onSurface = NegroTexto
)

@Composable
fun TecsupFitTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = TecsupColorScheme,
        typography = Typography,
        content = content
    )
}