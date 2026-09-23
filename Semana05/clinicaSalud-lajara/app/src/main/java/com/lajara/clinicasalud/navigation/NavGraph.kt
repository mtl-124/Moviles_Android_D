package com.lajara.clinicasalud.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lajara.clinicasalud.data.medicos
import com.lajara.clinicasalud.model.Cita
import com.lajara.clinicasalud.model.EstadoCita
import com.lajara.clinicasalud.screen.AgendarCitaScreen
import com.lajara.clinicasalud.screen.ConfirmacionScreen
import com.lajara.clinicasalud.screen.HistorialScreen
import com.lajara.clinicasalud.screen.InicioScreen
import com.lajara.clinicasalud.screen.MisCitasScreen
import com.lajara.clinicasalud.screen.PerfilMedicoScreen

sealed class Screen(val route: String) {

    data object Inicio : Screen("inicio")

    data object Perfil : Screen("perfil/{medicoId}") {
        fun createRoute(medicoId: Int) =
            "perfil/$medicoId"
    }

    data object Agendar : Screen("agendar/{medicoId}") {
        fun createRoute(medicoId: Int) =
            "agendar/$medicoId"
    }

    data object Confirmacion : Screen("confirmacion")

    data object MisCitas : Screen("mis_citas")

    data object Historial : Screen("historial")
}

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    val citas = remember {
        mutableStateListOf<Cita>()
    }

    var medicoIdSeleccionado = remember {
        mutableStateOf(0)
    }

    var fechaSeleccionada = remember {
        mutableStateOf("")
    }

    var horaSeleccionada = remember {
        mutableStateOf("")
    }

    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {

        composable(
            route = Screen.Inicio.route
        ) {

            InicioScreen(
                onMedicoClick = { medicoId ->

                    medicoIdSeleccionado.value = medicoId

                    navController.navigate(
                        Screen.Perfil.createRoute(medicoId)
                    )
                },

                onMisCitasClick = {
                    navController.navigate(
                        Screen.MisCitas.route
                    )
                },

                onHistorialClick = {
                    navController.navigate(
                        Screen.Historial.route
                    )
                }
            )
        }

        composable(
            route = Screen.Perfil.route,
            arguments = listOf(
                navArgument("medicoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val medicoId =
                backStackEntry.arguments?.getInt("medicoId") ?: 0

            PerfilMedicoScreen(
                medicoId = medicoId,

                onBackClick = {
                    navController.popBackStack()
                },

                onAgendarClick = {

                    navController.navigate(
                        Screen.Agendar.createRoute(medicoId)
                    )
                }
            )
        }

        composable(
            route = Screen.Agendar.route,
            arguments = listOf(
                navArgument("medicoId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val medicoId =
                backStackEntry.arguments?.getInt("medicoId") ?: 0

            AgendarCitaScreen(
                medicoId = medicoId,

                onBackClick = {
                    navController.popBackStack()
                },

                onConfirmarClick = { fecha, hora ->

                    medicoIdSeleccionado.value = medicoId
                    fechaSeleccionada.value = fecha
                    horaSeleccionada.value = hora

                    val medico = medicos.find {
                        it.id == medicoId
                    }

                    if (medico != null) {
                        citas.add(
                            Cita(
                                id = (citas.maxOfOrNull { it.id } ?: 0) + 1,
                                medico = medico.nombre,
                                especialidad = medico.especialidad,
                                fecha = fecha,
                                hora = hora,
                                estado = EstadoCita.CONFIRMADA
                            )
                        )
                    }

                    navController.navigate(
                        Screen.Confirmacion.route
                    )
                }
            )
        }

        composable(
            route = Screen.Confirmacion.route
        ) {

            ConfirmacionScreen(
                medicoId = medicoIdSeleccionado.value,
                fecha = fechaSeleccionada.value,
                hora = horaSeleccionada.value,

                onVerMisCitasClick = {
                    navController.navigate(
                        Screen.MisCitas.route
                    ) {
                        popUpTo(
                            Screen.Inicio.route
                        )
                    }
                },

                onInicioClick = {
                    navController.navigate(
                        Screen.Inicio.route
                    ) {
                        popUpTo(
                            Screen.Inicio.route
                        ) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(
            route = Screen.MisCitas.route
        ) {

            MisCitasScreen(
                citas = citas,
                onCancelarCita = { citaCancelada ->
                    val index = citas.indexOfFirst { it.id == citaCancelada.id }
                    if (index != -1) {
                        citas[index] = citas[index].copy(estado = EstadoCita.CANCELADA)
                    }
                }
            )
        }

        composable(
            route = Screen.Historial.route
        ) {

            HistorialScreen()
        }
    }
}
