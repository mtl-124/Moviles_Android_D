## Fase 2 — Mejora con IA (rama `mejora-ia`) - TECSUP FIT

La mejora se realizó con el agente de IA de Android Studio (Gemini) a partir de la rama `main`, mediante 3 prompts:

| # | Prompt | Resultado |
| --- | --- | --- |
| 1 | **Tema, Inicio y bottomBar:** paleta verde, desactivar dynamicColor, topBar verde con saludo, filtros "Hoy" / "Esta semana" funcionales, tarjetas de clases con ícono y bottomBar con pestaña activa resaltada. | Tema visual consistente con el diseño de referencia. |

En este proyecto Jetpack Compose (TECSUP Fit) aplica un tema visual verde consistente siguiendo estas reglas:

1. En ui/theme/Color.kt define: VerdePrincipal #0F6E56, VerdeClaro #E1F4EC, FondoTarjeta #F0F0F0, TextoSecundario #6E6E6E, GrisClaro #E4E4E4.
2. En ui/theme/Theme.kt elimina dynamicColor y el tema oscuro; usa solo un lightColorScheme con primary = VerdePrincipal, onPrimary = blanco, primaryContainer = VerdeClaro, surfaceVariant = FondoTarjeta y background blanco.
3. Pantalla de Inicio: TopAppBar con fondo VerdePrincipal, texto blanco, título "TECSUP Fit" en negrita y debajo "Hola, Diego" en 12sp.
4. Chips de filtro "Hoy" y "Esta semana" en la LazyRow con forma de píldora (RoundedCornerShape(50)): fondo VerdePrincipal y texto blanco si están seleccionados, FondoTarjeta y texto oscuro si no. Al seleccionar un chip, la lista debe filtrar las clases correspondientes.
5. Debajo de los chips, el título "Clases disponibles" en negrita.
6. Cada tarjeta de clase: fondo FondoTarjeta, esquinas de 12dp, un cuadro VerdeClaro con esquinas de 8dp a la izquierda con un ícono de pesa (Icons.Default.FitnessCenter) en VerdePrincipal, nombre en negrita y debajo "horario · sala" en TextoSecundario.
7. bottomBar con fondo blanco y una línea divisoria superior, 4 pestañas (Inicio, Reservas, Rutinas, Perfil). La pestaña activa muestra ícono y texto en VerdePrincipal con texto en negrita; las inactivas en gris. Sin el indicador de fondo por defecto de NavigationBarItem (indicatorColor transparente).

No uses ViewModel, mantén remember/mutableStateOf y no modifiques la navegación ni los parámetros existentes.

| 2 | **Cancelar reserva con AlertDialog:** estado CANCELADA en el modelo, lista compartida con mutableStateListOf, botón "Cancelar" en reservas confirmadas y diálogo de confirmación. | El usuario puede cancelar una reserva y ver su estado en rojo. |

Agrega una mejora funcional para cancelar reservas en la pantalla Mis reservas del proyecto TECSUP Fit:

1. En el modelo de la reserva agrega un id numérico y un enum EstadoReserva con los valores CONFIRMADA, COMPLETADA y CANCELADA.
2. La lista de reservas debe declararse con mutableStateListOf en el mismo nivel del NavHost, para compartirse entre Detalle de clase y Mis reservas y actualizar la UI al cambiar.
3. Cada tarjeta de reserva: fondo #F0F0F0, esquinas de 12dp, barra vertical de 4dp a la izquierda (verde #0F6E56 si está Confirmada, roja #C62828 si está Cancelada, sin barra si está Completada), nombre de la clase en negrita y debajo "día, hora" en gris.
4. Etiqueta de estado con forma de píldora: Confirmada (fondo #E1F4EC, texto #0F6E56), Completada (fondo #E4E4E4, texto gris), Cancelada (fondo #FDE7E7, texto #C62828).
5. Solo las reservas Confirmadas muestran un TextButton rojo "Cancelar" a la derecha de la etiqueta.
6. Al tocarlo se abre un AlertDialog con título "¿Cancelar reserva?", el texto "Se liberará tu cupo en [clase] el [día], [hora]. Esta acción no se puede deshacer." y los botones "Sí, cancelar" (rojo) y "No, mantener".
7. Al confirmar, reemplaza la reserva en la lista con estado CANCELADA usando copy(); al descartar solo se cierra el diálogo.
8. Si la lista está vacía, muestra centrado "Aún no tienes reservas".

No uses ViewModel, solo remember y mutableStateOf.

| 3 | **Detalle, Confirmación y Perfil:** detalle con cupos disponibles y botón fijo, confirmación con check verde y perfil con estadísticas en tarjetas. | Flujo completo alineado con el diseño de referencia. |

Ajusta el estilo de las pantallas restantes del proyecto TECSUP Fit con la paleta verde ya definida en el tema:

1. Detalle de clase: TopAppBar blanca con flecha de regreso y título "Detalle de clase". Arriba un recuadro VerdeClaro #E1F4EC de ancho completo, 120dp de alto y esquinas de 12dp con un ícono de pesa grande en verde #0F6E56. Debajo el nombre de la clase en negrita 20sp, "horario · sala · duración" en gris, la descripción y "X de Y cupos disponibles". Si hay selección de horario/cupo, que sea de selección única con chips verdes cuando están seleccionados y #F0F0F0 si no. Botón "Reservar cupo" fijo abajo, verde, ancho completo, 52dp de alto y esquinas de 12dp.
2. Confirmación: contenido centrado verticalmente, círculo VerdeClaro con un check verde, título "¡Cupo reservado!" en negrita, debajo el nombre de la clase y "día, hora · sala" en gris, y un botón tonal "Ver mis reservas" con fondo #F0F0F0.
3. Perfil: título "Mi perfil" en la TopAppBar blanca. Contenido centrado: círculo VerdeClaro de 80dp con las iniciales "DR" en verde y negrita, nombre "Diego Ramos" en negrita y "Plan Premium" en gris. Debajo dos tarjetas lado a lado del mismo ancho (fondo #F0F0F0, esquinas de 12dp): una con "14" en negrita y "Clases", otra con "3" en negrita y "Rachas".

Mantén todos los parámetros de navegación existentes, mantén el bottomBar visible en Inicio, Reservas, Rutinas y Perfil, no uses ViewModel y aplica el innerPadding del Scaffold.