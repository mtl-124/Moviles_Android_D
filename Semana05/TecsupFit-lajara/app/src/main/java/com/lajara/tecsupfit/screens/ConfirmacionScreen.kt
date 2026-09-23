package com.lajara.tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.Blanco
import com.lajara.tecsupfit.ui.theme.FondoTarjeta
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.TextoSecundario
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdePrincipal

@Composable
fun ConfirmacionScreen(
    clase: Clase,
    onVerReservas: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Blanco)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        // Círculo VerdeClaro con un check verde
        Column(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(VerdeClaro),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Reserva confirmada",
                tint = VerdePrincipal,
                modifier = Modifier.size(40.dp),
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Título "¡Cupo reservado!" en negrita
        Text(
            text = "¡Cupo reservado!",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = NegroTexto,
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Nombre de la clase en negrita
        Text(
            text = clase.nombre,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = NegroTexto,
        )

        Spacer(modifier = Modifier.height(4.dp))

        // "día, hora · sala" en gris
        Text(
            text = "Hoy, ${clase.horario} · ${clase.sala}",
            color = TextoSecundario,
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Botón tonal "Ver mis reservas" con fondo #F0F0F0
        Button(
            onClick = onVerReservas,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = FondoTarjeta,
                contentColor = VerdePrincipal,
            ),
        ) {
            Text(
                text = "Ver mis reservas",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
        }
    }
}
