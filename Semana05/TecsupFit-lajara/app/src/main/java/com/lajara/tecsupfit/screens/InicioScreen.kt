package com.example.tecsupfit.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.components.ClaseCard
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.Blanco
import com.lajara.tecsupfit.ui.theme.GrisClaro
import com.lajara.tecsupfit.ui.theme.GrisTexto
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.VerdeTecsup

@Composable
fun InicioScreen(
    onClaseClick: (Clase) -> Unit
) {

    val clases = listOf(
        Clase(
            id = 1,
            nombre = "Yoga funcional",
            horario = "7:00 am",
            sala = "Sala 2",
            duracion = "45 min",
            descripcion = "Entrenamiento funcional para mejorar movilidad y resistencia.",
            cuposDisponibles = 10,
            cuposTotales = 12
        ),
        Clase(
            id = 2,
            nombre = "Cross Training",
            horario = "6:00 pm",
            sala = "Sala 1",
            duracion = "45 min",
            descripcion = "Entrenamiento funcional de alta intensidad.",
            cuposDisponibles = 8,
            cuposTotales = 12
        ),
        Clase(
            id = 3,
            nombre = "Spinning",
            horario = "7:30 pm",
            sala = "Sala 3",
            duracion = "45 min",
            descripcion = "Sesión de bicicleta estática para mejorar resistencia.",
            cuposDisponibles = 6,
            cuposTotales = 10
        )
    )

    val filtros = listOf(
        "Hoy",
        "Esta semana"
    )

    val filtroSeleccionado = remember {
        mutableStateOf("Hoy")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blanco)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(VerdeTecsup)
                .padding(
                    horizontal = 18.dp,
                    vertical = 14.dp
                )
        ) {

            Text(
                text = "TECSUP Fit",
                color = Blanco
            )

            Text(
                text = "Hola, Gael",
                color = Blanco
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp)
        ) {

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(filtros) { filtro ->

                    val seleccionado =
                        filtro == filtroSeleccionado.value

                    Row(
                        modifier = Modifier
                            .background(
                                color = if (seleccionado) {
                                    VerdeTecsup
                                } else {
                                    GrisClaro
                                },
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            )
                            .padding(0.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        androidx.compose.material3.Text(
                            text = filtro,
                            color = if (seleccionado) {
                                Blanco
                            } else {
                                GrisTexto
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Clases disponibles",
                color = NegroTexto
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(clases) { clase ->

                    ClaseCard(
                        clase = clase,
                        onClick = {
                            onClaseClick(clase)
                        }
                    )
                }
            }
        }
    }
}