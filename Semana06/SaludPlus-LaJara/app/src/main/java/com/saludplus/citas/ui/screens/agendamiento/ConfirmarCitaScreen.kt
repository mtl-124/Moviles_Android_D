package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.model.Cita
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.theme.AzulMarinoTitulos
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTextoSecundario
import com.saludplus.citas.ui.util.FechasUtil
import java.time.LocalTime

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

    var motivoConsulta by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    val fechaFormateada = remember(fecha) {
        val dateObj = FechasUtil.desdeTexto(fecha)
        if (dateObj != null) {
            FechasUtil.fechaLarga(dateObj)
        } else {
            fecha
        }
    }

    val rangoHora = remember(hora) {
        calcularRangoHora(hora)
    }

    Scaffold(
        topBar = {
            TopBarSaludPlus(
                titulo = "Confirmar cita",
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
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Tarjeta del Médico
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFEBF3FE)
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val iniciales = remember(medico?.nombre) {
                            obtenerIniciales(medico?.nombre ?: "")
                        }
                        Surface(
                            modifier = Modifier.size(90.dp),
                            shape = CircleShape,
                            color = AzulPrimario
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = iniciales,
                                    fontSize = 28.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Column {
                            Text(
                                text = medico?.nombre ?: "Médico Especialista",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = AzulMarinoTitulos
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = especialidad?.nombre ?: "Especialidad General",
                                fontSize = 14.sp,
                                color = GrisTextoSecundario
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "CMP: ${medico?.colegiatura ?: "-"}",
                                fontSize = 13.sp,
                                color = AzulPrimario,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }

                // 4 Filas de Detalle
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // 1. Fecha
                        FilaDetalleItem(
                            icon = Icons.Default.CalendarMonth,
                            etiqueta = "Fecha",
                            valor = fechaFormateada
                        )

                        HorizontalDivider(color = Color(0xFFE5E7EB))

                        // 2. Hora
                        FilaDetalleItem(
                            icon = Icons.Default.Schedule,
                            etiqueta = "Hora",
                            valor = rangoHora
                        )

                        HorizontalDivider(color = Color(0xFFE5E7EB))

                        // 3. Tipo de atención
                        FilaDetalleItem(
                            icon = Icons.Default.MedicalServices,
                            etiqueta = "Tipo de atención",
                            valor = "Consulta presencial"
                        )

                        HorizontalDivider(color = Color(0xFFE5E7EB))

                        // 4. Dirección
                        FilaDetalleItem(
                            icon = Icons.Default.LocationOn,
                            etiqueta = "Dirección",
                            valor = "Av. Los Olivos 123, Lima"
                        )
                    }
                }

                // Campo "Motivo de consulta (opcional)"
                Column {
                    Text(
                        text = "Motivo de consulta (opcional)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AzulMarinoTitulos
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = motivoConsulta,
                        onValueChange = { motivoConsulta = it },
                        placeholder = { Text("Describe brevemente el motivo de tu consulta...", fontSize = 14.sp, color = Color.Gray) },
                        minLines = 3,
                        maxLines = 5,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )
                }

                if (mensajeError != null) {
                    Text(
                        text = mensajeError!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón Agendar Cita
            Button(
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
                        mensajeError = "Ese horario ya fue reservado"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AzulPrimario,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Agendar cita",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun FilaDetalleItem(
    icon: ImageVector,
    etiqueta: String,
    valor: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(40.dp),
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFFEBF3FE)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AzulPrimario,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = etiqueta,
                fontSize = 12.sp,
                color = GrisTextoSecundario
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = valor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = AzulMarinoTitulos
            )
        }
    }
}

private fun calcularRangoHora(hora: String): String {
    return try {
        val inicio = LocalTime.parse(hora)
        val fin = inicio.plusMinutes(30)
        "$inicio a $fin"
    } catch (_: Exception) {
        hora
    }
}

private fun obtenerIniciales(nombre: String): String {
    if (nombre.isBlank()) return "SP"
    val limpio = nombre
        .replace("Dr(a).", "", ignoreCase = true)
        .replace("Dra.", "", ignoreCase = true)
        .replace("Dr.", "", ignoreCase = true)
        .replace("Lic.", "", ignoreCase = true)
        .trim()
    val palabras = limpio.split("\\s+".toRegex()).filter { it.isNotBlank() }
    return when {
        palabras.isEmpty() -> "SP"
        palabras.size == 1 -> palabras[0].take(2).uppercase()
        else -> "${palabras[0].first().uppercaseChar()}${palabras[1].first().uppercaseChar()}"
    }
}
