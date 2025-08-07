package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Biblioteca {

    private List<Libro> libros;
    private List<Libro> librosPerdidos;

    public Biblioteca() {
        libros = new ArrayList<>();
        librosPerdidos = new ArrayList<>();
    }

    //--- AGREGAR LIBRO ---
    public void agregarLibro(Libro libro) {
        libros.add(libro);
    }

    //--- LISTAR LIBROS ---
    public void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros cargados.");
            return;
        }
        
        if (libros.size() == librosPerdidos.size()) {
            System.out.println("No quedan libros en la biblioteca porque han sido perdidos.");
            return;
        }

        for (Libro libro : libros) {
            
            // Si el LIBRO NO está PERDIDO, mostralo en la lista
            if (!libro.isPerdido()) {
                System.out.println(libro);
            }
        }
    }

    //--- BUSCAR LIBRO POR TÍTULO ---
    public Libro buscarLibroPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }

    //--- PRESTAR LIBRO ---
    public void prestarLibro(String titulo) {
        Libro encontrado = buscarLibroPorTitulo(titulo);

        if (encontrado != null) {
            if (encontrado.isDisponible() && !encontrado.isPerdido()) {
                encontrado.setDisponible(false);
                System.out.println("El libro fue prestado correctamente.");
            } else {
                System.out.println("El libro ya fue prestado y no está disponible.");
            }

        } else {
            MetodosAuxiliares.mostrarMensajeNoHayLibroConTituloBuscado(titulo);
        }
    }

    //--- DEVOLVER LIBRO ---
    public void devolverLibro(String titulo) {
        Libro encontrado = buscarLibroPorTitulo(titulo);

        if (encontrado != null) {
            if (!encontrado.isDisponible() && !encontrado.isPerdido()) {
                encontrado.setDisponible(true);
                System.out.println("El libro fue devuelto correctamente.");
            } else {
                System.out.println("El libro ya está disponible en la biblioteca.");
            }
        } else {
            MetodosAuxiliares.mostrarMensajeNoHayLibroConTituloBuscado(titulo);
        }

    }

    //--- LISTAR LIBROS PRESTADOS ---
    public void listarLibrosPrestados() {
        if (libros.isEmpty()) {
            System.out.println("La lista de libros de la biblioteca se encuentra vacía.");
            return;
        }

        List<Libro> listaOrdenadaAlfabeticamente = MetodosAuxiliares.ordenarLibrosAlfabeticamente(libros);

        for (Libro libro : listaOrdenadaAlfabeticamente) {
            if (!libro.isDisponible() && !libro.isPerdido()) {
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

    //--- DEVOLVER LIBROS POR AUTOR ---
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

    //--- FILTRAR LIBROS DISPONIBLES POR GÉNERO ---
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
            if (!libro.isPerdido()) {
                System.out.println("- " + libro.getTitulo());
            }
        }
    }

    //--- MOSTRAR ESTADÍSTICAS GENERALES ---
    public void mostrarEstadisticasGenerales() {
        if (libros.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoExistenLibrosEnLaLista();
            return;
        }

        List<String> listaGenerosConDuplicados = new ArrayList<>();

        for (Libro libro : libros) {
            if (!libro.isPerdido()) {
                listaGenerosConDuplicados.add(libro.getGenero().toLowerCase());
            }
        }

        List<String> listaGenerosSinDuplicados = listaGenerosConDuplicados.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Cantidad total de libros cargados: " + libros.size());
        System.out.println("Cantidad total de libros disponibles: " + MetodosAuxiliares.cantidadLibrosDisponibles(libros));
        System.out.println("Cantidad total de libros prestados: " + MetodosAuxiliares.cantidadLibrosPrestados(libros));
        System.out.println("Cantidad total de libros perdidos: " + librosPerdidos.size());
        
        if (!listaGenerosSinDuplicados.isEmpty()) {
            System.out.println("Géneros presentes en la biblioteca: ");
        }
        
        for (String genero : listaGenerosSinDuplicados) {
            System.out.println("- " + genero);
        }
    }

    //--- ELIMINAR LIBRO ---
    public void eliminarLibro(Scanner sc, String titulo) {
      
        if (libros.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoExistenLibrosEnLaLista();
            return;
        }
        
        // Verifico si existe el libro antes de preguntar si desea eliminarlo
        Optional<Libro> libroAeliminar = MetodosAuxiliares.buscarLibroPorTitulo(libros, titulo);
        
        if (libroAeliminar.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoHayLibroConTituloBuscado(titulo);
            return;
        }

        // Si existe, se pregunta si desea eliminar
        boolean confirmacionEliminar = MetodosAuxiliares.confirmacion(sc, "¿Estás seguro de eliminar este libro?");

        if (confirmacionEliminar) {
            // Extrae el libro que está adentro del Optional<Libro>
            libros.remove(libroAeliminar.get());
            System.out.println("Libro eliminado correctamente.");
        } else {
            System.out.println("De acuerdo. El libro no se eliminará.");
        }
    }
    
    //--- MARCAR COMO PERDIDO ---
    public void marcarComoPerdido(Scanner sc, String titulo) {
        
        if (libros.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoExistenLibrosEnLaLista();
            return;
        }
        
        Optional<Libro> libroAmarcar = MetodosAuxiliares.buscarLibroPorTitulo(libros, titulo);
        
        if (libroAmarcar.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoHayLibroConTituloBuscado(titulo);
            return;
        }
        
        boolean confirmacionMarcarComoPerdido = MetodosAuxiliares.confirmacion(sc, "¿Estás seguro de marcar este libro como perdido?");
        
        if (confirmacionMarcarComoPerdido) {
            if (!libroAmarcar.get().isDisponible()) {
                libroAmarcar.get().setPerdido(true);
                System.out.println("Libro marcado como perdido correctamente.");
                
                // Agrego el libro perdido a la nueva lista de libros perdidos
                librosPerdidos.add(libroAmarcar.get());
            } else {
                System.out.println("El libro no puede marcarse como perdido porque no está actualmente prestado.");
            }
        } else {
            System.out.println("De acuerdo. El libro no se marcará como perdido.");
        }
    }
    
    //--- LISTAR LIBROS PERDIDOS ---
    public void listarLibrosPerdidos() {
        if (librosPerdidos.isEmpty()) {
            MetodosAuxiliares.mostrarMensajeNoExistenLibrosEnLaLista();
            return;
        }
        
        for (Libro libro : librosPerdidos) {
            System.out.println(libro);
        }
    }

    public List<Libro> getLibros() {
        return libros;
    }
}
