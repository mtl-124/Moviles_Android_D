package com.saludplus.citas.ui.screens.agendamiento

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.theme.AzulMarinoTitulos
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTextoSecundario

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
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = "Médicos de ${especialidad?.nombre ?: "Especialidad"}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulMarinoTitulos
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = onBackClick) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver",
                                tint = AzulMarinoTitulos
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { mostrandoBusqueda = !mostrandoBusqueda }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar médico",
                                tint = AzulMarinoTitulos
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )

                if (mostrandoBusqueda) {
                    OutlinedTextField(
                        value = query,
                        onValueChange = { query = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        placeholder = { Text("Buscar por nombre de médico...", fontSize = 14.sp) },
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
                .padding(16.dp)
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
                        color = GrisTextoSecundario
                    )
                }
            } else {
                LazyColumn {
                    itemsIndexed(medicosFiltrados) { index, medico ->
                        val disponibilidadTexto = obtenerTextoDisponibilidad(medico.id)

                        Card(
                            onClick = { onSeleccionarMedico(medico.id) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.White
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 0.dp
                            )
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
                                    color = Color(0xFFEBF3FE)
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
                                        color = AzulMarinoTitulos
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = especialidad?.nombre ?: "Especialista",
                                        fontSize = 13.sp,
                                        color = GrisTextoSecundario
                                    )
                                    Spacer(modifier = Modifier.height(6.dp))

                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.Star,
                                            contentDescription = null,
                                            tint = Color(0xFFFFB300),
                                            modifier = Modifier.size(18.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "${medico.calificacion} (120)",
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = AzulMarinoTitulos
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Surface(
                                        modifier = Modifier.align(Alignment.End),
                                        shape = RoundedCornerShape(12.dp),
                                        color = Color(0xFFE8F5E9)
                                    ) {
                                        Text(
                                            text = disponibilidadTexto,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF2E7D32),
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                        )
                                    }
                                }
                            }
                        }

                        if (index < medicosFiltrados.size - 1) {
                            HorizontalDivider(
                                color = Color(0xFFE5E7EB),
                                thickness = 1.dp,
                                modifier = Modifier.padding(vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun obtenerTextoDisponibilidad(medicoId: String): String {
    return when (medicoId) {
        "m1", "m2", "m5" -> "Disponible hoy"
        "m3" -> "Disponible mañana"
        else -> "Disponible esta semana"
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
