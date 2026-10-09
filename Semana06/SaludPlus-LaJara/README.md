# PROMPTS.md — Fase 2: Mejora asistida por IA (rama `mejora-ia`)

Registro de los prompts usados con el agente de Gemini en Android Studio para la mejora obligatoria: **calendario dinámico** en la pantalla Fecha y hora, junto con la apariencia de las vistas según el diseño de referencia.

Cada entrada tiene: el **prompt** usado, la **respuesta resumida** y **lo que tuve que corregir**.

| # | Mejora que agrega | Pantallas |
|---|---|---|
| 1 | Imágenes del Splash, Registro e Inicio con saludo dinámico | Splash, Registro, Inicio |
| 2 | Especialidades, Médicos y calendario dinámico | Especialidades, Médicos, Fecha y hora |
| 3 | Fechas en español y cierre del flujo | Confirmar cita, Cita agendada, Mis citas |

---

## Prompt 1 — Splash con imágenes, Registro e Inicio

### Prompt

```
CONTEXTO
Proyecto Android "SaludPlusCitas" (com.saludplus.citas), Kotlin + Jetpack Compose + Navigation Compose, SIN base de datos (colecciones en el object Repositorio). Rama mejora-ia.

REGLAS
- No usar Room/SQLite/Firebase. No cambiar nombres ni parámetros de funciones del Repositorio ni de las pantallas. No modificar Rutas.kt ni AppNavigation.kt. No ejecutes comandos git.
- Usa colores del tema (Color.kt / Theme.kt). Si falta alguno, agrégalo ahí. Paleta aproximada tomada del diseño: azul primario #2563EB, azul marino de títulos #1E2A5E, fondo azul muy claro #F4F7FF, gris de textos secundarios #6B7280, blanco para tarjetas.

PASO PREVIO
Lee SplashScreen.kt, RegistroScreen.kt, HomeScreen.kt, Componentes.kt, Repositorio.kt (registrarUsuario, usuarioActual) y Rutas.kt. Lista qué rutas usan los botones actuales.

1) SPLASH (SplashScreen.kt) — hoy NO usa mis imágenes
Las imágenes están en res/drawable: logo_saludplus.png y medico_splash.png. Verifica que existan; si no, avísame y NO inventes otras. Llámalas con painterResource(R.drawable.logo_saludplus) y painterResource(R.drawable.medico_splash).
Apariencia (como la captura de bienvenida):
- Fondo a pantalla completa con degradado vertical muy suave de blanco a azul claro (#F4F7FF → #EAF1FF).
- Column centrada horizontalmente, con padding horizontal de 24.dp:
  a. Image del logo (cruz azul con corazón blanco), unos 90.dp de alto, contentDescription "Logo SaludPlus".
  b. Texto "Clínica" (azul marino, ~28.sp, semibold) y debajo "SaludPlus" (azul marino, ~40.sp, ExtraBold), centrados.
  c. Tagline "Tu salud, nuestra prioridad" en gris oscuro, ~16.sp.
  d. Image del médico con ilustración (doctor con bata, estetoscopio y plantas) que ocupe todo el espacio central restante: Modifier.weight(1f).fillMaxWidth(), ContentScale.Fit.
  e. Botón "Comenzar": ancho completo, alto 56.dp, azul #2563EB, texto blanco semibold, esquinas redondeadas de 16.dp.
  f. Debajo, TextButton "Ya tengo una cuenta" en azul marino, centrado, con 16.dp de separación.
- "Comenzar" navega a Registro. "Ya tengo una cuenta" navega a Login. Usa las rutas que ya existen.
- Respeta WindowInsets (statusBarsPadding / navigationBarsPadding). Sin barra superior.

2) REGISTRO (RegistroScreen.kt)
Apariencia (como la captura "Crear cuenta"), pantalla con scroll vertical:
- Título "Crear cuenta" centrado, azul marino, ~26.sp, bold; debajo subtítulo "Regístrate para agendar tus citas" en gris.
- 4 campos en Column, separados 16.dp. Cada campo = Row: a la izquierda un cuadro redondeado (52.dp, esquinas 14.dp, fondo azul muy claro) con ícono azul; a la derecha una Column con la etiqueta pequeña gris encima y un OutlinedTextField de esquinas 12.dp:
  • Ícono Person — "Nombre completo" (placeholder "Juan Pérez")
  • Ícono Phone — "Teléfono" (teclado numérico)
  • Ícono Email — "Correo (opcional)" (teclado email)
  • Ícono Lock — "Contraseña" (PasswordVisualTransformation, con ojo para mostrar/ocultar)
- Botón "Registrarme" azul ancho completo, 56.dp, esquinas 16.dp.
- Debajo, texto centrado "Al registrarme acepto nuestros" y en la línea siguiente "Términos y Condiciones" en azul, clickable → ruta Términos.
- Al fondo, centrado: "¿Ya tienes cuenta?" gris + "Iniciar sesión" azul clickable → Login.
Funcionalidad: validar nombre no vacío, teléfono de 9 dígitos, correo opcional pero con formato válido si se escribe, contraseña de al menos 6 caracteres. Mostrar errores bajo cada campo con isError y supportingText. Llamar a Repositorio.registrarUsuario; si el teléfono ya existe mostrar "Este teléfono ya está registrado". Si todo va bien, dejar al usuario en sesión (usuarioActual) y navegar a Inicio.

3) INICIO (HomeScreen.kt)
Apariencia (como la captura de Inicio):
- Fila superior: ícono de menú a la izquierda (sin acción) y campana a la derecha → Notificaciones.
- Título "¡Hola, <nombre>!" en azul marino bold ~28.sp, con "¿Qué deseas hacer hoy?" en gris debajo.
- IMPORTANTE: "Juan" en la captura es solo un ejemplo. El nombre debe salir del usuario que se registró o inició sesión: primer nombre de Repositorio.usuarioActual (nombre completo cortado en el primer espacio). Si no hay usuario, "¡Hola!". Ningún nombre fijo en el código.
- Cuadrícula 2x2 de tarjetas (esquinas 20.dp, altura ~120.dp, ícono grande arriba y texto debajo): "Agendar cita" fondo azul claro con ícono azul, "Mis citas" fondo verde claro con ícono verde, "Mis datos" fondo lila claro con ícono morado, "Resultados" fondo naranja claro con ícono naranja. Destinos: Especialidades, Mis citas, Perfil, Resultados.
- Fila "Especialidades destacadas" (semibold) con "Ver todas" azul a la derecha → Especialidades.
- LazyRow de tarjetas blancas con borde suave y esquinas 16.dp: círculo de color con ícono arriba y nombre centrado abajo (Medicina General, Pediatría, Ginecología, tomadas de Repositorio.especialidadesDestacadas). Al tocar una, navega a Médicos con su especialidadId.
- NavigationBar inferior con 4 ítems: Inicio, Citas, Resultados, Perfil (ícono + etiqueta; el activo en azul y los demás en gris).

Verifica que compila y dime cómo probar: (a) el Splash muestra ambas imágenes, (b) registrar a "María López" muestra "¡Hola, María!".
```

