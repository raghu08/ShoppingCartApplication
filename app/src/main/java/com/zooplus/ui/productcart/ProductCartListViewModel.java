package com.zooplus.ui.productcart;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.ViewModel;

import com.zooplus.entities.Order;
import com.zooplus.repository.ZooplusRepository;


/**
 * Created by raghavendramalgi on 25/07/17.
 */

public class ProductCartListViewModel extends ViewModel {

    private ZooplusRepository zooPlusRepo;
    private MediatorLiveData<Order> mApiResponse;

    public ProductCartListViewModel() {
        zooPlusRepo = new ZooplusRepository();
        mApiResponse = new MediatorLiveData<>();
    }


    public LiveData<Order> checkOut() {
        mApiResponse.addSource(
                zooPlusRepo.checkOut(),
                order -> mApiResponse.setValue(order)
        );
        return mApiResponse;
    }
}
