## Fase 2 — Mejora con IA (rama `mejora-ia`)

La mejora se realizó con el agente de IA de Android Studio (Gemini) a partir de la rama `main`, mediante 3 prompts:

| # | Prompt | Resultado |
| --- | --- | --- |
| 1 | **Tema y pantalla de Inicio:** paleta morada, desactivar dynamicColor, topBar morada con saludo, chips de especialidad en forma de píldora y tarjetas de médicos con calificación. | Tema visual consistente con el diseño de referencia. |

En este proyecto Jetpack Compose (Clínica Salud+) aplica un tema visual morado consistente siguiendo estas reglas:

1. En ui/theme/Color.kt define: MoradoPrincipal #5B2A86, MoradoClaro #EDE3F6, FondoTarjeta #F3EFF7, TextoSecundario #6E6A75, DoradoEstrella #C9A227, VerdeExito #1E9E6A, VerdeClaro #E1F4EC.
2. En ui/theme/Theme.kt elimina dynamicColor y el tema oscuro; usa solo un lightColorScheme con primary = MoradoPrincipal, onPrimary = blanco, primaryContainer = MoradoClaro, surfaceVariant = FondoTarjeta y background blanco.
3. Pantalla de Inicio: la TopAppBar debe tener fondo MoradoPrincipal, texto blanco, título "Clínica Salud+" en negrita y debajo "Hola, Juan" en tamaño 12sp, con el ícono ☰ del drawer en blanco.
4. Los chips de especialidad de la LazyRow deben tener forma de píldora (RoundedCornerShape(50)): fondo MoradoPrincipal y texto blanco si están seleccionados, FondoTarjeta y texto oscuro si no.
5. Debajo de los chips, el título "Médicos disponibles" en negrita.
6. Cada tarjeta de médico: fondo FondoTarjeta, esquinas de 12dp, un círculo MoradoClaro a la izquierda con un ícono "+" morado, nombre en negrita, especialidad en TextoSecundario, y a la derecha una estrella DoradoEstrella con la calificación.

No uses ViewModel, mantén remember/mutableStateOf y no modifiques la navegación ni los parámetros existentes.

| 2 | **Cancelar cita con AlertDialog:** estado CANCELADA en el modelo, lista compartida con mutableStateListOf, botón "Cancelar" en citas confirmadas y diálogo de confirmación. | El usuario puede cancelar una cita y ver su estado en rojo. |

Agrega una mejora funcional para cancelar citas en la pantalla Mis citas del proyecto Clínica Salud+:

1. En el modelo de la cita agrega un id numérico y un enum EstadoCita con los valores CONFIRMADA, COMPLETADA y CANCELADA.
2. La lista de citas debe declararse con mutableStateListOf en el mismo nivel del NavHost, para compartirse entre Agendar cita y Mis citas y actualizar la UI al cambiar.
3. Cada tarjeta de cita: fondo #F3EFF7, esquinas de 12dp, una barra vertical de 4dp a la izquierda (morada #5B2A86 si está Confirmada, roja #C62828 si está Cancelada, sin barra si está Completada), nombre del médico en negrita y debajo "fecha, hora" en gris.
4. Etiqueta de estado con forma de píldora: Confirmada (fondo #E1F4EC, texto #1E9E6A), Completada (fondo #E6E4EA, texto gris), Cancelada (fondo #FDE7E7, texto #C62828).
5. Solo las citas Confirmadas muestran un TextButton rojo "Cancelar" a la derecha de la etiqueta.
6. Al tocarlo se abre un AlertDialog con título "¿Cancelar cita?", el texto "Se cancelará tu cita con [médico] el [fecha], [hora]. Esta acción no se puede deshacer." y los botones "Sí, cancelar" (rojo) y "No, mantener".
7. Al confirmar, reemplaza la cita en la lista con estado CANCELADA usando copy(); al descartar solo se cierra el diálogo.
8. Si la lista está vacía, muestra centrado "Aún no tienes citas agendadas".

No uses ViewModel, solo remember y mutableStateOf.

| 3 | **Perfil, Agendar y Confirmación:** estilo del perfil del médico, chips de fecha y hora de selección única, botón deshabilitado hasta elegir ambas, y pantalla de confirmación con check verde. | Flujo completo alineado con el diseño de referencia. |

Ajusta el estilo de las pantallas restantes del proyecto Clínica Salud+ con la paleta morada ya definida en el tema:

1. Perfil del médico: TopAppBar blanca con flecha de regreso y título "Perfil del médico". Contenido centrado: círculo grande #EDE3F6 con un "+" morado, nombre en negrita 20sp, debajo "Especialidad · años exp." en gris, y una estrella dorada con la calificación y número de reseñas. Luego la descripción alineada a la izquierda. Botón "Agendar cita" fijo abajo, morado, ancho completo, 52dp de alto y esquinas de 12dp.
2. Agendar cita: TopAppBar blanca con flecha y título "Agendar cita". Texto "Selecciona fecha" y una fila de chips de dos líneas (día abreviado arriba, número en negrita abajo); texto "Selecciona hora" y una fila de chips de hora. Todos de selección única: morado con texto blanco si están seleccionados, #F3EFF7 si no, esquinas de 12dp. Botón "Confirmar cita" fijo abajo con el mismo estilo, deshabilitado hasta elegir fecha y hora.
3. Confirmación: contenido centrado verticalmente, círculo verde claro #E1F4EC con un check verde #1E9E6A, título "¡Cita agendada!" en negrita, debajo el nombre del médico y "fecha, hora" en gris, y un botón tonal "Ver mis citas" con fondo #F3EFF7.

Mantén todos los parámetros de navegación que ya reciben estas pantallas, no uses ViewModel y aplica el innerPadding del Scaffold.
