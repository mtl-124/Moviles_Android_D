package com.lajara.clinicasalud.model

enum class EstadoCita {
    CONFIRMADA,
    COMPLETADA,
    CANCELADA
}

data class Cita(
    val id: Int = 0,
    val medico: String,
    val especialidad: String,
    val fecha: String,
    val hora: String,
    val estado: EstadoCita = EstadoCita.CONFIRMADA
)
