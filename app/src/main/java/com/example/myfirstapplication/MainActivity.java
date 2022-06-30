package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.myfirstapplication.adapters.RestaurantListAdapter;
import com.example.myfirstapplication.model.RestuarantModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RestaurantListAdapter.RestaurantListClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle("Restaurant List");

        List<RestuarantModel> restaurantModelList=getRestaurantData();
        List<RestuarantModel> restuarantModel= getIntent().getParcelableExtra("RestuarantModel");

     initRecyclerView(restaurantModelList);



    }
private void initRecyclerView(List<RestuarantModel> restuarantModelList){
    RecyclerView recyclerView =findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    RestaurantListAdapter adapter= new RestaurantListAdapter(restuarantModelList,this);
    recyclerView.setAdapter(adapter);
}


    private List<RestuarantModel> getRestaurantData(){
  InputStream is= getResources().openRawResource(R.raw.getrestaurantcategories);
  Writer writer= new StringWriter();
  char[] buffer= new char[1024];
  try {
      Reader reader=new BufferedReader(new InputStreamReader(is,"UTF-8"));
      int n;
      while((n = reader.read(buffer))!= -1){
          writer.write(buffer,0,n);
      }
  }catch (Exception e){

  }
  String jsonStr=writer.toString();
        Gson gson =new Gson();
      RestuarantModel[] restuarantModels=  gson.fromJson(jsonStr,RestuarantModel[].class);
      List<RestuarantModel> restList= Arrays.asList(restuarantModels);
    return restList;
    }

    @Override
    public void onItemClick(RestuarantModel restuarantModel) {

            Intent intent = new Intent(MainActivity.this, SubCategory.class);
            intent.putExtra("RestuarantModel", restuarantModel);
            startActivity(intent);

    }
}
