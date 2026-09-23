package com.lajara.clinicasalud.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lajara.clinicasalud.data.especialidades
import com.lajara.clinicasalud.data.medicos
import kotlinx.coroutines.launch

private val Morado = Color(0xFF6A2CA0)
private val FondoTarjeta = Color(0xFFF3F1F6)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(
    onMedicoClick: (Int) -> Unit,
    onMisCitasClick: () -> Unit,
    onHistorialClick: () -> Unit
) {

    val especialidadSeleccionada = remember {
        mutableStateOf("Cardiología")
    }

    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet {

                Text(
                    text = "Clínica Salud+",
                    modifier = Modifier.padding(20.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                NavigationDrawerItem(
                    label = {
                        Text("Inicio")
                    },
                    selected = true,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Mis citas")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }

                        onMisCitasClick()
                    }
                )

                NavigationDrawerItem(
                    label = {
                        Text("Historial médico")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }

                        onHistorialClick()
                    }
                )
            }
        }
    ) {

        Scaffold(
            topBar = {

                TopAppBar(
                    title = {
                        Column {

                            Text(
                                text = "Clínica Salud+",
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )

                            Text(
                                text = "Hola, Gael",
                                fontSize = 12.sp,
                                color = Color.White
                            )
                        }
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Text(
                                text = "☰",
                                color = Color.White,
                                fontSize = 22.sp
                            )
                        }
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Morado
                    )
                )
            }
        ) { paddingValues ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
            ) {

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text(
                    text = "Especialidades",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(especialidades) { especialidad ->

                        FilterChip(
                            selected =
                                especialidadSeleccionada.value == especialidad,

                            onClick = {
                                especialidadSeleccionada.value =
                                    especialidad
                            },

                            label = {
                                Text(especialidad)
                            }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Text(
                    text = "Médicos disponibles",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    items(medicos) { medico ->

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onMedicoClick(medico.id)
                                },

                            shape = RoundedCornerShape(12.dp),

                            colors = CardDefaults.cardColors(
                                containerColor = FondoTarjeta
                            )
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),

                                verticalAlignment =
                                    Alignment.CenterVertically
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(44.dp)
                                        .background(
                                            color =
                                                Color(0xFFEDE4F5),
                                            shape = CircleShape
                                        ),

                                    contentAlignment =
                                        Alignment.Center
                                ) {

                                    Text(
                                        text = "+",
                                        color = Morado,
                                        fontSize = 30.sp
                                    )
                                }

                                Spacer(
                                    modifier = Modifier.width(10.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1f)
                                ) {

                                    Text(
                                        text = medico.nombre,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Text(
                                        text = medico.especialidad,
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }

                                Text(
                                    text =
                                        "⭐ ${medico.calificacion}",
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}