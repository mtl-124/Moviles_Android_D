package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.saludplus.citas.data.model.Cita
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.BotonPrimario
import com.saludplus.citas.ui.components.TopBarSaludPlus

@Composable
fun ConfirmarCitaScreen(
    medicoId: String,
    fecha: String,
    hora: String,
    onCitaConfirmada: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val medico = Repositorio.obtenerMedico(medicoId)
    val especialidad = medico?.let { Repositorio.obtenerEspecialidad(it.especialidadId) }
    val usuario = Repositorio.usuarioActual
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopBarSaludPlus(
                titulo = "Resumen de Cita",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Confirma los datos de tu consulta",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Médico y Especialidad
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = medico?.nombre ?: "Médico Desconocido",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = especialidad?.nombre ?: "Especialidad General",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        HorizontalDivider()

                        // Fecha
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.CalendarMonth,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Fecha: $fecha",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        // Hora
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Hora: $hora",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        HorizontalDivider()

                        // Paciente
                        Column {
                            Text(
                                text = "Paciente:",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = usuario?.nombre ?: "Paciente",
                                style = MaterialTheme.typography.bodyLarge,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                if (mensajeError != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = mensajeError!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            BotonPrimario(
                texto = "Confirmar Cita",
                onClick = {
                    val nuevaCitaId = System.currentTimeMillis().toString()
                    val nuevaCita = Cita(
                        id = nuevaCitaId,
                        usuarioId = usuario?.id ?: "1",
                        medicoId = medicoId,
                        fecha = fecha,
                        hora = hora,
                        estado = "Confirmada"
                    )

                    val exito = Repositorio.agendarCita(nuevaCita)
                    if (exito) {
                        onCitaConfirmada(nuevaCitaId)
                    } else {
                        mensajeError = "El horario ya no se encuentra disponible."
                    }
                }
            )
        }
    }
}