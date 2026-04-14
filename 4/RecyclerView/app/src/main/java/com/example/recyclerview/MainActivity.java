package com.example.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Pais> listaPaises;
    Adaptador adaptador;
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
        listaPaises = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        adaptador= new Adaptador(listaPaises);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adaptador);

        for (int i = 0; i < 20; i++) {

            listaPaises.add(new Pais("El Salvador", "San Salvador", R.drawable.ic_launcher_background, "6.3", "América Central"));
            listaPaises.add(new Pais("México", "Ciudad de México", R.drawable.ic_launcher_background, "131", "Norte América"));
            listaPaises.add(new Pais("Guatemala", "Ciudad de Guatemala", R.drawable.ic_launcher_background, "18.4", "América Central"));
            listaPaises.add(new Pais("Honduras", "Tegucigalpa", R.drawable.ic_launcher_background, "10.8", "América Central"));
            listaPaises.add(new Pais("Nicaragua", "Managua", R.drawable.ic_launcher_background, "6.9", "América Central"));
            listaPaises.add(new Pais("Costa Rica", "San José", R.drawable.ic_launcher_background, "5.1", "América Central"));
            listaPaises.add(new Pais("Panamá", "Ciudad de Panamá", R.drawable.ic_launcher_background, "4.5", "América Central"));
            listaPaises.add(new Pais("Brasil", "Brasilia", R.drawable.br, "212", "Sur América"));
            listaPaises.add(new Pais("Argentina", "Buenos Aires", R.drawable.arg, "45.7", "Sur América"));
            listaPaises.add(new Pais("Colombia", "Bogotá", R.drawable.ic_launcher_background, "52.9", "Sur América"));
            listaPaises.add(new Pais("Perú", "Lima", R.drawable.ic_launcher_background, "34.2", "Sur América"));
            listaPaises.add(new Pais("Chile", "Santiago", R.drawable.ic_launcher_background, "19.7", "Sur América"));
            listaPaises.add(new Pais("Ecuador", "Quito", R.drawable.ic_launcher_background, "18.1", "Sur América"));
            listaPaises.add(new Pais("Bolivia", "Sucre", R.drawable.ic_launcher_background, "12.4", "Sur América"));
            listaPaises.add(new Pais("Paraguay", "Asunción", R.drawable.ic_launcher_background, "6.9", "Sur América"));
            listaPaises.add(new Pais("Uruguay", "Montevideo", R.drawable.ic_launcher_background, "3.4", "Sur América"));
            listaPaises.add(new Pais("Venezuela", "Caracas", R.drawable.ic_launcher_background, "28.4", "Sur América"));

        }

    }
}