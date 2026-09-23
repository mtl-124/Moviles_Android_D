package com.lajara.ev05clinicasalud.datos

import androidx.compose.runtime.mutableStateListOf

data class Cita(
    val medico: Medico,
    val fecha: String,
    val hora: String,
    val estado: String = "Confirmada"
)

object CitasDatos {
    val listaCitas = mutableStateListOf<Cita>()
}