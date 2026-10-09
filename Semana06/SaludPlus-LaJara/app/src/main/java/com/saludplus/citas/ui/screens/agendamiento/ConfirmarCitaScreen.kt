package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.BorderStroke
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
import com.saludplus.citas.ui.components.BotonPrimario
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.FondoClaro
import com.saludplus.citas.ui.theme.GrisTexto
import com.saludplus.citas.ui.theme.PastelAzulFondo
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
    val sede = Repositorio.sedeSeleccionada

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

    val textoDireccionSede = remember(sede) {
        if (sede != null) {
            "${sede.nombre} · ${sede.direccion}"
        } else {
            "Av. Los Olivos 123, Lima"
        }
    }

    Scaffold(
        containerColor = Color.White,
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
                .padding(20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Tarjeta del Médico (fondo FondoClaro)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = FondoClaro
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
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
                                color = AzulMarino
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = especialidad?.nombre ?: "Especialidad General",
                                fontSize = 14.sp,
                                color = GrisTexto
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

                // 4 Filas de Detalle con caja azul pastel de 52.dp
                OutlinedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(1.dp, Color(0xFFE8ECF5)),
                    colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
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

                        HorizontalDivider(color = Color(0xFFE8ECF5))

                        // 2. Hora
                        FilaDetalleItem(
                            icon = Icons.Default.Schedule,
                            etiqueta = "Hora",
                            valor = rangoHora
                        )

                        HorizontalDivider(color = Color(0xFFE8ECF5))

                        // 3. Tipo de atención
                        FilaDetalleItem(
                            icon = Icons.Default.MedicalServices,
                            etiqueta = "Tipo de atención",
                            valor = "Consulta presencial"
                        )

                        HorizontalDivider(color = Color(0xFFE8ECF5))

                        // 4. Dirección / Sede
                        FilaDetalleItem(
                            icon = Icons.Default.LocationOn,
                            etiqueta = "Dirección",
                            valor = textoDireccionSede
                        )
                    }
                }

                // Campo "Motivo de consulta (opcional)"
                Column {
                    Text(
                        text = "Motivo de consulta (opcional)",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = AzulMarino
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    OutlinedTextField(
                        value = motivoConsulta,
                        onValueChange = { motivoConsulta = it },
                        placeholder = { Text("Describe brevemente el motivo de tu consulta...", fontSize = 14.sp, color = GrisTexto) },
                        minLines = 3,
                        maxLines = 5,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF4F7FF),
                            focusedContainerColor = Color(0xFFF4F7FF),
                            unfocusedBorderColor = Color.Transparent,
                            focusedBorderColor = AzulPrimario.copy(alpha = 0.5f)
                        )
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

            // Botón "Agendar cita"
            BotonPrimario(
                texto = "Agendar cita",
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
                }
            )
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
            modifier = Modifier.size(52.dp),
            shape = RoundedCornerShape(14.dp),
            color = PastelAzulFondo
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AzulPrimario,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = etiqueta,
                fontSize = 12.sp,
                color = GrisTexto
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = valor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = AzulMarino
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
