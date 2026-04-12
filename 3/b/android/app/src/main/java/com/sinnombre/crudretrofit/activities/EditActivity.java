package com.sinnombre.crudretrofit.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.sinnombre.crudretrofit.MainActivity;
import com.sinnombre.crudretrofit.R;
import com.sinnombre.crudretrofit.dto.ProductDTO;
import com.sinnombre.crudretrofit.interfaces.CRUDInterface;
import com.sinnombre.crudretrofit.model.Product;
import com.sinnombre.crudretrofit.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditActivity extends AppCompatActivity {

    Product product;
    EditText nameText, priceText;
    Button editButton;

    CRUDInterface crudInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Intent detailIntent = getIntent();
        product =(Product) detailIntent.getSerializableExtra("product");
        priceText = findViewById(R.id.priceText);
        nameText = findViewById(R.id.nameText);
        editButton = findViewById(R.id.editButton);
        nameText.setText(product.getName());
        priceText.setText(String.valueOf(product.getPrice()));
        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editButton.setEnabled(buttonEnabled());
            }
        });

        priceText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                editButton.setEnabled(buttonEnabled());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
        editButton.setEnabled(buttonEnabled());
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDTO dto = new ProductDTO(nameText.getText().toString(),Integer.valueOf(priceText.getText().toString()));
                edit( dto);
            }
        });

    }



    private void edit(ProductDTO dto){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);
        int id = product.getId();
        Call<Product> call = crudInterface.edit(id,dto);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if(!response.isSuccessful()){
                    Toast toast = Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();
                    return;
                }

                Product product = response.body();
                Toast toast = Toast.makeText(getApplicationContext(),product.getName() + " Edited",Toast.LENGTH_LONG);
                toast.show();

                callMain();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }
    private void callMain(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean buttonEnabled() {
        return nameText.getText().toString().trim().length() > 0 && priceText.getText().toString().length() > 0;
    }
}
