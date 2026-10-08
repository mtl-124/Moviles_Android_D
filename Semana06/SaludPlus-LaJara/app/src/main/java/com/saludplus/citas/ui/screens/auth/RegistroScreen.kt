package com.saludplus.citas.ui.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.saludplus.citas.data.model.Usuario
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.BotonPrimario
import com.saludplus.citas.ui.components.TopBarSaludPlus
import java.util.UUID

@Composable
fun RegistroScreen(
    onRegistroExitoso: () -> Unit,
    onIrALogin: () -> Unit,
    onBackClick: () -> Unit
) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var mensajeError by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBarSaludPlus(titulo = "Crear Cuenta", onBackClick = onBackClick) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it; mensajeError = "" },
                label = { Text("Nombre completo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it; mensajeError = "" },
                label = { Text("Correo electrónico") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = telefono,
                onValueChange = { telefono = it; mensajeError = "" },
                label = { Text("Teléfono") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it; mensajeError = "" },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )

            if (mensajeError.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(mensajeError, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.height(24.dp))

            BotonPrimario(
                texto = "Registrarse",
                onClick = {
                    if (nombre.isBlank() || correo.isBlank() || telefono.isBlank() || password.isBlank()) {
                        mensajeError = "Todos los campos son obligatorios"
                    } else {
                        val nuevoUsuario = Usuario(
                            id = UUID.randomUUID().toString(),
                            nombre = nombre,
                            correo = correo,
                            telefono = telefono,
                            password = password
                        )
                        if (Repositorio.registrarUsuario(nuevoUsuario)) {
                            onRegistroExitoso()
                        } else {
                            mensajeError = "El correo ya se encuentra registrado"
                        }
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = onIrALogin) {
                Text("¿Ya tienes una cuenta? Inicia sesión")
            }
        }
    }
}