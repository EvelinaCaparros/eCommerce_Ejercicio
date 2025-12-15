package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shampoos")
public class ShampooController {
    private final ShampooService service;

    @Autowired
    public ShampooController(ShampooService service) {
        this.service = service;
    }

    // 1. Crear shampoo
    @PostMapping
    public ResponseEntity<Shampoo> crear(@RequestBody Shampoo shampoo) {
        Shampoo creado = service.crearShampoo(shampoo.getNombre(), shampoo.getPrecio(), shampoo.getStock());
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // 2. Listar shampoos habilitados
    @GetMapping
    public List<Shampoo> listar() {
        return service.listarShampoos();
    }

    // 3. Buscar shampoo por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable("id") int id) {
        Shampoo shampoo = service.buscarShampoo(id);
        if (shampoo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final int Status = 0;
                public final String Mensaje = "El producto no existe";
            });
        }
        return ResponseEntity.ok(shampoo);
    }

    // 4. Actualizar shampoo (solo campos permitidos)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable("id") int id, @RequestBody Shampoo shampoo) {
        Shampoo existente = service.buscarShampoo(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final int Status = 0;
                public final String Mensaje = "El producto no existe";
            });
        }
        if (existente.getEstado() == 0) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Object() {
                public final int Status = 0;
                public final String Mensaje = "El producto fue eliminado. Para actualizarlo, primero debe habilitarse nuevamente (ponerlo a la venta).";
            });
        }
        Shampoo actualizado = service.actualizarShampoo(id, shampoo.getNombre(), shampoo.getPrecio(), shampoo.getStock());
        return ResponseEntity.ok(actualizado);
    }

    // 5. Eliminar shampoo (lógico)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") int id) {
        ResultadoEliminacion resultado = service.eliminarShampoo(id);
        return switch (resultado) {
            case ELIMINADO -> {
                Shampoo shampoo = service.buscarShampoo(id);
                yield ResponseEntity.ok(new Object() {
                    public final int Status = 1;
                    public final String Mensaje = "Se pudo eliminar correctamente";
                    public final Integer ID = shampoo != null ? shampoo.getId() : id;
                    public final String Nombre = shampoo != null ? shampoo.getNombre() : null;
                    public final Double Precio = shampoo != null ? shampoo.getPrecio() : null;
                    public final Integer Stock = shampoo != null ? shampoo.getStock() : null;
                });
            }
            case YA_ELIMINADO -> ResponseEntity.ok(new Object() {
                public final int Status = 0;
                public final String Mensaje = "El producto ya fue previamente eliminado";
            });
            case NO_EXISTE -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final int Status = 0;
                public final String Mensaje = "El producto no existe";
            });
        };
    }

    // 6. Eliminar todos los shampoos (lógico)
    @DeleteMapping
    public ResponseEntity<Void> eliminarTodos() {
        service.eliminarTodos();
        return ResponseEntity.noContent().build();
    }

    // 7. Listar shampoos eliminados
    @GetMapping("/eliminados")
    public List<Shampoo> listarEliminados() {
        return service.listarShampoosEliminados();
    }

    // 8. Habilitar shampoo (lógico)
    @PutMapping("/habilitar/{id}")
    public ResponseEntity<?> habilitar(@PathVariable("id") int id) {
        ResultadoEliminacion resultado = service.habilitarShampoo(id);
        return switch (resultado) {
            case ELIMINADO -> {
                Shampoo shampoo = service.buscarShampoo(id);
                yield ResponseEntity.ok(new Object() {
                    public final int Status = 1;
                    public final String Mensaje = "Se pudo habilitar correctamente. Revisar precio y stock del producto.";
                    public final Integer ID = shampoo != null ? shampoo.getId() : id;
                    public final String Nombre = shampoo != null ? shampoo.getNombre() : null;
                    public final Double Precio = shampoo != null ? shampoo.getPrecio() : null;
                    public final Integer Stock = shampoo != null ? shampoo.getStock() : null;
                });
            }
            case YA_ELIMINADO -> ResponseEntity.ok(new Object() {
                public final int Status = 0;
                public final String Mensaje = "El producto ya estaba habilitado";
            });
            case NO_EXISTE -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Object() {
                public final int Status = 0;
                public final String Mensaje = "El producto no existe";
            });
        };
    }
}
