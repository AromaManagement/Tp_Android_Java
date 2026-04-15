package com.tp1.blocknotas;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
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

    private TextView txvNotas;
    private EditText txtNuevaNota;
    private Button btnGuardar;
    private static final String PREFERENCIAS_NOTAS = "MisNotas";

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

        txtNuevaNota =findViewById(R.id.txtNuevaNota);
        txvNotas =findViewById(R.id.txvNotas);
        btnGuardar =findViewById(R.id.btnGuardar);

        cargarNotas();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNota();
            }
        });

        txtNuevaNota.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER){
                    guardarNota();
                    return true;
                }
                return false;
            }
        });
    }

    private void cargarNotas() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCIAS_NOTAS, Context.MODE_PRIVATE);
        String notasGuardadas = sharedPreferences.getString("nota", "");
        txtNuevaNota.setText(notasGuardadas);
        txvNotas.setText("Nota Guardada: " + notasGuardadas);
    }

    private void guardarNota() {
        String nota = txtNuevaNota.getText().toString().trim();

        if(!nota.isEmpty()){
            // Guardar nota usando SharedPreferences
            SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCIAS_NOTAS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nota", nota);
            editor.apply();

            //Mostrar nota guardada
            txvNotas.setText("Nota guardada: "+ nota);
            Toast.makeText(this, "Nota guardada: "+ nota, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "La nota está vacía", Toast.LENGTH_SHORT).show();

        }
    }
}