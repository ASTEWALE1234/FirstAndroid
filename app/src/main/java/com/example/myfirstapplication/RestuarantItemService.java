package com.example.myfirstapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.myfirstapplication.model.RestuarantModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class RestuarantItemService {
    Context context;
    public RestuarantItemService(Context context){
        this.context=context;
    }

    //interfACE  for callback/async and await

    public interface VolleyResponseListener{
        void OnError(String message);
        void onResponse(String message);
    }

    public static final String RESTUARANT_ITEM_API = "http://196.191.244.132:8090/api/Restaurant/GetRestaurantCategories?orgTin=0001738069";
    String categoryName="";


    public void getAllRestuarantList(VolleyResponseListener volleyResponseListener){
        String URL= RESTUARANT_ITEM_API;



        //RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonArrayRequest getRequest=new JsonArrayRequest(Request.Method.GET, URL, null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    try {
                        JSONObject restaInfo=response.getJSONObject(2);
                        categoryName=restaInfo.getString("name");


                        Log.e(" Response Arerrrr","Category: "+categoryName);
                        volleyResponseListener.onResponse(categoryName);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Errorrrr Arerrrr",error.toString());
                volleyResponseListener.OnError("Something wrong");

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(getRequest);

    }
}
