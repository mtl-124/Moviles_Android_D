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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lajara.tecsupfit.ui.theme.Blanco
import com.lajara.tecsupfit.ui.theme.FondoTarjeta
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.TextoSecundario
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdePrincipal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mi perfil",
                        fontWeight = FontWeight.Bold,
                        color = NegroTexto,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Blanco,
                ),
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Blanco)
                .padding(innerPadding)
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            // Círculo VerdeClaro de 80dp con iniciales "DR" en verde y negrita
            Column(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(VerdeClaro),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "DR",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = VerdePrincipal,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre "Diego Ramos" en negrita
            Text(
                text = "Diego Ramos",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = NegroTexto,
            )

            Spacer(modifier = Modifier.height(4.dp))

            // "Plan Premium" en gris
            Text(
                text = "Plan Premium",
                color = TextoSecundario,
            )

            Spacer(modifier = Modifier.height(30.dp))

            // Dos tarjetas lado a lado del mismo ancho (fondo #F0F0F0, esquinas de 12dp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = FondoTarjeta,
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "14",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = NegroTexto,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Clases",
                        color = TextoSecundario,
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = FondoTarjeta,
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "3",
                        fontWeight = FontWeight.Bold,
                        fontSize = 22.sp,
                        color = NegroTexto,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Rachas",
                        color = TextoSecundario,
                    )
                }
            }
        }
    }
}
