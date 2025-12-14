package org.example;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Shampoo crear(@RequestBody Shampoo shampoo) {
        return service.crearShampoo(shampoo.getNombre(), shampoo.getPrecio(), shampoo.getStock());
    }

    // 2. Listar shampoos habilitados
    @GetMapping
    public List<Shampoo> listar() {
        return service.listarShampoos();
    }

    // 3. Buscar shampoo por ID
    @GetMapping("/{id}")
    public Shampoo buscarPorId(@PathVariable int id) {
        return service.buscarShampoo(id);
    }

    // 4. Actualizar shampoo (solo campos permitidos)
    @PutMapping("/{id}")
    public Shampoo actualizar(@PathVariable int id, @RequestBody Shampoo shampoo) {
        return service.actualizarShampoo(id, shampoo.getNombre(), shampoo.getPrecio(), shampoo.getStock());
    }

    // 5. Eliminar shampoo (lógico)
    @DeleteMapping("/{id}")
    public boolean eliminar(@PathVariable int id) {
        return service.eliminarShampoo(id);
    }

    // 6. Eliminar todos los shampoos (lógico)
    @DeleteMapping
    public void eliminarTodos() {
        for (Shampoo s : service.listarShampoos()) {
            service.eliminarShampoo(s.getId());
        }
    }

    // 7. Listar shampoos eliminados
    @GetMapping("/eliminados")
    public List<Shampoo> listarEliminados() {
        return service.listarShampoosEliminados();
    }
}

