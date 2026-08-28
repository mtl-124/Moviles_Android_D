package com.lajara.lab02carritokotlinpooia

import com.lajara.lab02carritokotlinpooia.modelo.ProductoFisico
import com.lajara.lab02carritokotlinpooia.modelo.ProductoDigital
import com.lajara.lab02carritokotlinpooia.carrito.Carrito
import com.lajara.lab02carritokotlinpooia.finanzas.ResumenCompra

fun main() {
    val carrito = Carrito()

    val producto1 = ProductoFisico("P001", "Laptop", 3500.0, 2.5)
    val producto2 = ProductoDigital("P002", "Curso Kotlin", 150.0, 4.5)
    val producto3 = ProductoFisico("P003", "Monitor", 1800.0, 6.0)

    carrito.agregarProducto(producto1)
    carrito.agregarProducto(producto2)
    carrito.agregarProducto(producto3)

    println("Productos registrados en el carrito (${carrito.cantidadProductos()}):")
    for (producto in carrito.listarProductos()) {
        println("${producto.detalle()} - Precio: S/ ${producto.precio}")
    }

    val masCaro = carrito.productoMasCaro()
    println("\nProducto más caro: ${masCaro?.nombre} - S/ ${masCaro?.precio}")

    val resumen = ResumenCompra(carrito)
    println("\nSubtotal: S/ ${resumen.calcularSubtotal()}")
    println("Descuento aplicado: ${resumen.calcularPorcentajeDescuento() * 100}% (S/ ${resumen.calcularDescuento()})")
    println("IGV (18%): S/ ${resumen.calcularIgv()}")
    println("Total a pagar: S/ ${resumen.calcularTotal()}")
}