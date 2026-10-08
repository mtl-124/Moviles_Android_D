package com.saludplus.citas.ui.screens.citas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saludplus.citas.ui.components.TopBarSaludPlus

@Composable
fun MisCitasScreen(
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { TopBarSaludPlus(titulo = "Mis Citas", mostrarBotonAtras = false) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Próximamente: Lista de citas agendadas",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}