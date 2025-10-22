# Ejemplo de eCommerce

## Descripción
Este proyecto es un ejemplo básico de una aplicación de comercio electrónico (eCommerce) desarrollada en Java. Incluye funcionalidades esenciales como la gestión de productos y pedidos.

## Detalles del desarrollo
- Lenguaje: Java
- Herramientas: Maven, JUnit

### Estructura del proyecto
- `src/main/java/org/example/`: Código fuente principal
- `Shampoo.java`: Clase modelo de producto
- `ShampooRepositorio.java`: Lógica de acceso a datos
- `ShampooApp.java`: Interfaz de usuario por consola

## Características
- Alta, baja y modificación de productos (shampoos)
- Listado de productos
- Persistencia en archivo de texto
- Interfaz de usuario por consola (CLI)

### Proximamente:
- BD
- FE sencillo para persistencia de datos

## Diagrama:

![Diagrama UML](uml.png)

@startuml
class Shampoo {
- int id
- String nombre
- double precio
- int stock
  }

class ShampooRepositorio {
- List<Shampoo> shampoos
+ void guardar(Shampoo s)
+ List<Shampoo> buscarTodos()
+ void borrarPorID(int id)
  }

class ShampooApp {
+ main(String[] args)
  }

ShampooRepositorio "1" *-- "*" Shampoo
ShampooApp ..> ShampooRepositorio
@enduml

## Autor
- Evelina Caparrós
