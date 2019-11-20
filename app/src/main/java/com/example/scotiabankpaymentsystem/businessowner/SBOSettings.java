package com.example.scotiabankpaymentsystem.businessowner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.data.ChangePassword;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A page that has SBO's settings.
 */
public class SBOSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_settings);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        // Get the information of the current logged in user from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String newNameString = dataSnapshot.child("Business Owner").child(userID).child("Name").getValue(String.class);
                String newAddressString = dataSnapshot.child("Business Owner").child(userID).child("Address").getValue(String.class);
                TextView nameText = findViewById(R.id.ChangeName);
                TextView addressText = findViewById(R.id.Address);
                System.out.println("invoice check once");
                    nameText.setText(newNameString);
                    addressText.setText(newAddressString);

                //Checking if the changePassword in SBO's Settings page button has been pressed
                findViewById(R.id.password).setOnClickListener(v -> {
                    //open the next page for the SBO to chan
                    openActivityChangePassword();
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });}

    public void openActivityChangePassword() {
        Intent intent = new Intent(SBOSettings.this, ChangePassword.class);
        startActivity(intent);
    }
}