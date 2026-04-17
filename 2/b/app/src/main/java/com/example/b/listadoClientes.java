package com.example.b;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.b.entidades.ConexionSQLite;
import com.example.b.adaptador.AdapterCliente;
import com.example.b.entidades.Cliente;
import com.example.b.utils.Utilidades;

import java.util.ArrayList;

public class listadoClientes extends AppCompatActivity implements AdapterCliente.OnItemClickListener {

    RecyclerView recyclerLista;
    ConexionSQLite con;
    ArrayList<Cliente> listaClientes;
    AdapterCliente adapter;
    Button buscar,home;
    EditText txtBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_clientes);

        recyclerLista=findViewById(R.id.lista_clientes);
        buscar = findViewById(R.id.button_buscar);
        home = findViewById(R.id.button_home);
        txtBuscar = findViewById(R.id.buscar_doc);
        con = new ConexionSQLite(getApplicationContext(),"BD_CLIENTES",null,1);

        recyclerLista.setLayoutManager(new LinearLayoutManager(this));
        listarClientes("");

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarClientes(txtBuscar.getText().toString());
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listadoClientes.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void listarClientes(String documento) {
        SQLiteDatabase db = con.getReadableDatabase();
        Cliente cliente;
        listaClientes = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_CLIENTE+" WHERE "+Utilidades.CAMPO_DOC+" LIKE '%"+ documento +"%'" ,null);
        while (cursor.moveToNext()){
            cliente = new Cliente(cursor.getString(3),cursor.getString(2),cursor.getString(1),cursor.getInt(0));
            listaClientes.add(cliente);
        }
        adapter = new AdapterCliente(listaClientes,this);
        recyclerLista.setAdapter(adapter);
        db.close();
    }

    @Override
    public void itemClick(Cliente cliente){

        Intent intent = new Intent(listadoClientes.this, MainActivity.class);

        intent.putExtra("ID",cliente.getId());
        intent.putExtra("NOM",cliente.getNombre());
        intent.putExtra("AP",cliente.getApellido());
        intent.putExtra("DOC",cliente.getDocumento());

        startActivity(intent);
        finish();
    }
}