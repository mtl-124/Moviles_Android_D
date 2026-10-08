package com.saludplus.citas.ui.screens.agendamiento

import androidx.compose.runtime.Composable

@Composable
fun ConfirmarCitaScreen(
    medicoId: String,
    fecha: String,
    hora: String,
    onCitaConfirmada: (String) -> Unit,
    onBackClick: () -> Unit
) {}