package com.lajara.ev05tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.ev05tecsupfit.datos.ClasesDatos

@Composable
fun DetalleClaseScreen(claseId: Int, navController: NavController) {
    val clase = ClasesDatos.listaClases.find { it.id == claseId }

    if (clase == null) {
        Text("Clase no encontrada")
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("← Detalle de clase", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // Banner con icono grande, como en el mockup
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFDCEEE6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                Icons.Default.FitnessCenter,
                contentDescription = null,
                tint = VerdeTecsup,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(clase.nombre, style = MaterialTheme.typography.titleLarge)
        Text(
            "${clase.horario} · ${clase.sala} · ${clase.duracionMin} min",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(clase.descripcion, style = MaterialTheme.typography.bodyMedium)

        Spacer(modifier = Modifier.height(8.dp))
        Text(
            "${clase.cuposDisponibles} de ${clase.cuposTotales} cupos disponibles",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                navController.navigate("confirmacion_gym/${clase.id}/${clase.horario}")
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeTecsup),
            enabled = clase.cuposDisponibles > 0
        ) {
            Text("Reservar cupo")
        }
    }
}