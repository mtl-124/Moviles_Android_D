package com.lajara.clinicasalud.data

import com.lajara.clinicasalud.model.Medico

val especialidades = listOf(
    "Cardiología",
    "Pediatría",
    "Dermatología"
)

val medicos = listOf(
    Medico(
        id = 1,
        nombre = "Dra. Ana Torres",
        especialidad = "Cardiología",
        calificacion = 4.8
    ),
    Medico(
        id = 2,
        nombre = "Dr. Carlos Mendoza",
        especialidad = "Pediatría",
        calificacion = 4.7
    ),
    Medico(
        id = 3,
        nombre = "Dra. María López",
        especialidad = "Dermatología",
        calificacion = 4.9
    )
)