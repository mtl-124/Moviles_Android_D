package com.lajara.tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    clase: Clase,
    onBack: () -> Unit,
    onReservar: (String) -> Unit,
) {
    val horarios = listOf("6:00 pm", "7:00 pm", "8:00 pm")
    var horarioSeleccionado by remember { mutableStateOf(horarios[0]) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalle de clase",
                        fontWeight = FontWeight.Bold,
                        color = NegroTexto,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Regresar",
                            tint = NegroTexto,
                        )
                    }
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
        ) {
            // Recuadro VerdeClaro de ancho completo, 120dp de alto, esquinas 12dp con ícono de pesa grande
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(VerdeClaro),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Default.FitnessCenter,
                    contentDescription = "Pesa",
                    tint = VerdePrincipal,
                    modifier = Modifier.size(56.dp),
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nombre en negrita 20sp
            Text(
                text = clase.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = NegroTexto,
            )

            Spacer(modifier = Modifier.height(4.dp))

            // "horario · sala · duración" en gris
            Text(
                text = "${clase.horario} · ${clase.sala} · ${clase.duracion}",
                color = TextoSecundario,
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Descripción
            Text(
                text = clase.descripcion,
                color = TextoSecundario,
            )

            Spacer(modifier = Modifier.height(8.dp))

            // "X de Y cupos disponibles"
            Text(
                text = "${clase.cuposDisponibles} de ${clase.cuposTotales} cupos disponibles",
                color = TextoSecundario,
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Selecciona un horario",
                fontWeight = FontWeight.Bold,
                color = NegroTexto,
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Chips: selección única, verdes cuando seleccionados y #F0F0F0 si no
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(horarios) { horario ->
                    val seleccionado = horario == horarioSeleccionado

                    Row(
                        modifier = Modifier
                            .clickable { horarioSeleccionado = horario }
                            .background(
                                color = if (seleccionado) VerdePrincipal else FondoTarjeta,
                                shape = RoundedCornerShape(50.dp),
                            )
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        Text(
                            text = horario,
                            color = if (seleccionado) Blanco else NegroTexto,
                            fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.Normal,
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón "Reservar cupo" fijo abajo, verde, ancho completo, 52dp de alto, esquinas 12dp
            Button(
                onClick = { onReservar(horarioSeleccionado) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VerdePrincipal),
            ) {
                Text(
                    text = "Reservar cupo",
                    color = Blanco,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
            }
        }
    }
}
