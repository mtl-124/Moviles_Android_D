package com.lajara.ev05tecsupfit.screens

data class ClaseGym(
    val id: Int,
    val nombre: String,
    val horario: String,
    val sala: String,
    val duracionMin: Int,
    val descripcion: String,
    val cuposDisponibles: Int,
    val cuposTotales: Int,
    val dia: String // "Hoy" o "Esta semana"
)

object ClasesData {
    val listaClases = listOf(
        ClaseGym(1, "Yoga funcional", "7:00 am", "Sala 2", 60,
            "Sesión de yoga enfocada en movilidad y control corporal.", 10, 15, "Hoy"),
        ClaseGym(2, "Cross Training", "6:00 pm", "Sala 1", 45,
            "Entrenamiento funcional de alta intensidad. Cupos limitados.", 8, 12, "Hoy"),
        ClaseGym(3, "Spinning", "7:30 pm", "Sala 3", 50,
            "Cardio intenso en bicicleta estática con música motivadora.", 12, 20, "Esta semana")
    )

    val filtros = listOf("Hoy", "Esta semana")
}