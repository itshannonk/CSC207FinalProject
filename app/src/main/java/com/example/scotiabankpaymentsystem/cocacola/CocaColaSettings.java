package com.example.scotiabankpaymentsystem.cocacola;

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
 * A page that has CocaCola's settings.
 */

public class CocaColaSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_settings);


        //Checking if the changePassword in SBO's SettingsActivity page button has been pressed
        findViewById(R.id.password).setOnClickListener(v -> {
            //open the next page for the SBO to chan
            openActivityChangePassword();
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        // Get the information of the current logged in user from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String newNameString = dataSnapshot.child("CocaCola").child(userID).child("Name").getValue(String.class);
                String newAddressString = dataSnapshot.child("CocaCola").child(userID).child("Address").getValue(String.class);
                TextView nameText = findViewById(R.id.ChangeName);
                TextView addressText = findViewById(R.id.Address);
                System.out.println("invoice check once");
                nameText.setText(newNameString);
                addressText.setText(newAddressString);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void openActivityChangePassword() {
        Intent intent = new Intent(com.example.scotiabankpaymentsystem.cocacola.CocaColaSettings.this, ChangePassword.class);
        startActivity(intent);
    }
}

