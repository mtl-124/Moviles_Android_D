package com.saludplus.citas.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.saludplus.citas.navigation.Rutas
import com.saludplus.citas.ui.components.BarraInferior
import com.saludplus.citas.ui.screens.agendamiento.*
import com.saludplus.citas.ui.screens.auth.*
import com.saludplus.citas.ui.screens.citas.MisCitasScreen
import com.saludplus.citas.ui.screens.doctores.MisDoctoresScreen
import com.saludplus.citas.ui.screens.home.HomeScreen
import com.saludplus.citas.ui.screens.notificaciones.NotificacionesScreen
import com.saludplus.citas.ui.screens.perfil.PerfilScreen
import com.saludplus.citas.ui.screens.resultados.ResultadosScreen
import com.saludplus.citas.ui.screens.sedes.SedesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    // Las 4 pantallas principales con Barra Inferior
    val rutasConBottomBar = listOf(
        Rutas.HOME,
        Rutas.MIS_CITAS,
        Rutas.RESULTADOS,
        Rutas.PERFIL
    )

    val mostrarBottomBar = rutasConBottomBar.contains(rutaActual)

    Scaffold(
        bottomBar = {
            if (mostrarBottomBar) {
                BarraInferior(
                    rutaActual = rutaActual,
                    navController = navController
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Rutas.SPLASH,
            modifier = Modifier.padding(paddingValues)
        ) {
            // --- MÓDULO AUTH ---
            composable(Rutas.SPLASH) {
                SplashScreen(
                    onIrALogin = { navController.navigate(Rutas.LOGIN) },
                    onIrARegistro = { navController.navigate(Rutas.REGISTRO) }
                )
            }
            composable(Rutas.LOGIN) {
                LoginScreen(
                    onLoginExitoso = {
                        navController.navigate(Rutas.HOME) {
                            popUpTo(Rutas.SPLASH) { inclusive = true }
                        }
                    },
                    onIrARegistro = { navController.navigate(Rutas.REGISTRO) },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(Rutas.REGISTRO) {
                RegistroScreen(
                    onRegistroExitoso = {
                        navController.navigate(Rutas.LOGIN) {
                            popUpTo(Rutas.SPLASH) { inclusive = true }
                        }
                    },
                    onIrALogin = { navController.navigate(Rutas.LOGIN) },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // --- PANTALLAS PRINCIPALES (BARRA INFERIOR) ---
            composable(Rutas.HOME) {
                HomeScreen(
                    onNavegarASedes = { navController.navigate(Rutas.SEDES) },
                    onNavegarAEspecialidades = { navController.navigate(Rutas.ESPECIALIDADES) },
                    onSeleccionarEspecialidad = { espId -> navController.navigate(Rutas.medicos(espId)) },
                    onNavegarAMisCitas = { navController.navigate(Rutas.MIS_CITAS) },
                    onNavegarAMisDoctores = { navController.navigate(Rutas.MIS_DOCTORES) },
                    onNavegarAPerfil = { navController.navigate(Rutas.PERFIL) },
                    onNavegarAResultados = { navController.navigate(Rutas.RESULTADOS) },
                    onNavegarANotificaciones = { navController.navigate(Rutas.NOTIFICACIONES) }
                )
            }
            composable(Rutas.MIS_CITAS) {
                MisCitasScreen(
                    onBackClick = { navController.popBackStack() },
                    onAgendarCita = { navController.navigate(Rutas.ESPECIALIDADES) }
                )
            }
            composable(Rutas.RESULTADOS) {
                ResultadosScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(Rutas.PERFIL) {
                PerfilScreen(
                    onCerrarSesion = {
                        navController.navigate(Rutas.SPLASH) {
                            popUpTo(0) { inclusive = true }
                        }
                    },
                    onNavegarAMisCitas = {
                        navController.navigate(Rutas.MIS_CITAS)
                    }
                )
            }

            // --- NUEVAS PANTALLAS Y SECUNDARIAS ---
            composable(Rutas.SEDES) {
                SedesScreen(
                    onSeleccionarSede = {
                        navController.navigate(Rutas.ESPECIALIDADES)
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(Rutas.MIS_DOCTORES) {
                MisDoctoresScreen(
                    onSeleccionarMedico = { medId ->
                        navController.navigate(Rutas.fechaHora(medId))
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(Rutas.NOTIFICACIONES) {
                NotificacionesScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }

            // --- FLUJO DE AGENDAMIENTO ---
            composable(Rutas.ESPECIALIDADES) {
                EspecialidadesScreen(
                    onSeleccionarEspecialidad = { espId -> navController.navigate(Rutas.medicos(espId)) },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = Rutas.MEDICOS,
                arguments = listOf(navArgument("especialidadId") { type = NavType.StringType })
            ) { backStackEntry ->
                val espId = backStackEntry.arguments?.getString("especialidadId") ?: ""
                MedicosScreen(
                    especialidadId = espId,
                    onSeleccionarMedico = { medId -> navController.navigate(Rutas.fechaHora(medId)) },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = Rutas.FECHA_HORA,
                arguments = listOf(navArgument("medicoId") { type = NavType.StringType })
            ) { backStackEntry ->
                val medId = backStackEntry.arguments?.getString("medicoId") ?: ""
                FechaHoraScreen(
                    medicoId = medId,
                    onContinuar = { fecha, hora -> navController.navigate(Rutas.confirmarCita(medId, fecha, hora)) },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = "confirmar/{medicoId}/{fecha}/{hora}",
                arguments = listOf(
                    navArgument("medicoId") { type = NavType.StringType },
                    navArgument("fecha") { type = NavType.StringType },
                    navArgument("hora") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val medId = backStackEntry.arguments?.getString("medicoId") ?: ""
                val fecha = backStackEntry.arguments?.getString("fecha") ?: ""
                val hora = backStackEntry.arguments?.getString("hora") ?: ""
                ConfirmarCitaScreen(
                    medicoId = medId,
                    fecha = fecha,
                    hora = hora,
                    onCitaConfirmada = { citaId ->
                        navController.navigate("cita_exitosa/$citaId") {
                            popUpTo(Rutas.HOME) { inclusive = false }
                        }
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = "confirmar_cita/{medicoId}/{fecha}/{hora}",
                arguments = listOf(
                    navArgument("medicoId") { type = NavType.StringType },
                    navArgument("fecha") { type = NavType.StringType },
                    navArgument("hora") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val medId = backStackEntry.arguments?.getString("medicoId") ?: ""
                val fecha = backStackEntry.arguments?.getString("fecha") ?: ""
                val hora = backStackEntry.arguments?.getString("hora") ?: ""
                ConfirmarCitaScreen(
                    medicoId = medId,
                    fecha = fecha,
                    hora = hora,
                    onCitaConfirmada = { citaId ->
                        navController.navigate("cita_exitosa/$citaId") {
                            popUpTo(Rutas.HOME) { inclusive = false }
                        }
                    },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = "cita_exitosa/{citaId}",
                arguments = listOf(navArgument("citaId") { type = NavType.StringType })
            ) { backStackEntry ->
                val citaId = backStackEntry.arguments?.getString("citaId") ?: ""
                CitaExitosaScreen(
                    citaId = citaId,
                    onVolverInicio = {
                        navController.navigate(Rutas.HOME) {
                            popUpTo(Rutas.HOME) { inclusive = true }
                        }
                    },
                    onVerMisCitas = {
                        navController.navigate(Rutas.MIS_CITAS) {
                            popUpTo(Rutas.HOME) { inclusive = false }
                        }
                    }
                )
            }
        }
    }
}
