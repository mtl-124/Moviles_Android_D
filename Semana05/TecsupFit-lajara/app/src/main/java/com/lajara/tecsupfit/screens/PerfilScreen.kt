package com.lajara.tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.ui.theme.GrisClaro
import com.lajara.tecsupfit.ui.theme.GrisTexto
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdeTecsup

@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = "Mi perfil",
            color = NegroTexto
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = GrisClaro,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(18.dp)
        ) {
            Text(
                text = "Gael",
                color = NegroTexto
            )

            Text(
                text = "Alumno TECSUP",
                color = GrisTexto
            )
        }

        Spacer(modifier = Modifier.padding(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = VerdeClaro,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "12",
                    color = VerdeTecsup
                )

                Text(
                    text = "Clases tomadas",
                    color = GrisTexto
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        color = VerdeClaro,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = "5 días",
                    color = VerdeTecsup
                )

                Text(
                    text = "Racha",
                    color = GrisTexto
                )
            }
        }
    }
}