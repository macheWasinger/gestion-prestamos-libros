package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;

import java.util.Scanner;

public class MetodosAuxiliares {

    public static String leerTextoNoVacio(Scanner sc, String mensaje) {
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = sc.nextLine().trim();
            if (entrada.isEmpty()) {
                System.out.println("La entrada no puede estar vacía.");
            }
        } while (entrada.isEmpty());
        return entrada;
    }

    public static int leerIntEntre(Scanner sc, String mensaje, int min, int max) {
        int valor = -1;
        boolean valido = false;

        while (!valido) {
            try {
                System.out.print(mensaje);
                valor = Integer.parseInt(sc.nextLine());
                if (valor >= min && valor <= max) {
                    valido = true;
                } else {
                    System.out.printf("Por favor, ingresá un número entre %d y %d.\n", min, max);
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresá un número.");
            }
        }
        return valor;
    }

    public static void mostrarMenu() {
        System.out.println("\n--- Menú Biblioteca ---");
        System.out.println("1. Agregar libro");
        System.out.println("2. Listar libros");
        System.out.println("3. Buscar libro por título");
        System.out.println("4. Prestar libro");
        System.out.println("5. Devolver libro");
        System.out.println("6. Listar libros prestados");
        System.out.println("7. Salir");
    }
    
    public static void mostrarMensajeNoExisteLibroBuscado() {
        System.out.println("No se encontró ningún libro con ese título.");
    }
}