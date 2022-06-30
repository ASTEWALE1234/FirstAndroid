package com.example.myfirstapplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.Articles;
import com.example.myfirstapplication.model.RestuarantModel;

import java.util.List;

public class SubCategoryListAdapter extends RecyclerView.Adapter<SubCategoryListAdapter.MyViewHolder> {

    private List<RestuarantModel> subCategoryList;
    private SubCategoryListClickListener clickListener;

    public SubCategoryListAdapter(List<RestuarantModel> subCategoryList, SubCategoryListClickListener subCategoryListClickListener){
        this.subCategoryList =subCategoryList;
        this.clickListener=subCategoryListClickListener;
    }
    public void updateData(List<RestuarantModel> subCategoryList){
        this.subCategoryList =subCategoryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubCategoryListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_recycler_row,parent,false);
    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
  holder.subCategorytName.setText(subCategoryList.get(position).getName());
  holder.subcategoryAddress.setText(subCategoryList.get(position).getCategory());
  holder.subcategoryHours.setText("Price: "+ subCategoryList.get(position).getPrice() + " Birr");

  holder.addToCartButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          RestuarantModel articles= subCategoryList.get(position);
          articles.setTotalQtyInCart(1);
          clickListener.onAddToCartClick(articles);
          holder.addMoreLayout.setVisibility(View.VISIBLE);
          holder.addToCartButton.setVisibility(View.GONE);
          holder.tvCount.setText(articles.getTotalQtyInCart()+"");
      }
  });
holder.imageMinus.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        RestuarantModel articles= subCategoryList.get(position);
        int total=articles.getTotalQtyInCart();
        total--;

        if(total > 0 ){
            articles.setTotalQtyInCart(total);
            clickListener.onUpdateCartClick(articles);
            holder.tvCount.setText(total+"");
        }
        else{
            holder.addMoreLayout.setVisibility(View.GONE);
            holder.addToCartButton.setVisibility(View.VISIBLE);
            articles.setTotalQtyInCart(total);
            clickListener.onRemoveFromCart(articles);
        }
    }
});

holder.imageAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        RestuarantModel articles= subCategoryList.get(position);
        int total=articles.getTotalQtyInCart();
        total++;
        if(total<=10){
            articles.setTotalQtyInCart(total);
            clickListener.onUpdateCartClick(articles);
            holder.tvCount.setText(total +"");
        }
    }
});
    }
public interface SubCategoryListClickListener{
        public void onAddToCartClick(RestuarantModel articles);
        public void onUpdateCartClick(RestuarantModel articles);
        public void onRemoveFromCart(RestuarantModel articles);
}
    @Override
    public int getItemCount() {
       return subCategoryList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView subCategorytName;
        TextView subcategoryAddress;
        TextView subcategoryHours;
        ImageView subimage;
        TextView addToCartButton;
        ImageView imageAdd;
        ImageView imageMinus;
        TextView tvCount;
       LinearLayout addMoreLayout;

        public  MyViewHolder(View view){
          super(view);
            subCategorytName=view.findViewById(R.id.subCategorytName);
            subcategoryAddress=view.findViewById((R.id.subcategoryAddress));
            subcategoryHours=view.findViewById(R.id.subcategoryHours);
            subimage=view.findViewById(R.id.subimage);
            addToCartButton=view.findViewById(R.id.addToCartButton);
            imageAdd=view.findViewById(R.id.imageAdd);
            imageMinus=view.findViewById(R.id.imageMinus);
            tvCount=view.findViewById(R.id.tvCount);
            addMoreLayout=view.findViewById(R.id.addMoreLayout);
        }
    }
}
