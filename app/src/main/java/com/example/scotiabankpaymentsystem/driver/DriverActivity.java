package com.example.scotiabankpaymentsystem.driver;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * The main page of a Driver's profile.
 */
public class DriverActivity extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference databaseReference;
    String userName;
    String userEmail;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        // Initialize the current user and database
        // userToken = user.getIdToken();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the information of the current logged in user from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child("Driver").child(userID).child("Name").getValue(String.class);
                userEmail = dataSnapshot.child("Driver").child(userID).child("Email").getValue(String.class);

                TextView welcomeText = findViewById(R.id.welcome_name);
                String welcome = "Welcome " + userName;
                welcomeText.setText(welcome);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
            }
        });

        //Checking if the seeDeliveries in Driver page button has been pressed
        Button button = findViewById(R.id.SeeDelivery);
        button.setOnClickListener(v -> {
            //open the next page for Driver to seeDeliveries
            openactivityseedelivery();
        });

        //Checking if the settings in Driver page button has been pressed
        Button buttonSettings = findViewById(R.id.Setting);
        buttonSettings.setOnClickListener(v -> {
            //open the next page for Driver to change settings
            openActivitySetting();
        });

        //Checking if the logout in Driver page button has been pressed
        Button buttonLogOut = findViewById(R.id.LogOut);
        buttonLogOut.setOnClickListener(v -> {

            //clear session between app and FireBase database
            FirebaseAuth.getInstance().signOut();
            //go back to the original login page
            openActivityLogOut();
            //close the page
            finish();
            Toast.makeText(getApplicationContext(), "Goodbye :)", Toast.LENGTH_LONG).show();
        });

    }


    private void openactivityseedelivery() {
        Intent intent = new Intent(com.example.scotiabankpaymentsystem.driver.DriverActivity.this, DriverSeeDeliveries.class);
        startActivity(intent);
    }

    private void openActivitySetting() {
        Intent intent = new Intent(com.example.scotiabankpaymentsystem.driver.DriverActivity.this, DriverSettings.class);
        startActivity(intent);
    }

    private void openActivityLogOut() {
        Intent intent = new Intent(com.example.scotiabankpaymentsystem.driver.DriverActivity.this, LoginActivity.class);
        //erases the history of pages from last session
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }
}
