package com.sinnombre.sqlite.entidades;

public class Contactos {

    private  int id;
    private String nombre,telfono,correo;

    public void setId(int id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelfono(String telfono) {
        this.telfono = telfono;
    }

    public int getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelfono() {
        return telfono;
    }
}
