package com.example.scotiabankpaymentsystem.businessowner;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;

/**
 * A page that contains SBO's orders
 */

// TODO: differentiate if SBO has orders or not
public class SBOSeeOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_seestatus_has);
    }
}
