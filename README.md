# Ejemplo de eCommerce

## Descripción
Este proyecto es un ejemplo básico de una aplicación de comercio electrónico (eCommerce) desarrollada en Java y Spring Boot. Permite la gestión de productos (shampoos) con operaciones CRUD, persistencia en base de datos PostgreSQL y una API REST.

## Autor
- Evelina Caparrós

## Estructura del proyecto
- `src/main/java/org/example/`: Código fuente principal
    - `Shampoo.java`: Clase modelo de producto
    - `ShampooRepositorio.java`: Acceso a datos y persistencia con JPA/PostgreSQL
    - `ShampooService.java`: Lógica de negocio
    - `ShampooController.java`: API REST
    - `EcommerceApplication.java`: Clase principal Spring Boot
- `src/test/java/org/example/`: Tests unitarios y plan de pruebas
    - `ShampooServiceTest.java`: Tests unitarios con JUnit
    - `PLAN_PRUEBAS.md`: Plan de pruebas detallado
- `esquema_tabla_shampoos_postgres.txt`: Esquema SQL y tips para la base de datos PostgreSQL

## Propiedades de un Shampoo
Cada shampoo tiene las siguientes propiedades:
- `id` (int): Identificador único del producto.
- `nombre` (String): Nombre del shampoo (no puede ser vacío ni nulo).
- `precio` (double): Precio unitario (no puede ser negativo).
- `stock` (int): Cantidad disponible en stock (no puede ser negativo).
- `estado` (int): Estado lógico del producto:
    - `1`: habilitado (activo, visible en listados principales)
    - `0`: deshabilitado (eliminado lógicamente, solo visible en listados de eliminados)
> **Nota:** Si intentas modificar un producto en estado 0, la operación será ignorada arrojando una advertencia y no se realizará ningún cambio. Primero debes habilitarlo (cambiar a estado 1).

## Endpoints de la API y ejemplos de uso

### 1. Crear shampoo
- **Método:** POST
- **URL:** `/api/shampoos`
- **Curl:**
  ```bash
  curl -X POST http://localhost:8080/api/shampoos \
    -H "Content-Type: application/json" \
    -d '{"nombre": "Head & Shoulders", "precio": 123.45, "stock": 10}'
  ```
- **Ejemplo de respuesta exitosa:**
  ```json
  {
    "id": 1,
    "nombre": "Head & Shoulders",
    "precio": 123.45,
    "stock": 10,
    "estado": 1
  }
  ```
- **Errores posibles:**
  - 400 Bad Request: Datos inválidos (nombre vacío, precio/stock negativo)
  - 500 Internal Server Error: Error inesperado en el servidor
  - 405 Method Not Allowed: Método HTTP incorrecto

### 2. Listar shampoos habilitados y deshabilitados
- **Método:** GET
- **URL:** `/api/shampoos`
- **Curl:**
  ```bash
  curl http://localhost:8080/api/shampoos
  ```
> **Nota:** Pendiente sumar discriminacion entre habilitados y deshabilitados.

- **Ejemplo de respuesta exitosa:**
  ```json
  [
    {"id": 1, "nombre": "Head & Shoulders", "precio": 123.45, "stock": 10, "estado": 1},
    {"id": 2, "nombre": "Pantene", "precio": 99.99, "stock": 5, "estado": 1}
  ]
  ```
- **Errores posibles:**
  - 500 Internal Server Error: Error inesperado en el servidor
  - 405 Method Not Allowed: Método HTTP incorrecto

### 3. Buscar shampoo por ID
- **Método:** GET
- **URL:** `/api/shampoos/{id}`
- **Curl:**
  ```bash
  curl http://localhost:8080/api/shampoos/1
  ```
- **Ejemplo de respuesta exitosa:**
  ```json
  {"id": 1, "nombre": "Head & Shoulders", "precio": 123.45, "stock": 10, "estado": 1}
  ```
- **Errores posibles:**
  - 404 Not Found: No existe el shampoo con ese ID
  - 500 Internal Server Error: Error inesperado en el servidor
  - 405 Method Not Allowed: Método HTTP incorrecto

### 4. Actualizar shampoo
- **Método:** PUT
- **URL:** `/api/shampoos/{id}`
- **Curl:**
  ```bash
  curl -X PUT http://localhost:8080/api/shampoos/1 \
    -H "Content-Type: application/json" \
    -d '{"nombre": "Nuevo Nombre", "precio": 150.0, "stock": 8}'
  ```
- **Ejemplo de respuesta exitosa:**
  ```json
  {"id": 1, "nombre": "Nuevo Nombre", "precio": 150.0, "stock": 8, "estado": 1}
  ```
