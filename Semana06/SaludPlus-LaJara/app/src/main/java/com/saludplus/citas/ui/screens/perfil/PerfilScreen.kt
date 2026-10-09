package com.saludplus.citas.ui.screens.perfil

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.TopBarSaludPlus
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.FondoClaro
import com.saludplus.citas.ui.theme.GrisTexto
import com.saludplus.citas.ui.theme.PastelAzulFondo

@Composable
fun PerfilScreen(
    onCerrarSesion: () -> Unit,
    onNavegarAMisCitas: () -> Unit = {}
) {
    val usuario = Repositorio.usuarioActual
    val totalCitas = remember(usuario?.id) {
        if (usuario != null) Repositorio.citasDelUsuario(usuario.id).size else 0
    }

    val iniciales = remember(usuario?.nombre) {
        obtenerIniciales(usuario?.nombre ?: "")
    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopBarSaludPlus(
                titulo = "Mi perfil",
                mostrarBotonAtras = false
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                // Círculo de 96.dp con fondo Azul pastel e iniciales en AzulPrimario
                Surface(
                    shape = CircleShape,
                    color = PastelAzulFondo,
                    modifier = Modifier.size(96.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = iniciales,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulPrimario
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Nombre completo y teléfono
                Text(
                    text = usuario?.nombre ?: "Paciente Registrado",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulMarino,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = usuario?.telefono ?: "Sin teléfono",
                    fontSize = 15.sp,
                    color = GrisTexto,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Tarjeta "Mis datos" (fondo FondoClaro, esquinas 20.dp, sin borde)
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = FondoClaro
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        FilaDatoPerfil(
                            icon = Icons.Default.Person,
                            etiqueta = "Nombre completo",
                            valor = usuario?.nombre ?: "Sin registrar"
                        )

                        HorizontalDivider(color = Color(0xFFE8ECF5))

                        FilaDatoPerfil(
                            icon = Icons.Default.Phone,
                            etiqueta = "Teléfono",
                            valor = usuario?.telefono ?: "Sin registrar"
                        )

                        HorizontalDivider(color = Color(0xFFE8ECF5))

                        FilaDatoPerfil(
                            icon = Icons.Default.Email,
                            etiqueta = "Correo electrónico",
                            valor = if (!usuario?.correo.isNullOrBlank()) usuario.correo else "No registrado"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tarjeta "Mis citas"
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    border = BorderStroke(1.dp, Color(0xFFE8ECF5)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                modifier = Modifier.size(48.dp),
                                shape = RoundedCornerShape(14.dp),
                                color = PastelAzulFondo
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Icon(
                                        imageVector = Icons.Default.CalendarMonth,
                                        contentDescription = null,
                                        tint = AzulPrimario,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = "Mis citas",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = AzulMarino
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = if (totalCitas == 1) "1 cita agendada" else "$totalCitas citas agendadas",
                                    fontSize = 13.sp,
                                    color = GrisTexto
                                )
                            }
                        }

                        TextButton(onClick = onNavegarAMisCitas) {
                            Text(
                                text = "Ver mis citas",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = AzulPrimario
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botón "Cerrar sesión" en OutlinedButton rojo #E5384B
            OutlinedButton(
                onClick = {
                    Repositorio.cerrarSesion()
                    onCerrarSesion()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.5.dp, Color(0xFFE5384B)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFFE5384B)
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ExitToApp,
                    contentDescription = null,
                    tint = Color(0xFFE5384B)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cerrar sesión",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun FilaDatoPerfil(
    icon: ImageVector,
    etiqueta: String,
    valor: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier.size(52.dp),
            shape = RoundedCornerShape(14.dp),
            color = PastelAzulFondo
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
        Spacer(modifier = Modifier.width(14.dp))
        Column {
            Text(
                text = etiqueta,
                fontSize = 12.sp,
                color = GrisTexto
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = valor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = AzulMarino
            )
        }
    }
}

private fun obtenerIniciales(nombre: String): String {
    if (nombre.isBlank()) return "SP"
    val limpio = nombre.trim()
    val palabras = limpio.split("\\s+".toRegex()).filter { it.isNotBlank() }
    return when {
        palabras.isEmpty() -> "SP"
        palabras.size == 1 -> palabras[0].take(2).uppercase()
        else -> "${palabras[0].first().uppercaseChar()}${palabras[1].first().uppercaseChar()}"
    }
}
