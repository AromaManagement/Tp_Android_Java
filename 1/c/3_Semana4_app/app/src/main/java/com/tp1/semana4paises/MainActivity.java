package com.tp1.semana4paises;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity  {

    private ListView lsvPaises;

    private String[] paises = new String[]{"Guatemala", "Argentina", "China", "Perú", "Costa Rica"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lsvPaises = findViewById(R.id.lsvPaises);

        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1, paises);

        lsvPaises.setAdapter(adapter);

        lsvPaises.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Usted ha presionado la fila:" + (int) (position + 1), Toast.LENGTH_LONG).show();

                String paisSeleccionado = paises[position];

                if(paisSeleccionado.equals("Argentina")){
                    Intent intent = new Intent(MainActivity.this, Provincias.class);
                    startActivity(intent);
                }
            }
        });
    }

}