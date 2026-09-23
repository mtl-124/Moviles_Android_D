package com.lajara.tecsupfit.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.model.EstadoReserva
import com.lajara.tecsupfit.model.Reserva
import com.lajara.tecsupfit.ui.theme.FondoTarjeta
import com.lajara.tecsupfit.ui.theme.GrisClaro
import com.lajara.tecsupfit.ui.theme.NegroTexto
import com.lajara.tecsupfit.ui.theme.TextoSecundario
import com.lajara.tecsupfit.ui.theme.VerdeClaro
import com.lajara.tecsupfit.ui.theme.VerdePrincipal

@Composable
fun ReservasScreen(
    modifier: Modifier = Modifier,
    reservas: List<Reserva> = emptyList(),
    onCancelarReserva: (Reserva) -> Unit = {},
) {
    var reservaAEliminar by remember { mutableStateOf<Reserva?>(null) }
    var showDialog by remember { mutableStateOf(value = false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(18.dp),
    ) {
        Text(
            text = "Mis reservas",
            fontWeight = FontWeight.Bold,
            color = NegroTexto,
        )

        Spacer(modifier = Modifier.height(18.dp))

        if (reservas.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Aún no tienes reservas",
                    color = TextoSecundario,
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                items(reservas) { reserva ->
                    val esConfirmada = reserva.estado == EstadoReserva.CONFIRMADA
                    val esCancelada = reserva.estado == EstadoReserva.CANCELADA

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(color = FondoTarjeta),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        // Left vertical bar (4dp width)
                        if (esConfirmada) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .fillMaxHeight()
                                    .background(VerdePrincipal),
                            )
                        } else if (esCancelada) {
                            Box(
                                modifier = Modifier
                                    .width(4.dp)
                                    .fillMaxHeight()
                                    .background(Color(0xFFC62828)),
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(16.dp),
                        ) {
                            Text(
                                text = reserva.clase.nombre,
                                fontWeight = FontWeight.Bold,
                                color = NegroTexto,
                            )

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = "${reserva.dia}, ${reserva.horario}",
                                color = TextoSecundario,
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                val (bgColor, textColor, textLabel) = when (reserva.estado) {
                                    EstadoReserva.CONFIRMADA -> Triple(VerdeClaro, VerdePrincipal, "Confirmada")
                                    EstadoReserva.COMPLETADA -> Triple(GrisClaro, TextoSecundario, "Completada")
                                    EstadoReserva.CANCELADA -> Triple(Color(0xFFFDE7E7), Color(0xFFC62828), "Cancelada")
                                }

                                Row(
                                    modifier = Modifier
                                        .background(
                                            color = bgColor,
                                            shape = RoundedCornerShape(50.dp),
                                        )
                                        .padding(horizontal = 12.dp, vertical = 6.dp),
                                ) {
                                    Text(
                                        text = textLabel,
                                        color = textColor,
                                        fontWeight = FontWeight.Medium,
                                    )
                                }

                                Spacer(modifier = Modifier.weight(1f))

                                if (esConfirmada) {
                                    TextButton(
                                        onClick = {
                                            reservaAEliminar = reserva
                                            showDialog = true
                                        },
                                    ) {
                                        Text(
                                            text = "Cancelar",
                                            color = Color(0xFFC62828),
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog && (reservaAEliminar != null)) {
        val r = reservaAEliminar!!
        AlertDialog(
            onDismissRequest = {
                showDialog = false
                reservaAEliminar = null
            },
            title = {
                Text(text = "¿Cancelar reserva?")
            },
            text = {
                Text(text = "Se liberará tu cupo en ${r.clase.nombre} el ${r.dia}, ${r.horario}. Esta acción no se puede deshacer.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onCancelarReserva(r)
                        showDialog = false
                        reservaAEliminar = null
                    },
                ) {
                    Text(
                        text = "Sí, cancelar",
                        color = Color(0xFFC62828),
                        fontWeight = FontWeight.Bold,
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        reservaAEliminar = null
                    },
                ) {
                    Text(
                        text = "No, mantener",
                    )
                }
            },
        )
    }
}
