package com.saludplus.citas.ui.screens.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.BotonPrimario
import com.saludplus.citas.ui.components.TopBarSaludPlus

@Composable
fun PerfilScreen(
    onCerrarSesion: () -> Unit
) {
    val usuario = Repositorio.usuarioActual

    Scaffold(
        topBar = { TopBarSaludPlus(titulo = "Mi Perfil", mostrarBotonAtras = false) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = usuario?.nombre ?: "Paciente",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = usuario?.correo ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            BotonPrimario(
                texto = "Cerrar Sesión",
                onClick = {
                    Repositorio.cerrarSesion()
                    onCerrarSesion()
                }
            )
        }
    }
}