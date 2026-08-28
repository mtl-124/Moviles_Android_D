package com.lajara.lab02carritokotlinpooia.modelo

class ProductoFisico(
    codigo: String,
    nombre: String,
    precio: Double,
    val pesoKg: Double
) : Producto(codigo, nombre, precio) {

    override fun detalle(): String {
        return "$nombre (Físico) - Peso: $pesoKg kg"
    }
}