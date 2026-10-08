package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.BotonPrimario
import com.saludplus.citas.ui.components.TopBarSaludPlus

@Composable
fun FechaHoraScreen(
    medicoId: String,
    onContinuar: (String, String) -> Unit,
    onBackClick: () -> Unit
) {
    val medico = Repositorio.obtenerMedico(medicoId)

    val fechasDisponibles = listOf("2026-10-12", "2026-10-13", "2026-10-14", "2026-10-15")
    var fechaSeleccionada by remember { mutableStateOf(fechasDisponibles.first()) }

    val horasDisponibles = Repositorio.horariosDisponibles(medicoId, fechaSeleccionada)
    var horaSeleccionada by remember { mutableStateOf(horasDisponibles.firstOrNull() ?: "") }

    LaunchedEffect(fechaSeleccionada) {
        val disponibles = Repositorio.horariosDisponibles(medicoId, fechaSeleccionada)
        if (horaSeleccionada !in disponibles) {
            horaSeleccionada = disponibles.firstOrNull() ?: ""
        }
    }

    Scaffold(
        topBar = {
            TopBarSaludPlus(
                titulo = "Seleccionar Turno",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Dr(a). ${medico?.nombre ?: "Especialista"}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Selecciona la fecha y hora de tu preferencia",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Selección de Fecha
            Text(
                text = "Fechas disponibles",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(fechasDisponibles) { fecha ->
                    FilterChip(
                        selected = (fecha == fechaSeleccionada),
                        onClick = { fechaSeleccionada = fecha },
                        label = { Text(fecha) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Selección de Hora
            Text(
                text = "Horarios disponibles",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (horasDisponibles.isEmpty()) {
                Text(
                    text = "No hay horarios disponibles para esta fecha.",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.weight(1f))
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(horasDisponibles) { hora ->
                        FilterChip(
                            selected = (hora == horaSeleccionada),
                            onClick = { horaSeleccionada = hora },
                            label = { Text(hora) }
                        )
                    }
                }
            }

            BotonPrimario(
                texto = "Continuar a Confirmación",
                enabled = horaSeleccionada.isNotEmpty(),
                onClick = { onContinuar(fechaSeleccionada, horaSeleccionada) }
            )
        }
    }
}