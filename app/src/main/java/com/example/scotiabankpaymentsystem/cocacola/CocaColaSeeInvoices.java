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
        System.out.println(userID + "this is the ccSeeInvoiceuserID");

        // Get the information of the current logged in user from database
        //databaseReference.addValueEventListener(new ValueEventListener() {
            //@Override
            //public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
//                String newInvoiceString = dataSnapshot.child("CocaCola").child(userID).child("Invoices").getValue(String.class);
//                TextView invoiceText = findViewById(R.id.Invoice);
//                System.out.println("invoice check once");
//                System.out.println(newInvoiceString);
//                invoiceText.setText(newInvoiceString);
//                if(!(newInvoiceString.equals(""))){
//                    invoiceText.setText(newInvoiceString);
//                }
//                if(!testing) {
//                    //This is how you convert from a json file to an object
//                    Gson gson = new Gson();
//                    String json = newInvoiceString;
//
//                    Invoice invoice = gson.fromJson(json, Invoice.class);
//                    invoice.setId(6);
//                    invoice.getStatus().setIssued(true);
//                    invoice.getStatus().setDelivered(true);
//                    invoice.getStatus().setPaid(true);
//                    Gson gsonChanged = new Gson();
//                    String mynewJSON = gsonChanged.toJson(invoice);
//                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://csc207-tli.firebaseio.com");
//                    DatabaseReference roleDatabaseReference = database.getReference("CocaCola");
//                    DatabaseReference myRef = roleDatabaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    DatabaseReference RefToReplace = myRef.child("Invoices");
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            RefToReplace.setValue(mynewJSON);
//                        }
//                    }, 2000);
//                    testing = true;
//                }
            }

           // @Override
            //public void onCancelled(@NonNull DatabaseError databaseError) {
            //}
        //});
    //}

//    public void openActivity2() {
//        Intent intent = new Intent(CocaColaSeeInvoices.this, MainActivity.class);
//        startActivity(intent);
//    }
}
