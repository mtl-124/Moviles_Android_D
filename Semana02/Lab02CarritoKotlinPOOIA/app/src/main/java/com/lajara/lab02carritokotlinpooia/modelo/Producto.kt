package com.lajara.lab02carritokotlinpooia.modelo

abstract class Producto(
    val codigo: String,
    val nombre: String,
    val precio: Double
) {
    abstract fun detalle(): String
}