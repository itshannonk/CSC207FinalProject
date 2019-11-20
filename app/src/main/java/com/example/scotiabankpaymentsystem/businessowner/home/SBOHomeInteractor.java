package com.example.scotiabankpaymentsystem.businessowner.home;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SBOHomeInteractor {
    interface OnLoginFinishedListener {
        //successfully retrieves user information from database
        void onHomePageSuccess(String username);
    }

    FirebaseUser user;
    String userID;
    DatabaseReference databaseReference;

    //getting stuff from database
    public void displayName(final OnLoginFinishedListener listener) {
        databaseReference = FirebaseDatabase.getInstance().getReference();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        // Get the information of the current logged in user from database single time listener
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String username = dataSnapshot.child("Business Owner").child(userID).child("Name").getValue(String.class);
                listener.onHomePageSuccess(username);
                System.out.println("interactor username: " + username);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
            }
        });
    }
}
