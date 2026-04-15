package com.tp1.appgrafico;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText etContarDatos;

    private Button btnIngresarDato;

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


        etContarDatos = findViewById(R.id.etContarDatos);

        btnIngresarDato = findViewById(R.id.btnIngresarDato);

        btnIngresarDato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresarDatos();
            }
        });

    }

    private void ingresarDatos(){
        String dataCountStr = etContarDatos.getText().toString();

        int dataCount = Integer.parseInt(dataCountStr);

        Intent intent = new Intent(MainActivity.this, PieChartActivity.class);
        intent.putExtra("contarDato", dataCount);
        startActivity(intent);
    }
}