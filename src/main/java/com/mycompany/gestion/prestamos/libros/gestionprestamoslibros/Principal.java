package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();

        boolean salir = false;

        while (!salir) {
            MetodosAuxiliares.mostrarMenu();
            int opcion = MetodosAuxiliares.leerIntEntre(sc, "Elegí una opción: ", 1, 8);

            switch (opcion) {
                case 1 -> {
                    System.out.println("\n--- Agregar libro ---");
                    String titulo = MetodosAuxiliares.leerTextoNoVacio(sc, "Título: ");
                    String autor = MetodosAuxiliares.leerTextoNoVacio(sc, "Autor: ");
                    biblioteca.agregarLibro(new Libro(titulo, autor));
                    System.out.println("Libro agregado con éxito.");
                }

                case 2 -> {
                    System.out.println("\n--- Listado de libros ---");
                    biblioteca.listarLibros();
                }

                case 3 -> {
                    System.out.println("\n--- Buscar libro por título ---");
                    String titulo = MetodosAuxiliares.leerTextoNoVacio(sc, "Título: ");
                    Libro encontrado = biblioteca.buscarLibroPorTitulo(titulo);
                    if (encontrado != null) {
                        System.out.println("Libro encontrado:");
                        System.out.println(encontrado);
                    } else {
                        System.out.println("No se encontró ningún libro con ese título.");
                    }
                }

                case 4 -> {
                    System.out.println("\n--- Prestar libro ---");
                    String titulo = MetodosAuxiliares.leerTextoNoVacio(sc, "Ingrese el título del libro que desea prestar: ");
                    biblioteca.prestarLibro(titulo);
                }

                case 5 -> {
                    System.out.println("\n--- Devolver libro ---");
                    String titulo = MetodosAuxiliares.leerTextoNoVacio(sc, "Ingrese el título del libro que desea devolver: ");
                    biblioteca.devolverLibro(titulo);
                    
                }
                
                case 6 -> {
                    System.out.println("\n--- Lista de libros prestados ---");
                    biblioteca.listarLibrosPrestados();
                }
                
                case 7 -> {
                    System.out.println("\n--- Devolución de libros por autor ---");
                    String autor = MetodosAuxiliares.leerTextoNoVacio(sc, "Ingrese el autor cuyos libros desea devolver: ");
                    biblioteca.devolverLibrosPorAutor(autor);
                }
                
                case 8 -> {
                    salir = true;
                    System.out.println("¡Hasta luego!");
                }
            }
        }
    }
}
