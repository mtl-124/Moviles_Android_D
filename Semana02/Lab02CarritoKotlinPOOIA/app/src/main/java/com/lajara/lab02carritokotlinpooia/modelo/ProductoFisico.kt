package com.lajara.lab02carritokotlinpooia.modelo

class ProductoFisico(
    codigo: String,
    nombre: String,
    precio: Double,
    pesoKgInicial: Double
) : Producto(codigo, nombre, precio) {

    val pesoKg: Double = pesoKgInicial

    init {
        require(pesoKgInicial > 0) { "El peso debe ser mayor a 0" }
    }

    override fun detalle(): String {
        return "$nombre (Físico) - Peso: $pesoKg kg"
    }

    override fun calcularCostoEnvio(): Double {
        return pesoKg * 2.0
    }
}