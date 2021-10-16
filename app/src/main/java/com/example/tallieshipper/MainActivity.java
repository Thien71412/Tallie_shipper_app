package com.example.tallieshipper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallieshipper.utils.SharedPreferencesHandler;

public class MainActivity extends AppCompatActivity {

    //ShipperService shipperService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(ShipperService.class);

    LinearLayout gr_newly_Cr, gr_log_out,gr_taken_orders, gr_profile,gr_delivered, gr_delivering;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gr_newly_Cr =  findViewById(R.id.gr_newly_Cr);
        gr_log_out =  findViewById(R.id.gr_log_out);
        gr_taken_orders =  findViewById(R.id.gr_taken_orders);
        gr_profile = findViewById(R.id.gr_profile);
        gr_delivered = findViewById(R.id.gr_Delivered);
        gr_delivering = findViewById(R.id.gr_Delivering);

        gr_newly_Cr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewlyCreatedListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });

        gr_taken_orders.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, taken_orders_list.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        gr_delivering.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeliveringActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        gr_delivered.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DeliveredActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        gr_profile.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        gr_log_out.setOnClickListener(v -> {
            // xoa jwt == ghi de jwt == ""
            SharedPreferencesHandler.saveAppData(MainActivity.this, "");

            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}