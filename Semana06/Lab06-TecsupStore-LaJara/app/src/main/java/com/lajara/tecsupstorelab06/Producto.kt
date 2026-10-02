package com.lajara.tecsupstorelab06

import kotlin.collections.listOf

data class Producto(
    val id: Int,
    val nombre: String,
    val precio: Double
)

val listaProductosFicticios = listOf(
    Producto(1, "Laptop ASUS Rog Strix", 4500.00),
    Producto(2, "Mouse Gamer Logi G502", 250.50),
    Producto(3, "Teclado Mecánico Razer", 380.00),
    Producto(4, "Monitor Curvo Samsung 27\"", 1200.00),
    Producto(5, "Audífonos HyperX Cloud II", 320.90)
)