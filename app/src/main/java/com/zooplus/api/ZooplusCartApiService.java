package com.zooplus.api;

import com.zooplus.entities.ApiResponse;
import com.zooplus.entities.Order;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Url;


public interface ZooplusCartApiService {
    @GET
    Call<ApiResponse> getProducts(@Url String url);
    @PUT
    Call<Order> checkOut(@Url String url);
}
