package com.saludplus.citas.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocalHospital
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.saludplus.citas.ui.screens.agendamiento.*
import com.saludplus.citas.ui.screens.auth.*
import com.saludplus.citas.ui.screens.citas.MisCitasScreen
import com.saludplus.citas.ui.screens.home.HomeScreen
import com.saludplus.citas.ui.screens.perfil.PerfilScreen

// Definición de los 4 destinos de la barra inferior
sealed class DestinoBottom(val route: String, val titulo: String, val icono: ImageVector) {
    object Home : DestinoBottom("home", "Inicio", Icons.Default.Home)
    object Especialidades : DestinoBottom("especialidades", "Agendar", Icons.Default.LocalHospital)
    object MisCitas : DestinoBottom("mis_citas", "Mis Citas", Icons.Default.CalendarToday)
    object Perfil : DestinoBottom("perfil", "Perfil", Icons.Default.Person)
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = navBackStackEntry?.destination?.route

    val itemsBottom = listOf(
        DestinoBottom.Home,
        DestinoBottom.Especialidades,
        DestinoBottom.MisCitas,
        DestinoBottom.Perfil
    )

    // Mostrar la NavigationBar únicamente en los 4 destinos principales
    val mostrarBottomBar = itemsBottom.any { it.route == rutaActual }

    Scaffold(
        bottomBar = {
            if (mostrarBottomBar) {
                NavigationBar {
                    itemsBottom.forEach { destino ->
                        NavigationBarItem(
                            icon = { Icon(destino.icono, contentDescription = destino.titulo) },
                            label = { Text(destino.titulo) },
                            selected = rutaActual == destino.route,
                            onClick = {
                                navController.navigate(destino.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "splash",
            modifier = Modifier.padding(paddingValues)
        ) {
            // --- MÓDULO AUTH ---
            composable("splash") {
                SplashScreen(
                    onIrALogin = { navController.navigate("login") },
                    onIrARegistro = { navController.navigate("registro") }
                )
            }
            composable("login") {
                LoginScreen(
                    onLoginExitoso = {
                        navController.navigate("home") {
                            popUpTo("splash") { inclusive = true }
                        }
                    },
                    onIrARegistro = { navController.navigate("registro") },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("registro") {
                RegistroScreen(
                    onRegistroExitoso = {
                        navController.navigate("home") {
                            popUpTo("splash") { inclusive = true }
                        }
                    },
                    onIrALogin = { navController.navigate("login") },
                    onBackClick = { navController.popBackStack() }
                )
            }

            // --- DESTINOS PRINCIPALES (BOTTOM BAR) ---
            composable("home") {
                HomeScreen(
                    onNavegarAEspecialidades = { navController.navigate("especialidades") },
                    onSeleccionarEspecialidad = { espId -> navController.navigate("medicos/$espId") },
                    onNavegarAMisCitas = { navController.navigate("mis_citas") }
                )
            }
            composable("especialidades") {
                EspecialidadesScreen(
                    onSeleccionarEspecialidad = { espId -> navController.navigate("medicos/$espId") },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable("mis_citas") {
                MisCitasScreen(onBackClick = { navController.popBackStack() })
            }
            composable("perfil") {
                PerfilScreen(
                    onCerrarSesion = {
                        navController.navigate("splash") {
                            popUpTo(0) { inclusive = true }
                        }
                    }
                )
            }

            // --- FLUJO DE AGENDAMIENTO ---
            composable(
                route = "medicos/{especialidadId}",
                arguments = listOf(navArgument("especialidadId") { type = NavType.StringType })
            ) { backStackEntry ->
                val espId = backStackEntry.arguments?.getString("especialidadId") ?: ""
                MedicosScreen(
                    especialidadId = espId,
                    onSeleccionarMedico = { medId -> navController.navigate("fecha_hora/$medId") },
                    onBackClick = { navController.popBackStack() }
                )
            }
            composable(
                route = "fecha_hora/{medicoId}",
                arguments = listOf(navArgument("medicoId") { type = NavType.StringType })
            ) { backStackEntry ->
                val medId = backStackEntry.arguments?.getString("medicoId") ?: ""
                FechaHoraScreen(
                    medicoId = medId,
                    onContinuar = { fecha, hora -> navController.navigate("confirmar/$medId/$fecha/$hora") },
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
                            popUpTo("home") { inclusive = false }
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
                        navController.navigate("home") {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                )
            }
        }
    }
}