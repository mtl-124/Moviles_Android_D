package com.lajara.ev05clinicasalud.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class RegistroHistorial(
    val fecha: String,
    val descripcion: String
)

@Composable
fun HistorialScreen() {
    val historial = listOf(
        RegistroHistorial("12/03/2025", "Consulta general - Presión arterial normal"),
        RegistroHistorial("28/07/2025", "Vacuna antigripal aplicada"),
        RegistroHistorial("15/09/2025", "Control de rutina - Sin observaciones")
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Historial médico", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(historial) { registro ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(registro.fecha, style = MaterialTheme.typography.bodySmall)
                        Text(registro.descripcion, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}