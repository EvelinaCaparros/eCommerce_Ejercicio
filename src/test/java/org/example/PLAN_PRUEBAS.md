# Plan de Pruebas para ShampooRepositorio

## Objetivo
Asegurar la robustez, integridad de datos y correcto manejo de errores del repositorio de shampoos.

## Casos de Prueba

### 1. Creación de shampoo
- Crear shampoo válido (nombre, precio y stock correctos).
- Intentar crear shampoo con nombre vacío o nulo (debe lanzar excepción y devolver 400 Bad Request).
- Intentar crear shampoo con precio negativo (debe lanzar excepción).
- Intentar crear shampoo con stock negativo (debe lanzar excepción).

### 2. Búsqueda
- Buscar shampoo por ID existente.
- Buscar shampoo por ID inexistente (debe retornar null).

### 3. Listado
- Listar todos los shampoos activos.
- Listar shampoos eliminados.

### 4. Eliminación
- Eliminar shampoo por ID existente (debe marcar como eliminado, no borrar físicamente).
- Eliminar shampoo por ID inexistente (debe retornar NO_EXISTE).
- Eliminar shampoo ya eliminado (debe retornar YA_ELIMINADO).
- Eliminar todos los shampoos (borrarTodos).

### 5. Persistencia
- Verificar que los datos persisten correctamente en el archivo tras guardar.
- Simular error de escritura y verificar que se lanza excepción y no se pierde información.
- Simular error de lectura y verificar que se lanza excepción.

### 6. Concurrencia y atomicidad
- Simular acceso concurrente a guardar y borrar (debe mantener integridad de datos).

---

## Datos de pruebas posibles para insertar

1,Head & Shoulders,200.0,20,1
2,Sedal,50.0,0,1
3,Pantene,120.5,100,0
4,Clear Men,80.0,5,1
5,Herbal Essences,300.0,999999,1
6,Shampoo Económico,10.0,1,1
7,Shampoo Premium,999.99,2,1
8,Shampoo Eliminado,25.0,50,0
9,Repetido,44.0,987654321,1
10,Repetido,44.0,987654321,1
11,Sin Stock,15.0,0,1
12,Ultra Barato,1.0,1000,1
13,Ultra Caro,10000.0,3,1
14,Nombre Largo de Prueba para Shampoo,55.5,10,1
15,ShampooConCaracteresEspeciales@#%!,60.0,7,1


> **Query:** INSERT INTO shampoo (id, nombre, precio, stock, estado) VALUES
(1, 'Head & Shoulders', 200.0, 20, 1),
(2, 'Sedal', 50.0, 0, 1),
(3, 'Pantene', 120.5, 100, 0),
(4, 'Clear Men', 80.0, 5, 1),
(5, 'Herbal Essences', 300.0, 999999, 1),
(6, 'Shampoo Económico', 10.0, 1, 1),
(7, 'Shampoo Premium', 999.99, 2, 1),
(8, 'Shampoo Eliminado', 25.0, 50, 0),
(9, 'Repetido', 44.0, 987654321, 1),
(10, 'Repetido', 44.0, 987654321, 1),
(11, 'Sin Stock', 15.0, 0, 1),
(12, 'Ultra Barato', 1.0, 1000, 1),
(13, 'Ultra Caro', 10000.0, 3, 1),
(14, 'Nombre Largo de Prueba para Shampoo', 55.5, 10, 1),
(15, 'ShampooConCaracteresEspeciales@#%!', 60.0, 7, 1);


