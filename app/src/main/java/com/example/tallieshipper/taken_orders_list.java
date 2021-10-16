package com.example.tallieshipper;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class taken_orders_list extends AppCompatActivity {

    OrdersService ordersService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(OrdersService.class);

    ListView list_hasTaken;
    ImageView img_back2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.taken_order);

        img_back2 = findViewById(R.id.img_back2);
        list_hasTaken = findViewById(R.id.list_hasTaken);

        // TODO: 7/6/2021 binding data
        ordersService.has_taken(SharedPreferencesHandler.loadAppData(this)).enqueue(new Callback<OrderList>() {
            @Override
            public void onResponse(Call<OrderList> call, Response<OrderList> response) {
                if (response.isSuccessful() && response.body() != null) {
                    OrderList orderList = response.body();
                    List<Order> orders = orderList.getOrders();

                    list_hasTaken.setAdapter(new OrderAdapter(taken_orders_list.this, R.layout.list_item, orders));
                    list_hasTaken.setOnItemClickListener((parent, view, position, id) -> {
                        Intent intent = new Intent(taken_orders_list.this, taken_order_detail.class);
                        intent.putExtra("order", orders.get(position));
                        startActivity(intent);
                    });
                } else {
                    try {
                        Toast.makeText(taken_orders_list.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
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

        img_back2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(taken_orders_list.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}