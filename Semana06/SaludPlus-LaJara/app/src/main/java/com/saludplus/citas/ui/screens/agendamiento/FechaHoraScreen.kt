package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.BotonPrimario
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.FondoClaro
import com.saludplus.citas.ui.theme.GrisTexto
import com.saludplus.citas.ui.util.FechasUtil
import java.time.LocalDate

private val LocalDateSaver = Saver<LocalDate?, String>(
    save = { fecha -> fecha?.let { FechasUtil.aTexto(it) } ?: "" },
    restore = { texto -> if (texto.isNotEmpty()) FechasUtil.desdeTexto(texto) else null }
)

@Composable
fun FechaHoraScreen(
    medicoId: String,
    onContinuar: (String, String) -> Unit,
    onBackClick: () -> Unit
) {
    val medico = Repositorio.obtenerMedico(medicoId)
    val especialidad = medico?.let { Repositorio.obtenerEspecialidad(it.especialidadId) }

    var semanaOffset by rememberSaveable { mutableIntStateOf(0) }

    val dias = remember(semanaOffset) {
        FechasUtil.semana(semanaOffset)
    }

    val textoAtencion = remember(medicoId) {
        Repositorio.textoDiasAtencion(medicoId)
    }

    // Selecciona por defecto el primer día de la semana en que atiende el médico
    var diaSeleccionado by rememberSaveable(semanaOffset, stateSaver = LocalDateSaver) {
        mutableStateOf(dias.firstOrNull { Repositorio.atiendeEn(medicoId, it) })
    }

    var horaSeleccionada by rememberSaveable {
        mutableStateOf<String?>(null)
    }

    val horasDisponibles = remember(diaSeleccionado, medicoId) {
        diaSeleccionado?.let { fecha ->
            Repositorio.horariosDisponibles(medicoId, FechasUtil.aTexto(fecha))
        } ?: emptyList()
    }

    val puedeRetroceder = FechasUtil.puedeRetroceder(semanaOffset)
    val nombreMesAnio = remember(dias) {
        if (dias.isNotEmpty()) FechasUtil.nombreMesAnio(dias.first()) else ""
    }

    val atiendeEstaSemana = remember(dias, medicoId) {
        dias.any { Repositorio.atiendeEn(medicoId, it) }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBarSaludPlus(
                titulo = "Seleccionar fecha y hora",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
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
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val iniciales = remember(medico?.nombre) {
                            obtenerIniciales(medico?.nombre ?: "")
                        }
                        Surface(
                            modifier = Modifier.size(80.dp),
                            shape = CircleShape,
                            color = AzulPrimario
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                Text(
                                    text = iniciales,
                                    fontSize = 26.sp,
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
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = especialidad?.nombre ?: "Especialidad General",
                                fontSize = 14.sp,
                                color = GrisTexto
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = Color(0xFFE8ECF5))
                    Spacer(modifier = Modifier.height(8.dp))

                    // Línea "Atiende: Lun · Mié · Vie"
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Schedule,
                            contentDescription = null,
                            tint = AzulPrimario,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Atiende: $textoAtencion",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = AzulMarino
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Fila del Mes con Flechas "<  Octubre 2026  >"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        if (puedeRetroceder) {
                            semanaOffset--
                            horaSeleccionada = null
                        }
                    },
                    enabled = puedeRetroceder
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronLeft,
                        contentDescription = "Semana anterior",
                        tint = if (puedeRetroceder) AzulMarino else Color.Gray.copy(alpha = 0.4f)
                    )
                }

                Text(
                    text = nombreMesAnio,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulMarino
                )

                IconButton(
                    onClick = {
                        semanaOffset++
                        horaSeleccionada = null
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ChevronRight,
                        contentDescription = "Siguiente semana",
                        tint = AzulMarino
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 5 Chips de Día (deshabilitados los días en que NO atiende)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                dias.forEach { fecha ->
                    val atiendeElDia = Repositorio.atiendeEn(medicoId, fecha)
                    val esSeleccionado = (fecha == diaSeleccionado)

                    val fondoColor = when {
                        !atiendeElDia -> Color(0xFFF8FAFD)
                        esSeleccionado -> AzulPrimario
                        else -> Color(0xFFF1F4FB)
                    }

                    val textoColor = when {
                        !atiendeElDia -> Color(0xFFB8C0D0)
                        esSeleccionado -> Color.White
                        else -> AzulMarino
                    }

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .height(84.dp)
                            .then(
                                if (atiendeElDia) {
                                    Modifier.clickable {
                                        diaSeleccionado = fecha
                                        horaSeleccionada = null
                                    }
                                } else Modifier
                            ),
                        shape = RoundedCornerShape(16.dp),
                        color = fondoColor
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = FechasUtil.abreviaturaDia(fecha),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (esSeleccionado && atiendeElDia) Color.White.copy(alpha = 0.9f) else if (!atiendeElDia) Color(0xFFB8C0D0) else GrisTexto
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = fecha.dayOfMonth.toString(),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = textoColor
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Horarios disponibles",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AzulMarino
            )
            Spacer(modifier = Modifier.height(12.dp))

            // Contenido de Horarios o Mensaje
            if (!atiendeEstaSemana) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Este doctor no atiende esta semana. Prueba con otra semana.",
                        fontSize = 15.sp,
                        color = GrisTexto,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            } else if (horasDisponibles.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No hay horarios disponibles para este día",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.weight(1f)
                ) {
                    items(horasDisponibles) { hora ->
                        val esHoraSeleccionada = (hora == horaSeleccionada)
                        val fondoHora = if (esHoraSeleccionada) AzulPrimario else Color(0xFFF1F4FB)
                        val textoHora = if (esHoraSeleccionada) Color.White else AzulMarino

                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .clickable { horaSeleccionada = hora },
                            shape = RoundedCornerShape(14.dp),
                            color = fondoHora
                        ) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = hora,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = textoHora
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón "Continuar" deshabilitado si no hay día habilitado ni hora elegida
            val botonHabilitado = (diaSeleccionado != null && Repositorio.atiendeEn(medicoId, diaSeleccionado!!) && !horaSeleccionada.isNullOrEmpty())

            BotonPrimario(
                texto = "Continuar",
                enabled = botonHabilitado,
                onClick = {
                    val fechaTexto = diaSeleccionado?.let { FechasUtil.aTexto(it) } ?: ""
                    val horaTexto = horaSeleccionada ?: ""
                    if (fechaTexto.isNotEmpty() && horaTexto.isNotEmpty()) {
                        onContinuar(fechaTexto, horaTexto)
                    }
                }
            )
        }
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
