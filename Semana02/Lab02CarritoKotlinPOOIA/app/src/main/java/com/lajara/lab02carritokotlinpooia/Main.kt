package com.lajara.lab02carritokotlinpooia

import com.lajara.lab02carritokotlinpooia.modelo.ProductoFisico
import com.lajara.lab02carritokotlinpooia.modelo.ProductoDigital
import com.lajara.lab02carritokotlinpooia.carrito.Carrito

fun main() {
    val carrito = Carrito()

    val producto1 = ProductoFisico("P001", "Cuaderno", 5.5, 0.3)
    val producto2 = ProductoDigital("P002", "Ebook Kotlin", 12.0, 4.5)

    carrito.agregarProducto(producto1)
    carrito.agregarProducto(producto2)

    producto1.actualizarPrecio(6.0)

    println("Productos registrados en el carrito (${carrito.cantidadProductos()}):")
    for (producto in carrito.listarProductos()) {
        println("${producto.detalle()} - Precio: S/ ${producto.precio} - Envío: S/ ${producto.calcularCostoEnvio()}")
    }

    println("Costo de envío total: S/ ${carrito.calcularEnvioTotal()}")
}