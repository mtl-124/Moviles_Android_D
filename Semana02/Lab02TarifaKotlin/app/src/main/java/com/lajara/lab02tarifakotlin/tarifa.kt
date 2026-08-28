package com.lajara.lab02tarifakotlin

import kotlin.system.exitProcess

// Representa un vehículo registrado
data class Vehiculo(
    val placa: String,
    val tipo: String,
    val horas: Int,
    val cliente: String
)

// Encargada de calcular la tarifa total de un vehículo según su tipo y horas
class CalculadoraTarifa {

    private val tarifaBase = mapOf(
        "Moto" to 2.00,
        "Auto" to 4.00,
        "Camioneta" to 10.00
    )

    fun calcularTotal(vehiculo: Vehiculo): Double {
        val base = tarifaBase[vehiculo.tipo] ?: 0.0
        var total = 0.0

        for (hora in 1..vehiculo.horas) {
            val recargo = when {
                hora <= 2 -> 0.0
                hora in 3..5 -> 0.20
                else -> 0.50
            }
            total += base * (1 + recargo)
        }

        return total
    }
}

// Controla el registro de vehículos: almacenamiento, validación e ingreso de datos
class RegistroVehiculos {

    private val vehiculos = mutableListOf<Vehiculo>()
    private val capacidadMaxima = 10
    private val tiposValidos = listOf("Moto", "Auto", "Camioneta")
    private val calculadora = CalculadoraTarifa()

    fun registrarVehiculo() {
        if (vehiculos.size >= capacidadMaxima) {
            println("Registro lleno. No se pueden ingresar más vehículos.")
            return
        }

        val placa = leerPlaca()
        val tipo = leerTipo()
        val horas = leerHoras()
        val cliente = leerCliente()

        vehiculos.add(Vehiculo(placa, tipo, horas, cliente))
        println("Vehículo registrado correctamente.\n")
    }

    private fun leerPlaca(): String {
        print("Placa: ")
        return readLine()?.trim().orEmpty()
    }

    private fun leerTipo(): String {
        while (true) {
            println("Tipo de vehículo:")
            tiposValidos.forEachIndexed { i, t -> println("${i + 1}. $t") }
            print("Seleccione una opción: ")
            val opcion = readLine()?.trim()?.toIntOrNull()
            if (opcion != null && opcion in 1..tiposValidos.size) {
                return tiposValidos[opcion - 1]
            }
            println("Opción inválida. Intente nuevamente.\n")
        }
    }

    private fun leerHoras(): Int {
        while (true) {
            print("Horas: ")
            val horas = readLine()?.trim()?.toIntOrNull()
            if (horas != null && horas >= 1) {
                return horas
            }
            println("Las horas deben ser un número entero mayor o igual a 1.\n")
        }
    }

    private fun leerCliente(): String {
        print("Cliente: ")
        return readLine()?.trim().orEmpty()
    }

    fun mostrarVehiculosRegistrados() {
        if (vehiculos.isEmpty()) {
            println("No hay vehículos registrados aún.")
            return
        }
        println("\n--- Vehículos registrados (${vehiculos.size}) ---")
        vehiculos.forEachIndexed { i, v ->
            println("${i + 1}. Placa: ${v.placa} | Tipo: ${v.tipo} | Horas: ${v.horas} | Cliente: ${v.cliente}")
        }
    }

    fun calcularTotalAcumulado(vehiculo: Vehiculo): Double {
        return calculadora.calcularTotal(vehiculo)
    }

    fun obtenerVehiculos(): List<Vehiculo> = vehiculos

    fun espacioDisponible(): Boolean = vehiculos.size < capacidadMaxima
}

fun main() {
    val registro = RegistroVehiculos()

    while (registro.espacioDisponible()) {
        registro.registrarVehiculo()
        print("¿Desea registrar otro vehículo? (s/n): ")
        val continuar = readLine()?.trim()?.lowercase()
        if (continuar != "s") break
    }

    registro.mostrarVehiculosRegistrados()
    exitProcess(0)
}