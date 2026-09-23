package com.lajara.clinicasalud.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = MoradoPrincipal,
    onPrimary = Color.White,
    primaryContainer = MoradoClaro,
    surfaceVariant = FondoTarjeta,
    background = Color.White,
    surface = Color.White,
    onSurface = Color.Black,
    onBackground = Color.Black
)

@Composable
fun ClinicasaludTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
