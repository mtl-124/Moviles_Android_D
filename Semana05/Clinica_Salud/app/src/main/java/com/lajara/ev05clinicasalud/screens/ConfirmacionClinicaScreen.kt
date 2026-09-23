package com.lajara.ev05clinicasalud.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.ev05clinicasalud.datos.Cita
import com.lajara.ev05clinicasalud.datos.CitasDatos
import com.lajara.ev05clinicasalud.datos.MedicosDatos

@Composable
fun ConfirmacionClinicaScreen(medicoId: Int, fecha: String, hora: String, navController: NavController) {
    val medico = MedicosDatos.listaMedicos.find { it.id == medicoId }

    LaunchedEffect(medicoId, fecha, hora) {
        if (medico != null) {
            CitasDatos.listaCitas.add(Cita(medico, fecha, hora))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text("¡Cita confirmada!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (medico != null) {
            Text("Médico: ${medico.nombre}")
            Text("Especialidad: ${medico.especialidad}")
        }
        Text("Fecha: $fecha")
        Text("Hora: $hora")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            navController.navigate("inicio") {
                popUpTo("inicio") { inclusive = true }
            }
        }) {
            Text("Volver al inicio")
        }
    }
}