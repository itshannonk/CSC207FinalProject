package com.example.scotiabankpaymentsystem.businessowner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.CocaColaSeeInvoices;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * The main page of a SBO profile.
 */
public class SBOActivity extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference databaseReference;
    //userToken;
    String userName;
    String userEmail;
    String userID;

    // TODO: get invoices!



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_home);
        // Initialize the current user and database
        // userToken = user.getIdToken();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the information of the current logged in user from database single time listener
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child("Business Owner").child(userID).child("Name").getValue(String.class);
                userEmail = dataSnapshot.child("Business Owner").child(userID).child("Email").getValue(String.class);
                //display the username after the data is retrieved since this is a single time listener.
                displayName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
            }
        });

        //Checking if the seeStatus in SBO page button has been pressed
        Button button = findViewById(R.id.SeeStatus);
        button.setOnClickListener(v -> {
            //open the next page for the SBO to seeStatus
            openActivitySeeStatus();
        });

        //Checking if the settings in SBO page button has been pressed
        Button buttonSettings = findViewById(R.id.Setting);
        buttonSettings.setOnClickListener(v -> {
            //open the next page for the SBO to change settings
            openActivitySetting();
        });

        //Checking if the logout in SBO page button has been pressed
        Button buttonLogOut = findViewById(R.id.LogOut);
        buttonLogOut.setOnClickListener(v -> {
            //clear session between app and FireBase database
            FirebaseAuth.getInstance().signOut();
            //go back to the original login page
            openActivityLogOut();
            //close the SBO page
            finish();
            Toast.makeText(getApplicationContext(), "Goodbye :)", Toast.LENGTH_LONG).show();
        });
    }
    private void displayName(){
        TextView welcomeText = findViewById(R.id.welcome_name);
        String welcome = "Welcome " + userName;
        welcomeText.setText(welcome);
    }

    private void openActivitySeeStatus() {
        Intent intent = new Intent(SBOActivity.this, SBOSeeOrder.class);
        startActivity(intent);
    }

    private void openActivitySetting() {
        Intent intent = new Intent(SBOActivity.this, SBOSettings.class);
        startActivity(intent);
    }

    private void openActivityLogOut() {
        Intent intent = new Intent(SBOActivity.this, LoginActivity.class);
        //erases the history of pages from last session
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }
}

