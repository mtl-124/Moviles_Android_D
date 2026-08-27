CARRITO DE COMPRAS

1. ¿por qué nombre y precio son val pero cantidad es var? ¿Qué
pasaría si intentas cambiar el precio después de crear el producto?

- El uso de val para nombre y precio es porque normalmente no deberían cambiar después de crear al producto. 
  En cambio, cantidad es un valor mutable que puede cambiar con el tiempo (aumenta o disminuye) por eso es var.
- Kotlin mostraría un error, ya que no se puede cambiar un valor inmutable.