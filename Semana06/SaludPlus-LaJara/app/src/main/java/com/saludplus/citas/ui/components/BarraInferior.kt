package com.saludplus.citas.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.saludplus.citas.navigation.Rutas
import com.saludplus.citas.ui.theme.AzulPrimario
import com.saludplus.citas.ui.theme.Blanco
import com.saludplus.citas.ui.theme.GrisTexto

data class ElementoBarraInferior(
    val ruta: String,
    val etiqueta: String,
    val icono: ImageVector
)

val elementosBarraInferior = listOf(
    ElementoBarraInferior(Rutas.HOME, "Inicio", Icons.Filled.Home),
    ElementoBarraInferior(Rutas.MIS_CITAS, "Citas", Icons.Filled.CalendarMonth),
    ElementoBarraInferior(Rutas.RESULTADOS, "Resultados", Icons.Filled.Description),
    ElementoBarraInferior(Rutas.PERFIL, "Perfil", Icons.Filled.Person)
)

@Composable
fun BarraInferior(
    rutaActual: String?,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFFE8ECF5)
        )
        NavigationBar(
            containerColor = Blanco,
            tonalElevation = 0.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            elementosBarraInferior.forEach { item ->
                val seleccionado = (rutaActual == item.ruta)
                NavigationBarItem(
                    selected = seleccionado,
                    onClick = {
                        if (rutaActual != item.ruta) {
                            navController.navigate(item.ruta) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = item.icono,
                            contentDescription = item.etiqueta,
                            modifier = Modifier.size(26.dp)
                        )
                    },
                    label = {
                        Text(
                            text = item.etiqueta,
                            fontSize = 12.sp,
                            fontWeight = if (seleccionado) FontWeight.Bold else FontWeight.SemiBold
                        )
                    },
                    alwaysShowLabel = true,
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = AzulPrimario,
                        selectedTextColor = AzulPrimario,
                        unselectedIconColor = GrisTexto,
                        unselectedTextColor = GrisTexto,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}
