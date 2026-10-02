package com.lajara.tecsupstorelab06

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var pantallaActual by remember { mutableStateOf("mis_pedidos") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(text = "Menú", modifier = Modifier.padding(16.dp))

                NavigationDrawerItem(
                    label = { Text("Inicio") },
                    selected = pantallaActual == "inicio",
                    onClick = {
                        pantallaActual = "inicio"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Mis pedidos") },
                    selected = pantallaActual == "mis_pedidos",
                    onClick = {
                        pantallaActual = "mis_pedidos"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Favoritos") },
                    selected = pantallaActual == "favoritos",
                    onClick = {
                        pantallaActual = "favoritos"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Perfil") },
                    selected = pantallaActual == "perfil",
                    onClick = {
                        pantallaActual = "perfil"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
                NavigationDrawerItem(
                    label = { Text("Cerrar sesión") },
                    selected = pantallaActual == "cerrar_sesion",
                    onClick = {
                        pantallaActual = "cerrar_sesion"
                        scope.launch { drawerState.close() }
                    },
                    modifier = Modifier.padding(horizontal = 12.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                        }
                    },
                    title = {
                        val titulo = when(pantallaActual) {
                            "inicio" -> "Inicio"
                            "mis_pedidos" -> "TECSUP Store"
                            "favoritos" -> "Mis Favoritos"
                            "perfil" -> "Mi Perfil"
                            else -> "TECSUP Store"
                        }
                        Text(text = titulo)
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary
                    )
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (pantallaActual) {
                    "mis_pedidos" -> {
                        LazyColumn(
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            items(listaProductosFicticios) { itemProducto ->
                                Box(modifier = Modifier.padding(vertical = 8.dp)) {
                                    TarjetaProducto(producto = itemProducto, onExpanded = {})
                                }
                            }
                        }
                    }
                    "inicio" -> {
                        Text("Pantalla de Inicio", modifier = Modifier.padding(16.dp))
                    }
                    "favoritos" -> {
                        Text("Pantalla de Favoritos", modifier = Modifier.padding(16.dp))
                    }
                    "perfil" -> {
                        Text("Pantalla de Perfil", modifier = Modifier.padding(16.dp))
                    }
                    "cerrar_sesion" -> {
                        Text("Has cerrado sesión", modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}
