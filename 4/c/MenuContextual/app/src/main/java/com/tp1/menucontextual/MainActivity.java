package com.tp1.menucontextual;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lista;
    EditText texto;
    Button btnAgregar;
    ArrayList<String> listado;
    ArrayAdapter<String> adapter;

    int posicionLista;



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

        listado = new ArrayList<>();
        lista = findViewById(R.id.lista);
        texto = findViewById(R.id.texto);
        btnAgregar = findViewById(R.id.btnAgregar);

        registerForContextMenu(lista);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (texto.getText().toString().isEmpty()){
                    texto.setError("");
                }
                else {
                    if(btnAgregar.getText().toString().equals("Agregar")){
                        listado.add(texto.getText().toString());
                        llenarLista();
                        texto.setText("");
                        texto.requestFocus();
                    } else{
                        listado.set(posicionLista, texto.getText().toString());
                        llenarLista();
                        texto.setText("");
                        texto.requestFocus();
                        btnAgregar.setText("Agregar");
                    }
                }


            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.lista){
            posicionLista = ((AdapterView.AdapterContextMenuInfo) menuInfo).position;
            menu.setHeaderTitle(lista.getAdapter().getItem(posicionLista).toString());
            this.getMenuInflater().inflate(R.menu.menu_main, menu);
        }
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.opEditar){
            texto.setText(adapter.getItem(posicionLista));
            texto.setSelection(texto.getText().length());
            btnAgregar.setText("Modificar");
        }
        if(item.getItemId() == R.id.opEliminar){
            msg("Desea eliminar el elemento");
        }
        return super.onContextItemSelected(item);
    }

    private void msg(String mensaje) {
        AlertDialog.Builder dialogo = new AlertDialog.Builder(this);
        dialogo.setTitle("Menu Contextual");
        dialogo.setMessage(mensaje);
        dialogo.setIcon(R.drawable.baseline_announcement_24);
        dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                listado.remove(posicionLista);
                llenarLista();

            }
        });
        dialogo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


            }
        });
        dialogo.show();

    }

    private void llenarLista() {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listado);
        lista.setAdapter(adapter);

    }
}