package com.saludplus.citas.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChildCare
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Healing
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.saludplus.citas.R
import com.saludplus.citas.ui.theme.PastelAzulFondo
import com.saludplus.citas.ui.theme.PastelAzulIcono
import com.saludplus.citas.ui.theme.PastelCelesteFondo
import com.saludplus.citas.ui.theme.PastelCelesteIcono
import com.saludplus.citas.ui.theme.PastelNaranjaFondo
import com.saludplus.citas.ui.theme.PastelNaranjaIcono
import com.saludplus.citas.ui.theme.PastelRojoFondo
import com.saludplus.citas.ui.theme.PastelRojoIcono
import com.saludplus.citas.ui.theme.PastelRosaFondo
import com.saludplus.citas.ui.theme.PastelRosaIcono

data class DatosIconoEspecialidad(
    val imageVector: ImageVector? = null,
    val drawableRes: Int? = null,
    val colorFondo: Color,
    val colorIcono: Color
)

fun obtenerIconoEspecialidad(nombre: String): DatosIconoEspecialidad {
    val n = nombre.lowercase().trim()
    return when {
        n.contains("medicina") -> DatosIconoEspecialidad(
            imageVector = Icons.Filled.Person,
            colorFondo = PastelAzulFondo,
            colorIcono = PastelAzulIcono
        )
        n.contains("pediatra") || n.contains("pediatría") -> DatosIconoEspecialidad(
            imageVector = Icons.Filled.ChildCare,
            colorFondo = PastelNaranjaFondo,
            colorIcono = PastelNaranjaIcono
        )
        n.contains("gineco") -> DatosIconoEspecialidad(
            drawableRes = R.drawable.ic_ginecologia,
            colorFondo = PastelRosaFondo,
            colorIcono = PastelRosaIcono
        )
        n.contains("cardio") -> DatosIconoEspecialidad(
            imageVector = Icons.Filled.Favorite,
            colorFondo = PastelRojoFondo,
            colorIcono = PastelRojoIcono
        )
        n.contains("dermato") -> DatosIconoEspecialidad(
            imageVector = Icons.Filled.Face,
            colorFondo = PastelNaranjaFondo,
            colorIcono = PastelNaranjaIcono
        )
        n.contains("traumato") -> DatosIconoEspecialidad(
            imageVector = Icons.Filled.Healing,
            colorFondo = PastelAzulFondo,
            colorIcono = PastelAzulIcono
        )
        n.contains("oftalmo") -> DatosIconoEspecialidad(
            imageVector = Icons.Filled.Visibility,
            colorFondo = PastelAzulFondo,
            colorIcono = PastelAzulIcono
        )
        else -> DatosIconoEspecialidad(
            imageVector = Icons.Filled.MedicalServices,
            colorFondo = PastelCelesteFondo,
            colorIcono = PastelCelesteIcono
        )
    }
}

@Composable
fun IconoEspecialidad(
    nombre: String,
    modifier: Modifier = Modifier,
    sizeIcono: Dp = 28.dp
) {
    val datos = obtenerIconoEspecialidad(nombre)
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (datos.imageVector != null) {
            Icon(
                imageVector = datos.imageVector,
                contentDescription = nombre,
                tint = datos.colorIcono,
                modifier = Modifier.size(sizeIcono)
            )
        } else if (datos.drawableRes != null) {
            Icon(
                painter = painterResource(id = datos.drawableRes),
                contentDescription = nombre,
                tint = datos.colorIcono,
                modifier = Modifier.size(sizeIcono)
            )
        }
    }
}
