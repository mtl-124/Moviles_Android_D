package com.lajara.clinicasalud.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarCitaScreen(
    medicoId: Int,
    onConfirmarClick: (String, String) -> Unit,
    onBackClick: () -> Unit
) {

    val medico = medicos.find {
        it.id == medicoId
    }

    val fechas = listOf(
        Triple("Mié", "24", "24 Sep"),
        Triple("Jue", "25", "25 Sep"),
        Triple("Vie", "26", "26 Sep")
    )

    val horas = listOf(
        "09:00 AM",
        "11:00 AM",
        "03:00 PM"
    )

    var fechaSeleccionada by remember {
        mutableStateOf("")
    }

    var horaSeleccionada by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Agendar cita",
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
                .padding(16.dp)
        ) {

            Text(
                text = "Médico",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = medico?.nombre ?: "Médico",
                fontSize = 15.sp,
                color = TextoSecundario
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Selecciona fecha",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                fechas.forEach { fecha ->
                    val isSelected = fechaSeleccionada == fecha.third

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                fechaSeleccionada = fecha.third
                            },
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) MoradoPrincipal else FondoTarjeta
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = fecha.first,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.White else TextoSecundario
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = fecha.second,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Selecciona hora",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                horas.forEach { hora ->
                    val isSelected = horaSeleccionada == hora

                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clickable {
                                horaSeleccionada = hora
                            },
                        shape = RoundedCornerShape(12.dp),
                        color = if (isSelected) MoradoPrincipal else FondoTarjeta
                    ) {
                        Box(
                            modifier = Modifier
                                .padding(vertical = 14.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = hora,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {
                    onConfirmarClick(
                        fechaSeleccionada,
                        horaSeleccionada
                    )
                },
                enabled = fechaSeleccionada.isNotEmpty()
                        && horaSeleccionada.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MoradoPrincipal
                )
            ) {
                Text(
                    text = "Confirmar cita",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }
    }
}
