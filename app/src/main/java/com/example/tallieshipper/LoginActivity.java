package com.example.tallieshipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tallieshipper.models.Shipper;
import com.example.tallieshipper.services.ShipperService;
import com.example.tallieshipper.utils.RetrofitClient;
import com.example.tallieshipper.utils.SharedPreferencesHandler;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    ShipperService shipperService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(ShipperService.class);

    Button bt_login;
    EditText et_phonenumber, et_password;
    TextView tv_Register;
    //int counter = 3;
    // TODO: tong ket cac buoc
    /**
     * b1: la khoi tao service     ShipperService shipperService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(ShipperService.class);
     * b2: la tao ham trong interface ShipperService
     *      @Headers({"Content-Type: application/json"})
     *     @POST("api/auth")
     *     Call<String> loginShipper(@Body Shipper shipper)
     *
     * b3: thuc thi ham .enqueue(new Callback<String>() {
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // neu shipper login r thi lan ke tiep mo app khong can login nua
        if (SharedPreferencesHandler.isLoggedIn(this)) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

        bt_login = findViewById(R.id.bt_login);
        et_phonenumber = findViewById(R.id.et_phonenumber);
        et_password = findViewById(R.id.et_password);
        tv_Register = findViewById(R.id.tv_Register);

        bt_login.setOnClickListener(v -> {
            shipperService.loginShipper(new Shipper(et_phonenumber.getText().toString(), et_password.getText().toString())).enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        String jwt = response.body();

                        // neu login lan dau thi se luu vao dthoai
                        SharedPreferencesHandler.saveAppData(LoginActivity.this, jwt);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        try {
                            Toast.makeText(LoginActivity.this , response.errorBody().string() , Toast.LENGTH_SHORT).show();

                            Log.e("ERROR", "onResponse: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                    Log.e("ERROR", "onFailure: " + t.getMessage());
                }
            });
        });

        tv_Register.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            });
        }
}

