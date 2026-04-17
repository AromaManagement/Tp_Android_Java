package com.example.b.entidades;

public class Cliente {
    int id;
    String nombre, apellido, documento;

    public Cliente(String documento, String apellido, String nombre, int id) {
        this.documento = documento;
        this.apellido = apellido;
        this.nombre = nombre;
        this.id = id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
