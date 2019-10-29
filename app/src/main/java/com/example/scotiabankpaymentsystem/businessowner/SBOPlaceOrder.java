package com.example.scotiabankpaymentsystem.businessowner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;

/**
 * A page that allows SBO to place orders.
 */

//TODO: Uhhh I thought we are assuming that SBO already placed their orders on CocaCola's website??

public class SBOPlaceOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_placeorder);
    }
}
