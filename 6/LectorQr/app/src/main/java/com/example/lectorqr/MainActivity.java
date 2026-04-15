package com.example.lectorqr;

import android.content.Intent;import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;import com.google.zxing.integration.android.IntentResult;import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button btnGenerar, btnScanear;

    private ImageView imgCodigoQr;

    private EditText txtcodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGenerar = findViewById(R.id.btGenerar);
        btnScanear = findViewById(R.id.bScanear);
        imgCodigoQr = findViewById(R.id.ivCodigoQR);
        txtcodigo = findViewById(R.id.etCodigo);

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    BarcodeEncoder codigo_qr = new BarcodeEncoder();
                    Bitmap bitmap = codigo_qr.encodeBitmap(txtcodigo.getText().toString(), BarcodeFormat.QR_CODE, 800, 800);
                    imgCodigoQr.setImageBitmap(bitmap);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnScanear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lectura de codigos");
                integrator.setCameraId(0);
                integrator.setOrientationLocked(false);
                integrator.setCaptureActivity(CapturaVertical.class);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {

            if (result.getContents()==null) {
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_SHORT).show();
            } else {
                txtcodigo.setText(result.getContents());
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


}