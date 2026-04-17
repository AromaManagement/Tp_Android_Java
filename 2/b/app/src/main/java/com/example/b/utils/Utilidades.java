package com.example.b.utils;

public class Utilidades {
    public static final String TABLA_CLIENTE = "t_cliente";
    public static final String CAMPO_ID = "id_cliente";
    public static final String CAMPO_NOM = "nom_cliente";
    public static final String CAMPO_AP = "ap_cliente";
    public static final String CAMPO_DOC = "doc_cliente";
    public static final String crear_tabla_cliente = "CREATE TABLE "+ TABLA_CLIENTE + "("+
            CAMPO_ID + " INTEGER, " + CAMPO_NOM + " TEXT, " + CAMPO_AP + " TEXT, " + CAMPO_DOC + " TEXT)";
}
