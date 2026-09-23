package com.lajara.tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.Blanco
import com.lajara.tecsupfit.ui.theme.GrisTexto
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdeTecsup

@Composable
fun ConfirmacionScreen(
    clase: Clase,
    onVerReservas: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blanco)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Column(
            modifier = Modifier
                .background(
                    color = VerdeClaro,
                    shape = RoundedCornerShape(50.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Reserva confirmada",
                tint = VerdeTecsup
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¡Cupo reservado!"
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = clase.nombre
        )

        Text(
            text = "${clase.horario} · ${clase.sala}",
            color = GrisTexto
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onVerReservas,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Ver mis reservas"
            )
        }
    }
}