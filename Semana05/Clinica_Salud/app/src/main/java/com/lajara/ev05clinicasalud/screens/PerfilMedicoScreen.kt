package com.lajara.ev05clinicasalud.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.ev05clinicasalud.datos.MedicosDatos

@Composable
fun PerfilMedicoScreen(medicoId: Int, navController: NavController) {
    val medico = MedicosDatos.listaMedicos.find { it.id == medicoId }

    if (medico == null) {
        Text("Médico no encontrado")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(medico.nombre, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Especialidad: ${medico.especialidad}", style = MaterialTheme.typography.bodyLarge)
        Text("Calificación: ${medico.calificacion}", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("agendar_cita/${medico.id}") }) {
            Text("Agendar cita")
        }
    }
}