package com.tp1.appgrafico;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    private PieChart pieChart;

    private LinearLayout llInputs;

    private Button btnAceptar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pie_chart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pieChart = findViewById(R.id.pieChart);
        llInputs = findViewById(R.id.llinputs);
        btnAceptar = findViewById(R.id.btnAceptar);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarGraficoPastel();
            }
        });

        int dataCount = getIntent().getIntExtra("contarDato", 0);
        configurarEntradas(dataCount);
    }

    private void configurarEntradas(int dataCount){
        for (int i =0; i< dataCount; i++){
            EditText editText = new EditText(this);
            editText.setHint("Valor" + (i+1));

            llInputs.addView(editText);
        }
    }

    private void mostrarGraficoPastel() {
        // Array para las entradas del grafico
        ArrayList<PieEntry> entradas = new ArrayList<>();

        //Agregamos cada entrada al grafico
        for (int i = 0; i < llInputs.getChildCount(); i++){
            EditText editText = (EditText) llInputs.getChildAt(i);
            entradas.add(new PieEntry(Float.parseFloat(editText.getText().toString())));
        }

        //Definimos los datos que tendra mi grafico
        PieDataSet dataSet = new PieDataSet(entradas, "Datos");

        //Colores del grafico
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //Pasar info al grafico
        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());

        pieChart.setData(data);
        pieChart.invalidate();
    }
}