# Lab02CarritoKotlinPOOIA

## Datos del proyecto

**Curso:** Programación en Móviles  
**Docente:** Leon Suiyon, Juan Jose
**Estudiante:** La Jara Barboza, Gael Matteo
**Lenguaje:** Kotlin  
**Entorno:** Android Studio  
**Nombre del proyecto:** Lab02CarritoKotlinPOOIA  
**Tipo:** Aplicación de consola  

# Descripción
Este proyecto corresponde a la segunda rama del laboratorio de carrito de compras en Kotlin.
A diferencia de la primera versión, esta versión fue desarrollada con apoyo de Inteligencia Artificial y está orientada a Programación Orientada a Objetos (POO).
El proyecto funciona mediante la terminal y maneja la información en memoria, sin utilizar una base de datos.

Durante el desarrollo se aplican los cuatro pilares de POO:

- Abstracción
- Herencia
- Encapsulamiento
- Polimorfismo

El desarrollo se realizó progresivamente mediante 6 prompts, realizando un commit después de cada fase.

---

# Estructura de un prompt

1. **Contexto:** explicación de la situación y del proyecto.
2. **Objetivo o tarea:** indica qué se desea desarrollar en la fase.
3. **Libertad de diseño:** la IA puede proponer la arquitectura, clases, archivos y métodos que considere adecuados.
4. **Restricciones:** condiciones que debe respetar la solución.
5. **Resultado esperado:** indica qué debe entregar la IA y cómo debe explicarlo.

---

# Desarrollo mediante IA

## Prompt 1 - Infraestructura Inicial

Estoy desarrollando la rama 2 de un proyecto de carrito de compras en Kotlin orientado a objetos.

Nombre del proyecto:
Lab02CarritoKotlinPOOIA

Esta es la Fase 1 de 6.

Quiero construir únicamente la infraestructura inicial del proyecto.

Tienes libertad para decidir la arquitectura, paquetes, clases y archivos que consideres adecuados. No quiero imponerte una estructura específica; quiero que propongas una solución sencilla, organizada y coherente para un proyecto de estudiante.

Mi paquete principal es:
com.lajara.lab02carritokotlinpooia

IMPORTANTE SOBRE LA ESTRUCTURA:
- Estoy trabajando en Android Studio.
- El funcionamiento del carrito será únicamente mediante terminal/consola.
- No utilizaré interfaz gráfica, base de datos ni API.
- MainActivity.kt no debe utilizarse para la lógica del carrito.
- El punto de entrada del programa debe ser un main() ejecutable como Kotlin MainKt.
- Cada vez que crees un archivo o paquete, indica exactamente dónde debo crearlo.
- Antes del código muestra la estructura de carpetas y archivos.

En esta fase NO implementes todavía:
- herencia
- polimorfismo
- encapsulamiento avanzado
- descuentos
- IGV
- cálculos finales
- menú completo del carrito

Solo quiero una base funcional y organizada sobre la que podamos construir las siguientes fases.

Después de mostrar la estructura, proporciona únicamente el código necesario para esta fase.

Finalmente explica cómo ejecutarlo como MainKt desde Android Studio sin utilizar un emulador y qué debería representar el primer commit.

## Prompt 2 - Abstracción y Herencia

Continúa trabajando sobre el proyecto Lab02CarritoKotlinPOOIA y sobre la estructura creada en la Fase 1.

Esta es la Fase 2 de 6.

Ahora quiero incorporar los conceptos de ABSTRACCIÓN y HERENCIA.

Tienes libertad para decidir cómo modelar el dominio del carrito utilizando estos conceptos. No quiero imponerte una estructura concreta; analiza la arquitectura existente y propone la solución que consideres más adecuada.

Mi paquete principal es:
com.lajara.lab02carritokotlinpooia

IMPORTANTE:
- Mantener Kotlin.
- Mantener ejecución mediante terminal/consola.
- No utilizar base de datos, API ni interfaz gráfica.
- Mantener MainActivity.kt sin utilizar para la lógica del carrito.
- Trabajar sobre la estructura existente y evitar reemplazar archivos innecesariamente.
- No adelantar las funcionalidades de las siguientes fases.
- Cada archivo nuevo o modificado debe tener su ruta y paquete claramente indicados.
- Antes del código muestra la estructura resultante.

La abstracción y la herencia deben utilizarse realmente dentro del modelo del carrito y no solamente aparecer como código decorativo.

Después explica brevemente las decisiones de diseño, muestra la estructura y proporciona el código actualizado.

Finalmente indica cómo probar esta fase y qué debería representar el segundo commit.

## Prompt 3 - Encapsulamiento

Continúa trabajando sobre el proyecto Lab02CarritoKotlinPOOIA.

Esta es la Fase 3 de 6.

Ahora quiero incorporar ENCAPSULAMIENTO de forma correcta al proyecto.

Analiza la arquitectura creada en las fases anteriores y decide libremente cómo proteger y controlar el acceso a los datos y responsabilidades internas de las clases.

Mi paquete principal es:
com.lajara.lab02carritokotlinpooia

IMPORTANTE:
- Mantener Kotlin y ejecución mediante terminal/consola.
- Sin base de datos, API ni interfaz gráfica.
- Mantener MainActivity.kt fuera de la lógica del carrito.
- No romper las fases anteriores.
- No adelantar todavía el polimorfismo ni las funcionalidades finales del carrito.
- Puedes modificar o crear archivos cuando sea necesario, pero evita cambios innecesarios.
- Para cada archivo, indica exactamente la ruta donde debe estar.
- Antes del código muestra la estructura resultante.

Quiero que el encapsulamiento tenga una finalidad real dentro del proyecto, evitando dejar atributos importantes expuestos sin necesidad.

