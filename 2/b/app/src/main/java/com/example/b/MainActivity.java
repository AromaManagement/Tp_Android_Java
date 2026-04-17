package com.example.b;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.b.entidades.ConexionSQLite;
import com.example.b.utils.Utilidades;

public class MainActivity extends AppCompatActivity {

    EditText id, nom, ap, doc;
    Button registrar, editar, eliminar, listar;
    ConexionSQLite con;
    int idSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.textIdClient);
        nom = findViewById(R.id.textNomClient);
        ap = findViewById(R.id.textApClient);
        doc = findViewById(R.id.textDocClient);
        registrar = findViewById(R.id.button_registrar);
        editar = findViewById(R.id.button_editar);
        eliminar = findViewById(R.id.button_eliminar);
        listar = findViewById(R.id.button_listar);
        con = new ConexionSQLite(getApplicationContext(),"BD_CLIENTES",null,1);
        idCliente();


        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            id.setText(bundle.getInt("ID")+"");
            nom.setText(bundle.getString("NOM"));
            ap.setText(bundle.getString("AP"));
            doc.setText(bundle.getString("DOC"));
        }

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarCliente();
            }
        });

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarCliente();
            }
        });

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCliente();
            }
        });

        listar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,listadoClientes.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void eliminarCliente() {
        SQLiteDatabase db = con.getReadableDatabase();
        String parametro[] = {id.getText().toString()};

        db.delete(Utilidades.TABLA_CLIENTE, Utilidades.CAMPO_ID+"=?",parametro);
        Toast.makeText(getApplicationContext(),"Se eliminó el registro "+id.getText().toString(), Toast.LENGTH_LONG).show();
        db.close();
        limpiarCampos();
        idCliente();
    }

    private void registrarCliente(){
        SQLiteDatabase db = con.getReadableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID,id.getText().toString());
        values.put(Utilidades.CAMPO_NOM,nom.getText().toString());
        values.put(Utilidades.CAMPO_AP,ap.getText().toString());
        values.put(Utilidades.CAMPO_DOC,doc.getText().toString());

        Long idresultante = db.insert(Utilidades.TABLA_CLIENTE,Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(),"Se registro con ID "+idresultante, Toast.LENGTH_LONG).show();
        db.close();
        limpiarCampos();
        idCliente();
    }

    private void editarCliente(){
        SQLiteDatabase db = con.getReadableDatabase();
        String parametro[] = {id.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID,id.getText().toString());
        values.put(Utilidades.CAMPO_NOM,nom.getText().toString());
        values.put(Utilidades.CAMPO_AP,ap.getText().toString());
        values.put(Utilidades.CAMPO_DOC,doc.getText().toString());

        db.update(Utilidades.TABLA_CLIENTE, values, Utilidades.CAMPO_ID+"=?",parametro);
        Toast.makeText(getApplicationContext(),"Se actualizó el registro "+id.getText().toString(), Toast.LENGTH_LONG).show();
        db.close();
        limpiarCampos();
        idCliente();
    }

    private void limpiarCampos(){
        id.setText("");
        nom.setText("");
        ap.setText("");
        doc.setText("");
    }

    private void idCliente(){
        SQLiteDatabase db = con.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery("SELECT MAX (" + Utilidades.CAMPO_ID + ") FROM " + Utilidades.TABLA_CLIENTE, null);
            cursor.moveToFirst();
            idSiguiente = cursor.getInt(0) + 1;
            id.setText(idSiguiente+"");
            cursor.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}