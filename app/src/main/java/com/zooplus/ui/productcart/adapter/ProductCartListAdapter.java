package com.zooplus.ui.productcart.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zooplus.R;
import com.zooplus.entities.Products;

import java.util.List;


public class ProductCartListAdapter extends RecyclerView.Adapter<ProductCartListAdapter.ProductViewHolder> {

    private List<Products> itemList;
    private Context context;
    private int quantity;

    public ProductCartListAdapter(Context context, List<Products> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    public void notifyData(List<Products> itemList){
        this.itemList = itemList;
        notifyDataSetChanged();
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_view_row, null);
        ProductViewHolder rcv = new ProductViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Products product = itemList.get(position);
        holder.price.setText("Quantity: "+product.quantity);

        float price =  (float)Double.parseDouble(product.price);
        holder.name.setText(product.name+
                "\nTotal Price : "+product.quantity*price);
        holder.minusButton.setVisibility(View.GONE);
        holder.plusButton.setVisibility(View.GONE);

        try {
            int id = Integer.parseInt(product.id);
            String uri = "@drawable/img"+id;
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable res = context.getResources().getDrawable(imageResource);
            holder.thumbnailIv.setImageDrawable(res);
        } catch (Exception e) {
            e.printStackTrace();
        }


        holder.divider.setVisibility(position == itemList.size() - 1 ? View.GONE : View.VISIBLE);

    }



    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    public Products getItem(int position) {
        return itemList.get(position);
    }


    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView thumbnailIv;
        public TextView quantity;
        public TextView name;
        public TextView price;
        public TextView priceSum;
        public Button minusButton;
        public Button plusButton;
        public View divider;

        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            thumbnailIv = (ImageView) itemView.findViewById(R.id.product_photo);
            minusButton = (Button) itemView.findViewById(R.id.minus);
            plusButton = (Button) itemView.findViewById(R.id.plus);
            name = (TextView) itemView.findViewById(R.id.name);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            price = (TextView) itemView.findViewById(R.id.price);
            priceSum = (TextView) itemView.findViewById(R.id.price_sum);
            priceSum.setVisibility(View.VISIBLE);
            divider = (View) itemView.findViewById(R.id.divider);

        }

        @Override
        public void onClick(View view) {


        }


    }
}