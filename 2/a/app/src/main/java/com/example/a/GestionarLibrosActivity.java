package com.example.a;

import android.content.Context;
import android.content.Intent;
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

import com.example.a.controladores.LibroDB;
import com.example.a.modelos.Libro;

public class GestionarLibrosActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txttitulo, txtsubtitulo, txtisbn, txtautor,txtaniopublicacion,txtprecio;
    int id;
    LibroDB libroDB;

    Button btn_guardar
            ,btn_borrar,
            btn_actualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestionar_libros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }

    private void init(){
        context = getApplicationContext();
        txttitulo = findViewById(R.id.ges_txttitulo);
        txtsubtitulo = findViewById(R.id.ges_txtsubtitulo);
        txtautor = findViewById(R.id.ges_txtautor);
        txtisbn = findViewById(R.id.ges_txtisbn);
        txtaniopublicacion = findViewById(R.id.ges_txtaniopublicacion);
        txtprecio = findViewById(R.id.ges_txtprecio);
        btn_guardar = findViewById(R.id.ges_btnguardar);
        btn_actualizar = findViewById(R.id.ges_btnactualizar);
        btn_borrar = findViewById(R.id.ges_btnborrar);
        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        id = bolsa.getInt("id");
        if (id != 0){
            txttitulo.setText(bolsa.getString("titulo"));
            txtsubtitulo.setText(bolsa.getString("subtitulo"));
            txtautor.setText(bolsa.getString("autor"));
            txtisbn.setText(bolsa.getString("isbn"));
            txtaniopublicacion.setText(bolsa.getInt("anio_publicacion")+"");
            txtprecio.setText(bolsa.getDouble("precio")+"");
            btn_guardar.setEnabled(false);
        } else {
            btn_actualizar.setEnabled(false);
            btn_borrar.setEnabled(false);
        }
    }

    private void limpiarCampos(){
        id = 0;
        txttitulo.setText("");
        txtsubtitulo.setText("");
        txtautor.setText("");
        txtaniopublicacion.setText("");
        txtisbn.setText("");
        txtprecio.setText("");
    }

    private Libro llenarDatosLibro(){
        Libro libro = new Libro();
        String titulo = txttitulo.getText().toString();
        String subtitulo = txtsubtitulo.getText().toString();
        String autor = txtautor.getText().toString();
        String isbn = txtisbn.getText().toString();
        String anio = txtaniopublicacion.getText().toString();
        String precio = txtprecio.getText().toString();

        libro.setId(id);
        libro.setTitulo(titulo);
        libro.setSubtitulo(subtitulo);
        libro.setAutor(autor);
        libro.setIsbn(isbn);
        libro.setAnioPublicacion(Integer.parseInt(anio));
        libro.setPrecio(Double.parseDouble(precio));

        return libro;
    }

    private void guardar(){
        libroDB =new LibroDB(context,"LibrosDB.db",null,1);
        Libro libro = llenarDatosLibro();
        if (id == 0){
            libroDB.agregar(libro);
            btn_actualizar.setEnabled(true);
            btn_borrar.setEnabled(true);
            Toast.makeText(context,"Libro guardado",Toast.LENGTH_LONG).show();
        } else {
            libroDB.actualizar(id,libro);
            Toast.makeText(context,"Libro actualizado",Toast.LENGTH_LONG).show();
        }
    }

    private void borrar(){
        libroDB = new LibroDB(context,"LibrosDB.db",null,1);
        Libro libro = llenarDatosLibro();
        if (id != 0){
            libroDB.borrar(id);
            limpiarCampos();
            btn_guardar.setEnabled(true);
            Toast.makeText(context,"Libro borrado",Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,"El libro no existe",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.ges_btnguardar){
            Toast.makeText(context,"Guardar",Toast.LENGTH_LONG).show();
            guardar();
        } else if (id == R.id.ges_btnactualizar){
            Toast.makeText(context,"Actualizar",Toast.LENGTH_LONG).show();
            guardar();
        } else if (id == R.id.ges_btnborrar){
            Toast.makeText(context,"Borrar",Toast.LENGTH_LONG).show();
            borrar();
        }
    }


}