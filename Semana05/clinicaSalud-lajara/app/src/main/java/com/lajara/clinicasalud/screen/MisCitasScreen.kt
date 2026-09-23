package com.lajara.clinicasalud.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lajara.clinicasalud.model.Cita

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasScreen(
    citas: List<Cita> = emptyList()
) {

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(
                        text = "Mis citas",
                        fontWeight = FontWeight.Bold
                    )
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        if (citas.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(20.dp)
            ) {

                Text(
                    text = "No tienes citas agendadas."
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                items(citas) { cita ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),

                        colors = CardDefaults.cardColors(
                            containerColor =
                                Color(0xFFF3F1F6)
                        )
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {

                            Text(
                                text = cita.medico,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = cita.especialidad
                            )

                            Text(
                                text =
                                    "${cita.fecha} - ${cita.hora}"
                            )

                            Text(
                                text = "Estado: ${cita.estado}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}