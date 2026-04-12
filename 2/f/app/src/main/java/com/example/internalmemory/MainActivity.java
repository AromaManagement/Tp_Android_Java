package com.example.internalmemory;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText etId, etName, etPrice;
    private TextView tvDisplay;
    private ArrayList<Product> productList;
    private final String FILE_NAME = "products.dat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        tvDisplay = findViewById(R.id.tvDisplay);
        Button btnSave = findViewById(R.id.btnSave);

        // Load existing data from internal storage
        productList = loadProducts();
        updateDisplay();

        btnSave.setOnClickListener(v -> {
            String idStr = etId.getText().toString();
            String name = etName.getText().toString();
            String priceStr = etPrice.getText().toString();

            if (!idStr.isEmpty() && !name.isEmpty() && !priceStr.isEmpty()) {
                int id = Integer.parseInt(idStr);
                float price = Float.parseFloat(priceStr);

                Product newProduct = new Product(id, name, price);
                productList.add(newProduct);

                saveProducts(productList);
                updateDisplay();
                clearInputs();
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveProducts(ArrayList<Product> list) {
        try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(list);
        } catch (Exception e) {
            Toast.makeText(this, "Error saving", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Product> loadProducts() {
        try {
            FileInputStream fis = openFileInput(FILE_NAME);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (ArrayList<Product>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>(); // Return empty list if file doesn't exist
        }
    }

    private void updateDisplay() {
        StringBuilder sb = new StringBuilder();
        for (Product p : productList) {
            sb.append(p.toString()).append("\n");
        }
        tvDisplay.setText(sb.toString());
    }

    private void clearInputs() {
        etId.setText("");
        etName.setText("");
        etPrice.setText("");
    }
}