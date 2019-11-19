package com.example.scotiabankpaymentsystem.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.scotiabankpaymentsystem.Invoice.Invoice;
import com.example.scotiabankpaymentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class driverSeeOrder extends AppCompatActivity {
    public static boolean testing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_see_order);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the information of the current logged in user from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String newInvoiceString = dataSnapshot.child("Truck Driver").child(userID).child("Invoices").getValue(String.class);
                TextView invoiceText = findViewById(R.id.Invoice);
                System.out.println("invoice check once");
                if(!(newInvoiceString.equals(""))){
                    invoiceText.setText(newInvoiceString);
                }
                if(!testing) {
                    //This is how you convert from a json file to an object
                    Gson gson = new Gson();
                    String json = newInvoiceString;
                    Invoice invoice = gson.fromJson(json, Invoice.class);
                    invoice.setId(5);
                    invoice.getStatus().setIssued(true);
                    invoice.getStatus().setDelivered(true);
                    invoice.getStatus().setPaid(true);
                    Gson gsonChanged = new Gson();
                    String mynewJSON = gsonChanged.toJson(invoice);
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://csc207-tli.firebaseio.com");
                    DatabaseReference roleDatabaseReference = database.getReference("Truck Driver");
                    DatabaseReference myRef = roleDatabaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    DatabaseReference RefToReplace = myRef.child("Invoices");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RefToReplace.setValue(mynewJSON);
                        }
                    }, 2000);
                    testing = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
