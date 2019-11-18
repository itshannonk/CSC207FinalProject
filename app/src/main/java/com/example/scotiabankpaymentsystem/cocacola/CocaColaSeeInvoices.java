package com.example.scotiabankpaymentsystem.cocacola;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.Invoice.Invoice;
import com.example.scotiabankpaymentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class CocaColaSeeInvoices extends AppCompatActivity {
//    public static boolean testing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocacola_seedeliveries_has);
    }

//    public void openActivity2() {
//        Intent intent = new Intent(CocaColaSeeInvoices.this, MainActivity.class);
//        startActivity(intent);
//    }
}
