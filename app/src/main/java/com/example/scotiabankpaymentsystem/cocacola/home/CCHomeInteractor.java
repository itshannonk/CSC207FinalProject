package com.example.scotiabankpaymentsystem.cocacola.home;


import androidx.annotation.NonNull;

import com.example.scotiabankpaymentsystem.model.Supplier;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class CCHomeInteractor {

    interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onHomePageSuccess(String username);
    }

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private String userID = user.getUid();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private Supplier supplier = new Supplier();


    public void displayName(final onDisplayDataFinishedListener listener) {
        // Get the information of the current logged in user from database single time listener
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("CocaCola").child(userID).child("Name").getValue(String.class);
                listener.onHomePageSuccess(username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
            }
        });
    }

    // Creates the customer by retrieving information from the database
    public void createSupplier() {
        // Get the information of the current logged in user from database single time listener
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //TODO for amy: implement this
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
            }
        });
    }

    public FirebaseUser getFirebaseUser() {
        return this.user;
    }

    public DatabaseReference getDatabaseReference() {
        return this.databaseReference;
    }
}

