package com.lajara.ev05clinicasalud.datos

import java.util.stream.IntStream

data class Medico(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val calificacion: Float
)

object MedicosDatos {
    val listaMedicos = listOf(
        Medico(1,"Dr. Carlos Junco", "Traumatología", 4.6f),
        Medico(2,"Dra. Magaly Barboza", "Pediatría", 4.8f),
        Medico(3,"Dr. Gael La Jara", "Cardiología", 4.5f),
        Medico(4,"Dr. Roger Cuadroz", "Dermatología", 4.9f),
    )

    val especialidades = listOf("Todas", "Traumatología", "Pediatría", "Cardiología", "Demartología")
}