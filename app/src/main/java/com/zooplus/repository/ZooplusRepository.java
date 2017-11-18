package com.zooplus.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.zooplus.api.ZooplusCartApiService;
import com.zooplus.entities.ApiResponse;
import com.zooplus.entities.Order;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by raghavendramalgi on 25/07/17.
 */


public class ZooplusRepository {


    public LiveData<ApiResponse> getProductList(){
        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();
        ZooplusCartApiService apiService = RetrofitClient.getInstance().getZooPlusApiService();
        Call<ApiResponse> call = apiService.getProducts(RetrofitClient.BASE_URL);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                liveData.setValue(response.body());

            }
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                //liveData.setValue(response.body());
            }

        });

        return  liveData;
    }

    public LiveData<Order> checkOut(){
        final MutableLiveData<Order> liveData = new MutableLiveData<>();
        ZooplusCartApiService apiService = RetrofitClient.getInstance().getZooPlusApiService();
        Call<Order> call = apiService.checkOut("http://10.0.2.2:3000/orders/");
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                liveData.setValue(response.body());

            }
            public void onFailure(Call<Order> call, Throwable t) {
                //liveData.setValue(response.body());
            }

        });

        return  liveData;
    }



}
