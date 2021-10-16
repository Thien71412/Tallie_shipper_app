package com.example.tallieshipper.services;

import com.example.tallieshipper.models.Order;
import com.example.tallieshipper.models.OrderList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface OrdersService {

    @Headers({"Content-Type: application/json"})
    @GET("api/orders/newly_created")
    Call<OrderList> newly_Created (@Header("X-Auth-Token") String jwt);
    
    @Headers({"Content-Type: application/json"})
    @GET("api/orders/has_taken")
    Call<OrderList> has_taken (@Header("X-Auth-Token") String jwt);
    
    @Headers({"Content-Type: application/json"})
    @GET("api/orders/is_delivering")
    Call<OrderList> delivering (@Header("X-Auth-Token") String jwt);
    
    @Headers({"Content-Type: application/json"})
    @GET("api/orders/is_delivered")
    Call<OrderList> delivered (@Header("X-Auth-Token") String jwt);


    // TODO: 7/10/2021 xu li order 
    @Headers({"Content-Type: application/json"})
    @GET("api/orders/{id}/has_taken")
    Call<Order> isHasTaken(@Header("X-Auth-Token") String jwt, @Path("id") String _id);

    @Headers({"Content-Type: application/json"})
    @GET("api/orders/{id}/is_delivering")
    Call<Order> isdelivering(@Header("X-Auth-Token") String jwt, @Path("id") String _id);

    @Headers({"Content-Type: application/json"})
    @GET("api/orders/{id}/is_delivered")
    Call<Order> isdelivered(@Header("X-Auth-Token") String jwt, @Path("id") String _id);


    @Headers({"Content-Type: application/json"})
    @GET("api/orders/{id}")
    Call<Order> detail(@Header("X-Auth-Token") String jwt, @Path("id") String _id);
}
