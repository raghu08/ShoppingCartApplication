package com.zooplus.ui.products.adapter;

import com.zooplus.entities.Products;

import java.util.LinkedHashSet;

/**
 * Created by raghavendramalgi on 25/07/17.
 */

public class ProductsSelected {
    public LinkedHashSet<Products> productSet = new LinkedHashSet<>();
    public float sum;
    public boolean isRefresh;
    private static final ProductsSelected ourInstance = new ProductsSelected();

    public static ProductsSelected getInstance() {
        return ourInstance;
    }

    private ProductsSelected() {
    }



}
