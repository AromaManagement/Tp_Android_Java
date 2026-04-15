package com.example.qrgenerate;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;

import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.google.zxing.BarcodeFormat;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText txtDatos = findViewById(R.id.txtDatos);
        Button btnGenera = findViewById(R.id.btnGenera);
        ImageView imgQr = findViewById(R.id.qrCode);

        btnGenera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    /*
                    * "tel:" +txtDatos.getText().toString(),
                    * "SMSTO:2634549797:" +txtDatos.getText().toString(),
                    * "WIFI:S:CDP;T:WEP;P:123456789;" +txtDatos.getText().toString(),
                    * */
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(
                            txtDatos.getText().toString(), // <-
                            BarcodeFormat.QR_CODE,
                            750,
                            750
                    );
                    imgQr.setImageBitmap(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}