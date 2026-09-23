package com.lajara.ev05clinicasalud.screens


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lajara.ev05clinicasalud.datos.Cita
import com.lajara.ev05clinicasalud.datos.CitasDatos

@Composable
fun MisCitasScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mis citas", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (CitasDatos.listaCitas.isEmpty()) {
            Text("Aún no tienes citas agendadas.")
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(CitasDatos.listaCitas) { cita ->
                    CitaCard(cita)
                }
            }
        }
    }
}

@Composable
fun CitaCard(cita: Cita) {
    val colorEstado = if (cita.estado == "Confirmada") {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.tertiary
    }

    Card(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Column {
                Text(cita.medico.nombre, style = MaterialTheme.typography.titleMedium)
                Text("${cita.fecha} · ${cita.hora}", style = MaterialTheme.typography.bodySmall)
            }
            AssistChip(
                onClick = { },
                label = { Text(cita.estado) },
                colors = AssistChipDefaults.assistChipColors(
                    labelColor = colorEstado
                )
            )
        }
    }
}