package com.lajara.ev05clinicasalud.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.ev05clinicasalud.datos.MedicosDatos

@Composable
fun InicioClinicaScreen(navController: NavController) {
    var especialidadSeleccionada by remember { mutableStateOf("Todas") }

    val medicosFiltrados = if (especialidadSeleccionada == "Todas") {
        MedicosDatos.listaMedicos
    } else {
        MedicosDatos.listaMedicos.filter { it.especialidad == especialidadSeleccionada }
    }

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        // LazyRow de chips de especialidad
        LazyRow(
            modifier = Modifier.padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(MedicosDatos.especialidades) { especialidad ->
                FilterChip(
                    selected = especialidad == especialidadSeleccionada,
                    onClick = { especialidadSeleccionada = especialidad },
                    label = { Text(especialidad) }
                )
            }
        }

        // LazyColumn de médicos
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(medicosFiltrados) { medico ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { navController.navigate("perfil_medico/${medico.id}") }
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(medico.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(medico.especialidad, style = MaterialTheme.typography.bodyMedium)
                        Text("⭐ ${medico.calificacion}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}
