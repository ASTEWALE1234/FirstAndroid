package com.example.myfirstapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myfirstapplication.adapters.placeYourOrderAdapter;
import com.example.myfirstapplication.model.Articles;
import com.example.myfirstapplication.model.RestuarantModel;

public class PlaceYourOrder extends AppCompatActivity {

    private EditText inputName,inputAddress,inputCity,inputState,inputZip,inputCardNumber,inputCardExpiry,inputCardPin;
    private TextView tvSubtotalAmount,tvDeliveryChargeAmount,tvTotalAmount,buttonPlaceYourOrder,tvDeliveryCharge;
    private RecyclerView cartItemRecyclerView;
    private SwitchCompat switchDelivery;

    private placeYourOrderAdapter placeYourOrderAdapter;
    private boolean isDeliveryOn=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_your_order);

        RestuarantModel restuarantModel= getIntent().getParcelableExtra("RestuarantModel");
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle(restuarantModel.getName());
        actionBar.setSubtitle(restuarantModel.getProductType());
        actionBar.setDisplayHomeAsUpEnabled(true);

        inputName=findViewById(R.id.inputName);
        inputAddress=findViewById(R.id.inputAddress);
        inputCity=findViewById(R.id.inputCity);
        inputState=findViewById(R.id.inputState);
        inputZip=findViewById(R.id.inputZip);
        inputCardNumber=findViewById(R.id.inputCardNumber);
        inputCardExpiry=findViewById(R.id.inputCardExpiry);
        inputCardPin=findViewById(R.id.inputCardPin);
        tvSubtotalAmount=findViewById(R.id.tvSubtotalAmount);
        tvDeliveryChargeAmount=findViewById(R.id.tvDeliveryChargeAmount);
        tvDeliveryCharge=findViewById(R.id.tvDeliveryCharge);
        tvTotalAmount=findViewById(R.id.tvTotalAmount);
        buttonPlaceYourOrder=findViewById(R.id.buttonPlaceYourOrder);

        switchDelivery=findViewById(R.id.switchDelivery);

        cartItemRecyclerView=findViewById(R.id.cartItemRecyclerView);

        buttonPlaceYourOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onPlaceOrderButtonClick(restuarantModel);
            }
        });

        switchDelivery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked){
                 inputAddress.setVisibility(View.VISIBLE);
                 inputCity.setVisibility(View.VISIBLE);
                 inputState.setVisibility(View.VISIBLE);
                 inputZip.setVisibility(View.VISIBLE);
                 tvDeliveryChargeAmount.setVisibility(View.VISIBLE);
                    tvDeliveryCharge.setVisibility(View.VISIBLE);
                    isDeliveryOn=true;
                    calculateTotalAmount(restuarantModel);

                }else{
                    inputAddress.setVisibility(View.GONE);
                    inputCity.setVisibility(View.GONE);
                    inputState.setVisibility(View.GONE);
                    inputZip.setVisibility(View.GONE);
                    tvDeliveryChargeAmount.setVisibility(View.GONE);
                    tvDeliveryCharge.setVisibility(View.GONE);
                    isDeliveryOn=false;
                    calculateTotalAmount(restuarantModel);
                }

            }
        });
        //onCreate
        initRecyclerView(restuarantModel);
        calculateTotalAmount(restuarantModel);
    }
    private void calculateTotalAmount(RestuarantModel restuarantModel){

        float subTotalAmount=0f;

          for(RestuarantModel a:restuarantModel.getChildren()){
              subTotalAmount += a.getPrice() * a.getTotalQtyInCart();
          }
         tvSubtotalAmount.setText(subTotalAmount+" birr");

          if(isDeliveryOn){
         tvDeliveryChargeAmount.setText(restuarantModel.getDelivery_charge()+" birr");
             subTotalAmount += restuarantModel.getDelivery_charge();
          }
          tvTotalAmount.setText(subTotalAmount +" birr");
    }

    private void onPlaceOrderButtonClick(RestuarantModel restuarantModel){
        if(TextUtils.isEmpty(inputName.getText().toString())){
         inputName.setError("please enter Name");
         return;
        }
   else if(isDeliveryOn && TextUtils.isEmpty(inputAddress.getText().toString()) ){
            inputAddress.setError("please enter Address");
            return;
        }
       else if(isDeliveryOn && TextUtils.isEmpty(inputState.getText().toString())){
            inputState.setError("please enter State");
            return;
        }
      else if(isDeliveryOn && TextUtils.isEmpty(inputCity.getText().toString()) ){
            inputCity.setError("please enter City");
            return;
        }  else if( TextUtils.isEmpty(inputCardNumber.getText().toString()) ){
            inputCardNumber.setError("please enter card number");
            return;
        }
      else if(isDeliveryOn && TextUtils.isEmpty(inputCardExpiry.getText().toString()) ){
            inputCardExpiry.setError("please enter card expiry date");
            return;
        }
      else if(isDeliveryOn && TextUtils.isEmpty(inputCardPin.getText().toString()) ){
            inputCardPin.setError("please enter pin");
            return;
        }
        Intent i= new Intent(PlaceYourOrder.this,OrderSuccessActivity.class);
        i.putExtra("RestuarantModel",restuarantModel);
        startActivityForResult(i,1000);
    }
    private void initRecyclerView(RestuarantModel restuarantModel){
        cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeYourOrderAdapter=new placeYourOrderAdapter(restuarantModel.getChildren());
         cartItemRecyclerView.setAdapter(placeYourOrderAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1000){
            setResult(Activity.RESULT_OK);
            finish();
        }
        super.onActivityResult(requestCode, resultCode, data);
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
    public void onBackPressed() {
        super.onBackPressed();
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
}
