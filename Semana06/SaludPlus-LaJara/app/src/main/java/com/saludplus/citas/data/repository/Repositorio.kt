package com.saludplus.citas.data.repository

import com.saludplus.citas.data.model.Cita
import com.saludplus.citas.data.model.Especialidad
import com.saludplus.citas.data.model.Medico
import com.saludplus.citas.data.model.Sede
import com.saludplus.citas.data.model.Usuario
import com.saludplus.citas.ui.util.FechasUtil
import java.time.DayOfWeek
import java.time.LocalDate

object Repositorio {

    var usuarioActual: Usuario? = null

    val sedes = listOf(
        Sede(1, "Sede La Independencia", "Independencia, Lima"),
        Sede(2, "Sede La Molina", "La Molina, Lima")
    )

    var sedeSeleccionada: Sede? = null

    private val usuarios = mutableListOf(
        Usuario("1", "Juan Pérez", "juan@correo.com", "987654321", "123456")
    )

    private val especialidades = mutableListOf(
        Especialidad("1", "Medicina General", "Atención médica primaria e integral", "ic_medicina"),
        Especialidad("2", "Pediatría", "Cuidado integral para niños y adolescentes", "ic_pediatria"),
        Especialidad("3", "Cardiología", "Diagnóstico y tratamiento del corazón", "ic_cardiologia"),
        Especialidad("4", "Odontología", "Salud bucal e higiene dental", "ic_odontologia"),
        Especialidad("5", "Dermatología", "Cuidado y tratamiento de la piel", "ic_dermatologia"),
        Especialidad("6", "Traumatología", "Lesiones de huesos y articulaciones", "ic_traumatologia"),
        Especialidad("7", "Ginecología", "Salud de la mujer", "ic_ginecologia")
    )

    private val medicos = mutableListOf(
        // Medicina General (1)
        Medico("m1", "1", "Dra. Ana Torres", "CMP 45678", "4.8", "Especialista en medicina preventiva."),
        Medico("m2", "1", "Dr. Carlos Rojas", "CMP 38921", "4.6", "Médico general con 10 años de experiencia."),
        Medico("m3", "1", "Dra. Sofía Mendoza", "CMP 41209", "4.7", "Medicina familiar e integral."),
        Medico("m4", "1", "Dr. Jorge Ramos", "CMP 33451", "4.9", "Especialista en control y prevención."),

        // Pediatría (2)
        Medico("m5", "2", "Dra. Elena Ruiz", "CMP 51234", "4.9", "Atención pediátrica con calidez."),
        Medico("m6", "2", "Dr. Miguel Paredes", "CMP 56712", "4.8", "Pediatra neonatólogo con experiencia."),
        Medico("m7", "2", "Dra. Carmen Silva", "CMP 58901", "4.7", "Especialista en desarrollo infantil."),
        Medico("m8", "2", "Dr. Roberto Castro", "CMP 52341", "4.6", "Cuidado integral del adolescente."),

        // Cardiología (3)
        Medico("m9", "3", "Dr. Luis Ferney", "CMP 29811", "4.7", "Cardiólogo especialista en hipertensión."),
        Medico("m10", "3", "Dra. Patricia Vega", "CMP 31245", "4.9", "Especialista en electrofisiología."),
        Medico("m11", "3", "Dr. Fernando Alva", "CMP 28765", "4.8", "Cardiología clínica e intervencionista."),
        Medico("m12", "3", "Dra. Lucía Benítez", "CMP 34512", "4.6", "Prevención de riesgo cardiovascular."),

        // Odontología (4)
        Medico("m13", "4", "Dra. Jhonsy Soto", "CMP 61023", "4.8", "Cirujano dentista e higiene oral."),
        Medico("m14", "4", "Dr. Andrés Gil", "CMP 62341", "4.7", "Especialista en endodoncia y prótesis."),
        Medico("m15", "4", "Dra. María Paz", "CMP 63452", "4.9", "Ortodoncia y estética dental."),
        Medico("m16", "4", "Dr. Gabriel Ortiz", "CMP 64512", "4.5", "Odontopediatría y cirugía bucal."),

        // Dermatología (5)
        Medico("m17", "5", "Dra. Beatriz León", "CMP 71234", "4.9", "Dermatología clínica y cosmética."),
        Medico("m18", "5", "Dr. Héctor Prado", "CMP 72345", "4.8", "Especialista en acné y alopecias."),
        Medico("m19", "5", "Dra. Vanessa Ríos", "CMP 73456", "4.7", "Dermatología pediátrica y láser."),
        Medico("m20", "5", "Dr. Manuel Yáñez", "CMP 74567", "4.6", "Prevención y cáncer de piel."),

        // Traumatología (6)
        Medico("m21", "6", "Dr. Ricardo Palma", "CMP 81234", "4.8", "Traumatología de rodilla y hombro."),
        Medico("m22", "6", "Dra. Diana Quispe", "CMP 82345", "4.9", "Especialista en medicina deportiva."),
        Medico("m23", "6", "Dr. César Vidal", "CMP 83456", "4.7", "Cirugía de columna y rodilla."),
        Medico("m24", "6", "Dra. Gloria Núñez", "CMP 84567", "4.6", "Traumatología infantil y fracturas."),

        // Ginecología (7) - 6 doctores
        Medico("m25", "7", "Dra. Ana Torres", "CMP 91234", "4.9", "Ginecología y obstetricia integral."),
        Medico("m26", "7", "Dra. Claudia Rojas", "CMP 92345", "4.8", "Especialista en medicina fetal y ecografía."),
        Medico("m27", "7", "Dr. Luis Ramírez", "CMP 93456", "4.9", "Ginecología endocrinológica y fertilidad."),
        Medico("m28", "7", "Dra. Mariana Soto", "CMP 94567", "4.7", "Salud reproductiva y laparoscopia."),
        Medico("m29", "7", "Dra. Isabel Guzmán", "CMP 95678", "4.8", "Atención ginecológica del adolescente."),
        Medico("m30", "7", "Dr. Hugo Morales", "CMP 96789", "4.6", "Obstetricia de alto riesgo y cirugía.")
    )

