package com.lajara.tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.GrisClaro
import com.lajara.tecsupfit.ui.theme.GrisTexto
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.VerdeTecsup

@Composable
fun DetalleScreen(
    clase: Clase,
    onBack: () -> Unit,
    onReservar: (String) -> Unit // Cambiado para enviar el horario seleccionado
) {
    val horarios = listOf("6:00 pm", "7:00 pm", "8:00 pm")
    var horarioSeleccionado by remember { mutableStateOf(horarios[0]) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = clase.nombre,
            color = NegroTexto
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = clase.descripcion,
            color = GrisTexto
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Selecciona un horario",
            color = NegroTexto
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Selección única de horario
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(horarios) { horario ->
                val seleccionado = horario == horarioSeleccionado

                Row(
                    modifier = Modifier
                        .clickable { horarioSeleccionado = horario }
                        .background(
                            color = if (seleccionado) VerdeTecsup else GrisClaro,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = horario,
                        color = if (seleccionado) Color.White else GrisTexto
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onReservar(horarioSeleccionado) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = VerdeTecsup)
        ) {
            Text("Reservar cupo", color = Color.White)
        }
    }
}