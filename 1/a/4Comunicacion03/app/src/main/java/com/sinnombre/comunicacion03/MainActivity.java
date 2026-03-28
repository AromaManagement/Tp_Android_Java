package com.sinnombre.comunicacion03;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    TextInputEditText nombre,apellido,celular;
    Button btnEnviar;
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

        nombre=findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        celular=findViewById(R.id.celular);
        btnEnviar=findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().isEmpty()){
                    nombre.setError("");
                }else {
                    if(apellido.getText().toString().isEmpty()){
                        apellido.setError("");
                    }else {
                        if(celular.getText().toString().isEmpty()){
                            celular.setError("");
                        }else {
                            finish();
                            Intent intent= new Intent(getBaseContext(), SecondaryActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            intent.putExtra("keyNombre",nombre.getText().toString());
                            intent.putExtra("keyApellido",apellido.getText().toString());
                            intent.putExtra("keyCelular",celular.getText().toString());
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}