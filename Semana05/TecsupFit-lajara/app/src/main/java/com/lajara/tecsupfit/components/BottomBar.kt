package com.lajara.tecsupfit.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lajara.tecsupfit.ui.theme.Blanco
import com.lajara.tecsupfit.ui.theme.GrisClaro
import com.lajara.tecsupfit.ui.theme.TextoSecundario
import com.lajara.tecsupfit.ui.theme.VerdePrincipal

@Composable
fun BottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {

    Column {
        HorizontalDivider(
            thickness = 1.dp,
            color = GrisClaro
        )
        NavigationBar(
            containerColor = Blanco,
            tonalElevation = 0.dp
        ) {

            NavigationBarItem(
                selected = currentRoute == "inicio",
                onClick = {
                    onNavigate("inicio")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Inicio",
                        tint = if (currentRoute == "inicio") VerdePrincipal else TextoSecundario
                    )
                },
                label = {
                    Text(
                        text = "Inicio",
                        fontWeight = if (currentRoute == "inicio") FontWeight.Bold else FontWeight.Normal,
                        color = if (currentRoute == "inicio") VerdePrincipal else TextoSecundario
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )

            NavigationBarItem(
                selected = currentRoute == "reservas",
                onClick = {
                    onNavigate("reservas")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Reservas",
                        tint = if (currentRoute == "reservas") VerdePrincipal else TextoSecundario
                    )
                },
                label = {
                    Text(
                        text = "Reservas",
                        fontWeight = if (currentRoute == "reservas") FontWeight.Bold else FontWeight.Normal,
                        color = if (currentRoute == "reservas") VerdePrincipal else TextoSecundario
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )

            NavigationBarItem(
                selected = currentRoute == "rutinas",
                onClick = {
                    onNavigate("rutinas")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.FitnessCenter,
                        contentDescription = "Rutinas",
                        tint = if (currentRoute == "rutinas") VerdePrincipal else TextoSecundario
                    )
                },
                label = {
                    Text(
                        text = "Rutinas",
                        fontWeight = if (currentRoute == "rutinas") FontWeight.Bold else FontWeight.Normal,
                        color = if (currentRoute == "rutinas") VerdePrincipal else TextoSecundario
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )

            NavigationBarItem(
                selected = currentRoute == "perfil",
                onClick = {
                    onNavigate("perfil")
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Perfil",
                        tint = if (currentRoute == "perfil") VerdePrincipal else TextoSecundario
                    )
                },
                label = {
                    Text(
                        text = "Perfil",
                        fontWeight = if (currentRoute == "perfil") FontWeight.Bold else FontWeight.Normal,
                        color = if (currentRoute == "perfil") VerdePrincipal else TextoSecundario
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}
