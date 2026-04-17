package com.example.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btnListar, btnRegistrar, btnBuscar;


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
        init();
    }

    private void init(){
        context = getApplicationContext();
        btnRegistrar = findViewById(R.id.btnregistrar);
        btnListar = findViewById(R.id.btnlistar);
        btnBuscar = findViewById(R.id.btnbuscar);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnbuscar){
            Toast.makeText(context,"Buscar",Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, BuscarLibroActivity.class);
            startActivity(i);
        } else if (id == R.id.btnlistar){
            Toast.makeText(context,"Listar",Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, ListadoLibrosActivity.class);
            startActivity(i);
        } else if (id == R.id.btnregistrar){
            Toast.makeText(context,"Registrar",Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, GestionarLibrosActivity.class);
            Bundle bolsa = new Bundle();
            bolsa.putInt("id",0);
            i.putExtras(bolsa);
            startActivity(i);
        }

    }
}