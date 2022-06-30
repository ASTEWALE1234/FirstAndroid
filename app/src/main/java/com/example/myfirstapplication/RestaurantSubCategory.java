package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapplication.adapters.SubCategoryListAdapter;
import com.example.myfirstapplication.model.Articles;
import com.example.myfirstapplication.model.RestuarantModel;

import java.util.ArrayList;
import java.util.List;

public class RestaurantSubCategory extends AppCompatActivity implements SubCategoryListAdapter.SubCategoryListClickListener {

   private List<RestuarantModel> subCategoryList=null;
   private SubCategoryListAdapter subCategoryListAdapter;
   private List<RestuarantModel> articleListInCart=new ArrayList<>();;
   private int totalItemInCart=0;
   private TextView buttonCheckOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_sub_category);

        RestuarantModel restuarantModel= getIntent().getParcelableExtra("RestuarantModel");
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle(restuarantModel.getName());
        actionBar.setSubtitle(restuarantModel.getProductType());
        actionBar.setDisplayHomeAsUpEnabled(true);

      subCategoryList= restuarantModel.getChildren();
        initRecyclerView();

    buttonCheckOut=   findViewById(R.id.buttonCheckOut);
     buttonCheckOut.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
         if(articleListInCart == null || articleListInCart.size() <=0){
             Toast.makeText(RestaurantSubCategory.this,"Please add some items in the cart",Toast.LENGTH_LONG).show();
       return;
         }
         restuarantModel.setChildren(articleListInCart);
             Intent intent=new Intent(RestaurantSubCategory.this,PlaceYourOrder.class);
             intent.putExtra("RestuarantModel", restuarantModel);
             startActivity(intent);
         }

     });
    }
    private void initRecyclerView(){
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        subCategoryListAdapter=new SubCategoryListAdapter(subCategoryList, this);

        recyclerView.setAdapter(subCategoryListAdapter);
    }

    @Override
    public void onAddToCartClick(RestuarantModel articles) {
    if(articleListInCart==null){
        articleListInCart=new ArrayList<>();
    }
    articleListInCart.add(articles);
        totalItemInCart=0;
        for(RestuarantModel articles1: articleListInCart){
            totalItemInCart = totalItemInCart + articles1.getTotalQtyInCart();
        }
        buttonCheckOut.setText("CheckOut (" +totalItemInCart +") items");
    }

    @Override
    public void onUpdateCartClick(RestuarantModel articles) {
   if(articleListInCart.contains(articles)){
       int index=articleListInCart.indexOf(articles);
       articleListInCart.remove(index);
       articleListInCart.add(index,articles);

       totalItemInCart=0;
       for(RestuarantModel articles1: articleListInCart){
           totalItemInCart +=articles1.getTotalQtyInCart();
       }

       buttonCheckOut.setText("CheckOut (" +totalItemInCart +") items");
   }

    }

    @Override
    public void onRemoveFromCart(RestuarantModel articles) {
        if(articleListInCart.contains(articles)){
            articleListInCart.remove(articles);

            totalItemInCart=0;
            for(RestuarantModel articles1:articleListInCart){
                totalItemInCart +=articles1.getTotalQtyInCart();
            }
            buttonCheckOut.setText("CheckOut (" +totalItemInCart +" ) items");
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
            default:
                //do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode ==1000 && requestCode== Activity.RESULT_OK){
            //
            finish();
        }
    }
}
