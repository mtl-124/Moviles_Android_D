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

    fun calcularEnvioTotal(): Double {
        var total = 0.0
        for (producto in productos) {
            total += producto.calcularCostoEnvio()
        }
        return total
    }
}