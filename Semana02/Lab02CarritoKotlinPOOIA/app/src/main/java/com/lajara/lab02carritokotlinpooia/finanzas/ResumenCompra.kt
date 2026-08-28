package com.lajara.lab02carritokotlinpooia.finanzas

import com.lajara.lab02carritokotlinpooia.carrito.Carrito

class ResumenCompra(private val carrito: Carrito) {

    companion object {
        const val PORCENTAJE_IGV = 0.18
        const val LIMITE_DESCUENTO_10 = 5000.0
        const val LIMITE_DESCUENTO_5 = 3000.0
        const val PORCENTAJE_DESCUENTO_10 = 0.10
        const val PORCENTAJE_DESCUENTO_5 = 0.05
    }

    fun calcularSubtotal(): Double {
        var subtotal = 0.0
        for (producto in carrito.listarProductos()) {
            subtotal += producto.precio
        }
        return subtotal
    }

    fun calcularPorcentajeDescuento(): Double {
        val subtotal = calcularSubtotal()
        return when {
            subtotal > LIMITE_DESCUENTO_10 -> PORCENTAJE_DESCUENTO_10
            subtotal > LIMITE_DESCUENTO_5 -> PORCENTAJE_DESCUENTO_5
            else -> 0.0
        }
    }

    fun calcularDescuento(): Double {
        return calcularSubtotal() * calcularPorcentajeDescuento()
    }

    fun calcularIgv(): Double {
        val baseConDescuento = calcularSubtotal() - calcularDescuento()
        return baseConDescuento * PORCENTAJE_IGV
    }

    fun calcularTotal(): Double {
        val baseConDescuento = calcularSubtotal() - calcularDescuento()
        return baseConDescuento + calcularIgv()
    }
}