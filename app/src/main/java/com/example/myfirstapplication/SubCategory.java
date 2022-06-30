package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.myfirstapplication.adapters.RestaurantListAdapter;
import com.example.myfirstapplication.model.RestuarantModel;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class SubCategory extends AppCompatActivity implements RestaurantListAdapter.RestaurantListClickListener {

    private List<RestuarantModel> subCategoryList=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Restaurant List");

        RestuarantModel restuarantModel= getIntent().getParcelableExtra("RestuarantModel");
        subCategoryList=restuarantModel.getChildren();

        initRecyclerView(subCategoryList);
    }
    private void initRecyclerView(List<RestuarantModel> restuarantModelList){
        RecyclerView recyclerView =findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RestaurantListAdapter adapter= new RestaurantListAdapter(restuarantModelList,this);
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onItemClick(RestuarantModel restuarantModel) {
        if(restuarantModel.getDelivery_charge() != 0){
            Intent intent=new Intent(SubCategory.this,RestaurantSubCategory.class);
            intent.putExtra("RestuarantModel", restuarantModel);
            startActivity(intent);
        }else{
            Intent intent=new Intent(SubCategory.this,SubCategory.class);
            intent.putExtra("RestuarantModel", restuarantModel);
            startActivity(intent);
        }
    }
}
