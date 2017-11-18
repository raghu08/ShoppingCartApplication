package com.zooplus.ui.productcart;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zooplus.R;
import com.zooplus.entities.Products;
import com.zooplus.ui.productcart.adapter.ProductCartListAdapter;
import com.zooplus.ui.products.adapter.ProductsSelected;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProductCartListActivityFragment extends LifecycleFragment {

    private ProductCartListAdapter adapter;
    private RecyclerView rv;
    private TextView proceed;
    private TextView total;

    public ProductCartListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.fragment_product_cart_list, container, false);
        initView(container);
        return  container;
    }

    private void initView(ViewGroup container) {
        rv = container.findViewById(R.id.productCartListRv);
        proceed = container.findViewById(R.id.proceed);
        total = container.findViewById(R.id.total);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        Toast.makeText(getActivity(),"Please press back button to order more",Toast.LENGTH_SHORT).show();
        getData();
        initCheckOut();

    }



    private void initCheckOut() {
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductCartListViewModel viewModel = ViewModelProviders.of(getActivity()).get(ProductCartListViewModel.class);
                viewModel.checkOut().observe(ProductCartListActivityFragment.this,  apiResponse-> {
                    Toast.makeText(getActivity(),"Order placed with Order id "+apiResponse.order_id,Toast.LENGTH_LONG).show();
                    ProductsSelected.getInstance().productSet.clear();
                    ProductsSelected.getInstance().sum = 0f;
                    ProductsSelected.getInstance().isRefresh = true;
                    getActivity().finish();
                });
            }
        });
    }

    private void getData(){
        total.setText("Total : "+ProductsSelected.getInstance().sum+" euros");
        HashSet<Products> products = ProductsSelected.getInstance().productSet;
        List<Products> list = new ArrayList<> ();
        for (Products product: products) {
            list.add(product);
        }
        adapter = new ProductCartListAdapter(getActivity(),list);
        rv.setAdapter(adapter);


    }
}
