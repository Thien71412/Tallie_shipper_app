package com.example.tallieshipper;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tallieshipper.models.Order;
import com.example.tallieshipper.services.OrdersService;
import com.example.tallieshipper.utils.RetrofitClient;
import com.example.tallieshipper.utils.SharedPreferencesHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewlyCreatedDetailActivity extends AppCompatActivity {

    OrdersService ordersService = RetrofitClient.getInStances("https://tallie-shipping.herokuapp.com/").create(OrdersService.class);

    Date date;
    String dt;

    ImageView bt_closeDetail;
    Button bt_taken;
    TextView id_order;
    TextView time_created;
    TextView tv_estimatedDateArrival;
    TextView tv_userId;
    TextView tv_productId;
    TextView tv_recipientName;
    TextView tv_recipientPhone;
    TextView tv_deliverTo;
    // lan sau ong tao thi man hinh android theo convention la <Ten>Activity, layout tuong ung la activity_<ten> nhe cho de nhin ok o
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        bt_taken = findViewById(R.id.bt_taken);
        bt_closeDetail = findViewById(R.id.bt_closeDetail);
        id_order = findViewById(R.id.id_order);
        time_created = findViewById(R.id.time_created);
        tv_estimatedDateArrival = findViewById(R.id.tv_estimatedDateArrival);
        tv_userId = findViewById(R.id.tv_userId);
        tv_productId = findViewById(R.id.tv_productId);
        tv_recipientName = findViewById(R.id.tv_recipientName);
        tv_recipientPhone = findViewById(R.id.tv_recipientPhone);
        tv_deliverTo = findViewById(R.id.tv_deliverTo);

        // TODO: 7/6/2021 binding data to Textview

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        Order order = (Order) getIntent().getSerializableExtra("order");

        ordersService.detail(SharedPreferencesHandler.loadAppData(this),String.valueOf(order.get_id()) ).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful() && response.body() != null){
                    //if (order == null) return;
                    Date starDate = null;
                    try {
                        starDate = sdf.parse(response.body().getestimatedDateArrival());

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    id_order.setText(order.get_id());
                    time_created.setText(order.getCreatedAt());
                    tv_estimatedDateArrival.setText(sdf.format(starDate));
                    tv_userId.setText(String.valueOf(order.getUserId()));
                    tv_productId.setText(String.valueOf(order.getProductId()));
                    tv_recipientName.setText(response.body().getRecipientName());
                    tv_recipientPhone.setText(response.body().getRecipientPhone());
                    tv_deliverTo.setText(response.body().getDeliverTo());
                    //Log.e("Tag", "time" + response.body().getestimatedDateArrival() );
                }
            }
            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Toast.makeText(NewlyCreatedDetailActivity.this , t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Tag", "onFailure" + t.getMessage());
            }
        });

        // TODO: 7/6/2021 handler events
        bt_closeDetail.setOnClickListener(v -> finish());
        bt_taken.setOnClickListener(v ->
                ordersService.isHasTaken(SharedPreferencesHandler.loadAppData(this), String.valueOf(order.get_id())).enqueue(new Callback<Order>(){
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        if (response.isSuccessful() && response.body() != null){
                            Toast.makeText(NewlyCreatedDetailActivity.this , "Product has been taken" , Toast.LENGTH_SHORT).show();

                            //change color button if call api Successfully
                            bt_taken.setBackgroundDrawable(getResources().getDrawable(R.drawable.dash_bg4));

                        }
                    }
                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        Toast.makeText(NewlyCreatedDetailActivity.this , t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Tag", "onFailure" + t.getMessage());
                    }
                }));



    }
}


