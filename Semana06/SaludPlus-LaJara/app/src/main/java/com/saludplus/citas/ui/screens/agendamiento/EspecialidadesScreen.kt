package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.IconoEspecialidad
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.components.obtenerIconoEspecialidad
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTexto

@Composable
fun EspecialidadesScreen(
    onSeleccionarEspecialidad: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    val especialidades = Repositorio.buscarEspecialidades(query)

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBarSaludPlus(
                titulo = "Especialidades",
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
            // Buscador con esquinas 28.dp y fondo #F4F7FF (FondoClaro)
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar especialidad", fontSize = 15.sp, color = GrisTexto) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = AzulPrimario
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(28.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF4F7FF),
                    focusedContainerColor = Color(0xFFF4F7FF),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = AzulPrimario.copy(alpha = 0.5f)
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            if (especialidades.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No se encontraron especialidades",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = GrisTexto
                    )
                }
            } else {
                LazyColumn {
                    itemsIndexed(especialidades) { index, especialidad ->
                        val datosIcono = obtenerIconoEspecialidad(especialidad.nombre)
                        val descripcionPersonalizada = obtenerDescripcionPersonalizada(especialidad.nombre, especialidad.descripcion)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSeleccionarEspecialidad(especialidad.id) }
                                .padding(vertical = 14.dp, horizontal = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier.size(60.dp),
                                shape = RoundedCornerShape(16.dp),
                                color = datosIcono.colorFondo
                            ) {
                                IconoEspecialidad(
                                    nombre = especialidad.nombre,
                                    sizeIcono = 30.dp
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = especialidad.nombre,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AzulMarino
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = descripcionPersonalizada,
                                    fontSize = 13.sp,
                                    color = GrisTexto
                                )
                            }

                            Icon(
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = null,
                                tint = Color.Gray,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        if (index < especialidades.size - 1) {
                            HorizontalDivider(
                                color = Color(0xFFE8ECF5),
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun obtenerDescripcionPersonalizada(nombre: String, descripcionOriginal: String): String {
    val n = nombre.lowercase()
    return when {
        n.contains("medicina") -> "Atención médica primaria e integral"
        n.contains("pediatra") || n.contains("pediatría") -> "Niños y adolescentes"
        n.contains("gineco") -> "Salud de la mujer"
        n.contains("cardio") -> "Corazón y vasos sanguíneos"
        n.contains("dermato") -> "Piel, cabello y uñas"
        n.contains("traumato") -> "Huesos y articulaciones"
        n.contains("oftalmo") -> "Salud visual"
        else -> descripcionOriginal
    }
}
