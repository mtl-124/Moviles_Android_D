package com.lajara.clinicasalud.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lajara.clinicasalud.data.medicos

private val Morado = Color(0xFF6A2CA0)

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
        "25 Sep",
        "26 Sep",
        "27 Sep"
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
                    androidx.compose.material3.IconButton(
                        onClick = onBackClick
                    ) {
                        Text("←")
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
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = medico?.nombre ?: "Médico"
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Selecciona una fecha",
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                fechas.forEach { fecha ->

                    FilterChip(
                        selected = fechaSeleccionada == fecha,
                        onClick = {
                            fechaSeleccionada = fecha
                        },
                        label = {
                            Text(fecha)
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = "Selecciona una hora",
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                horas.forEach { hora ->

                    FilterChip(
                        selected = horaSeleccionada == hora,
                        onClick = {
                            horaSeleccionada = hora
                        },
                        label = {
                            Text(hora)
                        }
                    )
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
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Confirmar cita"
                )
            }
        }
    }
}