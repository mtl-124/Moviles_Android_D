package com.saludplus.citas.data.model

data class Medico(
    val id: String,
    val especialidadId: String,
    val nombre: String,
    val cmp: String,
    val calificacion: String,
    val biografia: String
)