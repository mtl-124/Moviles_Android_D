package com.lajara.tecsupfit.model

enum class EstadoReserva {
    CONFIRMADA,
    COMPLETADA,
    CANCELADA,
}

data class Reserva(
    val id: Int,
    val clase: Clase,
    val estado: EstadoReserva,
    val dia: String = "Hoy",
    val horario: String = clase.horario,
)
