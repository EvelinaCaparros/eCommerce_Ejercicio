package org.example;

// Entry point de consola antiguo basado en repositorio manual.
// Ya no se usa con la version Spring Boot/JPA y generaba errores de compilacion
// al intentar instanciar directamente ShampooRepositorio (que ahora es una interfaz JPA).
// Si quisieras una interfaz de consola, se deberia reescribir usando beans de Spring.

public class Main {
    public static void main(String[] args) {
        System.out.println("Esta clase Main ya no se utiliza. Ejecuta la aplicacion con Spring Boot usando EcommerceApplication.");
    }
}