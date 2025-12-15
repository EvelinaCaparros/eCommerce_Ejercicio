/*
Acá va la logica del negocio, ahora basada en JPA/PostgreSQL.
 */
package org.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShampooService {
    private final ShampooRepositorio repository;

    public ShampooService(ShampooRepositorio repository) {
        this.repository = repository;
    }

    // Crear shampoo nuevo (estado habilitado por defecto)
    public Shampoo crearShampoo(String nombre, double precio, int stock) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío ni nulo");
        }
        Shampoo shampoo = new Shampoo();
        shampoo.setNombre(nombre);
        shampoo.setPrecio(precio);
        shampoo.setStock(stock);
        shampoo.setEstado(1);
        return repository.save(shampoo);
    }

    // Listar solo shampoos habilitados (estado = 1)
    public List<Shampoo> listarShampoos() {
        return repository.findByEstado(1);
    }

    // Buscar shampoo por ID (puede estar habilitado o no)
    public Shampoo buscarShampoo(int id) {
        Optional<Shampoo> opt = repository.findById(id);
        return opt.orElse(null);
    }

    // Actualizar shampoo existente (si no existe o está eliminado devuelve null)
    public Shampoo actualizarShampoo(int id, String nombre, double precio, int stock) {
        Shampoo shampoo = buscarShampoo(id);
        if (shampoo != null && shampoo.getEstado() == 1) {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede ser vacío ni nulo");
            }
            shampoo.setNombre(nombre);
            shampoo.setPrecio(precio);
            shampoo.setStock(stock);
            return repository.save(shampoo);
        }
        return null;
    }

    // Eliminación lógica: estado = 0
    public ResultadoEliminacion eliminarShampoo(int id) {
        Shampoo shampoo = buscarShampoo(id);
        if (shampoo == null) {
            return ResultadoEliminacion.NO_EXISTE;
        }
        if (shampoo.getEstado() == 0) {
            return ResultadoEliminacion.YA_ELIMINADO;
        }
        shampoo.setEstado(0);
        repository.save(shampoo);
        return ResultadoEliminacion.ELIMINADO;
    }

    // Listar todos los shampoos eliminados lógicamente
    public List<Shampoo> listarShampoosEliminados() {
        return repository.findByEstado(0);
    }

    // Habilitar shampoo: pasa estado a 1 y resetea precio/stock
    public ResultadoEliminacion habilitarShampoo(int id) {
        Shampoo shampoo = buscarShampoo(id);
        if (shampoo == null) {
            return ResultadoEliminacion.NO_EXISTE;
        }
        if (shampoo.getEstado() == 1) {
            return ResultadoEliminacion.YA_ELIMINADO; // Reutilizo enum aunque el mensaje sea distinto en el controlador
        }
        shampoo.setEstado(1);
        shampoo.setPrecio(0.0); // Precio en cero al habilitar
        shampoo.setStock(0);    // Stock en cero al habilitar
        repository.save(shampoo);
        return ResultadoEliminacion.ELIMINADO;
    }

    // Eliminar todos lógicamente (estado = 0)
    public void eliminarTodos() {
        List<Shampoo> activos = repository.findByEstado(1);
        for (Shampoo s : activos) {
            s.setEstado(0);
            repository.save(s);
        }
    }
}
