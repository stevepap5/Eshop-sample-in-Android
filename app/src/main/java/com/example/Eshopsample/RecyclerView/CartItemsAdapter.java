package com.example.Eshopsample.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Eshopsample.R;

import java.util.List;

public class CartItemsAdapter extends RecyclerView.Adapter<CartItemsAdapter.CartItemViewHolder> {

    List<CartItem> cartItemList;


    public CartItemsAdapter(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_row,parent,false);
        return new CartItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartItemViewHolder holder, int position) {

        holder.nameCartItemTextView.setText(cartItemList.get(position).getNameOfCartItem());
        holder.attributCartItemTextView.setText(cartItemList.get(position).getAttributeOfCartItem());
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public class CartItemViewHolder extends RecyclerView.ViewHolder {

        private TextView nameCartItemTextView;
        private TextView attributCartItemTextView;

        public CartItemViewHolder(@NonNull View itemView) {
            super(itemView);

            nameCartItemTextView=itemView.findViewById(R.id.nameCartItemTextView);
            attributCartItemTextView=itemView.findViewById(R.id.attributeCartItemTextView);
        }
    }
}
