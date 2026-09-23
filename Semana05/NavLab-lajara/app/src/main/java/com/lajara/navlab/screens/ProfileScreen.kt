package com.lajara.navlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.navlab.components.CircleAvatarPlaceholder
import com.lajara.navlab.components.InfoRow
import com.lajara.navlab.components.LogoutButton
import com.lajara.navlab.components.SectionLabel
import com.lajara.navlab.components.WhiteTopBar
import com.lajara.navlab.navigation.Screen
import com.lajara.navlab.ui.theme.GradientHeader

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        WhiteTopBar(
            title = "Configuración de Perfil",
            onBack = { navController.popBackStack() }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                .background(brush = GradientHeader)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircleAvatarPlaceholder(
                    size = 90.dp,
                    borderColor = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Gael Matteo La Jara Barboza",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {
            SectionLabel(text = "INFORMACIÓN PERSONAL")

            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(
                icon = Icons.Default.Person,
                label = "NOMBRE COMPLETO",
                value = "Gael Matteo La Jara Barboza"
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoRow(
                icon = Icons.Default.Email,
                label = "CORREO",
                value = "gael@gmail.com"
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoRow(
                icon = Icons.Default.Phone,
                label = "TELÉFONO",
                value = "+51 987 654 321"
            )

            Spacer(modifier = Modifier.height(24.dp))

            SectionLabel(text = "ACADÉMICO")

            Spacer(modifier = Modifier.height(8.dp))

            InfoRow(
                icon = Icons.Default.School,
                label = "CARRERA",
                value = "Ingeniería de Sistemas"
            )

            Spacer(modifier = Modifier.height(12.dp))

            InfoRow(
                icon = Icons.Default.CalendarMonth,
                label = "CICLO ACTUAL",
                value = "VI Ciclo (2024-II)"
            )
        }

        LogoutButton(
            text = "Cerrar Sesión",
            filled = true,
            onClick = {
                navController.navigate(Screen.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        )
    }
}
