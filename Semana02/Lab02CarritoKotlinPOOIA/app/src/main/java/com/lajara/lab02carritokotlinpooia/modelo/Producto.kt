package com.lajara.lab02carritokotlinpooia.modelo

abstract class Producto(
    val codigo: String,
    val nombre: String,
    precioInicial: Double
) {
    var precio: Double = precioInicial
        private set

    init {
        require(precioInicial > 0) { "El precio debe ser mayor a 0" }
    }

    fun actualizarPrecio(nuevoPrecio: Double) {
        require(nuevoPrecio > 0) { "El precio debe ser mayor a 0" }
        precio = nuevoPrecio
    }

    abstract fun detalle(): String
}