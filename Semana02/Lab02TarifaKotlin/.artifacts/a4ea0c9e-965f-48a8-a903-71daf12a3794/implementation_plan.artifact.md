# Plan de Corrección del Proyecto Lab02TarifaKotlin

El proyecto presenta errores de sincronización de Gradle porque le faltan archivos fundamentales de configuración y tiene una discrepancia entre el tipo de proyecto (Android) y el código implementado (Consola Kotlin).

## Cambios Propuestos

### Configuración de Gradle

#### [NEW] [libs.versions.toml](file:///E:/AndroidS_Labs/Moviles_Android_D/Semana02/Lab02TarifaKotlin/gradle/libs.versions.toml)
Crear el catálogo de versiones para resolver las referencias `libs` en los archivos de construcción.

#### [NEW] [build.gradle.kts](file:///E:/AndroidS_Labs/Moviles_Android_D/Semana02/Lab02TarifaKotlin/app/build.gradle.kts)
Crear el archivo de construcción para el módulo `:app` con la configuración básica de Android y Kotlin.

### Estructura de Android

#### [NEW] [AndroidManifest.xml](file:///E:/AndroidS_Labs/Moviles_Android_D/Semana02/Lab02TarifaKotlin/app/src/main/AndroidManifest.xml)
Crear un manifiesto mínimo para que el módulo sea reconocido como un módulo Android válido.

## Plan de Verificación

### Pruebas Automatizadas
- Ejecutar **Gradle Sync** para asegurar que todos los errores de configuración desaparezcan.
- Compilar el proyecto.

### Verificación Manual
- Abrir `tarifa.kt` y verificar que el botón de "Run" (flecha verde) aparezca junto a la función `main()`.
- Ejecutar la función `main()` para confirmar que la lógica de consola funciona correctamente.
