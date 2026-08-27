#Laboratorio 02 - CARRITO DE COMPRAS EN KOTLIN

**Nombre**: Gael La Jara

## Descripción
Este programa simulo un carrito de compras. Permite registrar productos, calcular el subtotal, el IGV, el total a pagar, identificar el producto más caro y aplicar un descuento según el monto de la compra.

## Pregunta de la parte 2
1. ¿por qué nombre y precio son val pero cantidad es var? ¿Qué
pasaría si intentas cambiar el precio después de crear el producto?

- El uso de val para nombre y precio es porque normalmente no deberían cambiar después de crear al producto. 
  En cambio, cantidad es un valor mutable que puede cambiar con el tiempo (aumenta o disminuye) por eso es var.
- Kotlin mostraría un error, ya que no se puede cambiar un valor inmutable.

<img width="357" height="533" alt="carrito_kotlin" src="https://github.com/user-attachments/assets/eb9633f5-99ff-4cf0-9523-fb0c2b14fa1a" />
