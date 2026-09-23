package com.lajara.clinicasalud.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lajara.clinicasalud.data.medicos

private val Morado = Color(0xFF6A2CA0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmacionScreen(
    medicoId: Int,
    fecha: String,
    hora: String,
    onInicioClick: () -> Unit
) {

    val medico = medicos.find {
        it.id == medicoId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Confirmación",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "✓",
                fontSize = 48.sp,
                color = Morado
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Cita confirmada",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Médico",
                fontWeight = FontWeight.Bold
            )

            Text(
                text = medico?.nombre ?: "Médico"
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Fecha",
                fontWeight = FontWeight.Bold
            )

            Text(
                text = fecha
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "Hora",
                fontWeight = FontWeight.Bold
            )

            Text(
                text = hora
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onInicioClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Volver al inicio"
                )
            }
        }
    }
}