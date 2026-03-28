package com.sinnombre.calculosalario;

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

    TextInputEditText salario,isss,afp,renta,liquido;
    Button btnCalcular,btnNuevo;

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

        salario=findViewById(R.id.salario);
        isss=findViewById(R.id.isss);
        afp=findViewById(R.id.afp);
        renta=findViewById(R.id.renta);
        liquido=findViewById(R.id.liquido);
        btnCalcular=findViewById(R.id.btnCalcular);
        btnNuevo=findViewById(R.id.btnNuevo);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(salario.getText().toString().isEmpty()){
                    salario.setError(getResources().getString(R.string.errorSalario));
                }else {
                    try {
                        double sueldo = Double.parseDouble(salario.getText().toString());

                        double calculosISSS = 0.00;
                        if( sueldo > 1000) calculosISSS = 30.00;
                        else {
                            calculosISSS = sueldo * 0.03;
                        }

                        double calculoAfp = 0.00;
                        calculoAfp = sueldo * 0.0725;

                        double calculoRenta = 0.00;

                        sueldo = sueldo - (calculosISSS + calculoAfp);
                        if(sueldo <= 472.00){
                            calculoRenta = 0.00;
                        } else if (sueldo <= 895.24) {
                            calculoRenta = 17.67 + (sueldo - 472.01) * 0.10;
                        } else if (sueldo <= 2038.10) {
                            calculoRenta = 60.00 + (sueldo - 895.25) * 0.20;
                        } else {
                            calculoRenta = 288.57 + (sueldo - 2038.11) * 0.30;
                        }

                        isss.setText(String.valueOf(Math.round(calculosISSS * 100.0) / 100.0));
                        afp.setText(String.valueOf(Math.round(calculoAfp * 100.0) / 100.0));
                        renta.setText(String.valueOf(Math.round(calculoRenta * 100.0) / 100.0));
                        liquido.setText(String.valueOf(Math.round((sueldo - calculoRenta) * 100.0) / 100.0));
                    }catch (Exception ex){
                        salario.setError(getResources().getString(R.string.errorSalarioIncorrecto));
                    }

                }
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salario.setText("");
                isss.setText("");
                afp.setText("");
                renta.setText("");
                liquido.setText("");
            }
        });
    }
}