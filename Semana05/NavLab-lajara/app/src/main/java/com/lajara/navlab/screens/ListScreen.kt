package com.lajara.navlab.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lajara.navlab.components.StudentListItem
import com.lajara.navlab.navigation.Screen
import com.lajara.navlab.ui.theme.PurpleDark
import com.lajara.navlab.ui.theme.PurpleLight
import com.lajara.navlab.ui.theme.SurfaceGray

data class StudentData(
    val name: String,
    val career: String
)

val sampleStudents = listOf(
    StudentData("Juan León", "Ingeniería de Sistemas"),
    StudentData("María García", "Arquitectura"),
    StudentData("Carlos Pérez", "Medicina"),
    StudentData("Ana López", "Derecho"),
    StudentData("Luis Ramírez", "Administración")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    Scaffold(
        containerColor = SurfaceGray,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Directorio de Alumnos",
                        color = PurpleDark,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = PurpleDark
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PurpleLight
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            itemsIndexed(sampleStudents) { index, student ->
                StudentListItem(
                    name = student.name,
                    career = student.career,
                    onClick = {
                        navController.navigate(
                            Screen.Detail.createRoute(index + 1)
                        )
                    }
                )
            }
        }
    }
}
