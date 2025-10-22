/*
Acá van los métodos que uso en to-do el proyecto, pero no son lógica de negocio.
TODO test unitarios con Junit
 */

package org.example;
import java.util.Scanner;

public class Utils {

    public static String validarString(String mensaje, Scanner scanner){
        System.out.println(mensaje);
        while (!scanner.hasNext("[a-zA-ZáéíóúÁÉÍÓÚüÜñÑ'’]+")) {
            System.out.println("Revise el valor ingresado. Intente de nuevo: ");
            scanner.next();
        }
        String valor = scanner.next();
        return valor;
    }

    public static int validarInt(String mensaje, Scanner scanner){
        System.out.println(mensaje);
        while (!scanner.hasNextInt()) {
            System.out.println("Revise el valor ingresado. Intente de nuevo: ");
            scanner.nextInt();
        }
        int valor = scanner.nextInt();
        while (valor < 0) { //ni el stock ni nada de lo que usaria acá seria negativo... El stock puede ser cero. Tmb la opcion jajaja
            System.out.println("Revise el valor ingresado. Intente de nuevo: ");
            valor = scanner.nextInt();
        }
        return valor;
    }

    public static double validarDouble(String mensaje, Scanner scanner){
        System.out.println(mensaje);
        while (!scanner.hasNextDouble()) {
            System.out.println("Revise el valor ingresado. Intente de nuevo: ");
            scanner.next();
        }
        double valor = scanner.nextDouble();
        while (valor <= 0.0) { //el precio no puede ser negativo ni cero
            System.out.println("Revise el valor ingresado. Intente de nuevo: ");
            valor = scanner.nextDouble();
        }
        return valor;
    }
}
