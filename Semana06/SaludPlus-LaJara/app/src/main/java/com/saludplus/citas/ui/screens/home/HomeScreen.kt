package com.saludplus.citas.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.model.Especialidad
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.components.IconoEspecialidad
import com.saludplus.citas.ui.components.obtenerIconoEspecialidad
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTexto
import com.saludplus.citas.ui.theme.PastelAzulFondo
import com.saludplus.citas.ui.theme.PastelCelesteFondo
import com.saludplus.citas.ui.theme.PastelLilaFondo
import com.saludplus.citas.ui.theme.PastelNaranjaFondo
import com.saludplus.citas.ui.theme.PastelVerdeFondo

@Composable
fun HomeScreen(
    onNavegarASedes: () -> Unit,
    onNavegarAEspecialidades: () -> Unit,
    onSeleccionarEspecialidad: (String) -> Unit,
    onNavegarAMisCitas: () -> Unit,
    onNavegarAMisDoctores: () -> Unit = {},
    onNavegarAPerfil: () -> Unit = {},
    onNavegarAResultados: () -> Unit = {},
    onNavegarANotificaciones: () -> Unit = {}
) {
    val usuario = Repositorio.usuarioActual
    val especialidadesDestacadas = Repositorio.especialidadesDestacadas()

    val primerNombre = remember(usuario?.nombre) {
        val n = usuario?.nombre?.trim() ?: ""
        if (n.isNotEmpty()) n.split("\\s+".toRegex()).firstOrNull() else null
    }

    val saludo = if (!primerNombre.isNullOrEmpty()) "¡Hola, $primerNombre!" else "¡Hola!"

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 16.dp)
        ) {
            // Fila superior: Menú y Campana de Notificaciones
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú",
                        tint = AzulMarino,
                        modifier = Modifier.size(28.dp)
                    )
                }

                IconButton(onClick = onNavegarANotificaciones) {
                    Icon(
                        imageVector = Icons.Default.NotificationsNone,
                        contentDescription = "Notificaciones",
                        tint = AzulMarino,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Saludo dinámico al usuario activo
            Text(
                text = saludo,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = AzulMarino
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "¿Qué deseas hacer hoy?",
                fontSize = 17.sp,
                color = GrisTexto
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Fila 1: Sedes (Azul) y Mis citas (Verde)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TarjetaCuadranteHome(
                    titulo = "Sedes",
                    icon = Icons.Default.LocationOn,
                    backgroundColor = PastelAzulFondo,
                    circleColor = Color(0xFF2563EB),
                    textColor = Color(0xFF1E40AF),
                    onClick = onNavegarASedes,
                    modifier = Modifier.weight(1f)
                )

                TarjetaCuadranteHome(
                    titulo = "Mis citas",
                    icon = Icons.Default.CalendarMonth,
                    backgroundColor = PastelVerdeFondo,
                    circleColor = Color(0xFF2E9E5B),
                    textColor = Color(0xFF1F7A4D),
                    onClick = onNavegarAMisCitas,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Fila 2: Mis doctores (Celeste) y Mis datos (Lila)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                TarjetaCuadranteHome(
                    titulo = "Mis doctores",
                    icon = Icons.Default.MedicalServices,
                    backgroundColor = PastelCelesteFondo,
                    circleColor = Color(0xFF0891B2),
                    textColor = Color(0xFF0E6F8A),
                    onClick = onNavegarAMisDoctores,
                    modifier = Modifier.weight(1f)
                )

                TarjetaCuadranteHome(
                    titulo = "Mis datos",
                    icon = Icons.Default.Person,
                    backgroundColor = PastelLilaFondo,
                    circleColor = Color(0xFF8B5CF6),
                    textColor = Color(0xFF6D3FD6),
                    onClick = onNavegarAPerfil,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Fila 3: Resultados (Naranja) - Ancho completo
            TarjetaCuadranteHome(
                titulo = "Resultados",
                icon = Icons.Default.Description,
                backgroundColor = PastelNaranjaFondo,
                circleColor = Color(0xFFF59E0B),
                textColor = Color(0xFFB45309),
                onClick = onNavegarAResultados,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Encabezado "Especialidades destacadas"
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Especialidades destacadas",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = AzulMarino
                )
                TextButton(onClick = onNavegarAEspecialidades) {
                    Text(
                        text = "Ver todas",
                        fontSize = 15.sp,
                        color = AzulPrimario,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // LazyRow de tarjetas de especialidades destacadas
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(end = 4.dp)
            ) {
                items(especialidadesDestacadas) { especialidad ->
                    TarjetaEspecialidadDestacada(
                        especialidad = especialidad,
                        onClick = { onSeleccionarEspecialidad(especialidad.id) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun TarjetaCuadranteHome(
    titulo: String,
    icon: ImageVector,
    backgroundColor: Color,
    circleColor: Color,
    textColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(140.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(64.dp),
                shape = CircleShape,
                color = circleColor
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = titulo,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun TarjetaEspecialidadDestacada(
    especialidad: Especialidad,
    onClick: () -> Unit
) {
    val datosIcono = obtenerIconoEspecialidad(especialidad.nombre)

    Card(
        modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color(0xFFE8ECF5)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Surface(
                modifier = Modifier.size(72.dp),
                shape = CircleShape,
                color = datosIcono.colorFondo
            ) {
                IconoEspecialidad(
                    nombre = especialidad.nombre,
                    sizeIcono = 36.dp
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = especialidad.nombre,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = AzulMarino,
                textAlign = TextAlign.Center,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                lineHeight = 18.sp
            )
        }
    }
}
