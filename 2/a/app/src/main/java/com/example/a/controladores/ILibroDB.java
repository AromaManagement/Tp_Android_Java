package com.example.a.controladores;

import com.example.a.modelos.Libro;

import java.util.List;

public interface ILibroDB {

    Libro elemento(int id);
    Libro elemento(String title);

    List<Libro> lista();

    void agregar(Libro book);
    void actualizar(int id, Libro book);

    void borrar(int id);

}
