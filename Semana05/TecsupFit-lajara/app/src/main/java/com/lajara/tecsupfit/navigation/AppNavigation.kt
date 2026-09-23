package com.lajara.tecsupfit.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lajara.tecsupfit.components.BottomBar
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.model.EstadoReserva
import com.lajara.tecsupfit.model.Reserva
import com.lajara.tecsupfit.screens.ConfirmacionScreen
import com.lajara.tecsupfit.screens.DetalleScreen
import com.lajara.tecsupfit.screens.InicioScreen
import com.lajara.tecsupfit.screens.PerfilScreen
import com.lajara.tecsupfit.screens.ReservasScreen
import com.lajara.tecsupfit.screens.RutinasScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    val clases = listOf(
        Clase(
            id = 1,
            nombre = "Yoga funcional",
            horario = "7:00 am",
            sala = "Sala 2",
            duracion = "45 min",
            descripcion = "Entrenamiento funcional para mejorar movilidad y resistencia.",
            cuposDisponibles = 10,
            cuposTotales = 12
        ),
        Clase(
            id = 2,
            nombre = "Cross Training",
            horario = "6:00 pm",
            sala = "Sala 1",
            duracion = "45 min",
            descripcion = "Entrenamiento funcional de alta intensidad. Cupos limitados.",
            cuposDisponibles = 8,
            cuposTotales = 12
        ),
        Clase(
            id = 3,
            nombre = "Spinning",
            horario = "7:30 pm",
            sala = "Sala 3",
            duracion = "45 min",
            descripcion = "Sesión de bicicleta estática para mejorar resistencia.",
            cuposDisponibles = 6,
            cuposTotales = 10
        )
    )

    val reservas = remember {
        mutableStateListOf(
            Reserva(
                id = 1,
                clase = Clase(
                    id = 2,
                    nombre = "Cross Training",
                    horario = "6:00 pm",
                    sala = "Sala 1",
                    duracion = "45 min",
                    descripcion = "Entrenamiento funcional.",
                    cuposDisponibles = 8,
                    cuposTotales = 12
                ),
                estado = EstadoReserva.CONFIRMADA,
                dia = "Hoy",
                horario = "6:00 pm"
            ),
            Reserva(
                id = 2,
                clase = Clase(
                    id = 1,
                    nombre = "Yoga funcional",
                    horario = "7:00 am",
                    sala = "Sala 2",
                    duracion = "45 min",
                    descripcion = "Movilidad y resistencia.",
                    cuposDisponibles = 10,
                    cuposTotales = 12
                ),
                estado = EstadoReserva.COMPLETADA,
                dia = "Ayer",
                horario = "7:00 am"
            )
        )
    }

    // Obtiene la ruta activa para el BottomBar
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "inicio"

    Scaffold(
        bottomBar = {
            BottomBar(
                currentRoute = currentRoute,
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {

            composable("inicio") {
                InicioScreen(
                    onClaseClick = { clase ->
                        navController.navigate("detalle/${clase.id}")
                    }
                )
            }

            composable("reservas") {
                ReservasScreen(
                    reservas = reservas,
                    onCancelarReserva = { reserva ->
                        val index = reservas.indexOfFirst { it.id == reserva.id }
                        if (index != -1) {
                            reservas[index] = reserva.copy(estado = EstadoReserva.CANCELADA)
                        }
                    }
                )
            }

            composable("rutinas") {
                RutinasScreen()
            }

            composable("perfil") {
                PerfilScreen()
            }

            composable(
                route = "detalle/{claseId}",
                arguments = listOf(
                    navArgument("claseId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val claseId = backStackEntry.arguments?.getInt("claseId")
                val clase = clases.find { it.id == claseId }

                if (clase != null) {
                    DetalleScreen(
                        clase = clase,
                        onBack = { navController.popBackStack() },
                        onReservar = { horarioElegido ->
                            val nuevaReserva = Reserva(
                                id = reservas.size + 1,
                                clase = clase,
                                estado = EstadoReserva.CONFIRMADA,
                                dia = "Hoy",
                                horario = horarioElegido
                            )
                            reservas.add(nuevaReserva)
                            navController.navigate("confirmacion/${clase.id}")
                        }
                    )
                }
            }

            composable(
                route = "confirmacion/{claseId}",
                arguments = listOf(
                    navArgument("claseId") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val claseId = backStackEntry.arguments?.getInt("claseId")
                val clase = clases.find { it.id == claseId }

                if (clase != null) {
                    ConfirmacionScreen(
                        clase = clase,
                        onVerReservas = {
                            navController.navigate("reservas") {
                                popUpTo("inicio")
                            }
                        }
                    )
                }
            }
        }
    }
}
