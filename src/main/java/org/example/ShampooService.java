/*
Acá va la logica del negocio.
 */
package org.example;

import java.util.List;

public class ShampooService {
    private final ShampooRepositorio repository;

    public ShampooService(ShampooRepositorio repository) {
        this.repository = repository;
    }

    public Shampoo crearShampoo(String nombre, double precio, int stock) {
        Shampoo shampoo = new Shampoo(0, nombre, precio, stock);
        return repository.guardar(shampoo);
    }

    public List<Shampoo> listarShampoos() {
        return repository.buscarTodos();
    }

    public Shampoo buscarShampoo(int id) {
        return repository.buscarPorID(id);
    }

    public Shampoo actualizarShampoo(int id, String nombre, double precio, int stock) {
        Shampoo shampoo = repository.buscarPorID(id);
        if (shampoo != null) {
            shampoo.setNombre(nombre);
            shampoo.setPrecio(precio);
            shampoo.setStock(stock);
            repository.guardar(shampoo);
        }
        return shampoo;
    }

    public boolean eliminarShampoo(int id) {
        return repository.borrarPorID(id);
    }
}
