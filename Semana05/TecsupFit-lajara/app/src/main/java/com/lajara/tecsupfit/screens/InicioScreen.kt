package com.lajara.tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lajara.tecsupfit.components.ClaseCard
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.Blanco
import com.lajara.tecsupfit.ui.theme.FondoTarjeta
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.VerdePrincipal

@OptIn(ExperimentalMaterial3Api::class)
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

    val clasesFiltradas = when (filtroSeleccionado.value) {
        "Hoy" -> clases.filter { (it.id == 1) || (it.id == 2) }
        else -> clases
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "TECSUP Fit",
                            fontWeight = FontWeight.Bold,
                            color = Blanco
                        )
                        Text(
                            text = "Hola, Diego",
                            fontSize = 12.sp,
                            color = Blanco
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = VerdePrincipal
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blanco)
                .padding(innerPadding)
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
                            .clickable {
                                filtroSeleccionado.value = filtro
                            }
                            .background(
                                color = if (seleccionado) {
                                    VerdePrincipal
                                } else {
                                    FondoTarjeta
                                },
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = filtro,
                            color = if (seleccionado) {
                                Blanco
                            } else {
                                NegroTexto
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Clases disponibles",
                fontWeight = FontWeight.Bold,
                color = NegroTexto
            )

            Spacer(modifier = Modifier.height(12.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(clasesFiltradas) { clase ->

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
