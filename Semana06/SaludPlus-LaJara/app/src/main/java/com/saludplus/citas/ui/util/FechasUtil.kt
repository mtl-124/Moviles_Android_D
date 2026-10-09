package com.saludplus.citas.ui.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Utilidad centralizada para la gestión de fechas y formateo en español sin dependencia del Locale.
 */
object FechasUtil {

    private const val MES_SEPTIEMBRE = "setiembre"

    private val MESES = listOf(
        "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", MES_SEPTIEMBRE, "Octubre", "Noviembre", "Diciembre"
    )

    private val DIAS_SEMANA_COMPLETOS = listOf(
        "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"
    )

    private val DIAS_SEMANA_ABREVIADOS = listOf(
        "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom"
    )

    private val FORMATO_ISO = DateTimeFormatter.ISO_LOCAL_DATE

    /**
     * Devuelve los próximos [cantidad] días hábiles (lunes a viernes) empezando en [inicio].
     * Si [inicio] es sábado o domingo, comienza el lunes siguiente.
     */
    fun diasHabilesDesde(inicio: LocalDate, cantidad: Int = 5): List<LocalDate> {
        if (cantidad <= 0) return emptyList()
        val resultado = mutableListOf<LocalDate>()
        var actual = inicio
        while (resultado.size < cantidad) {
            if (actual.dayOfWeek != DayOfWeek.SATURDAY && actual.dayOfWeek != DayOfWeek.SUNDAY) {
                resultado.add(actual)
            }
            actual = actual.plusDays(1)
        }
        return resultado
    }

    /**
     * Devuelve los 5 días hábiles correspondientes a la semana indicada por [offset].
     * offset 0 = los próximos 5 días hábiles desde hoy incluido.
     * offset k > 0 = los 5 días hábiles que empiezan en el primer día hábil igual o posterior a hoy + 7*k días.
     */
    fun semana(offset: Int, hoy: LocalDate = LocalDate.now()): List<LocalDate> {
        val k = offset.coerceAtLeast(0)
        val inicioTeorico = hoy.plusDays(7L * k)
        return diasHabilesDesde(inicioTeorico, 5)
    }

    /**
     * Indica si es posible retroceder a una semana anterior según el [offset].
     */
    fun puedeRetroceder(offset: Int): Boolean = offset > 0

    /**
     * Retorna el nombre del mes con mayúscula inicial y el año en formato "Mes Año".
     * Ejemplo: "Octubre 2026"
     */
    fun nombreMesAnio(fecha: LocalDate): String {
        val mes = MESES[fecha.monthValue - 1]
        return "$mes ${fecha.year}"
    }

    /**
     * Retorna la abreviatura de tres letras del día de la semana.
     * Ejemplo: "Lun", "Mar", "Mié", "Jue", "Vie"
     */
    fun abreviaturaDia(fecha: LocalDate): String {
        return DIAS_SEMANA_ABREVIADOS[fecha.dayOfWeek.value - 1]
    }

    /**
     * Retorna la fecha en formato largo.
     * Ejemplo: "Martes 16 de setiembre 2026"
     */
    fun fechaLarga(fecha: LocalDate): String {
        val diaNombre = DIAS_SEMANA_COMPLETOS[fecha.dayOfWeek.value - 1]
        val mesNombreLower = MESES[fecha.monthValue - 1].lowercase()
        return "$diaNombre ${fecha.dayOfMonth} de $mesNombreLower ${fecha.year}"
    }

    /**
     * Convierte una fecha [LocalDate] a su representación en texto ISO (yyyy-MM-dd).
     */
    fun aTexto(fecha: LocalDate): String {
        return fecha.format(FORMATO_ISO)
    }

    /**
     * Parsea una cadena en formato ISO (yyyy-MM-dd) a [LocalDate].
     */
    fun desdeTexto(texto: String): LocalDate? {
        return try {
            LocalDate.parse(texto, FORMATO_ISO)
        } catch (_: Exception) {
            null
        }
    }
}
