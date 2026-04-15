package com.tp1.semana4paises;

public class Modelo {

    private String titulo,contenido;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Modelo(String titulo, String contenido, int imagen) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.imagen = imagen;
    }

    private int imagen;


}
