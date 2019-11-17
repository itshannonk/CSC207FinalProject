package com.example.scotiabankpaymentsystem.businessowner;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.data.model.Invoice;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

// TODO: differentiate if SBO has orders or not

/**
 * A page that contains SBO's orders
 */
public class SBOSeeOrder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_seestatus_has);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the information of the current logged in user from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String newInvoiceString = dataSnapshot.child("Business Owner").child(userID).child("Invoices").getValue(String.class);
                TextView invoiceText = findViewById(R.id.Invoice);
                System.out.println("invoice check once");
                if(!(newInvoiceString.equals(""))){
                    invoiceText.setText(newInvoiceString);
                }

                //This is how you convert from a json file to an object
                Gson gson = new Gson();
                String json = newInvoiceString;
                Invoice invoice = gson.fromJson(json, Invoice.class);
                System.out.println(invoice.getID());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
