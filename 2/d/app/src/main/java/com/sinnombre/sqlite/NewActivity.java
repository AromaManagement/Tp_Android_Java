package com.sinnombre.sqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sinnombre.sqlite.db.DbContactos;

public class NewActivity extends AppCompatActivity {

    EditText nombre,telefono,correo;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.txtNombre);
        telefono = findViewById(R.id.txtTelefono);
        correo = findViewById(R.id.txtCorreoElectronico);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(NewActivity.this);
                long id = dbContactos.insertarContacto(nombre.getText().toString(),telefono.getText().toString(),correo.getText().toString());

                if(id > 0){
                    Toast.makeText(NewActivity.this,"REGISTRO GUARDADO",Toast.LENGTH_LONG).show();
                    limpiar();
                }else {
                    Toast.makeText(NewActivity.this,"ERROR AL GUARDAR REGISTRO",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    private  void limpiar(){
        nombre.setText("");
        telefono.setText("");
        correo.setText("");
    }
}