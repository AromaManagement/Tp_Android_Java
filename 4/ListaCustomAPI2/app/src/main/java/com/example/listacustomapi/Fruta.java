package com.example.listacustomapi;

import java.io.Serializable;

public class Fruta implements Serializable {

    private String nombre;
    private int color;
    private String lugar;
    private String vitaminas;
    private int foto;

    public Fruta(String nombre, int color, String lugar, String vitaminas, int foto) {
        this.nombre = nombre;
        this.color = color;
        this.lugar = lugar;
        this.vitaminas = vitaminas;
        this.foto = foto;
    }

    public int getColor() {
        return color;
    }

    public String getNombre() {
        return nombre;
    }

    public String getLugar() {
        return lugar;
    }

    public String getVitaminas() {
        return vitaminas;
    }

    public int getFoto() {
        return foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setVitaminas(String vitaminas) {
        this.vitaminas = vitaminas;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
