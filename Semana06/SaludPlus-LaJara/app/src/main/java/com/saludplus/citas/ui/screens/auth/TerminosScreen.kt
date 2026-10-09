package com.saludplus.citas.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.saludplus.citas.ui.components.TopBarSaludPlus

@Composable
fun TerminosScreen(
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopBarSaludPlus(
                titulo = "Términos y Condiciones",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text(
                text = "Términos del Servicio SaludPlus",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Al registrarte y hacer uso de la aplicación SaludPlus, aceptas los términos de reserva de citas médicas, protección de datos personales de salud conforme a la normativa vigente y el cumplimiento de las políticas de asistencia punctual a tus consultas.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}