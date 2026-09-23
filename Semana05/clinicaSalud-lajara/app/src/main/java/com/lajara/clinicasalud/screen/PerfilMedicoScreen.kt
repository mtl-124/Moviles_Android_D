package com.lajara.clinicasalud.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
fun PerfilMedicoScreen(
    medicoId: Int,
    onBackClick: () -> Unit,
    onAgendarClick: () -> Unit
) {

    val medico = medicos.find {
        it.id == medicoId
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Perfil del médico",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick
                    ) {
                        Text(
                            text = "←",
                            fontSize = 24.sp
                        )
                    }
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = Color(0xFFEDE4F5),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    color = Morado,
                    fontSize = 40.sp
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = medico?.nombre ?: "Médico",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )

            Text(
                text = medico?.especialidad ?: "",
                fontSize = 12.sp,
                color = Color.Gray
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "⭐ ${medico?.calificacion ?: 0.0} (128 reseñas)",
                fontSize = 13.sp
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Column(
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Especialista en ${medico?.especialidad ?: "medicina"} y atención médica.",
                    fontSize = 14.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Profesional con experiencia y atención personalizada.",
                    fontSize = 14.sp
                )
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = onAgendarClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Morado
                )
            ) {
                Text(
                    text = "Agendar cita",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}