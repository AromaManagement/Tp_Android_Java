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

public class SecondaryActivity extends AppCompatActivity {


    TextInputEditText nombre,apellido,celular;
    Button btnVolver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_secondary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre=findViewById(R.id.nombre1);
        apellido=findViewById(R.id.apellido1);
        celular=findViewById(R.id.celular1);
        btnVolver=findViewById(R.id.btnVolver);

        nombre.setEnabled(false);
        apellido.setEnabled(false);
        celular.setEnabled(false);

        Bundle params = this.getIntent().getExtras();
        nombre.setText(params.getString("keyNombre"));
        apellido.setText(params.getString("keyApellido"));
        celular.setText(params.getString("keyCelular"));

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent= new Intent(getBaseContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
    }
}