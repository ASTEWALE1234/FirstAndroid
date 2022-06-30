package com.example.myfirstapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.Articles;
import com.example.myfirstapplication.model.RestuarantModel;

import java.util.List;

public class placeYourOrderAdapter extends RecyclerView.Adapter<placeYourOrderAdapter.MyViewHolder> {

    private List<RestuarantModel> subCategoryList;

    public placeYourOrderAdapter(List<RestuarantModel> subCategoryList){
        this.subCategoryList =subCategoryList;
    }
    public void updateData(List<RestuarantModel> subCategoryList){
        this.subCategoryList =subCategoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public placeYourOrderAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.place_order_recycler_row,parent,false);
    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull placeYourOrderAdapter.MyViewHolder holder, int position) {
  holder.subCategoryName.setText(subCategoryList.get(position).getName());
  holder.articlePrice.setText("Price "+subCategoryList.get(position).getPrice() * subCategoryList.get(position).getTotalQtyInCart());
  holder.articleQty.setText("qty: "+ subCategoryList.get(position).getTotalQtyInCart()+"");

    }
    @Override
    public int getItemCount() {
       return subCategoryList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView subCategoryName;
        TextView articlePrice;
        ImageView subImage;
        TextView articleQty;

        public  MyViewHolder(View view){
          super(view);
            subCategoryName=view.findViewById(R.id.subCategoryName);
            articlePrice=view.findViewById((R.id.articlePrice));
            articleQty=view.findViewById(R.id.articleQty);
            subImage=view.findViewById(R.id.subImage);
        }
    }
}
