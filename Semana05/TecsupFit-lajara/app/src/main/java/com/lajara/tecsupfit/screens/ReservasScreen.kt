package com.lajara.tecsupfit.screens

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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.GrisClaro
import com.lajara.tecsupfit.ui.theme.GrisTexto
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdeTecsup

data class ReservaItem(
    val clase: Clase,
    val estado: String // "Confirmada" o "Completada"
)

@Composable
fun ReservasScreen(
    modifier: Modifier = Modifier
) {
    val reservas = listOf(
        ReservaItem(
            clase = Clase(
                id = 2,
                nombre = "Cross Training",
                horario = "6:00 pm",
                sala = "Sala 1",
                duracion = "45 min",
                descripcion = "Entrenamiento funcional.",
                cuposDisponibles = 8,
                cuposTotales = 12
            ),
            estado = "Confirmada"
        ),
        ReservaItem(
            clase = Clase(
                id = 1,
                nombre = "Yoga funcional",
                horario = "7:00 am",
                sala = "Sala 2",
                duracion = "45 min",
                descripcion = "Movilidad y resistencia.",
                cuposDisponibles = 10,
                cuposTotales = 12
            ),
            estado = "Completada"
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
            items(reservas) { item ->
                val esConfirmada = item.estado == "Confirmada"

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = GrisClaro,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(16.dp)
                ) {
                    Text(
                        text = item.clase.nombre,
                        color = NegroTexto
                    )

                    Text(
                        text = "${item.clase.horario} · ${item.clase.sala}",
                        color = GrisTexto
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .background(
                                color = if (esConfirmada) VerdeClaro else GrisClaro,
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = item.estado,
                            color = if (esConfirmada) VerdeTecsup else GrisTexto
                        )
                    }
                }
            }
        }
    }
}