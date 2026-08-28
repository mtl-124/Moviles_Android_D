package com.lajara.lab02carritokotlinpooia.uicarrito

import com.lajara.lab02carritokotlinpooia.carrito.Carrito
import com.lajara.lab02carritokotlinpooia.finanzas.ResumenCompra
import com.lajara.lab02carritokotlinpooia.modelo.ProductoDigital
import com.lajara.lab02carritokotlinpooia.modelo.ProductoFisico

class MenuCarrito {

    private val carrito = Carrito()

    fun iniciar() {
        var opcion: Int
        do {
            mostrarMenu()
            opcion = readLine()?.toIntOrNull() ?: -1
            procesarOpcion(opcion)
        } while (opcion != 0)
    }

    private fun mostrarMenu() {
        println(
            """
            
            ===== MENU CARRITO DE COMPRAS =====
            1. Agregar producto físico
            2. Agregar producto digital
            3. Mostrar detalle del carrito
            4. Mostrar resumen de compra (subtotal, IGV, descuento, total)
            5. Mostrar producto más caro
            0. Salir
            Seleccione una opción: 
            """.trimIndent()
        )
    }

    private fun procesarOpcion(opcion: Int) {
        when (opcion) {
            1 -> agregarProductoFisico()
            2 -> agregarProductoDigital()
            3 -> mostrarDetalleCarrito()
            4 -> mostrarResumenCompra()
            5 -> mostrarProductoMasCaro()
            0 -> println("Saliendo del sistema...")
            else -> println("Opción no válida.")
        }
    }

    private fun agregarProductoFisico() {
        try {
            println("Código:")
            val codigo = readLine().orEmpty()
            println("Nombre:")
            val nombre = readLine().orEmpty()
            println("Precio:")
            val precio = readLine()?.toDoubleOrNull() ?: 0.0
            println("Peso (kg):")
            val peso = readLine()?.toDoubleOrNull() ?: 0.0

            val producto = ProductoFisico(codigo, nombre, precio, peso)
            carrito.agregarProducto(producto)
            println("Producto físico agregado correctamente.")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    }

    private fun agregarProductoDigital() {
        try {
            println("Código:")
            val codigo = readLine().orEmpty()
            println("Nombre:")
            val nombre = readLine().orEmpty()
            println("Precio:")
            val precio = readLine()?.toDoubleOrNull() ?: 0.0
            println("Tamaño (MB):")
            val tamano = readLine()?.toDoubleOrNull() ?: 0.0

            val producto = ProductoDigital(codigo, nombre, precio, tamano)
            carrito.agregarProducto(producto)
            println("Producto digital agregado correctamente.")
        } catch (e: IllegalArgumentException) {
            println("Error: ${e.message}")
        }
    }

    private fun mostrarDetalleCarrito() {
        val productos = carrito.listarProductos()
        if (productos.isEmpty()) {
            println("El carrito está vacío.")
            return
        }
        println("Detalle del carrito (${carrito.cantidadProductos()} productos):")
        for (producto in productos) {
            println("${producto.detalle()} - Precio: S/ ${producto.precio} - Envío: S/ ${producto.calcularCostoEnvio()}")
        }
        println("Envío total: S/ ${carrito.calcularEnvioTotal()}")
    }

    private fun mostrarResumenCompra() {
        if (carrito.cantidadProductos() == 0) {
            println("El carrito está vacío.")
            return
        }
        val resumen = ResumenCompra(carrito)
        println("Subtotal: S/ ${resumen.calcularSubtotal()}")
        println("Descuento aplicado: ${resumen.calcularPorcentajeDescuento() * 100}% (S/ ${resumen.calcularDescuento()})")
        println("IGV (18%): S/ ${resumen.calcularIgv()}")
        println("Total a pagar: S/ ${resumen.calcularTotal()}")
    }

    private fun mostrarProductoMasCaro() {
        val masCaro = carrito.productoMasCaro()
        if (masCaro == null) {
            println("El carrito está vacío.")
        } else {
            println("Producto más caro: ${masCaro.nombre} - S/ ${masCaro.precio}")
        }
    }
}