### Respuesta resumida

_(Resume en 3 a 5 líneas qué generó Gemini: archivos modificados y qué implementó.)_

### Qué tuve que corregir

- _(Ejemplo: nombres de `R.drawable` mal escritos o imagen que no se veía.)_
- _(Ejemplo: saludo con nombre fijo o con el nombre completo en lugar del primer nombre.)_
- _(Agrega aquí lo que realmente corregiste.)_

---

## Prompt 2 — Especialidades, Médicos y calendario dinámico

### Prompt

```
CONTEXTO Y REGLAS
Mismo proyecto. No cambiar firmas de Repositorio ni de pantallas, no tocar Rutas.kt/AppNavigation.kt, sin base de datos, sin comandos git. Usa colores del tema (azul #2563EB, marino #1E2A5E, fondo claro #F4F7FF, gris #6B7280). Lee EspecialidadesScreen.kt, MedicosScreen.kt, FechaHoraScreen.kt, Repositorio.kt (buscarEspecialidades, medicosPorEspecialidad, horariosDisponibles, agendarCita) y Cita.kt. Dime en qué formato viaja hoy la "fecha" (String) y no cambies su tipo.

1) ESPECIALIDADES (EspecialidadesScreen.kt)
- Barra superior: flecha atrás a la izquierda y título "Especialidades" centrado, azul marino bold.
- Barra de búsqueda: OutlinedTextField con esquinas muy redondeadas (28.dp), fondo azul muy claro, sin borde marcado, ícono de lupa y placeholder "Buscar especialidad". Filtra en tiempo real con Repositorio.buscarEspecialidades (sin botón).
- LazyColumn con filas separadas por un Divider sutil: a la izquierda un cuadro redondeado (56.dp, esquinas 16.dp) con fondo pastel e ícono (Medicina General: azul/persona, Pediatría: naranja/niño, Ginecología: rosa, Cardiología: rojo/corazón, Dermatología: naranja suave, Traumatología: azul/hueso, Oftalmología: azul/ojo), nombre en bold y descripción gris debajo ("Atención integral", "Niños y adolescentes", "Salud de la mujer", "Corazón y vasos sanguíneos", "Piel, cabello y uñas", "Huesos y articulaciones", "Salud visual"), y a la derecha un chevron gris. Toda la fila es clickable → Médicos con especialidadId.
- Si la búsqueda no encuentra nada: "No se encontraron especialidades".

2) MÉDICOS (MedicosScreen.kt)
- Barra superior: flecha atrás, título "Médicos de <especialidad>" centrado y lupa a la derecha (al tocarla aparece un campo de búsqueda por nombre con Repositorio.buscarMedicos).
- LazyColumn de tarjetas sin sombra fuerte, separadas por línea suave: foto circular de ~80.dp a la izquierda (si no hay recurso de foto, círculo azul claro con iniciales), y a la derecha nombre en bold (ej. "Dra. Ana Torres"), especialidad en gris, fila con estrella dorada + calificación y número de reseñas entre paréntesis ("4.9 (120)"), y abajo a la derecha un chip verde claro redondeado con texto verde oscuro ("Disponible hoy", "Disponible mañana", "Disponible esta semana").
- Orden por calificación descendente. Al tocar una tarjeta → Fecha y hora con medicoId.

3) FECHA Y HORA con CALENDARIO DINÁMICO (FechaHoraScreen.kt) — mejora principal
Crea ui/util/FechasUtil.kt (object FechasUtil, solo java.time y listas propias en español, sin depender del Locale del teléfono):
- semana(offset: Int, hoy: LocalDate = LocalDate.now()): List<LocalDate>: offset 0 = los próximos 5 días hábiles (lunes a viernes) desde hoy incluido (si hoy es sábado o domingo, empieza el lunes); offset k = los 5 días hábiles desde el primer día hábil igual o posterior a hoy + 7*k días. Nunca fechas pasadas. offset negativo se trata como 0.
- puedeRetroceder(offset) = offset > 0.
- nombreMesAnio(fecha): "Octubre 2026". abreviaturaDia(fecha): "Lun", "Mar", "Mié", "Jue", "Vie".
- aTexto(fecha)/desdeTexto(texto) en ISO yyyy-MM-dd. Si mis datos de ejemplo usan otro formato, actualízalos a ISO sin cambiar firmas.

Apariencia (como la captura "Seleccionar fecha y hora"):
- Barra superior con flecha y título "Seleccionar fecha y hora" centrado.
- Tarjeta del médico: fondo azul muy claro, esquinas 20.dp, foto circular ~90.dp, nombre en bold y especialidad debajo.
- Fila del mes: "<" a la izquierda, "Octubre 2026" centrado en bold, ">" a la derecha. "<" se ve gris y sin click en la semana actual.
- Fila de 5 chips de día (Row, weight(1f) cada uno, alto ~80.dp, esquinas 16.dp): arriba la abreviatura, abajo el número en bold. Seleccionado: fondo azul con texto blanco; no seleccionado: fondo gris azulado claro.
- LazyVerticalGrid de 3 columnas con botones de hora (alto ~56.dp, esquinas 14.dp, separación 12.dp); seleccionado azul con texto blanco, el resto gris claro con texto oscuro.
- Botón "Continuar" azul ancho completo abajo, deshabilitado (más claro) hasta elegir día y hora.

Funcionalidad:
- Estados con rememberSaveable: semanaOffset = 0, diaSeleccionado (primer día de la semana), horaSeleccionada = null.
- ">" suma una semana; "<" resta una solo si puedeRetroceder. Al cambiar de semana: diaSeleccionado = primer día de la nueva semana y horaSeleccionada = null. El mes mostrado = nombreMesAnio del primer día de la semana.
- Al tocar un día: se actualiza diaSeleccionado y horaSeleccionada = null.
- Los horarios salen de Repositorio.horariosDisponibles(medicoId, aTexto(dia)) dentro de remember(diaSeleccionado, medicoId): se recalculan solos. NO modifiques horariosDisponibles; debe seguir ocultando horarios ya reservados para ese médico y fecha.
- Sin horarios: "No hay horarios disponibles para este día".
- "Continuar" navega con los mismos parámetros de antes (medicoId, fecha, hora), enviando la fecha con aTexto.

Verifica que compila y dime cómo probar: "<" bloqueada en la primera semana, el mes cambia al avanzar, y la hora se desmarca al cambiar de día.
```

