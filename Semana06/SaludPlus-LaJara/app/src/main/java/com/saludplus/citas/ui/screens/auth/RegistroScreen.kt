package com.saludplus.citas.ui.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.model.Usuario
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTexto
import java.util.UUID

@Composable
fun RegistroScreen(
    onRegistroExitoso: () -> Unit,
    onIrALogin: () -> Unit,
    onBackClick: () -> Unit,
    onIrATerminos: () -> Unit = {}
) {
    var nombre by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var errorNombre by remember { mutableStateOf<String?>(null) }
    var errorTelefono by remember { mutableStateOf<String?>(null) }
    var errorCorreo by remember { mutableStateOf<String?>(null) }
    var errorPassword by remember { mutableStateOf<String?>(null) }
    var errorGeneral by remember { mutableStateOf<String?>(null) }

    fun validarFormulario(): Boolean {
        var esValido = true
        errorNombre = null
        errorTelefono = null
        errorCorreo = null
        errorPassword = null
        errorGeneral = null

        if (nombre.trim().isEmpty()) {
            errorNombre = "Ingresa tu nombre completo"
            esValido = false
        }

        val telLimpio = telefono.trim()
        if (telLimpio.length != 9 || !telLimpio.all { it.isDigit() }) {
            errorTelefono = "El teléfono debe tener 9 dígitos"
            esValido = false
        } else if (Repositorio.existeTelefono(telLimpio)) {
            errorTelefono = "Este teléfono ya está registrado"
            esValido = false
        }

        val correoLimpio = correo.trim()
        if (correoLimpio.isNotEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(correoLimpio).matches()) {
            errorCorreo = "Correo electrónico no válido"
            esValido = false
        } else if (correoLimpio.isNotEmpty() && Repositorio.existeCorreo(correoLimpio)) {
            errorCorreo = "Este correo ya está registrado"
            esValido = false
        }

        if (password.length < 6) {
            errorPassword = "La contraseña debe tener al menos 6 caracteres"
            esValido = false
        }

        return esValido
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBarSaludPlus(
                titulo = "",
                mostrarBotonAtras = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .navigationBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Crear cuenta",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulMarino,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Regístrate para agendar tus citas",
                    fontSize = 14.sp,
                    color = GrisTexto,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // 1. Nombre Completo
                    CampoRegistroItem(
                        icon = Icons.Default.Person,
                        label = "Nombre completo",
                        value = nombre,
                        onValueChange = { nombre = it; errorNombre = null },
                        placeholder = "Juan Pérez",
                        errorText = errorNombre,
                        keyboardType = KeyboardType.Text
                    )

                    // 2. Teléfono
                    CampoRegistroItem(
                        icon = Icons.Default.Phone,
                        label = "Teléfono",
                        value = telefono,
                        onValueChange = { telefono = it; errorTelefono = null },
                        placeholder = "987654321",
                        errorText = errorTelefono,
                        keyboardType = KeyboardType.Number
                    )

                    // 3. Correo (opcional)
                    CampoRegistroItem(
                        icon = Icons.Default.Email,
                        label = "Correo (opcional)",
                        value = correo,
                        onValueChange = { correo = it; errorCorreo = null },
                        placeholder = "correo@ejemplo.com",
                        errorText = errorCorreo,
                        keyboardType = KeyboardType.Email
                    )

                    // 4. Contraseña
                    CampoRegistroItem(
                        icon = Icons.Default.Lock,
                        label = "Contraseña",
                        value = password,
                        onValueChange = { password = it; errorPassword = null },
                        placeholder = "••••••••",
                        errorText = errorPassword,
                        isPassword = true,
                        keyboardType = KeyboardType.Password
                    )
                }

                if (errorGeneral != null) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = errorGeneral!!,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // Botón Registrarme
                Button(
                    onClick = {
                        if (validarFormulario()) {
                            val nuevoUsuario = Usuario(
                                id = UUID.randomUUID().toString(),
                                nombre = nombre.trim(),
                                correo = correo.trim(),
                                telefono = telefono.trim(),
                                password = password
                            )
                            if (Repositorio.registrarUsuario(nuevoUsuario)) {
                                onRegistroExitoso()
                            } else {
                                errorGeneral = "Este teléfono ya está registrado"
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AzulPrimario,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        text = "Registrarme",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Términos y Condiciones
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Al registrarme acepto nuestros",
                        fontSize = 12.sp,
                        color = GrisTexto
                    )
                    Text(
                        text = "Términos y Condiciones",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario,
                        modifier = Modifier.clickable { onIrATerminos() }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Enlace a Iniciar Sesión al fondo
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "¿Ya tienes cuenta? ",
                        fontSize = 14.sp,
                        color = GrisTexto
                    )
                    Text(
                        text = "Iniciar sesión",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = AzulPrimario,
                        modifier = Modifier.clickable { onIrALogin() }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun CampoRegistroItem(
    icon: ImageVector,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    errorText: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    var passwordVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Surface(
            modifier = Modifier
                .size(52.dp)
                .padding(top = 4.dp),
            shape = RoundedCornerShape(14.dp),
            color = Color(0xFFF4F7FF)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = AzulPrimario,
                    modifier = Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                label = { Text(label, fontSize = 12.sp, color = GrisTexto) },
                placeholder = if (placeholder.isNotEmpty()) {
                    { Text(placeholder, fontSize = 14.sp, color = Color.Gray) }
                } else null,
                singleLine = true,
                isError = errorText != null,
                supportingText = errorText?.let { err ->
                    { Text(err, color = MaterialTheme.colorScheme.error, fontSize = 11.sp) }
                },
                visualTransformation = if (isPassword && !passwordVisible) {
                    PasswordVisualTransformation()
                } else {
                    VisualTransformation.None
                },
                trailingIcon = if (isPassword) {
                    {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña",
                                tint = GrisTexto
                            )
                        }
                    }
                } else null,
                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0xFFF4F7FF),
                    focusedContainerColor = Color(0xFFF4F7FF),
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = AzulPrimario.copy(alpha = 0.5f)
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
