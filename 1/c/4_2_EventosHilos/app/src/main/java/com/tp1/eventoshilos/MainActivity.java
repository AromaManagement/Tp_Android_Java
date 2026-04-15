package com.tp1.eventoshilos;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView txvMensaje;
    private Button btnIniciar;
    private Button btnDetener;
    private Integer suma = 0;

    private boolean running = false;

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


        txvMensaje = findViewById(R.id.txvMensaje);
        btnIniciar =findViewById(R.id.btnIniciar);
        btnDetener = findViewById(R.id.btnDetener);

        findViewById(R.id.txtTexto).setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN){
                    txvMensaje.setText("Tecla presionada " + event.getKeyCode());
                    return true;
                }
                return false;
            }
        });

        txvMensaje.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        txvMensaje.setText("Tocaste la pantalla");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        txvMensaje.setText("Moviendo el dedo por la pantalla");
                        break;
                    case MotionEvent.ACTION_UP:
                        txvMensaje.setText("Levantaste el texto de la pantalla");
                        break;
                }
                return false;
            }
        });

        //Uso de hilos
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running =true;
                new Thread(new Runnable() {
                    @Override
                    public void run() {


                            try{
                                for (int i = 0; i <= 5000; i++){
                                    suma =+ i;
                                }

                            } catch(Exception e){
                                e.printStackTrace();
                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    txvMensaje.setText("La suma total fue: "+ suma.toString());
                                }
                            });


                    }
                }).start();
            }
        });

        btnDetener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                txvMensaje.setText("Hilo de ejecución detenido");
            }
        });

    }
}