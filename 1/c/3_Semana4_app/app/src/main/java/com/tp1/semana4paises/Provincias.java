package com.tp1.semana4paises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class Provincias extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lsvProvincias;

    private Button btnRegresar, btnSalida;

    private List<Modelo> mList = new ArrayList<>();

    ListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_departamentos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lsvProvincias = findViewById(R.id.lsvProvincias);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnSalida = findViewById(R.id.btnSalida);

        lsvProvincias.setOnItemClickListener(this);
        mList.add(new Modelo("Mendoza","Ciudad del sol y del buen vino.", R.drawable.potre));
        mList.add(new Modelo("Buenos Aires","Capital de Argentina", R.drawable.baires));
        mList.add(new Modelo("Jujuy","Una joyita de lugar", R.drawable.jujuy));

        mAdapter = new Adapter(Provincias.this, R.layout.lugares, mList);

        lsvProvincias.setAdapter(mAdapter);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Provincias.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Elemento Seleccionado: " + (int) (position+1), Toast.LENGTH_SHORT).show();
    }
}