package com.tp1.myapplication.modelo;

import java.io.Serializable;

public class Ciudad implements Serializable {
    private String nombre;
    private int habitantes;
    private double distancia;
    private int foto;

    public Ciudad(String nombre, int habitantes, double distancia, int foto) {
        this.nombre = nombre;
        this.habitantes = habitantes;
        this.distancia = distancia;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }


    public int getHabitantes() {
        return habitantes;
    }

    public void setHabitantes(int habitantes) {
        this.habitantes = habitantes;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
