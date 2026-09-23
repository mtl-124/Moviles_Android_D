package com.lajara.navlab.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.navlab.components.ActionOptionCard
import com.lajara.navlab.components.GradientBox
import com.lajara.navlab.components.LogoutButton
import com.lajara.navlab.navigation.Screen
import com.lajara.navlab.ui.theme.GradientHome

@Composable
fun HomeScreen(navController: NavController) {
    GradientBox(
        brush = GradientHome,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenido,",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Gael La Jara",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "¿Qué deseas gestionar hoy?",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            ActionOptionCard(
                icon = Icons.Default.People,
                title = "Directorio de Alumnos",
                subtitle = "Ver y gestionar estudiantes",
                onClick = { navController.navigate(Screen.List.route) }
            )

            Spacer(modifier = Modifier.height(12.dp))

            ActionOptionCard(
                icon = Icons.Default.Person,
                title = "Mi Perfil Académico",
                subtitle = "Datos personales y progreso",
                onClick = { navController.navigate(Screen.Profile.route) }
            )
        }

        LogoutButton(
            text = "Cerrar Sesión Segura",
            filled = false,
            onClick = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        )
    }
}
