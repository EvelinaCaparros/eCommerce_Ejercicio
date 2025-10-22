package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ShampooRepositorio repo = new ShampooRepositorio();
        ShampooService service = new ShampooService(repo);
        Utils utils = new Utils();
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) { //Solo va a ser false para la opcion cero (salir). Tambien podría haber sido un do-while
            System.out.println("""
                Ingrese el nro de la opcion.
                1. Crear shampoo
                2. Listar shampoos
                3. Buscar shampoo por ID
                4. Actualizar shampoo
                5. Eliminar shampoo
                6. Eliminar todos los shampoos
                0. Salir
                """);

            int op = utils.validarInt("Opción: ", sc);

            //switch es una estructura que permite ejecutar distintos bloques de codigo segun el valor de la variable (op en este caso)
            switch (op) {
                case 1 -> {
                    String nombre = utils.validarString("Nombre: ", sc);
                    double precio = utils.validarDouble("Precio: ", sc);
                    int stock = utils.validarInt("Stock: ", sc);

                    Shampoo nuevo = service.crearShampoo(nombre, precio, stock);
                    System.out.println("Creado: " + nuevo);
                }
                case 2 -> {
                    System.out.println("Lista de shampoos:");
                    for (Shampoo s : service.listarShampoos()) {
                        System.out.println(s);
                    }
                }
                case 3 -> {
                    int id = utils.validarInt("ID: ", sc);
                    Shampoo s = service.buscarShampoo(id);
                    System.out.println(s != null ? s : "No encontrado");
                }
                case 4 -> {
                    int id = utils.validarInt("ID a actualizar: ", sc);
                    String nombre = utils.validarString("Nuevo nombre: ",sc);
                    double precio = utils.validarDouble("Nuevo precio: ", sc);
                    int stock = utils.validarInt("Nuevo stock: ", sc);

                    Shampoo actualizado = service.actualizarShampoo(id, nombre, precio, stock);
                    System.out.println(actualizado != null ? "Actualizado: " + actualizado : "No encontrado");
                }
                case 5 -> {
                    int id = utils.validarInt("ID a eliminar: ", sc);
                    boolean eliminado = service.eliminarShampoo(id);
                    System.out.println(eliminado ? "Eliminado" : "No encontrado");
                }
                case 6 -> {
                    for (Shampoo s : service.listarShampoos()) {
                        service.eliminarShampoo(s.getId());
                    }
                    System.out.println("Todos los shampoos han sido eliminados.");
                }
                case 0 -> running = false;
                default -> System.out.println("Opción inválida");
            }
        }
        System.out.println("¡Chau 👋!");
    }
}