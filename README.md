# Ejemplo de eCommerce

## Descripción
Este proyecto es un ejemplo básico de una aplicación de comercio electrónico (eCommerce) desarrollada en Java y Spring Boot. Permite la gestión de productos (shampoos) con operaciones CRUD, persistencia en archivo y una API REST.

## Detalles del desarrollo
- Lenguaje: Java
- Framework: Spring Boot
- Herramientas: Maven, JUnit

### Estructura del proyecto
- `src/main/java/org/example/`: Código fuente principal
    - `Shampoo.java`: Clase modelo de producto
    - `ShampooRepositorio.java`: Acceso a datos y persistencia
    - `ShampooService.java`: Lógica de negocio
    - `ShampooController.java`: API REST
    - `EcommerceApplication.java`: Clase principal Spring Boot
    - `Main.java`: Interfaz de usuario por consola (CLI)
- `lista de shampoos`: Archivo de persistencia de productos

## Propiedades de un Shampoo
Cada shampoo tiene las siguientes propiedades:
- `id` (int): Identificador único del producto.
- `nombre` (String): Nombre del shampoo.
- `precio` (double): Precio unitario.
- `stock` (int): Cantidad disponible en stock.
- `estado` (int): Estado lógico del producto:
    - `1`: habilitado (activo, visible en listados principales)
    - `0`: deshabilitado (eliminado lógicamente, solo visible en listados de eliminados)

Al listar, solo se muestran los shampoos habilitados (`estado = 1`). Los eliminados pueden consultarse aparte mediante el endpoint correspondiente.

## Cómo iniciar la aplicación

### Opción 1: Interfaz por consola (Java puro)
1. Compila el proyecto:
   ```sh
   mvn clean install
   ```
2. Ejecuta la aplicación por consola:
   ```sh
   java -cp target/eCommerce_Ejercicio-1.0-SNAPSHOT.jar org.example.Main
   ```

### Opción 2: API REST con Spring Boot
1. Instala las dependencias:
   ```sh
   mvn clean install
   ```
2. Inicia la aplicación Spring Boot:
   ```sh
   mvn spring-boot:run
   ```
3. La API estará disponible en: [http://localhost:8080/api/shampoos](http://localhost:8080/api/shampoos)

## Endpoints y ejemplos curl

### 1. Crear shampoo
```sh
curl -X POST http://localhost:8080/api/shampoos -H "Content-Type: application/json" -d '{"nombre":"Head & Shoulders","precio":123.45,"stock":10}'
```

### 2. Listar shampoos habilitados
```sh
curl http://localhost:8080/api/shampoos
```

### 3. Buscar shampoo por ID
```sh
curl http://localhost:8080/api/shampoos/1
```

### 4. Actualizar shampoo
```sh
curl -X PUT http://localhost:8080/api/shampoos/1 -H "Content-Type: application/json" -d '{"nombre":"Nuevo Nombre","precio":200.0,"stock":20}'
```

### 5. Eliminar shampoo (lógico)
```sh
curl -X DELETE http://localhost:8080/api/shampoos/1
```

### 6. Eliminar todos los shampoos (lógico)
```sh
curl -X DELETE http://localhost:8080/api/shampoos
```

### 7. Listar shampoos eliminados
```sh
curl http://localhost:8080/api/shampoos/eliminados
```

## Diagrama UML

Puedes generar el diagrama actualizado de clases con PlantUML:

1. Ve a: https://www.plantuml.com/plantuml
2. Pega el siguiente texto en el editor:

```plantuml
@startuml
class Shampoo {
    - int id
    - String nombre
    - double precio
    - int stock
    - int estado
    + Shampoo()
    + Shampoo(int, String, double, int)
    + Shampoo(int, String, double, int, int)
    + getters/setters
}

class ShampooRepositorio {
    - Map<Integer, Shampoo> shampoos
    - int nextId
    - String archivo
    + guardar(Shampoo): Shampoo
    + buscarPorID(int): Shampoo
    + buscarTodos(): List<Shampoo>
    + buscarEliminados(): List<Shampoo>
    + borrarPorID(int): boolean
    + borrarTodos(): void
}

class ShampooService {
    - ShampooRepositorio repository
    + crearShampoo(String, double, int): Shampoo
    + listarShampoos(): List<Shampoo>
    + listarShampoosEliminados(): List<Shampoo>
    + buscarShampoo(int): Shampoo
    + actualizarShampoo(int, String, double, int): Shampoo
    + eliminarShampoo(int): boolean
}

class ShampooController {
    - ShampooService service
    + crear(Shampoo): Shampoo
    + listar(): List<Shampoo>
    + buscarPorId(int): Shampoo
    + actualizar(int, Shampoo): Shampoo
    + eliminar(int): boolean
    + eliminarTodos(): void
    + listarEliminados(): List<Shampoo>
}

class EcommerceApplication {
    + main(String[]): void
}

class Main {
    + main(String[]): void
}

ShampooService --> ShampooRepositorio
ShampooController --> ShampooService
EcommerceApplication ..> ShampooController
Main ..> ShampooService
Main ..> ShampooRepositorio
@enduml
```

Esto generará un diagrama de clases actualizado con las relaciones y atributos principales del sistema.

## Autor
- Evelina Caparrós
