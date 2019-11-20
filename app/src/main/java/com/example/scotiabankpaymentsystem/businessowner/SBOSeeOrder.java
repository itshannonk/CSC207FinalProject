package com.example.scotiabankpaymentsystem.businessowner;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.Invoice.Invoice;
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
    private static boolean testing = false;
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
                if(!testing) {
                    //This is how you convert from a json file to an object
                    Gson gson = new Gson();
                    String json = newInvoiceString;
                    Invoice invoice = gson.fromJson(json, Invoice.class);
                    //we can now do methods around the invoices
                    invoice.setId(5);
                    invoice.getStatus().setIssued(true);
                    invoice.getStatus().setDelivered(true);
                    invoice.getStatus().setPaid(true);
                    //changing it back to an String to push back to the firebase
                    Gson gsonChanged = new Gson();
                    String mynewJSON = gsonChanged.toJson(invoice);
                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://csc207-tli.firebaseio.com");
                    DatabaseReference roleDatabaseReference = database.getReference("Business Owner");
                    DatabaseReference myRef = roleDatabaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                    DatabaseReference RefToReplace = myRef.child("Invoices");
                    //slight delay for the display of the data to mimic realtime
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RefToReplace.setValue(mynewJSON);
                        }
                    }, 2000);
                    //testing boolean is for testing purposes so that it only executes this once.
                    testing = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
