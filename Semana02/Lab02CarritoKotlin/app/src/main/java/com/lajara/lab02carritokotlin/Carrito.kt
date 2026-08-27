package com.lajara.lab02carritokotlin

data class Producto(
    val nombre: String,
    val precio: Double,
    var cantidad: Int
)


fun main() {
    println("=========================================")
    println("   CARRITO DE COMPRAS - TIENDA TECSUP    ")
    println("=========================================")
    println("-----------------------------------------")


    val nombreCliente = "Gael La Jara"
    val carrito = mutableListOf<Producto>()
    println("Cliente: $nombreCliente")
    println()
    carrito.add(Producto("Laptop HP",2500.0,1))
    carrito.add(Producto("Mouse Logitech",45.5,2))
    carrito.add(Producto("Tablet Lenovo",1200.0,1))
    carrito.add(Producto("Smartphone Samsung",1800.0,1))
    for (Producto in carrito) {
        println("Producto agregado: ${Producto.nombre}")
    }
    println()

    val subtotal = calcularSubtotal(carrito)
    val igv = calcularIGV(subtotal)
    val total = calcularTotal(subtotal, igv)


    mostrarDetalle(carrito)
    println(String.format("%-25s : %d", "Cantidad de productos", carrito.size))

    println(String.format("%-25s : S/ %8.2f", "Subtotal",subtotal))
    println(String.format("%-25s : S/ %8.2f", "IGV",igv))
    println(String.format("%-25s : S/ %8.2f", "TOTAL A PAGAR",total))
    println("-----------------------------------------")

    mostrarProductoMasCaro(carrito)
    val descuento = calcularDescuento(total)
    val totalConDescuento = total - descuento

    if (descuento > 0) {
        println(String.format("Descuento aplicado: S/ %.2f", descuento))
    } else {
        println("Descuento aplicado: S/ 0.00")
    }

    println(String.format("%-25s : S/ %8.2f", "TOTAL CON DESCUENTO", totalConDescuento))

    println("-----------------------------------------")
    println("Gracias por su compra, $nombreCliente!")
}

fun calcularSubtotal(productos: List<Producto>): Double {
    var subtotal = 0.0
    for (p in productos){
        subtotal += p.precio * p.cantidad
    }
    return subtotal
}

fun calcularIGV(subtotal: Double): Double {
    return subtotal * 0.18
}

fun calcularTotal(subtotal:Double, igv: Double):Double {
    return subtotal + igv
}

fun mostrarDetalle(productos:List<Producto>){
    println("---------- DETALLE DEL CARRITO ----------")
    var i = 1
    for (p in productos){
        val importe = p.precio * p.cantidad
        println(String.format("%d. %-20s x%d S/ %8.2f",
            i, p.nombre, p.cantidad, importe))
        i++
    }
    println("-----------------------------------------")
}

fun mostrarProductoMasCaro(productos: List<Producto>) {
    val masCaro = productos.maxByOrNull { it.precio}

    if (masCaro != null) {
        println("Producto mas caro: ${masCaro.nombre}" +
            String.format(" (S/ %.2f) ", masCaro.precio))
    }
}

fun calcularDescuento(total: Double): Double {
    return when {
        total > 5000 -> total * 0.10
        total > 3000 -> total * 0.05
        else -> 0.0
    }
}