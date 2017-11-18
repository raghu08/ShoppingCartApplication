package com.zooplus.ui.products;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zooplus.R;
import com.zooplus.ui.productcart.ProductCartListActivity;
import com.zooplus.ui.products.adapter.ProductListAdapter;
import com.zooplus.ui.products.adapter.ProductsSelected;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListActivityFragment extends LifecycleFragment {

    private ProductListAdapter adapter;
    private RecyclerView rv;
    private TextView proceed;
    private TextView note;

    public ProductListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        container = (ViewGroup) inflater.inflate(R.layout.fragment_product_list, container, false);
        initView(container);
        return  container;
    }

    private void initView(ViewGroup container) {
         rv = container.findViewById(R.id.productListRv);
        proceed = container.findViewById(R.id.proceed);
        note = container.findViewById(R.id.note);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        rv.setHasFixedSize(true);
        adapter = new ProductListAdapter(getActivity(),new ArrayList<>());
        rv.setAdapter(adapter);
        initCheckOut();
        getData();


    }

    @Override
    public void onResume() {
        super.onResume();
        if(ProductsSelected.getInstance().isRefresh){
            getData();
            ProductsSelected.getInstance().isRefresh=false;
        }

    }

    private void initCheckOut() {
        if(adapter.getItemCount()==0) {
            proceed.setVisibility(View.GONE);
        }
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ProductsSelected.getInstance().productSet.size()>0) {
                    Intent intent = new Intent(getActivity(), ProductCartListActivity.class);
                    getActivity().startActivity(intent);
                }
                else
                {
                    Toast.makeText(getActivity(),"Please add items to cart",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void getData(){
        ProductListViewModel viewModel = ViewModelProviders.of(getActivity()).get(ProductListViewModel.class);
        viewModel.loadProductList().observe(this,  apiResponse-> {
            adapter.notifyData(apiResponse.products);
            if(adapter.getItemCount()>0) {
                proceed.setVisibility(View.VISIBLE);
                note.setVisibility(View.GONE);
            }
        });

    }
}
