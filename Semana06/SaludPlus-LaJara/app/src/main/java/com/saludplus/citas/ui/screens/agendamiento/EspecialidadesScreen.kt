package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessibilityNew
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.theme.AzulMarinoTitulos
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTextoSecundario

private data class InfoIconoEspecialidad(
    val icon: ImageVector,
    val fondoColor: Color,
    val iconoColor: Color
)

@Composable
fun EspecialidadesScreen(
    onSeleccionarEspecialidad: (String) -> Unit,
    onBackClick: () -> Unit
) {
    var query by remember { mutableStateOf("") }
    val especialidades = Repositorio.buscarEspecialidades(query)

    Scaffold(
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
                .padding(16.dp)
        ) {
            // Barra de Búsqueda
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar especialidad", fontSize = 14.sp, color = GrisTextoSecundario) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar",
                        tint = GrisTextoSecundario
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
                        color = GrisTextoSecundario
                    )
                }
            } else {
                LazyColumn {
                    itemsIndexed(especialidades) { index, especialidad ->
                        val infoIcono = obtenerIconoEspecialidad(especialidad.nombre)

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onSeleccionarEspecialidad(especialidad.id) }
                                .padding(vertical = 12.dp, horizontal = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                modifier = Modifier.size(56.dp),
                                shape = RoundedCornerShape(16.dp),
                                color = infoIcono.fondoColor
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = infoIcono.icon,
                                        contentDescription = null,
                                        tint = infoIcono.iconoColor,
                                        modifier = Modifier.size(28.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = especialidad.nombre,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AzulMarinoTitulos
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = especialidad.descripcion,
                                    fontSize = 13.sp,
                                    color = GrisTextoSecundario
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
                                color = Color(0xFFE5E7EB),
                                thickness = 1.dp
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun obtenerIconoEspecialidad(nombre: String): InfoIconoEspecialidad {
    val n = nombre.lowercase()
    return when {
        n.contains("medicina") -> InfoIconoEspecialidad(Icons.Default.MedicalServices, Color(0xFFEBF3FE), Color(0xFF2563EB))
        n.contains("pediatra") || n.contains("pediatría") -> InfoIconoEspecialidad(Icons.Default.ChildCare, Color(0xFFFFF3E0), Color(0xFFE65100))
        n.contains("gineco") -> InfoIconoEspecialidad(Icons.Default.Female, Color(0xFFFCE4EC), Color(0xFFC2185B))
        n.contains("cardio") -> InfoIconoEspecialidad(Icons.Default.Favorite, Color(0xFFFFEBEE), Color(0xFFD32F2F))
        n.contains("dermato") -> InfoIconoEspecialidad(Icons.Default.Face, Color(0xFFFFF8E1), Color(0xFFF57C00))
        n.contains("traumato") -> InfoIconoEspecialidad(Icons.Default.AccessibilityNew, Color(0xFFE0F7FA), Color(0xFF0097A7))
        n.contains("oftalmo") -> InfoIconoEspecialidad(Icons.Default.Visibility, Color(0xFFE1F5FE), Color(0xFF0288D1))
        else -> InfoIconoEspecialidad(Icons.Default.LocalHospital, Color(0xFFF4F7FF), Color(0xFF2563EB))
    }
}
