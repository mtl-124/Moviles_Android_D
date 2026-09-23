package com.lajara.navlab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
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
import com.lajara.navlab.components.SectionLabel
import com.lajara.navlab.components.WhiteTopBar
import com.lajara.navlab.ui.theme.GradientHeader
import com.lajara.navlab.ui.theme.PurpleAccent
import com.lajara.navlab.ui.theme.SurfaceGray

@Composable
fun DetailScreen(navController: NavController, itemId: Int) {
    val studentIndex = itemId - 1
    val student = if (studentIndex in sampleStudents.indices) {
        sampleStudents[studentIndex]
    } else {
        StudentData("Alumno #$itemId", "Especialidad General")
    }

    val studentCode = "2024-000$itemId"
    val studentEmail = "estudiante$itemId@tecsup.edu.pe"
    val faculty = "Ingeniería y Tecnología"
    val biography = "Estudiante destacado del programa de ${student.career} (ID tipado: $itemId). Cuenta con un promedio sobresaliente y participación activa en proyectos de desarrollo tecnológico e innovación."

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .statusBarsPadding()
    ) {
        WhiteTopBar(
            title = "Expediente Académico",
            onBack = { navController.popBackStack() }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                    .background(brush = GradientHeader)
            )

            CircleAvatarPlaceholder(
                size = 100.dp,
                borderColor = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .offset(y = 50.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = student.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = student.career,
                style = MaterialTheme.typography.bodyMedium,
                color = PurpleAccent,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceGray),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    InfoRow(
                        icon = Icons.Default.Badge,
                        label = "ID ESTUDIANTE",
                        value = studentCode
                    )

                    HorizontalDivider(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    InfoRow(
                        icon = Icons.Default.Email,
                        label = "CORREO ELECTRÓNICO",
                        value = studentEmail
                    )

                    HorizontalDivider(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    InfoRow(
                        icon = Icons.Default.School,
                        label = "FACULTAD",
                        value = faculty
                    )

                    HorizontalDivider(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    SectionLabel(text = "BIOGRAFÍA")

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = biography,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF444444)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
