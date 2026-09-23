package com.lajara.clinicasalud.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AgendarCitaScreen(
    medicoId: Int
) {
    Text(
        text = "Agendar cita - Médico: $medicoId"
    )
}