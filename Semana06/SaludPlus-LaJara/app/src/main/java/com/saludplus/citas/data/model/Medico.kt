package com.saludplus.citas.data.model

data class Medico(
    val id: String,
    val especialidadId: String,
    val nombre: String,
    val colegiatura: String,
    val calificacion: String,
    val biografia: String
)