package com.example.tallieshipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallieshipper.models.Shipper;
import com.example.tallieshipper.services.ShipperService;
import com.example.tallieshipper.utils.RetrofitClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    ShipperService shipperService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(ShipperService.class);

    Button bt_register;

    ImageView bt_back;

    EditText et_phonenumber, et_password, et_confirmpassword, et_name, et_nationalId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Shipper shipper = (Shipper) getIntent().getSerializableExtra("shipper");

        bt_register = findViewById(R.id.bt_register);
        bt_back = findViewById(R.id.bt_back);
        et_phonenumber = findViewById(R.id.et_phonenumber);
        et_password = findViewById(R.id.et_password);
        et_name = findViewById(R.id.et_name);
        et_nationalId = findViewById(R.id.et_nationalId);
        et_confirmpassword= findViewById(R.id.et_Confirmpassword);

        bt_register.setOnClickListener(v -> {
            shipperService.registerShipper(new Shipper(et_phonenumber.getText().toString(),
                    et_password.getText().toString(),
                    et_name.getText().toString(),
                    et_nationalId.getText().toString())).enqueue(new Callback<Shipper>() {
                @Override
                public void onResponse(@NonNull Call<Shipper> call, @NonNull Response<Shipper> response) {
                    if (response.isSuccessful() && response.body() != null && validate() == true ) {

                        Toast.makeText(RegisterActivity.this , "Account successfully created" , Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else if (validate() == false){

                        Toast.makeText(RegisterActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();

                    }
                    else {
                        try {
                            Toast.makeText(RegisterActivity.this , response.errorBody().string() , Toast.LENGTH_SHORT).show();
                            Log.e("ERROR", "onResponse: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Shipper> call, @NonNull Throwable t) {
                    Log.e("ERROR", "onFailure: " + t.getMessage());
                }

                private Boolean validate(){
                    boolean temp = true;

                    String Pass = et_password.getText().toString();
                    String CPass = et_confirmpassword.getText().toString();
                    if(!Pass.equals(CPass)){

                        temp=false;
                    }
                    return temp;
                }

            });
        });

        bt_back.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
