package com.lajara.clinicasalud.screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PerfilMedicoScreen(
    medicoId: Int,
    onAgendarClick: () -> Unit
) {
    Text("Perfil del médico: $medicoId")
}