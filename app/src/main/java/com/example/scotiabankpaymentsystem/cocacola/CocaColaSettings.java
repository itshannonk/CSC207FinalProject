package com.example.scotiabankpaymentsystem.cocacola;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;

/**
 * A page that has SBO's settings.
 */
//TODO: Should we implement inheritance for pages like settings???

public class CocaColaSettings extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_settings);
    }
}
