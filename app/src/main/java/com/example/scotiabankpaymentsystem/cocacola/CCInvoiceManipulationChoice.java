package com.example.scotiabankpaymentsystem.cocacola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.scotiabankpaymentsystem.R;

public class CCInvoiceManipulationChoice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccinvoice_manipulation_choice);
        findViewById(R.id.create_invoice).setOnClickListener(v -> navigateToActivityCreateInvoice());
    }
    public void navigateToActivityCreateInvoice() {
        Intent intent = new Intent(CCInvoiceManipulationChoice.this, CCCreateInvoice.class);
        String userID = intent.getStringExtra("userID");
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}
