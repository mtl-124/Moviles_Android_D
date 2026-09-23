package com.lajara.clinicasalud.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lajara.clinicasalud.model.Cita
import com.lajara.clinicasalud.model.EstadoCita
import com.lajara.clinicasalud.ui.theme.FondoCancelacion
import com.lajara.clinicasalud.ui.theme.FondoCompletada
import com.lajara.clinicasalud.ui.theme.FondoTarjeta
import com.lajara.clinicasalud.ui.theme.MoradoPrincipal
import com.lajara.clinicasalud.ui.theme.RojoCancelacion
import com.lajara.clinicasalud.ui.theme.TextoSecundario
import com.lajara.clinicasalud.ui.theme.VerdeClaro
import com.lajara.clinicasalud.ui.theme.VerdeExito

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisCitasScreen(
    citas: List<Cita> = emptyList(),
    onCancelarCita: (Cita) -> Unit = {}
) {
    var citaACancelar by remember { mutableStateOf<Cita?>(null) }

    if (citaACancelar != null) {
        val c = citaACancelar!!
        AlertDialog(
            onDismissRequest = { citaACancelar = null },
            title = {
                Text(text = "¿Cancelar cita?")
            },
            text = {
                Text(text = "Se cancelará tu cita con ${c.medico} el ${c.fecha}, ${c.hora}. Esta acción no se puede deshacer.")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onCancelarCita(c)
                        citaACancelar = null
                    }
                ) {
                    Text(
                        text = "Sí, cancelar",
                        color = RojoCancelacion,
                        fontWeight = FontWeight.Bold
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { citaACancelar = null }
                ) {
                    Text(text = "No, mantener")
                }
            }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Mis citas",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        if (citas.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Aún no tienes citas agendadas",
                    fontSize = 16.sp,
                    color = TextoSecundario
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(10.dp)
            ) {

                items(citas) { cita ->

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = FondoTarjeta
                        )
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            // Barra vertical de 4dp a la izquierda
                            when (cita.estado) {
                                EstadoCita.CONFIRMADA -> {
                                    Box(
                                        modifier = Modifier
                                            .width(4.dp)
                                            .height(80.dp)
                                            .background(MoradoPrincipal)
                                    )
                                }
                                EstadoCita.CANCELADA -> {
                                    Box(
                                        modifier = Modifier
                                            .width(4.dp)
                                            .height(80.dp)
                                            .background(RojoCancelacion)
                                    )
                                }
                                EstadoCita.COMPLETADA -> {
                                    // sin barra
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(12.dp)
                            ) {

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(
                                        modifier = Modifier.weight(1f)
                                    ) {
                                        Text(
                                            text = cita.medico,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )

                                        Spacer(modifier = Modifier.height(2.dp))

                                        Text(
                                            text = "${cita.fecha}, ${cita.hora}",
                                            fontSize = 12.sp,
                                            color = TextoSecundario
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    val (bgColor, textColor, labelText) = when (cita.estado) {
                                        EstadoCita.CONFIRMADA -> Triple(VerdeClaro, VerdeExito, "Confirmada")
                                        EstadoCita.COMPLETADA -> Triple(FondoCompletada, TextoSecundario, "Completada")
                                        EstadoCita.CANCELADA -> Triple(FondoCancelacion, RojoCancelacion, "Cancelada")
                                    }

                                    Surface(
                                        shape = RoundedCornerShape(50),
                                        color = bgColor
                                    ) {
                                        Text(
                                            text = labelText,
                                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                            fontSize = 11.sp,
                                            color = textColor,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }

                                    if (cita.estado == EstadoCita.CONFIRMADA) {
                                        TextButton(
                                            onClick = {
                                                citaACancelar = cita
                                            }
                                        ) {
                                            Text(
                                                text = "Cancelar",
                                                color = RojoCancelacion,
                                                fontWeight = FontWeight.Bold
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
    }
}
