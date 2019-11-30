package com.example.scotiabankpaymentsystem.cocacola;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CocaColaSeeInvoices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_see_invoices);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        System.out.println(userID + "this is the ccSeeInvoiceuserID");}

}
