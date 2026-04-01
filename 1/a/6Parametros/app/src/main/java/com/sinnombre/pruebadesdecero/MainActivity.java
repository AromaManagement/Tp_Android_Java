package com.sinnombre.pruebadesdecero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText campoNombre;
    TextView etiquetaNombre;

    Button buttonIngresar, buttonEnviar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        campoNombre = findViewById(R.id.txtNombre);
        etiquetaNombre = findViewById(R.id.etiNombre);
        buttonIngresar = findViewById(R.id.btnIngresar);
        buttonEnviar = findViewById(R.id.btnEnviar);

        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if(id == R.id.btnIngresar){
                    String nombre = campoNombre.getText().toString();
                    etiquetaNombre.setText("Bienvenido: " +nombre);

                    Toast.makeText(v.getContext(),"El nombre es: "+nombre,Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MessageActivity.class);
                intent.putExtra("nombre",campoNombre.getText().toString());
                startActivity(intent);

            }
        });

    }


}