package com.lajara.tecsupfit.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tecsupfit.ui.screens.InicioScreen
import com.lajara.tecsupfit.model.Clase
import com.lajara.tecsupfit.screens.DetalleScreen
import com.lajara.tecsupfit.screens.ConfirmacionScreen


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

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {

        composable("inicio") {

            InicioScreen(
                onClaseClick = { clase ->

                    navController.navigate(
                        "detalle/${clase.id}"
                    )
                }
            )
        }

        composable(
            route = "detalle/{claseId}",
            arguments = listOf(
                navArgument("claseId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val claseId =
                backStackEntry.arguments?.getInt("claseId")

            val clase =
                clases.find { it.id == claseId }

            if (clase != null) {

                DetalleScreen(
                    clase = clase,
                    onBack = {
                        navController.popBackStack()
                    },
                    onReservar = {
                        navController.navigate(
                            "confirmacion/${clase.id}"
                        )
                    }
                )
            }
        }
        composable(
            route = "confirmacion/{claseId}",
            arguments = listOf(
                navArgument("claseId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val claseId =
                backStackEntry.arguments?.getInt("claseId")

            val clase =
                clases.find { it.id == claseId }

            if (clase != null) {

                ConfirmacionScreen(
                    clase = clase,
                    onVerReservas = {
                        // Se conectará con Reservas en la Parte 5.
                    }
                )
            }
        }
    }
}