Primero explica brevemente cómo estás aplicando encapsulamiento.
Después muestra la estructura y el código actualizado.
Finalmente indica cómo probar esta fase y qué debería representar el tercer commit.

## Prompt 4 - Polimorfismo

Continúa trabajando sobre el proyecto Lab02CarritoKotlinPOOIA.

Esta es la Fase 4 de 6.

Ahora quiero incorporar POLIMORFISMO al proyecto.

Tienes libertad para decidir cómo aplicar este concepto aprovechando la abstracción y herencia creadas anteriormente. Quiero que el polimorfismo sea utilizado realmente en el funcionamiento del sistema.

Mi paquete principal es:
com.lajara.lab02carritokotlinpooia

IMPORTANTE:
- Mantener Kotlin.
- Mantener ejecución desde terminal/consola.
- Sin base de datos, API ni interfaz gráfica.
- Mantener MainActivity.kt fuera de la lógica.
- Conservar y aprovechar las fases anteriores.
- No adelantar todavía las funcionalidades de negocio de la Fase 5 ni la integración final de la Fase 6.
- Indicar exactamente qué archivos se crean o modifican y su ubicación.
- Antes del código mostrar la estructura actualizada.

Primero explica dónde se aplica el polimorfismo y por qué la solución propuesta es adecuada.

Después muestra la estructura y el código de esta fase.

Finalmente indica cómo probar el polimorfismo y qué debería representar el cuarto commit.

## Prompt 5 - Funcionalidades del carrito

Continúa trabajando sobre el proyecto Lab02CarritoKotlinPOOIA.

Esta es la Fase 5 de 6.

Ahora quiero implementar las principales funcionalidades del carrito de compras utilizando la arquitectura POO creada en las fases anteriores.

Las funcionalidades que debe incorporar esta fase son:
- agregar y gestionar productos en el carrito
- calcular subtotal
- calcular IGV del 18%
- calcular total
- aplicar descuento según el monto
- identificar el producto más caro

Reglas de descuento:
- más de S/ 3000 → 5%
- más de S/ 5000 → 10%
- en cualquier otro caso → sin descuento

Tienes libertad para decidir en qué clases, archivos y métodos colocar cada responsabilidad. Quiero una separación adecuada de responsabilidades y que se aprovechen los cuatro pilares de POO ya desarrollados.

Mi paquete principal es:
com.lajara.lab02carritokotlinpooia

IMPORTANTE:
- Kotlin.
- Ejecución desde terminal/consola.
- Sin base de datos ni API.
- Sin interfaz gráfica.
- Información manejada en memoria.
- Mantener MainActivity.kt fuera de la lógica del carrito.
- No implementar todavía el menú final de interacción.
- No modificar innecesariamente las fases anteriores.
- Indicar la ruta exacta de cada archivo creado o modificado.
- Antes del código mostrar la estructura resultante.

Primero explica cómo distribuirás las responsabilidades.
Después muestra la estructura y el código actualizado.
Finalmente indica cómo probar cada funcionalidad y qué debería representar el quinto commit.

## Prompt 6 - Integración Final

Continúa trabajando sobre el proyecto Lab02CarritoKotlinPOOIA.

Esta es la Fase 6 y última.

Quiero integrar todas las fases anteriores y dejar el proyecto completamente funcional desde la terminal.

Tienes libertad para realizar los ajustes necesarios en la arquitectura, pero evita cambios innecesarios y conserva la lógica que ya funciona.

El sistema final debe permitir desde la consola:
- interactuar mediante un menú sencillo
- gestionar los productos del carrito
- mostrar el detalle del carrito
- mostrar subtotal
- mostrar IGV del 18%
- mostrar descuento
- mostrar total final
- mostrar el producto más caro

Debe conservar correctamente:
- abstracción
- herencia
- encapsulamiento
- polimorfismo

Condiciones:
- Kotlin.
- Ejecución mediante terminal/consola.
- Sin base de datos.
- Sin API.
- Sin interfaz gráfica.
- Información manejada en memoria.
- MainActivity.kt no debe contener la lógica del carrito.
- El proyecto debe poder ejecutarse mediante MainKt desde Android Studio sin utilizar un emulador.
- Mantener una estructura sencilla y apropiada para un estudiante.

Antes del código:
1. Revisa la arquitectura completa.
2. Muestra la estructura final de carpetas y archivos.
3. Explica brevemente dónde se encuentra cada uno de los cuatro pilares de POO.

Después proporciona el código final completo indicando la ubicación de cada archivo.

Finalmente proporciona una lista de pruebas que debo realizar antes del sexto y último commit.

---

# Evidencias

<img width="570" height="233" alt="image" src="https://github.com/user-attachments/assets/a8f7adac-da26-4a4e-a1f1-ac1ce0ce42ab" />

<img width="375" height="249" alt="image" src="https://github.com/user-attachments/assets/05efe781-ee1a-4f1f-b76d-8ead006fdc99" />

<img width="397" height="250" alt="image" src="https://github.com/user-attachments/assets/4e93403d-b542-4850-b365-631ac3901b38" />

<img width="709" height="146" alt="image" src="https://github.com/user-attachments/assets/17475658-e10a-4d81-8b29-5be46b077480" />

<img width="294" height="142" alt="image" src="https://github.com/user-attachments/assets/f6c3ab65-6e26-432c-ad3e-c876899b5ecd" />

<img width="342" height="78" alt="image" src="https://github.com/user-attachments/assets/664b18b5-238a-4435-b647-8e68863cc8f3" />

<img width="507" height="280" alt="image" src="https://github.com/user-attachments/assets/08c1a0fb-6028-46ef-9cc3-1fc653a53f2a" />
