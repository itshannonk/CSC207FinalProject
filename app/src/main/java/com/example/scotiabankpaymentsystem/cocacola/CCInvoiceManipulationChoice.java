package com.example.scotiabankpaymentsystem.cocacola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.scotiabankpaymentsystem.R;

public class CCInvoiceManipulationChoice extends AppCompatActivity {
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccinvoice_manipulation_choice);
        findViewById(R.id.create_invoice).setOnClickListener(v -> navigateToActivityCreateInvoice());
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }
    public void navigateToActivityCreateInvoice() {
        Intent intent = new Intent(CCInvoiceManipulationChoice.this, CCCreateInvoice.class);
        System.out.println(userID + "manipulationChoice");
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
}
