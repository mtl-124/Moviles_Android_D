package com.lajara.authfirebaseapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

data class Course(
    val id: String = "",
    val name: String = "",
    val teacher: String = "",
    val credits: Int = 0,
    val userId: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoursesScreen(onBack: () -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val uid = FirebaseAuth.getInstance().currentUser?.uid ?: ""
    var courses by remember { mutableStateOf(listOf<Course>()) }
    var editing by remember { mutableStateOf<Course?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    DisposableEffect(uid) {
        val registration = db.collection("courses")
            .whereEqualTo("userId", uid)
            .addSnapshotListener { snapshot, _ ->
                courses = snapshot?.documents
                    ?.mapNotNull { doc -> doc.toObject(Course::class.java)?.copy(id = doc.id) }
                    ?.sortedBy { it.name }
                    ?: emptyList()
            }
        onDispose { registration.remove() }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mis cursos") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                editing = null
                showDialog = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar curso")
            }
        }
    ) { padding ->
        if (courses.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("Aún no tienes cursos registrados")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(courses, key = { it.id }) { course ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(course.name, style = MaterialTheme.typography.titleMedium)
                                Text("Docente: ${course.teacher}")
                                Text("Créditos: ${course.credits}")
                            }
                            IconButton(onClick = {
                                editing = course
                                showDialog = true
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar")
                            }
                            IconButton(onClick = {
                                db.collection("courses").document(course.id).delete()
                            }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }

    if (showDialog) {
        CourseDialog(
            initial = editing,
            onDismiss = { showDialog = false },
            onSave = { name, teacher, credits ->
                val data = mapOf(
                    "name" to name,
                    "teacher" to teacher,
                    "credits" to credits,
                    "userId" to uid
                )
                val current = editing
                if (current == null) {
                    db.collection("courses").add(data)
                } else {
                    db.collection("courses").document(current.id).update(data)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun CourseDialog(
    initial: Course?,
    onDismiss: () -> Unit,
    onSave: (String, String, Int) -> Unit
) {
    var name by remember { mutableStateOf(initial?.name ?: "") }
    var teacher by remember { mutableStateOf(initial?.teacher ?: "") }
    var credits by remember { mutableStateOf(initial?.credits?.toString() ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initial == null) "Nuevo curso" else "Editar curso") },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre del curso") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = teacher,
                    onValueChange = { teacher = it },
                    label = { Text("Docente") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = credits,
                    onValueChange = { credits = it.filter { c -> c.isDigit() } },
                    label = { Text("Créditos") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onSave(name.trim(), teacher.trim(), credits.toIntOrNull() ?: 0) },
                enabled = name.isNotBlank() && teacher.isNotBlank()
            ) { Text("Guardar") }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}