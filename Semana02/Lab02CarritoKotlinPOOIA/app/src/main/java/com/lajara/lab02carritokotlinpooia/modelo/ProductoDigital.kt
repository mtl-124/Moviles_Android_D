package com.lajara.lab02carritokotlinpooia.modelo

class ProductoDigital(
    codigo: String,
    nombre: String,
    precio: Double,
    val tamanoMB: Double
) : Producto(codigo, nombre, precio) {

    override fun detalle(): String {
        return "$nombre (Digital) - Tamaño: $tamanoMB MB"
    }
}