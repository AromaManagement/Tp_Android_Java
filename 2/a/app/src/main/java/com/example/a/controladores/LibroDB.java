package com.example.a.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.a.modelos.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibroDB extends SQLiteOpenHelper implements ILibroDB {

    Context contexto;
    private List<Libro> librosList = new ArrayList<>();

    public LibroDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE libros("+
                "_id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "titulo TEXT,"+
                "subtitulo TEXT,"+
                "isbn TEXT,"+
                "autor TEXT,"+
                "anio INTEGER,"+
                "precio REAL)";
        db.execSQL(sql);

        String insert = "INSERT INTO libros VALUES (null, " +
                "'Como aprobar programacion movil'," +
                "'Tecnicas para sobrevivir el cursado'," +
                "'456789'," +
                "'Fulano Detal'," +
                "2026," +
                "50000)";
        db.execSQL(insert);

        insert = "INSERT INTO libros VALUES (null, " +
                "'Patrones de Ingenieria de Software for Dummies'," +
                "'Todo lo que necesitas para aprender sin haber cursado'," +
                "'666777'," +
                "'Jhon Doe'," +
                "2024," +
                "85000)";
        db.execSQL(insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public Libro elemento(int id) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM libros WHERE _id ="+id;
        Cursor cursor = database.rawQuery(sql,null);
        try {
            if (cursor.moveToNext()){
                return extraerLibro(cursor);
            } else {
                return null;
            }
        } catch (Exception e){
            Log.d("TAG", "Error elemento(id) LibroDB" + e.getMessage());
            throw e;
        }finally {
            if( cursor != null ) cursor.close();
        }
    }

    private Libro extraerLibro(Cursor cursor) {
        Libro libro = new Libro();
        libro.setId(cursor.getInt(0));
        libro.setTitulo(cursor.getString(1));
        libro.setSubtitulo(cursor.getString(2));
        libro.setIsbn(cursor.getString(3));
        libro.setAutor(cursor.getString(4));
        libro.setAnioPublicacion(cursor.getInt(5));
        libro.setPrecio(cursor.getDouble(6));
        return libro;
    }

    @Override
    public Libro elemento(String title) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM libros WHERE titulo ='"+title+"'";
        Cursor cursor = database.rawQuery(sql,null);
        try {
            if (cursor.moveToNext()){
                return extraerLibro(cursor);
            } else {
                return null;
            }
        } catch (Exception e){
            Log.d("TAG", "Error elemento(title) LibroDB" + e.getMessage());
            throw e;
        }finally {
            if( cursor != null ) cursor.close();
        }
    }

    @Override
    public List<Libro> lista() {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM libros ORDER BY titulo ASC";
        Cursor cursor = database.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                librosList.add(extraerLibro(cursor));
            } while(cursor.moveToNext());
        }
        cursor.close();
        return librosList;
    }

    @Override
    public void agregar(Libro book) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo",book.getTitulo());
        values.put("subtitulo",book.getSubtitulo());
        values.put("autor",book.getAutor());
        values.put("isbn",book.getIsbn());
        values.put("anio",book.getAnioPublicacion());
        values.put("precio",book.getPrecio());
        database.insert("libros", null, values);
    }

    @Override
    public void actualizar(int id, Libro book) {
        SQLiteDatabase database = getReadableDatabase();
        String[] parametros = { String.valueOf(id) };

        ContentValues values = new ContentValues();
        values.put("titulo",book.getTitulo());
        values.put("subtitulo",book.getSubtitulo());
        values.put("autor",book.getAutor());
        values.put("isbn",book.getIsbn());
        values.put("anio",book.getAnioPublicacion());
        values.put("precio",book.getPrecio());
        database.update("libros", null, "_id=?",parametros);
    }

    @Override
    public void borrar(int id) {
        SQLiteDatabase database = getReadableDatabase();
        String[] parametros = { String.valueOf(id) };

        database.delete("libros", "_id=?",parametros);
    }
}
