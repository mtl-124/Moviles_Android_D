package com.lajara.ev05clinicasalud.navigate

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lajara.ev05clinicasalud.screens.*
import kotlinx.coroutines.launch

object ClinicaRoutes {
    const val INICIO = "inicio"
    const val PERFIL_MEDICO = "perfil_medico/{medicoId}"
    const val AGENDAR_CITA = "agendar_cita/{medicoId}"
    const val CONFIRMACION = "confirmacion/{medicoId}/{fecha}/{hora}"
    const val MIS_CITAS = "mis_citas"
    const val HISTORIAL = "historial"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicaApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text("Clínica Salud+", modifier = Modifier.padding(16.dp))
                Divider()
                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = false,
                    onClick = {
                        navController.navigate(ClinicaRoutes.INICIO)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Mis citas") },
                    selected = false,
                    onClick = {
                        navController.navigate(ClinicaRoutes.MIS_CITAS)
                        scope.launch { drawerState.close() }
                    }
                )
                NavigationDrawerItem(
                    label = { Text("Historial médico") },
                    selected = false,
                    onClick = {
                        navController.navigate(ClinicaRoutes.HISTORIAL)
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Clínica Salud+") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menú")
                        }
                    }
                )
            }
        ) { padding ->
            NavHost(
                navController = navController,
                startDestination = ClinicaRoutes.INICIO,
                modifier = Modifier.padding(padding)
            ) {
                composable(ClinicaRoutes.INICIO) {
                    InicioClinicaScreen(navController)
                }
                composable(
                    ClinicaRoutes.PERFIL_MEDICO,
                    arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getInt("medicoId") ?: 0
                    PerfilMedicoScreen(medicoId, navController)
                }
                composable(
                    ClinicaRoutes.AGENDAR_CITA,
                    arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getInt("medicoId") ?: 0
                    AgendarCitaScreen(medicoId, navController)
                }
                composable(
                    ClinicaRoutes.CONFIRMACION,
                    arguments = listOf(
                        navArgument("medicoId") { type = NavType.IntType },
                        navArgument("fecha") { type = NavType.StringType },
                        navArgument("hora") { type = NavType.StringType }
                    )
                ) { backStackEntry ->
                    val medicoId = backStackEntry.arguments?.getInt("medicoId") ?: 0
                    val fecha = backStackEntry.arguments?.getString("fecha") ?: ""
                    val hora = backStackEntry.arguments?.getString("hora") ?: ""
                    ConfirmacionClinicaScreen(medicoId, fecha, hora, navController)
                }
                composable(ClinicaRoutes.MIS_CITAS) {
                    MisCitasScreen()
                }
                composable(ClinicaRoutes.HISTORIAL) {
                    HistorialScreen()
                }
            }
        }
    }
}