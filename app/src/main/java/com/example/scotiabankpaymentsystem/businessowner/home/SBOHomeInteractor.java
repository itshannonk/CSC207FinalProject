package com.example.scotiabankpaymentsystem.businessowner.home;

import android.content.Context;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.model.Customer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SBOHomeInteractor {
    interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onHomePageSuccess(String username);
    }
    public void displayName(final onDisplayDataFinishedListener listener, final String userID, Context context){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_display_name?userID="+userID;
        System.out.println(url);
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onHomePageSuccess(response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }


//    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    private String userID = user.getUid();
//    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//    private Customer customer = new Customer();
//
//
//    public void displayName(final onDisplayDataFinishedListener listener) {
//        // Get the information of the current logged in user from database single time listener
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String username = dataSnapshot.child("Business Owner").child(userID).child("Name").getValue(String.class);
//                listener.onHomePageSuccess(username);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                //Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
//            }
//        });
//    }
//
//    // Creates the customer by retrieving information from the database
//    public void createCustomer(){
//        // Get the information of the current logged in user from database single time listener
//        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                customer.setName(dataSnapshot.child("Business Owner").child(userID).child("Name").getValue(String.class));
//                customer.setAddress(dataSnapshot.child("Business Owner").child(userID).child("Address").getValue(String.class));
//
//                //getting invoices from the database as string, creating Invoice objects and storing them in an ArrayList
//                //TODO for amy: don't forget to set invoices!
////                String invoiceString = dataSnapshot.child("Business Owner").child(userID).child("Invoices").getValue(String.class);
////                Gson gson = new Gson();
////                Invoice invoice = gson.fromJson(invoiceString, Invoice.class);
////
////                "{\"id\":5,\"price\":0,\"status\":{\"delivered\":true,\"issued\":true,\"paid\":true}}"
////                customer.setInvoice();
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                //Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
//            }
//        });
//    }
//
//    public FirebaseUser getFirebaseUser(){
//        return this.user;
//    }
//
//    public DatabaseReference getDatabaseReference(){
//        return this.databaseReference;
//    }
//
//    public Customer getCustomer(){
//        return this.customer;
//    }
}
