package com.zooplus.repository;

import com.zooplus.api.ZooplusCartApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

  private static RetrofitClient instance = null;
  private Retrofit retrofit;
  private OkHttpClient client;

  private ZooplusCartApiService zooPlusApiService;

  public static final String BASE_URL =  "http://10.0.2.2:3000/products/";

  public RetrofitClient() {

    HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
    loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
    okHttpBuilder.addInterceptor(loggingInterceptor);
    client = okHttpBuilder.build();
    retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build();

    zooPlusApiService = retrofit.create(ZooplusCartApiService.class);
  }

  public static RetrofitClient getInstance() {
    if (instance == null) {
      instance = new RetrofitClient();
    }

    return instance;
  }

  public ZooplusCartApiService getZooPlusApiService() {
    return zooPlusApiService;
  }
}