package com.example.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.a.controladores.LibroDB;
import com.example.a.controladores.SelectListener;
import com.example.a.modelos.Libro;

import java.util.ArrayList;
import java.util.List;

public class ListadoLibrosActivity extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<String> nombresLibros;
    ArrayList<Integer> idLibros;
    LibroDB libroDB;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_libros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init(){
        context = this.getApplicationContext();
        libroDB = new LibroDB(context, "LibrosDB.db",null,1);
        listView = findViewById(R.id.listaLibros);
        llenarListView();
    }

    private void llenarListView(){
        nombresLibros = new ArrayList<String>();
        idLibros = new ArrayList<Integer>();

        List<Libro> listaLibros = libroDB.lista();
        for(int i = 0; i<listaLibros.size();i++){
            Libro l = listaLibros.get(i);
            nombresLibros.add(l.getTitulo());
            idLibros.add(l.getId());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1,nombresLibros);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Libro libro = libroDB.elemento(idLibros.get(position));
                Bundle bolsa = new Bundle();
                bolsa.putInt("id",libro.getId());
                bolsa.putString("titulo",libro.getTitulo());
                bolsa.putString("subtitulo",libro.getSubtitulo());
                bolsa.putString("autor",libro.getAutor());
                bolsa.putString("isbn",libro.getIsbn());
                bolsa.putInt("anio_publicacion",libro.getAnioPublicacion());
                bolsa.putDouble("precio",libro.getPrecio());

                Intent intent = new Intent(context, GestionarLibrosActivity.class);
                intent.putExtras(bolsa);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(String titulo) {

    }
}