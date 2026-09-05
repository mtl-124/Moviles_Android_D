package com.lajara.lab03registroproducto

import android.R
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lajara.lab03registroproducto.ui.theme.Lab03RegistroProductoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab03RegistroProductoTheme {
                PantallaRegistro()
            }
        }
    }
}

@Composable
fun PantallaRegistro(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var mostrarResumen by remember { mutableStateOf(false) }



    Column(
    modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
){
    Text(
        text = "Nuevo producto",
        style = MaterialTheme.typography.headlineSmall
    )
    Text(
        text = "Completa los datos y presionar Agregar",style
        = MaterialTheme.typography.bodyMedium, color =
            MaterialTheme.colorScheme.outline
    )

    Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = nombre,
            onValueChange = {nombre = it },
            label = {Text("Nombre del Producto")}, modifier = Modifier.fillMaxWidth())

    Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ){
                OutlinedTextField(
                    value = precio,
                    onValueChange =  { precio = it },
                    label =  { Text("Precio") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    value = cantidad,
                    onValueChange = { cantidad = it },
                    label = { Text("Cantidad" )},
                    modifier = Modifier.weight(1f)
                )
            }

    Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { mostrarResumen = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("AGREGAR PRODUCTO")
        }

    }

    Spacer(modifier = Modifier.height(24.dp))

    if (mostrarResumen) {
        val precioNum = precio.toDoubleOrNull() ?: 0.0
        val cantidadNum = cantidad.toIntOrNull() ?: 0
        val importe = 0.0 // TODO: calcula precio x cantidad

        Card(
            modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(nombre, style = MaterialTheme.typography.titleLarge)
                Text("Precio: S/ " + String.format("%.2f", precioNum))
            }
        }
    }




}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab03RegistroProductoTheme() {
        PantallaRegistro()
    }
}