- **Errores posibles:**
  - 404 Not Found: No existe el shampoo con ese ID
  - 409 Conflict: El producto fue eliminado. Para actualizarlo, primero debe habilitarse nuevamente (ponerlo a la venta).
    - **Ejemplo de respuesta:**
      ```json
      {
        "Status": 0,
        "Mensaje": "El producto fue eliminado. Para actualizarlo, primero debe habilitarse nuevamente (ponerlo a la venta)."
      }
      ```
  - 400 Bad Request: Datos inválidos
  - 500 Internal Server Error: Error inesperado en el servidor
  - 405 Method Not Allowed: Método HTTP incorrecto

### 5. Eliminar shampoo (eliminación lógica)
- **Método:** DELETE
- **URL:** `/api/shampoos/{id}`
- **Curl:**
  ```bash
  curl -X DELETE http://localhost:8080/api/shampoos/1
  ```
- **Ejemplo de respuesta exitosa:**
  ```json
  {"resultado": "El producto ya fue previamente eliminado."}
  ```
- **Errores posibles:**
  - 404 Not Found: No existe el shampoo con ese ID
  - 500 Internal Server Error: Error inesperado en el servidor
  - 405 Method Not Allowed: Método HTTP incorrecto

> **Validaciones y manejo de errores:** El backend valida que el nombre no sea vacío o nulo, el precio no sea negativo y el stock no sea negativo. Si el nombre es vacío o nulo, devuelve 400 Bad Request con un mensaje claro.

## Tests unitarios y plan de pruebas
- Los tests unitarios están implementados con JUnit en `src/test/java/org/example/ShampooServiceTest.java`.
- Se cubren casos de creación, validaciones, búsqueda, listado, eliminación y persistencia.
- El plan de pruebas detallado está en `src/test/java/org/example/PLAN_PRUEBAS.md`.
- Evidencias en el archivo correspondiente.

## Ejecución de tests
Para ejecutar los tests unitarios:

```
mvn test
```

Esto ejecutará todos los tests definidos en `src/test/java/org/example/ShampooRepositorioTest.java` y validará la robustez del repositorio.

## Supuestos y limitaciones del sistema

- El stock se maneja solo como cantidad de unidades (no por cajas ni otros formatos).
- El precio se maneja en una única moneda (por defecto, pesos argentinos o la moneda local del usuario; no hay conversión ni símbolo).
- No hay control de usuarios ni autenticación: cualquier usuario puede operar sobre los datos.
- No se valida la unicidad del nombre de shampoo en el backend (se recomienda hacerlo en el frontend).
- El campo `estado` solo permite dos valores: `1` (habilitado) y `0` (eliminado lógicamente).
- No hay control de fechas de alta, baja ni modificación.
- No se maneja historial de cambios ni auditoría.
- No se contemplan impuestos, descuentos ni promociones.
- No hay validación de formato para caracteres especiales en el nombre.

## Consideraciones para el Frontend
- Validar que no se creen shampoos con nombres repetidos.
- Mostrar mensajes claros ante datos inválidos o duplicados.
- Sincronizar la lista con el backend tras crear, editar o eliminar.
- Controlar que los campos obligatorios estén completos y sean válidos.
- Manejar errores del backend mostrando mensajes amigables.
- Evitar envíos múltiples deshabilitando el botón de guardar durante la petición.
- **Importante:** Los endpoints que reciben un parámetro `{id}` en la URL (por ejemplo, `/api/shampoos/{id}`) requieren que el valor sea un número entero positivo. Si se envía un valor no numérico o con caracteres especiales (por ejemplo, `/api/shampoos/%` o `/api/shampoos/$$%`), el servidor web (Tomcat) puede rechazar la petición antes de que llegue a la aplicación, devolviendo una página HTML de error 400 en vez de un JSON. 
  - Valida en el frontend que el ID sea un número antes de enviar la petición.
  - No uses caracteres especiales en la URL.
  - Si recibes una respuesta HTML de error 400, revisa que el parámetro sea un número válido.
  - Este comportamiento es estándar en servidores Java y no puede ser interceptado por el backend. (Juro lo intenté)

## Diagrama UML
![Diagrama UML](UML.png)

## Ideas y mejoras a futuro

- Soporte para imágenes de shampoo (guardar, actualizar y devolver en las APIs).
- Autenticación y control de usuarios. Controlado tambien por el FE.
- Paginación y filtros avanzados en los listados. Puede ordenar por habilitado y deshabilitado y dentro ordenado por ID. Si se suma categoria, podria sumarse eso tambien.
- Validación de unicidad de nombre de shampoo en backend.
- Soporte para múltiples monedas y conversión de precios.
- Gestión de categorías y marcas de shampoo.
- API para carga masiva de productos.
- Documentación Swagger para la API REST.
- Mejor manejo de errores y mensajes personalizados para todos los casos.
- Frontend web.
- Y mucho màs! 😄
