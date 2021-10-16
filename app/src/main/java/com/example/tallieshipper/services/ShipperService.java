package com.example.tallieshipper.services;

import com.example.tallieshipper.models.Shipper;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ShipperService {

    // TODO: thu 1:ong nhin ben response no tra ve <JWT> ma jWT no la 1 String nen minh se Call<String>
    // TODO: thu 2: tk Retrofit no se tu convert JSON sang GSON nen minh phai tao 1 doi tuong Java giong voi response va request cung the
    @Headers({"Content-Type: application/json"})
    @POST("api/auth")
    Call<String> loginShipper(@Body Shipper shipper);

    @Headers({"Content-Type: application/json"})
    @POST("api/shippers")
    Call<Shipper> registerShipper(@Body Shipper shipper);

    @Headers({"Content-Type: application/json"})
    @GET("api/shippers/me")
    Call<Shipper> PROFILE_CALL (@Header("X-Auth-Token") String jwt);
}
