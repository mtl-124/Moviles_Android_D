package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTexto
import com.saludplus.citas.ui.theme.PastelAzulFondo
import com.saludplus.citas.ui.util.FechasUtil
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicosScreen(
    especialidadId: String,
    onSeleccionarMedico: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val especialidad = Repositorio.obtenerEspecialidad(especialidadId)
    val todosMedicos = remember(especialidadId) {
        Repositorio.medicosPorEspecialidad(especialidadId)
            .sortedByDescending { it.calificacion.toDoubleOrNull() ?: 0.0 }
    }

    var mostrandoBusqueda by remember { mutableStateOf(false) }
    var query by remember { mutableStateOf("") }

    val medicosFiltrados = remember(query, todosMedicos) {
        if (query.isBlank()) {
            todosMedicos
        } else {
            todosMedicos.filter { it.nombre.contains(query, ignoreCase = true) }
        }
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "Médicos de ${especialidad?.nombre ?: "Especialidad"}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulMarino,
                            textAlign = TextAlign.Center
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = AzulMarino
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { mostrandoBusqueda = !mostrandoBusqueda }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar médico",
                                tint = AzulMarino
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        scrolledContainerColor = Color.White,
                        navigationIconContentColor = AzulMarino,
                        titleContentColor = AzulMarino
                    )
                )

                if (mostrandoBusqueda) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 8.dp),
                        placeholder = { Text("Buscar por nombre de médico...", fontSize = 14.sp, color = GrisTexto) },
                        singleLine = true,
                        shape = RoundedCornerShape(20.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = Color(0xFFF4F7FF),
                            focusedContainerColor = Color(0xFFF4F7FF)
                        )
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {
            if (medicosFiltrados.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron médicos disponibles",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = GrisTexto
                    )
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    itemsIndexed(medicosFiltrados) { _, medico ->
                        val (textoChip, esVerde) = remember(medico.id) {
                            calcularChipDisponibilidad(medico.id)
                        }

                        Card(
                            onClick = { onSeleccionarMedico(medico.id) },
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
                                    modifier = Modifier.size(80.dp),
                                    shape = CircleShape,
                                    color = PastelAzulFondo
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = iniciales,
                                            fontSize = 24.sp,
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
                                        text = especialidad?.nombre ?: "Especialista",
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
