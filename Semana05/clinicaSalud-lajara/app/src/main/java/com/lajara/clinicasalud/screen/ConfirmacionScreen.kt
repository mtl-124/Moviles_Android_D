package com.lajara.clinicasalud.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.lajara.clinicasalud.ui.theme.FondoTarjeta
import com.lajara.clinicasalud.ui.theme.MoradoPrincipal
import com.lajara.clinicasalud.ui.theme.TextoSecundario
import com.lajara.clinicasalud.ui.theme.VerdeClaro
import com.lajara.clinicasalud.ui.theme.VerdeExito

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConfirmacionScreen(
    medicoId: Int,
    fecha: String,
    hora: String,
    onInicioClick: () -> Unit,
    onVerMisCitasClick: () -> Unit = {}
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .size(72.dp)
                    .background(
                        color = VerdeClaro,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✓",
                    fontSize = 40.sp,
                    color = VerdeExito
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "¡Cita agendada!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = medico?.nombre ?: "Médico",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = "$fecha, $hora",
                fontSize = 14.sp,
                color = TextoSecundario
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onVerMisCitasClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = FondoTarjeta,
                    contentColor = MoradoPrincipal
                )
            ) {
                Text(
                    text = "Ver mis citas",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Button(
                onClick = onInicioClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoradoPrincipal
                )
            ) {
                Text(
                    text = "Volver al inicio",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
