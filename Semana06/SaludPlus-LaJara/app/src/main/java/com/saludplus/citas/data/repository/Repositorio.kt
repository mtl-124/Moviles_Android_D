package com.saludplus.citas.data.repository

import com.saludplus.citas.data.model.Cita
import com.saludplus.citas.data.model.Especialidad
import com.saludplus.citas.data.model.Medico
import com.saludplus.citas.data.model.Usuario

object Repositorio {


    var usuarioActual: Usuario? = null


    private val usuarios = mutableListOf(
        Usuario("1", "Juan Pérez", "juan@correo.com", "987654321", "123456")
    )

    private val especialidades = mutableListOf(
        Especialidad("1", "Medicina General", "Atención médica primaria e integral", "ic_medicina"),
        Especialidad("2", "Pediatría", "Cuidado integral para niños y adolescentes", "ic_pediatria"),
        Especialidad("3", "Cardiología", "Diagnóstico y tratamiento del corazón", "ic_cardiologia"),
        Especialidad("4", "Odontología", "Salud bucal e higiene dental", "ic_odontologia"),
        Especialidad("5", "Dermatología", "Cuidado y tratamiento de la piel", "ic_dermatologia"),
        Especialidad("6", "Traumatología", "Lesiones de huesos y articulaciones", "ic_traumatologia")
    )

    private val medicos = mutableListOf(
        Medico("m1", "1", "Dra. Ana Torres", "CMP 45678", "4.8", "Especialista en medicina preventiva."),
        Medico("m2", "1", "Dr. Carlos Rojas", "CMP 38921", "4.6", "Médico general con 10 años de experiencia."),
        Medico("m3", "2", "Dra. Elena Ruiz", "CMP 51234", "4.9", "Atención pediátrica con calidez."),
        Medico("m4", "3", "Dr. Luis Ferney", "CMP 29811", "4.7", "Cardiólogo especialista en hipertensión."),
        Medico("m5", "4", "Dra. Jhonsy Soto", "CMP 61023", "4.8", "Cirujano dentista e higiene oral.")
    )

    private val citas = mutableListOf<Cita>()

    val horariosBase = listOf("08:00", "09:00", "10:00", "11:00", "14:00", "15:00", "16:00", "17:00")


    fun registrarUsuario(usuario: Usuario): Boolean {
        if (usuarios.any { it.correo.equals(usuario.correo, ignoreCase = true) }) {
            return false
        }
        usuarios.add(usuario)
        usuarioActual = usuario
        return true
    }

    fun iniciarSesion(correo: String, pass: String): Usuario? {
        val user = usuarios.find { it.correo.equals(correo, ignoreCase = true) && it.password == pass }
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

    fun buscarMedicos(query: String): List<Medico> {
        if (query.isBlank()) return medicos
        return medicos.filter { it.nombre.contains(query, ignoreCase = true) }
    }


    fun horariosDisponibles(medicoId: String, fecha: String): List<String> {
        val horasOcupadas = citas.filter { it.medicoId == medicoId && it.fecha == fecha }.map { it.hora }
        return horariosBase.filter { it !in horasOcupadas }
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