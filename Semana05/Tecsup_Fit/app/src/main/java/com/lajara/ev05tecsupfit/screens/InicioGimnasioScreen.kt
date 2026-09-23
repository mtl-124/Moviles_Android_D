package com.lajara.ev05tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.ev05tecsupfit.datos.ClasesDatos

val VerdeTecsup = Color(0xFF1B6E52)

@Composable
fun InicioGimnasioScreen(navController: NavController) {
    var filtroSeleccionado by remember { mutableStateOf("Hoy") }

    val clasesFiltradas = ClasesData.listaClases.filter { it.dia == filtroSeleccionado }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Hola, Diego", style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(12.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(ClasesDatos.filtros) { filtro ->
                val seleccionado = filtro == filtroSeleccionado
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(if (seleccionado) VerdeTecsup else Color(0xFFEFEFEF))
                        .clickable { filtroSeleccionado = filtro }
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        filtro,
                        color = if (seleccionado) Color.White else Color.Gray
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Clases disponibles", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(clasesFiltradas) { clase ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF2F2F2))
                        .clickable { navController.navigate("detalle_clase/${clase.id}") }
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDCEEE6)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.FitnessCenter, contentDescription = null, tint = VerdeTecsup)
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(clase.nombre, style = MaterialTheme.typography.titleSmall)
                        Text("${clase.horario} · ${clase.sala}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}