    val diasAtencion: Map<String, Set<DayOfWeek>> = mapOf(
        "m1" to setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
        "m2" to setOf(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
        "m3" to setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
        "m4" to setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY),

        "m5" to setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
        "m6" to setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
        "m7" to setOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY),
        "m8" to setOf(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),

        "m9" to setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY),
        "m10" to setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
        "m11" to setOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY),
        "m12" to setOf(DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY),

        "m13" to setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY),
        "m14" to setOf(DayOfWeek.THURSDAY, DayOfWeek.FRIDAY),
        "m15" to setOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY),
        "m16" to setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),

        "m17" to setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
        "m18" to setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
        "m19" to setOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY),
        "m20" to setOf(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),

        "m21" to setOf(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
        "m22" to setOf(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
        "m23" to setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.SATURDAY),
        "m24" to setOf(DayOfWeek.TUESDAY, DayOfWeek.FRIDAY),

        "m25" to setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
        "m26" to setOf(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY),
        "m27" to setOf(DayOfWeek.MONDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY),
        "m28" to setOf(DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY),
        "m29" to setOf(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY),
        "m30" to setOf(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
    )

    val horasAtencion: Map<String, List<String>> = mapOf(
        "m1" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00"),
        "m2" to listOf("14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00"),
        "m3" to listOf("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00"),
        "m4" to listOf("08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00"),

        "m5" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"),
        "m6" to listOf("14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"),
        "m7" to listOf("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"),
        "m8" to listOf("10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30"),

        "m9" to listOf("14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"),
        "m10" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"),
        "m11" to listOf("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"),
        "m12" to listOf("08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00"),

        "m13" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"),
        "m14" to listOf("14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"),
        "m15" to listOf("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"),
        "m16" to listOf("15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"),

        "m17" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"),
        "m18" to listOf("14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"),
        "m19" to listOf("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"),
        "m20" to listOf("15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"),

        "m21" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"),
        "m22" to listOf("14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"),
        "m23" to listOf("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"),
        "m24" to listOf("15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"),

        "m25" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"),
        "m26" to listOf("14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30"),
        "m27" to listOf("09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30"),
        "m28" to listOf("15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30"),
        "m29" to listOf("08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30"),
        "m30" to listOf("10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30")
    )

    private val citas = mutableListOf<Cita>()

    val horariosBase = listOf("08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00")

    fun diasAtencionDe(medicoId: String): Set<DayOfWeek> {
        return diasAtencion[medicoId] ?: setOf(DayOfWeek.MONDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)
    }

    fun atiendeEn(medicoId: String, fecha: LocalDate): Boolean {
        return fecha.dayOfWeek in diasAtencionDe(medicoId)
    }

    fun textoDiasAtencion(medicoId: String): String {
        val dias = diasAtencionDe(medicoId).sortedBy { it.value }
        val nombres = mapOf(
            DayOfWeek.MONDAY to "Lun",
            DayOfWeek.TUESDAY to "Mar",
            DayOfWeek.WEDNESDAY to "Mié",
            DayOfWeek.THURSDAY to "Jue",
            DayOfWeek.FRIDAY to "Vie",
            DayOfWeek.SATURDAY to "Sáb",
            DayOfWeek.SUNDAY to "Dom"
        )
        return dias.mapNotNull { nombres[it] }.joinToString(" · ")
    }

    fun existeTelefono(telefono: String): Boolean {
        return usuarios.any { it.telefono == telefono }
    }

    fun existeCorreo(correo: String): Boolean {
        if (correo.isBlank()) return false
        return usuarios.any { it.correo.equals(correo, ignoreCase = true) }
    }

    fun registrarUsuario(usuario: Usuario): Boolean {
        if (existeTelefono(usuario.telefono)) {
            return false
        }
        if (usuario.correo.isNotBlank() && existeCorreo(usuario.correo)) {
            return false
        }
        usuarios.add(usuario)
        usuarioActual = usuario
        return true
    }

    fun iniciarSesion(correo: String, pass: String): Usuario? {
        val user = usuarios.find { (it.correo.equals(correo, ignoreCase = true) || it.telefono == correo) && it.password == pass }
        if (user != null) {
            usuarioActual = user
        }
        return user
    }

    fun cerrarSesion() {
        usuarioActual = null
    }

    fun buscarEspecialidades(query: String): List<Especialidad> {
        if (query.isBlank()) return especialidades
        return especialidades.filter { it.nombre.contains(query, ignoreCase = true) }
    }

    fun especialidadesDestacadas(): List<Especialidad> {
        return especialidades.take(4)
    }

    fun obtenerEspecialidad(id: String): Especialidad? = especialidades.find { it.id == id }

    fun obtenerMedico(id: String): Medico? = medicos.find { it.id == id }

    fun medicosPorEspecialidad(especialidadId: String): List<Medico> {
        return medicos.filter { it.especialidadId == especialidadId }
            .sortedByDescending { it.calificacion.toDoubleOrNull() ?: 0.0 }
    }

    fun medicosAgrupadosPorEspecialidad(): Map<Especialidad, List<Medico>> {
        return especialidades.associateWith { esp ->
            medicosPorEspecialidad(esp.id)
        }.filterValues { it.isNotEmpty() }
    }

    fun buscarMedicos(query: String): List<Medico> {
        if (query.isBlank()) return medicos
        return medicos.filter { it.nombre.contains(query, ignoreCase = true) }
    }

    fun horariosDisponibles(medicoId: String, fecha: String): List<String> {
        val fechaObj = FechasUtil.desdeTexto(fecha)
        if (fechaObj != null && !atiendeEn(medicoId, fechaObj)) {
            return emptyList()
        }

        val horariosDelMedico = horasAtencion[medicoId] ?: horariosBase
        val horasOcupadas = citas.filter { it.medicoId == medicoId && it.fecha == fecha }.map { it.hora }
        return horariosDelMedico.filter { it !in horasOcupadas }
    }

    fun agendarCita(cita: Cita): Boolean {
        if (citas.any { it.medicoId == cita.medicoId && it.fecha == cita.fecha && it.hora == cita.hora }) {
            return false
        }
        citas.add(cita)
        return true
    }

    fun citasDelUsuario(usuarioId: String): List<Cita> {
        return citas.filter { it.usuarioId == usuarioId }
            .sortedWith(compareBy({ it.fecha }, { it.hora }))
    }

    fun obtenerCita(id: String): Cita? = citas.find { it.id == id }

    fun cancelarCita(citaId: String): Boolean {
        return citas.removeIf { it.id == citaId }
    }
}
