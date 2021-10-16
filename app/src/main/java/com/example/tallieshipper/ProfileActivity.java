package com.example.tallieshipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallieshipper.models.Shipper;
import com.example.tallieshipper.services.ShipperService;
import com.example.tallieshipper.utils.RetrofitClient;
import com.example.tallieshipper.utils.SharedPreferencesHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {

    ShipperService shipperService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(ShipperService.class);

    TextView tv_IdShipper;
    TextView tv_Name_Shipper;
    TextView tv_nationalIdShipper;
    TextView tv_phoneShipper;

    ImageView bt_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        tv_IdShipper = findViewById(R.id.tv_IdShipper);
        tv_Name_Shipper = findViewById(R.id.tv_Name_Shipper);
        tv_nationalIdShipper = findViewById(R.id.tv_nationalIdShipper);
        tv_phoneShipper = findViewById(R.id.tv_phoneShipper);


        bt_back= findViewById(R.id.bt_back);

        Shipper shipper = (Shipper) getIntent().getSerializableExtra("shipper");

        shipperService.PROFILE_CALL(SharedPreferencesHandler.loadAppData(this)).enqueue(new Callback<Shipper>(){

            @Override
            public void onResponse(Call<Shipper> call, Response<Shipper> response) {
                if (response.isSuccessful() && response.body() != null){
                    response.body();
                    tv_IdShipper.setText(response.body().get_id());
                    tv_Name_Shipper.setText(response.body().getName());
                    tv_nationalIdShipper.setText(response.body().getNationalId());
                    tv_phoneShipper.setText(response.body().getPhone());
                }
            }

            @Override
            public void onFailure(Call<Shipper> call, Throwable t) {
                Log.e("ERROR", "onFailure: " + t.getMessage());
            }
        });

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}