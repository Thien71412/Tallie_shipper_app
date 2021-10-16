package com.example.tallieshipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallieshipper.models.Order;
import com.example.tallieshipper.models.OrderList;
import com.example.tallieshipper.services.OrdersService;
import com.example.tallieshipper.utils.RetrofitClient;
import com.example.tallieshipper.utils.SharedPreferencesHandler;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveredActivity extends AppCompatActivity {

    OrdersService ordersService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(OrdersService.class);

    ListView list_Delivered;
    ImageView img_back3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivered);

        img_back3 = findViewById(R.id.img_back3);
        list_Delivered = findViewById(R.id.list_Delivered);

        // TODO: 7/6/2021 binding data
        ordersService.delivered(SharedPreferencesHandler.loadAppData(this)).enqueue(new Callback<OrderList>() {
            @Override
            public void onResponse(Call<OrderList> call, Response<OrderList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    OrderList orderList = response.body();
                    List<Order> orders = orderList.getOrders();
                    list_Delivered.setAdapter(new OrderAdapter(DeliveredActivity.this, R.layout.list_item, orders));

                    list_Delivered.setOnItemClickListener((parent, view, position, id) -> {
                        Intent intent = new Intent(DeliveredActivity.this, Delivered_detail.class);
                        intent.putExtra("order", orders.get(position));
                        startActivity(intent);
                    });
                } else {
                    try {
                        Toast.makeText(DeliveredActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("TAG", "onResponse: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<OrderList> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

        // TODO: 7/6/2021 handler events
        img_back3.setOnClickListener(v -> {
            Intent intent = new Intent(DeliveredActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
