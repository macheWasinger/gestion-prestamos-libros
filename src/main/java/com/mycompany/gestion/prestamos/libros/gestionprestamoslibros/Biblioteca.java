package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Biblioteca {

    private List<Libro> libros;

    public Biblioteca() {
        libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros cargados.");
            return;
        }

        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    public void prestarLibro(String titulo) {
        Libro encontrado = buscarLibroPorTitulo(titulo);

        if (encontrado != null) {
            if (encontrado.isDisponible()) {
                encontrado.setDisponible(false);
                System.out.println("✅ El libro fue prestado correctamente.");
            } else {
                System.out.println("⛔ El libro ya fue prestado y no está disponible.");
            }

        } else {
            MetodosAuxiliares.mostrarMensajeNoExisteLibroBuscado();
        }
    }

    public void devolverLibro(String titulo) {
        Libro encontrado = buscarLibroPorTitulo(titulo);

        if (encontrado != null) {
            if (!encontrado.isDisponible()) {
                encontrado.setDisponible(true);
                System.out.println("El libro fue devuelto correctamente.");
            } else {
                System.out.println("El libro ya está disponible en la biblioteca.");
            }
        } else {
            MetodosAuxiliares.mostrarMensajeNoExisteLibroBuscado();
        }

    }

    public void listarLibrosPrestados() {
        if (libros.isEmpty()) {
            System.out.println("La lista de libros de la biblioteca se encuentra vacía.");
            return;
        }

        /*
        Es la forma moderna de Java para ordenar listas según un criterio 
        (en este caso, por el título del libro). */
        List<Libro> listaOrdenadaAlfabeticamente = new ArrayList<>(libros);
        listaOrdenadaAlfabeticamente.sort(Comparator.comparing(Libro::getTitulo));

        for (Libro libro : listaOrdenadaAlfabeticamente) {
            if (!libro.isDisponible()) {
                System.out.println(libro);
            }
        }

        /* Para reemplazar el contador:
            - Siempre preciso, sin riesgo de error si me olvido de actualizar el contador.
            - Ligeramente más costoso (pero irrelevante en listas pequeñas).
         */
        long cantidadPrestados = listaOrdenadaAlfabeticamente.stream()
                .filter(libro -> !libro.isDisponible())
                .count();

        if (cantidadPrestados == 0) {
            System.out.println("No hay libros prestados actualmente.");
        } else {
            System.out.println("Cantidad total de libros prestados: " + cantidadPrestados);
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
