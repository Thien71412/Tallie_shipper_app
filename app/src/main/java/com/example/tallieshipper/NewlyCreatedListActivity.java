package com.example.tallieshipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallieshipper.models.OrderList;
import com.example.tallieshipper.models.Order;
import com.example.tallieshipper.services.OrdersService;
import com.example.tallieshipper.utils.RetrofitClient;
import com.example.tallieshipper.utils.SharedPreferencesHandler;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewlyCreatedListActivity extends AppCompatActivity {

    OrdersService ordersService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(OrdersService.class);

    ListView list_newlyCreated;
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newly_created_list);

        img_back = findViewById(R.id.img_back);
        list_newlyCreated = findViewById(R.id.activity_list_newly_created);

        // TODO: 7/6/2021 binding data
        ordersService.newly_Created(SharedPreferencesHandler.loadAppData(this)).enqueue(new Callback<OrderList>() {
            @Override
            public void onResponse(Call<OrderList> call, Response<OrderList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    OrderList orderList = response.body();
                    List<Order> orders = orderList.getOrders();
                    list_newlyCreated.setAdapter(new OrderAdapter(NewlyCreatedListActivity.this, R.layout.list_item, orders));

                    list_newlyCreated.setOnItemClickListener((parent, view, position, id) -> {
                        Intent intent = new Intent(NewlyCreatedListActivity.this, NewlyCreatedDetailActivity.class);
                        intent.putExtra("order", orders.get(position));
                        startActivity(intent);
                    });
                } else {
                    try {
                        Toast.makeText(NewlyCreatedListActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
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
        img_back.setOnClickListener(v -> {
            Intent intent = new Intent(NewlyCreatedListActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}