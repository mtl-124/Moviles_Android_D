package com.lajara.tecsupfit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.ui.theme.FondoTarjeta
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.TextoSecundario
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdePrincipal

@Composable
fun ClaseCard(
    clase: Clase,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .background(
                color = FondoTarjeta,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = VerdeClaro,
                    shape = RoundedCornerShape(8.dp)
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = Icons.Default.FitnessCenter,
                contentDescription = "Clase",
                tint = VerdePrincipal
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Column {

            Text(
                text = clase.nombre,
                fontWeight = FontWeight.Bold,
                color = NegroTexto
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "${clase.horario} · ${clase.sala}",
                color = TextoSecundario
            )
        }
    }
}
