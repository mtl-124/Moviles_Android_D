package com.lajara.ev05clinicasalud.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AgendarCitaScreen(medicoId: Int, navController: NavController) {
    val fechas = listOf("Lun 06", "Mar 07", "Mié 08")
    val horas = listOf("09:00", "11:00", "15:00")

    var fechaSeleccionada by remember { mutableStateOf<String?>(null) }
    var horaSeleccionada by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Elige una fecha", style = MaterialTheme.typography.titleMedium)
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(fechas) { fecha ->
                FilterChip(
                    selected = fecha == fechaSeleccionada,
                    onClick = { fechaSeleccionada = fecha },
                    label = { Text(fecha) }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Elige una hora", style = MaterialTheme.typography.titleMedium)
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(horas) { hora ->
                FilterChip(
                    selected = hora == horaSeleccionada,
                    onClick = { horaSeleccionada = hora },
                    label = { Text(hora) }
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate("confirmacion/$medicoId/$fechaSeleccionada/$horaSeleccionada")
            },
            enabled = fechaSeleccionada != null && horaSeleccionada != null
        ) {
            Text("Confirmar cita")
        }
    }
}