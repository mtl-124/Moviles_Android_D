package com.lajara.lab02carritokotlinpooia

import com.lajara.lab02carritokotlinpooia.modelo.Producto
import com.lajara.lab02carritokotlinpooia.carrito.Carrito

fun main() {
    val carrito = Carrito()

    val producto1 = Producto("P001", "Cuaderno", 5.5)
    val producto2 = Producto("P002", "Lapicero", 2.0)

    carrito.agregarProducto(producto1)
    carrito.agregarProducto(producto2)

    println("Productos registrados en el carrito:")
    for (producto in carrito.listarProductos()) {
        println("${producto.codigo} - ${producto.nombre} - S/ ${producto.precio}")
    }
}