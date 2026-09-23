package com.lajara.tecsupfit.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun BottomBar(
    currentRoute: String,
    onNavigate: (String) -> Unit
) {

    NavigationBar {

        NavigationBarItem(
            selected = currentRoute == "inicio",
            onClick = {
                onNavigate("inicio")
            },
            icon = {
                Icon(
                    Icons.Default.Home,
                    contentDescription = "Inicio"
                )
            },
            label = {
                Text("Inicio")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "reservas",
            onClick = {
                onNavigate("reservas")
            },
            icon = {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = "Reservas"
                )
            },
            label = {
                Text("Reservas")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "rutinas",
            onClick = {
                onNavigate("rutinas")
            },
            icon = {
                Icon(
                    Icons.Default.FitnessCenter,
                    contentDescription = "Rutinas"
                )
            },
            label = {
                Text("Rutinas")
            }
        )

        NavigationBarItem(
            selected = currentRoute == "perfil",
            onClick = {
                onNavigate("perfil")
            },
            icon = {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            },
            label = {
                Text("Perfil")
            }
        )
    }
}