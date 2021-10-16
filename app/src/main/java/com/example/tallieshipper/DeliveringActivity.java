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

public class DeliveringActivity extends AppCompatActivity {

    OrdersService ordersService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(OrdersService.class);

    ListView list_Delivering;
    ImageView img_back5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivering);

        img_back5 = findViewById(R.id.img_back5);
        list_Delivering = findViewById(R.id.list_delivering);

        // TODO: 7/6/2021 binding data
        ordersService.delivering(SharedPreferencesHandler.loadAppData(this)).enqueue(new Callback<OrderList>() {
            @Override
            public void onResponse(Call<OrderList> call, Response<OrderList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    OrderList orderList = response.body();
                    List<Order> orders = orderList.getOrders();
                    list_Delivering.setAdapter(new OrderAdapter(DeliveringActivity.this, R.layout.list_item, orders));

                    list_Delivering.setOnItemClickListener((parent, view, position, id) -> {
                        Intent intent = new Intent(DeliveringActivity.this, Delivering_detail.class);
                        intent.putExtra("order", orders.get(position));
                        startActivity(intent);
                    });
                } else {
                    try {
                        Toast.makeText(DeliveringActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
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
        img_back5.setOnClickListener(v -> {
            Intent intent = new Intent(DeliveringActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}