### Respuesta resumida

_(Resume en 3 a 5 líneas qué generó Gemini: `FechasUtil.kt` creado, cambios en las tres pantallas.)_

### Qué tuve que corregir

- _(Ejemplo: `LocalDate` con `rememberSaveable` que requería un `Saver` o guardar el texto.)_
- _(Ejemplo: flecha "<" que no se deshabilitaba en la semana actual.)_
- _(Ejemplo: formato de fecha incompatible con `horariosDisponibles` o con las citas precargadas.)_
- _(Agrega aquí lo que realmente corregiste.)_

---

## Prompt 3 — Fechas en español, confirmación y Mis citas

### Prompt

```
CONTEXTO Y REGLAS
Mismo proyecto. No cambiar firmas, no tocar Rutas.kt/AppNavigation.kt, sin base de datos, sin comandos git. Ya existe FechasUtil (semana, nombreMesAnio, abreviaturaDia, aTexto, desdeTexto) y el calendario dinámico funciona. Lee ConfirmarCitaScreen.kt, CitaExitosaScreen.kt, MisCitasScreen.kt, Repositorio.kt (agendarCita, citasDelUsuario, obtenerMedico) y las rutas ya definidas.

1) FechasUtil: agrega fechaLarga(LocalDate): String → "Martes 16 de setiembre 2026" (día con mayúscula inicial, mes en minúscula; "setiembre" en una constante para poder cambiarlo a "septiembre").

2) CONFIRMAR CITA (ConfirmarCitaScreen.kt) — captura "Confirmar cita"
- Barra superior: flecha y título "Confirmar cita" centrado.
- Tarjeta del médico (fondo azul muy claro, esquinas 20.dp): foto circular ~90.dp a la izquierda; a la derecha nombre en bold, especialidad y "CMP: <número>".
- 4 filas, cada una con un cuadro redondeado azul claro con ícono azul a la izquierda y a la derecha etiqueta gris pequeña + valor en bold, separadas por línea suave: Fecha (calendario) con fechaLarga(desdeTexto(fecha)) con texto de respaldo si es null; Hora (reloj) con el rango "09:30 a 10:00" (hora + 30 minutos); Tipo de atención (ícono de consultorio) "Consulta presencial"; Dirección (pin) "Av. Los Olivos 123, Lima".
- "Motivo de consulta (opcional)" en semibold y debajo un OutlinedTextField multilínea (mínimo 3 líneas, esquinas 12.dp).
- Botón "Agendar cita" azul ancho, 56.dp. Llama a Repositorio.agendarCita: si devuelve false muestra "Ese horario ya fue reservado" y no navega; si devuelve true navega a Cita agendada con el popUpTo que ya está definido (el flujo de agendamiento desaparece del historial).

3) CITA AGENDADA (CitaExitosaScreen.kt) — vista faltante, mismo estilo
- Centrado: círculo verde claro de 120.dp con check verde grande; título "¡Cita agendada!" en marino bold y subtítulo gris "Te esperamos en la clínica".
- Tarjeta resumen azul muy claro con médico, especialidad, fechaLarga y hora.
- Botón azul "Ver mis citas" (→ Mis citas) y botón con contorno azul "Ir al inicio" (→ Inicio). Atrás no vuelve al flujo.

4) MIS CITAS (MisCitasScreen.kt) — vista faltante, mismo estilo
- Barra superior con título "Mis citas" y la NavigationBar con "Citas" activo.
- LazyColumn de tarjetas blancas con borde suave, esquinas 16.dp: foto o iniciales del médico, nombre en bold, especialidad en gris, fecha con fechaLarga y hora, y un chip de estado verde claro "Confirmada". Ordenadas por fecha y hora REALES (LocalDate + hora), de la más próxima a la más lejana, no comparando textos.
- Lista vacía: ícono de calendario gris, "Aún no tienes citas agendadas" y botón azul "Agendar cita".

Prueba de punta a punta y repórtala: registrar a cualquier usuario → saludo con su nombre → agendar → fecha en español en Confirmar, Cita agendada y Mis citas → volver a Fecha y hora con el mismo médico y día y comprobar que ese horario ya no aparece; con otro médico o día sí sigue disponible.
```

