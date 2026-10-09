package com.saludplus.citas.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.EventNote
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.model.Especialidad
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.theme.AzulMarinoTitulos
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.GrisTextoSecundario

@Composable
fun HomeScreen(
    onNavegarAEspecialidades: () -> Unit,
    onSeleccionarEspecialidad: (String) -> Unit,
    onNavegarAMisCitas: () -> Unit,
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Fila superior: Ícono de menú a la izquierda y Campana (Notificaciones) a la derecha
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menú",
                    tint = AzulMarinoTitulos,
                    modifier = Modifier.size(28.dp)
                )
            }

            IconButton(onClick = onNavegarANotificaciones) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notificaciones",
                    tint = AzulMarinoTitulos,
                    modifier = Modifier.size(28.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Saludo dinámico al usuario activo
        Text(
            text = saludo,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = AzulMarinoTitulos
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "¿Qué deseas hacer hoy?",
            fontSize = 15.sp,
            color = GrisTextoSecundario
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Cuadrícula 2x2 de Tarjetas de Acceso Rápido
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 1. Agendar cita (Azul)
            TarjetaCuadranteHome(
                titulo = "Agendar cita",
                icon = Icons.Default.CalendarMonth,
                backgroundColor = Color(0xFFEBF3FE),
                iconColor = Color(0xFF2563EB),
                onClick = onNavegarAEspecialidades,
                modifier = Modifier.weight(1f)
            )

            // 2. Mis citas (Verde)
            TarjetaCuadranteHome(
                titulo = "Mis citas",
                icon = Icons.AutoMirrored.Filled.EventNote,
                backgroundColor = Color(0xFFE8F5E9),
                iconColor = Color(0xFF2E7D32),
                onClick = onNavegarAMisCitas,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // 3. Mis datos (Morado / Lila)
            TarjetaCuadranteHome(
                titulo = "Mis datos",
                icon = Icons.Default.Person,
                backgroundColor = Color(0xFFF3E5F5),
                iconColor = Color(0xFF7B1FA2),
                onClick = onNavegarAPerfil,
                modifier = Modifier.weight(1f)
            )

            // 4. Resultados (Naranja)
            TarjetaCuadranteHome(
                titulo = "Resultados",
                icon = Icons.Default.LocalHospital,
                backgroundColor = Color(0xFFFFF3E0),
                iconColor = Color(0xFFE65100),
                onClick = onNavegarAResultados,
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(28.dp))

        // Fila "Especialidades destacadas"
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Especialidades destacadas",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = AzulMarinoTitulos
            )
            TextButton(onClick = onNavegarAEspecialidades) {
                Text(
                    text = "Ver todas",
                    fontSize = 14.sp,
                    color = AzulPrimario,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // LazyRow de tarjetas de especialidades
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(especialidadesDestacadas) { especialidad ->
                TarjetaEspecialidadItem(
                    especialidad = especialidad,
                    onClick = { onSeleccionarEspecialidad(especialidad.id) }
                )
            }
        }
    }
}

@Composable
private fun TarjetaCuadranteHome(
    titulo: String,
    icon: ImageVector,
    backgroundColor: Color,
    iconColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.height(120.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Surface(
                modifier = Modifier.size(42.dp),
                shape = CircleShape,
                color = Color.White
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Text(
                text = titulo,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = AzulMarinoTitulos
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TarjetaEspecialidadItem(
    especialidad: Especialidad,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = Modifier.width(140.dp),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                color = Color(0xFFF4F7FF)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.LocalHospital,
                        contentDescription = null,
                        tint = AzulPrimario,
                        modifier = Modifier.size(26.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = especialidad.nombre,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = AzulMarinoTitulos
            )
        }
    }
}
