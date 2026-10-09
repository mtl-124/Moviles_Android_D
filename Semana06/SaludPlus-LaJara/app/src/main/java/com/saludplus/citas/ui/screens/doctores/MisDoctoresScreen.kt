package com.saludplus.citas.ui.screens.doctores

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.model.Especialidad
import com.saludplus.citas.data.model.Medico
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.IconoEspecialidad
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.components.obtenerIconoEspecialidad
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTexto
import com.saludplus.citas.ui.theme.PastelAzulFondo
import com.saludplus.citas.ui.util.FechasUtil
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MisDoctoresScreen(
    onSeleccionarMedico: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val medicosPorEspecialidadMap = remember {
        Repositorio.medicosAgrupadosPorEspecialidad()
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBarSaludPlus(
                titulo = "Mis doctores",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            medicosPorEspecialidadMap.forEach { (especialidad, medicos) ->
                stickyHeader {
                    EncabezadoSeccionEspecialidad(
                        especialidad = especialidad,
                        cantidadDoctores = medicos.size
                    )
                }

                items(medicos) { medico ->
                    TarjetaDoctorItem(
                        medico = medico,
                        especialidad = especialidad,
                        onClick = { onSeleccionarMedico(medico.id) }
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }
    }
}

@Composable
private fun EncabezadoSeccionEspecialidad(
    especialidad: Especialidad,
    cantidadDoctores: Int
) {
    val datosIcono = obtenerIconoEspecialidad(especialidad.nombre)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    modifier = Modifier.size(44.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = datosIcono.colorFondo
                ) {
                    IconoEspecialidad(
                        nombre = especialidad.nombre,
                        sizeIcono = 24.dp
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = especialidad.nombre,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulMarino
                )
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = datosIcono.colorFondo
            ) {
                Text(
                    text = if (cantidadDoctores == 1) "1 doctor" else "$cantidadDoctores doctores",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = datosIcono.colorIcono,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
private fun TarjetaDoctorItem(
    medico: Medico,
    especialidad: Especialidad,
    onClick: () -> Unit
) {
    val (textoChip, esVerde) = remember(medico.id) {
        calcularChipDisponibilidad(medico.id)
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFFE8ECF5)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val iniciales = remember(medico.nombre) {
                obtenerIniciales(medico.nombre)
            }

            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = PastelAzulFondo
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = iniciales,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = medico.nombre,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulMarino
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = especialidad.nombre,
                    fontSize = 13.sp,
                    color = GrisTexto
                )
                Spacer(modifier = Modifier.height(6.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFFF5B301),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${medico.calificacion} (120)",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulMarino
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    modifier = Modifier.align(Alignment.End),
                    shape = RoundedCornerShape(12.dp),
                    color = if (esVerde) Color(0xFFE3F8EE) else Color(0xFFE0F4FA)
                ) {
                    Text(
                        text = textoChip,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (esVerde) Color(0xFF1F7A4D) else Color(0xFF0E6F8A),
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }
        }
    }
}

private fun calcularChipDisponibilidad(medicoId: String): Pair<String, Boolean> {
    val hoy = LocalDate.now()
    val manana = hoy.plusDays(1)
    val hoyTexto = FechasUtil.aTexto(hoy)
    val mananaTexto = FechasUtil.aTexto(manana)

    return when {
        Repositorio.atiendeEn(medicoId, hoy) && Repositorio.horariosDisponibles(medicoId, hoyTexto).isNotEmpty() -> {
            "Disponible hoy" to true
        }
        Repositorio.atiendeEn(medicoId, manana) && Repositorio.horariosDisponibles(medicoId, mananaTexto).isNotEmpty() -> {
            "Disponible mañana" to true
        }
        else -> {
            "Atiende: ${Repositorio.textoDiasAtencion(medicoId)}" to false
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
