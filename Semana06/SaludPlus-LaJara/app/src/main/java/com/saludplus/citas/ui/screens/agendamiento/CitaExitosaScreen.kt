package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.saludplus.citas.data.repository.Repositorio
import com.saludplus.citas.ui.theme.AzulMarino
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.FondoClaro
import com.saludplus.citas.ui.theme.GrisTexto
import com.saludplus.citas.ui.util.FechasUtil

@Composable
fun CitaExitosaScreen(
    citaId: String,
    onVolverInicio: () -> Unit,
    onVerMisCitas: () -> Unit = onVolverInicio
) {
    val cita = Repositorio.obtenerCita(citaId)
    val medico = cita?.let { Repositorio.obtenerMedico(it.medicoId) }
    val especialidad = medico?.let { Repositorio.obtenerEspecialidad(it.especialidadId) }

    val fechaFormateada = remember(cita?.fecha) {
        cita?.fecha?.let { f ->
            FechasUtil.desdeTexto(f)?.let { FechasUtil.fechaLarga(it) } ?: f
        } ?: "-"
    }

    Scaffold(
        containerColor = Color.White
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Círculo verde pastel de 120.dp con check verde grande (#1F7A4D)
                Surface(
                    modifier = Modifier.size(120.dp),
                    shape = CircleShape,
                    color = Color(0xFFE3F8EE)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color(0xFF1F7A4D),
                            modifier = Modifier.size(64.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "¡Cita agendada!",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulMarino,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Te esperamos en la clínica",
                    fontSize = 15.sp,
                    color = GrisTexto,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(28.dp))

                // Tarjeta resumen FondoClaro
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = FondoClaro
                    ),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Código de Confirmación",
                            fontSize = 12.sp,
                            color = GrisTexto
                        )
                        Text(
                            text = citaId,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = AzulPrimario
                        )

                        if (medico != null && cita != null) {
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = Color(0xFFE8ECF5)
                            )
                            Text(
                                text = "Dr(a). ${medico.nombre}",
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold,
                                color = AzulMarino
                            )
                            Text(
                                text = especialidad?.nombre ?: "Especialidad General",
                                fontSize = 13.sp,
                                color = GrisTexto
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "$fechaFormateada a las ${cita.hora}",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                                color = AzulPrimario
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botones "Ver mis citas" e "Ir al inicio"
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = onVerMisCitas,
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
                        text = "Ver mis citas",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                OutlinedButton(
                    onClick = onVolverInicio,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = AzulPrimario
                    )
                ) {
                    Text(
                        text = "Ir al inicio",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}
