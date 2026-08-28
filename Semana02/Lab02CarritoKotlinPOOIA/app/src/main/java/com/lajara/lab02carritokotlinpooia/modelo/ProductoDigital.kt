package com.lajara.lab02carritokotlinpooia.modelo

class ProductoDigital(
    codigo: String,
    nombre: String,
    precio: Double,
    tamanoMBInicial: Double
) : Producto(codigo, nombre, precio) {

    val tamanoMB: Double = tamanoMBInicial

    init {
        require(tamanoMBInicial > 0) { "El tamaño debe ser mayor a 0" }
    }

    override fun detalle(): String {
        return "$nombre (Digital) - Tamaño: $tamanoMB MB"
    }

    override fun calcularCostoEnvio(): Double {
        return 0.0
    }
}