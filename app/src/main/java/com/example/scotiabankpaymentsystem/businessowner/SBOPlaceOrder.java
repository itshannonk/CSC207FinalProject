package com.example.scotiabankpaymentsystem.businessowner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
// TODO: Are we assuming that SBO already placed their orders on CocaCola's website??

/**
 * A page that allows SBO to place orders.
 */
public class SBOPlaceOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_placeorder);
    }
}
