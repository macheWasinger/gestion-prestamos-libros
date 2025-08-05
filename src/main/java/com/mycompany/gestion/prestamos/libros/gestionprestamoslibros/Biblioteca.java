package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;

import java.util.ArrayList;
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
            }
        } else {
            MetodosAuxiliares.mostrarMensajeNoExisteLibroBuscado();
        }
        
        if (encontrado.isDisponible()) {
            System.out.println("El libro ya está disponible en la biblioteca.");
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
