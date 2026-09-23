package com.lajara.ev05tecsupfit.datos

import androidx.compose.runtime.mutableStateListOf

data class Reserva(
    val clase: ClaseGym,
    val horario: String,
    val estado: String = "Confirmada"
)

object ReservasDatos {
    val listaReservas = mutableStateListOf<Reserva>()
}