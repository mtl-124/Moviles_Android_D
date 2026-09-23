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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.Blanco
import com.lajara.tecsupfit.ui.theme.GrisTexto
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdeTecsup

@Composable
fun DetalleScreen(
    clase: Clase,
    onBack: () -> Unit,
    onReservar: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blanco)
            .padding(18.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = onBack
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Regresar"
                )
            }

            Text(
                text = "Detalle de clase",
                color = NegroTexto
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    color = VerdeClaro,
                    shape = RoundedCornerShape(14.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = "Clase",
                tint = VerdeTecsup
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = clase.nombre,
            color = NegroTexto
        )

        Text(
            text = "${clase.horario} · ${clase.sala} · ${clase.duracion}",
            color = GrisTexto
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = clase.descripcion,
            color = NegroTexto
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "${clase.cuposDisponibles} de ${clase.cuposTotales} cupos disponibles",
            color = NegroTexto
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onReservar,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Reservar cupo"
            )
        }
    }
}