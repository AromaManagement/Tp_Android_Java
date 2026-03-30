package com.sinnombre.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sinnombre.sqlite.db.DbContactos;
import com.sinnombre.sqlite.entidades.Contactos;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar;

    FloatingActionButton fabEditar,fabEliminar;
    Contactos contacto;

    boolean correcto = false;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        btnGuardar = findViewById(R.id.btnGuardar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else {
                id = extras.getInt("id");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("id");
        }

        DbContactos dbContactos = new DbContactos(EditarActivity.this);
        contacto = dbContactos.verContacto(id);

        if(contacto != null){
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelfono());
            txtCorreo.setText(contacto.getCorreo());
        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!txtNombre.getText().toString().isEmpty() && !txtTelefono.getText().toString().isEmpty()){
                    correcto = dbContactos.editarContacto(id,txtNombre.getText().toString(),txtTelefono.getText().toString(),txtCorreo.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this,"REGISTRO MODIFICADO",Toast.LENGTH_LONG).show();
                        verRegistro();
                    }else {
                        Toast.makeText(EditarActivity.this,"ERROR AL MODIFICAR REGISTRO",Toast.LENGTH_LONG).show();

                    }
                }else {
                    Toast.makeText(EditarActivity.this,"DEBE LLENAR LOS CAMPOS OBLIGATORIOS",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this,ViewActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
        finish();
    }
}
