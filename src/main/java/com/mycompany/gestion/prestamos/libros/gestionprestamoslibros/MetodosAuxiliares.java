package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        System.out.println("7. Devolver libros por autor");
        System.out.println("8. Filtrar libros disponibles por género");
        System.out.println("9. Mostrar estadísticas generales");
        System.out.println("10. Eliminar un libro por su título");
        System.out.println("11. Marcar libro como perdido");
        System.out.println("12. Listar libros perdidos");
        System.out.println("13. Recuperar libro perdido");
        System.out.println("14. Salir");
    }

    public static void mostrarMensajeNoExistenLibrosEnLaLista() {
        System.out.println("La lista de libros de la biblioteca se encuentra vacía.");
    }
    
    public static void mostrarMensajeNoHayLibroConTituloBuscado(String titulo) {
        System.out.println("No hay ningún libro con el título " + titulo + " en la biblioteca.");
    }

    public static List<Libro> ordenarLibrosAlfabeticamente(List<Libro> libros) {
        /*
        Es la forma moderna de Java para ordenar listas según un criterio 
        (en este caso, por el título del libro). */
        List<Libro> listaOrdenadaAlfabeticamente = new ArrayList<>(libros);
        listaOrdenadaAlfabeticamente.sort(Comparator.comparing(Libro::getTitulo));

        return listaOrdenadaAlfabeticamente;
    }

    public static long cantidadLibrosPrestados(List<Libro> libros) {

        /* Para reemplazar el contador:
            - Siempre preciso, sin riesgo de error si me olvido de actualizar el contador.
            - Ligeramente más costoso (pero irrelevante en listas pequeñas).
         */
        long cantidadPrestados = libros.stream()
                .filter(libro -> !libro.isDisponible() && !libro.isPerdido())
                .count();

        return cantidadPrestados;
    }

    public static long cantidadLibrosDisponibles(List<Libro> libros) {
        long cantidadDisponibles = libros.stream()
                .filter(libro -> libro.isDisponible() && !libro.isPerdido())
                .count();

        return cantidadDisponibles;
    }
    
    public static Optional<Libro> buscarLibroPorTitulo(List<Libro> libros, String titulo) {
        Optional<Libro> libroAeliminar = libros.stream()
                .filter(libro -> libro.getTitulo().equalsIgnoreCase(titulo))
                .findFirst();
        
        return libroAeliminar;
    }

    // VALIDACIÓN PARA ENTRADA DE TIPO BOOLEAN
    public static boolean confirmacion(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje + " (s/n): ");
            String input = sc.nextLine().trim().toLowerCase();

            switch (input) {
                case "s", "si", "sí" -> {
                    return true;
                }
                case "n", "no" -> {
                    return false;
                }
                default ->
                    System.out.println("Entrada inválida. Por favor ingresá 's' para sí o 'n' para no.");
            }
        }
    }
    
    public static String inputTitulo(Scanner sc) {
        return leerTextoNoVacio(sc, "Título del libro: ");
    }
}
