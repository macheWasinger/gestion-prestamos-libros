package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        List<Libro>listaOrdenadaAlfabeticamente = MetodosAuxiliares.ordenarLibrosAlfabeticamente(libros);

        for (Libro libro : listaOrdenadaAlfabeticamente) {
            if (!libro.isDisponible()) {
                System.out.println(libro);
            }
        }

        long cantidadPrestados = MetodosAuxiliares.cantidadLibrosPrestados(listaOrdenadaAlfabeticamente);

        if (cantidadPrestados == 0) {
            System.out.println("No hay libros prestados actualmente.");
        } else {
            System.out.println("Cantidad total de libros prestados: " + cantidadPrestados);
        }
    }
    
    public void devolverLibrosPorAutor(String autor) {
        
        if (libros.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoExistenLibrosEnLaLista();
            return;
        }
        
        List<Libro> listaOrdenadaAlfabeticamente = MetodosAuxiliares.ordenarLibrosAlfabeticamente(libros);     
        List<Libro> librosDelAutor = listaOrdenadaAlfabeticamente.stream()
                .filter(libro -> libro.getAutor().equalsIgnoreCase(autor) && !libro.isDisponible())
                .collect(Collectors.toList());
        
        if (librosDelAutor.isEmpty()) {
            System.out.println("No se encontraron libros prestados del autor " + autor);
            return;
        }
        
        long cantidadDeLibrosParaDevolver = librosDelAutor.size();
        
        System.out.println("Se devolvieron " + cantidadDeLibrosParaDevolver + " libros prestados del autor " + autor + ": ");
        for (Libro libro : librosDelAutor) {
            /* Recorro la lista "librosDelAutor" porque ya está filtrada, así 
            EVITO tener que aplicar NUEVAMENTE las CONDICIONES:
            `libro.getAutor().equalsIgnoreCase(autor) && !libro.isDisponible())`
            */
            libro.setDisponible(true);
            System.out.println("- " + libro.getTitulo());
        }
        
        if (cantidadDeLibrosParaDevolver > 0) {
            System.out.println("Cantidad total de libros devueltos: " + cantidadDeLibrosParaDevolver);
        }
    }
    
    public void filtrarLibrosDisponiblesPorGenero(String genero) {
        if (libros.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoExistenLibrosEnLaLista();
            return;
        }
        
        List<Libro> listaOrdenadaAlfabeticamentePorTitulo = MetodosAuxiliares.ordenarLibrosAlfabeticamente(libros);
        List<Libro> listaDelGenero = listaOrdenadaAlfabeticamentePorTitulo.stream()
                .filter(libro -> libro.getGenero().equalsIgnoreCase(genero) && libro.isDisponible())
                .collect(Collectors.toList());
        
        if (listaDelGenero.isEmpty()) {
            System.out.println("No hay libros disponibles del género " + genero);
            return;
        }
        
        System.out.println("Libros disponibles filtrados por el género " + genero + ": ");
        for (Libro libro : listaDelGenero) {
            System.out.println("- " + libro.getTitulo());
        }
    }
    
    public void mostrarEstadisticasGenerales() {
        if (libros.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoExistenLibrosEnLaLista();
            return;
        }
        
        List<String> listaGenerosConDuplicados = new ArrayList<>();
        
        for (Libro libro : libros) {
            listaGenerosConDuplicados.add(libro.getGenero().toLowerCase());
        }
        
        List<String> listaGenerosSinDuplicados = listaGenerosConDuplicados.stream()
                .distinct()
                .collect(Collectors.toList());
        
        
        System.out.println("Cantidad total de libros cargados: " + libros.size());
        System.out.println("Cantidad total de libros disponibles: " + MetodosAuxiliares.cantidadLibrosDisponibles(libros));
        System.out.println("Cantidad total de libros prestados: " + MetodosAuxiliares.cantidadLibrosPrestados(libros));
        
        System.out.println("Géneros presentes en la biblioteca: ");
        for(String genero : listaGenerosSinDuplicados) {
            System.out.println("- " + genero);
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
