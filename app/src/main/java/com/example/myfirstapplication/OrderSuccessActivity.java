package com.example.myfirstapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.myfirstapplication.model.RestuarantModel;

public class OrderSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        RestuarantModel restuarantModel= getIntent().getParcelableExtra("RestuarantModel");
        ActionBar actionBar =getSupportActionBar();
        actionBar.setTitle(restuarantModel.getName());
        actionBar.setSubtitle(restuarantModel.getProductType());
        actionBar.setDisplayHomeAsUpEnabled(false);

        TextView buttonDone=findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
