package com.mycompany.gestion.prestamos.libros.gestionprestamoslibros;


public class Libro {
    private String titulo;
    private String autor;
    private String genero;
    private boolean disponible;
    private boolean perdido;

    public Libro(String titulo, String autor, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.disponible = true;
        this.perdido = false;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
    
    public String getGenero() {
        return genero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public boolean isPerdido() {
        return perdido;
    }
    
    public void setPerdido(boolean perdido) {
        this.perdido = perdido;
    }

    @Override
    public String toString() {
        return String.format("Título: %s | Autor: %s | Género: %s | Disponible: %s | Perdido: %s",
                titulo, autor, genero, disponible, perdido? "Sí" : "No");
    }
}