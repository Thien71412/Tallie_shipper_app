package com.example.tallieshipper.services;

import com.example.tallieshipper.models.Profile;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

public interface ProfileService {

    @Headers({"Content-Type: application/json"})
    @GET("api/shippers/me")
    Call<Profile> PROFILE_CALL (@Header("X-Auth-Token") String jwt);
}
