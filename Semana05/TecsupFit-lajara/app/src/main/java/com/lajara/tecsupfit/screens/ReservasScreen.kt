package com.lajara.tecsupfit.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.components.ClaseCard
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.NegroTexto

@Composable
fun ReservasScreen(
    modifier: Modifier = Modifier
) {
    val reservas = listOf(
        Clase(
            id = 2,
            nombre = "Cross Training",
            horario = "6:00 pm",
            sala = "Sala 1",
            duracion = "45 min",
            descripcion = "Entrenamiento funcional.",
            cuposDisponibles = 8,
            cuposTotales = 12
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = "Mis reservas",
            color = NegroTexto
        )

        Spacer(modifier = Modifier.height(18.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(reservas) { reserva ->
                ClaseCard(
                    clase = reserva,
                    onClick = { }
                )
            }
        }
    }
}