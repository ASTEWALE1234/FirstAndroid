package com.example.myfirstapplication.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapplication.R;
import com.example.myfirstapplication.model.RestuarantModel;

import java.util.List;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.MyViewHolder> {

    private List<RestuarantModel> restuarantModelList;
    private RestaurantListClickListener clickListener;

    public  RestaurantListAdapter(List<RestuarantModel> restaurantModelList, RestaurantListClickListener restaurantListClickListener){
        this.restuarantModelList=restaurantModelList;
        this.clickListener=restaurantListClickListener;
    }
    public void updateData(List<RestuarantModel> restaurantModelList){
        this.restuarantModelList=restaurantModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
    return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantListAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
  holder.restaurantName.setText(restuarantModelList.get(position).getName());
  holder.restaurantAddress.setText(restuarantModelList.get(position).getProductType());

  holder.itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
          clickListener.onItemClick(restuarantModelList.get(position));
      }
  });
    }
public interface RestaurantListClickListener{
        public void onItemClick(RestuarantModel restuarantModel);
}
    @Override
    public int getItemCount() {
       return restuarantModelList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView restaurantName;
        TextView restaurantAddress;
        ImageView image;
        public  MyViewHolder(View view){
          super(view);
          restaurantName=view.findViewById(R.id.restaurantName);
          restaurantAddress=view.findViewById((R.id.restaurantAddress));
          image=view.findViewById(R.id.image);
        }
    }
}
