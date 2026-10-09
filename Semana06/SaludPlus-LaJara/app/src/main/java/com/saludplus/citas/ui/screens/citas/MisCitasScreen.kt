package com.saludplus.citas.ui.screens.citas

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.model.Cita
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.BotonPrimario
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.theme.AzulMarinoTitulos
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTextoSecundario
import com.saludplus.citas.ui.util.FechasUtil
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun MisCitasScreen(
    onBackClick: () -> Unit = {},
    onAgendarCita: () -> Unit = {}
) {
    val usuario = Repositorio.usuarioActual
    var citas by remember {
        mutableStateOf(obtenerCitasOrdenadas(usuario?.id ?: "1"))
    }
    var citaACancelar by remember { mutableStateOf<Cita?>(null) }

    Scaffold(
        topBar = {
            TopBarSaludPlus(
                titulo = "Mis citas",
                mostrarBotonAtras = false
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            if (citas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Aún no tienes citas agendadas",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulMarinoTitulos
                        )
                        Spacer(modifier = Modifier.height(24.dp))
                        BotonPrimario(
                            texto = "Agendar cita",
                            onClick = onAgendarCita,
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(citas) { cita ->
                        val medico = Repositorio.obtenerMedico(cita.medicoId)
                        val especialidad = medico?.let { Repositorio.obtenerEspecialidad(it.especialidadId) }

                        val fechaFormateada = remember(cita.fecha) {
                            FechasUtil.desdeTexto(cita.fecha)?.let {
                                FechasUtil.fechaLarga(it)
                            } ?: cita.fecha
                        }

                        OutlinedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                            colors = CardDefaults.outlinedCardColors(
                                containerColor = Color.White
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "Código: ${cita.id}",
                                        fontSize = 12.sp,
                                        color = AzulPrimario,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Surface(
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0xFFE8F5E9)
                                    ) {
                                        Text(
                                            text = "Confirmada",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2E7D32),
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    val iniciales = remember(medico?.nombre) {
                                        obtenerIniciales(medico?.nombre ?: "")
                                    }
                                    Surface(
                                        modifier = Modifier.size(48.dp),
                                        shape = CircleShape,
                                        color = Color(0xFFEBF3FE)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = iniciales,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = AzulPrimario
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.width(12.dp))

                                    Column {
                                        Text(
                                            text = "Dr(a). ${medico?.nombre ?: "Médico"}",
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AzulMarinoTitulos
                                        )
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(
                                            text = especialidad?.nombre ?: "General",
                                            fontSize = 13.sp,
                                            color = GrisTextoSecundario
                                        )
                                    }
                                }

                                HorizontalDivider(color = Color(0xFFE5E7EB))

                                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.CalendarToday,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                            tint = AzulPrimario
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = fechaFormateada,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = AzulMarinoTitulos
                                        )
                                    }

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Schedule,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                            tint = AzulPrimario
                                        )
                                        Spacer(modifier = Modifier.width(8.dp))
                                        Text(
                                            text = cita.hora,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = AzulMarinoTitulos
                                        )
                                    }
                                }

                                TextButton(
                                    onClick = { citaACancelar = cita },
                                    modifier = Modifier.align(Alignment.End),
                                    colors = ButtonDefaults.textButtonColors(
                                        contentColor = MaterialTheme.colorScheme.error
                                    )
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Cancel,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Cancelar Cita", fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (citaACancelar != null) {
        AlertDialog(
            onDismissRequest = { citaACancelar = null },
            title = { Text("Cancelar Cita") },
            text = { Text("¿Estás seguro de que deseas cancelar la cita con código ${citaACancelar?.id}?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        citaACancelar?.let { cita ->
                            Repositorio.cancelarCita(cita.id)
                            citas = obtenerCitasOrdenadas(usuario?.id ?: "1")
                        }
                        citaACancelar = null
                    }
                ) {
                    Text("Confirmar Cancelación", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { citaACancelar = null }) {
                    Text("Volver")
                }
            }
        )
    }
}

private fun obtenerCitasOrdenadas(usuarioId: String): List<Cita> {
    val raw = Repositorio.citasDelUsuario(usuarioId)
    return raw.sortedWith { c1, c2 ->
        val f1 = FechasUtil.desdeTexto(c1.fecha) ?: LocalDate.MIN
        val f2 = FechasUtil.desdeTexto(c2.fecha) ?: LocalDate.MIN
        val fechaComp = f1.compareTo(f2)
        if (fechaComp != 0) {
            fechaComp
        } else {
            val h1 = try { LocalTime.parse(c1.hora) } catch (_: Exception) { LocalTime.MIN }
            val h2 = try { LocalTime.parse(c2.hora) } catch (_: Exception) { LocalTime.MIN }
            h1.compareTo(h2)
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
