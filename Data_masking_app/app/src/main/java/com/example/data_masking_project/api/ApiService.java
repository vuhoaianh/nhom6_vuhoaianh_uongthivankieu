package com.example.data_masking_project.api;

import com.example.data_masking_project.model.LoginRequest;
import com.example.data_masking_project.model.LoginResponse;
import com.example.data_masking_project.model.User;
import com.example.data_masking_project.model.UserResponce;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.43.134:8082")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);
    @POST("/user")
    Call<UserResponce> addUser(@Body User user);
    @POST("/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
    @GET("/user/detail/{username}")
    Call<UserResponce> getProfileIn(@Path("username") String username);
}
