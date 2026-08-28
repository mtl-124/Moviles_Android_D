package com.lajara.lab02carritokotlinpooia.carrito

import com.lajara.lab02carritokotlinpooia.modelo.Producto

class Carrito {

    private val productos = mutableListOf<Producto>()

    fun agregarProducto(producto: Producto) {
        productos.add(producto)
    }

    fun listarProductos(): List<Producto> {
        return productos.toList()
    }

    fun cantidadProductos(): Int {
        return productos.size
    }
}