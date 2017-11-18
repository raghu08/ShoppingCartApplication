package com.zooplus.ui.products;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.zooplus.entities.ApiResponse;
import com.zooplus.repository.ZooplusRepository;


/**
 * Created by raghavendramalgi on 25/07/17.
 */

public class ProductListViewModel extends ViewModel {

    private ZooplusRepository movieRepo;
    private MediatorLiveData<ApiResponse> mApiResponse;

    public ProductListViewModel() {
        movieRepo = new ZooplusRepository();
        mApiResponse = new MediatorLiveData<>();
    }


    public LiveData<ApiResponse> loadProductList() {
        mApiResponse.addSource(
                movieRepo.getProductList(),
                apiResponse -> mApiResponse.setValue(apiResponse)
        );
        return mApiResponse;
    }
}
