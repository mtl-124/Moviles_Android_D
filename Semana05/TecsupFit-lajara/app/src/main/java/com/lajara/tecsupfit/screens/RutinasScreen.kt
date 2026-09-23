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
import com.lajara.tecsupfit.ui.theme.GrisClaro
import com.lajara.tecsupfit.ui.theme.NegroTexto

@Composable
fun RutinasScreen(
    modifier: Modifier = Modifier
) {
    val rutinas = listOf(
        "Rutina de fuerza",
        "Rutina de resistencia",
        "Rutina de movilidad"
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp)
    ) {
        Text(
            text = "Mis rutinas",
            color = NegroTexto
        )

        Spacer(modifier = Modifier.height(18.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(rutinas) { rutina ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = GrisClaro,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(18.dp)
                ) {
                    Text(
                        text = rutina,
                        color = NegroTexto
                    )
                }
            }
        }
    }
}