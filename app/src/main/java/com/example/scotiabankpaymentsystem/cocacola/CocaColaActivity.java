package com.example.scotiabankpaymentsystem.cocacola;

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
 * The main page of CocaCola's profile.
 */
public class CocaColaActivity extends AppCompatActivity {

    FirebaseUser user;
    DatabaseReference databaseReference;
    String userName;
    String userEmail;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocacola_home);

        // Initialize the current user and database
        // userToken = user.getIdToken();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the information of the current logged in user from database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                userName = dataSnapshot.child("CocaCola").child(userID).child("Name").getValue(String.class);
                userEmail = dataSnapshot.child("CocaCola").child(userID).child("Email").getValue(String.class);
                //display the username after the data is retrieved since this is a single time listener.
                displayName();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Network error, please check your connection", Toast.LENGTH_LONG);
            }
        });

        //Checking if the seeDeliveries in CocaCola page button has been pressed
        Button button = findViewById(R.id.Order);
        button.setOnClickListener(v -> {
            //open the next page for CocaCola to seeStatus
            openActivitySeeOrder();
        });

        //Checking if the settings in CocaCola page button has been pressed
        Button buttonSettings = findViewById(R.id.Setting);
        buttonSettings.setOnClickListener(v -> {
            //open the next page for CocaCola to change settings
            openActivitySetting();
        });

        //Checking if the logout in CocaCola page button has been pressed
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

//        openActivity2();
    }
//    public void openActivity2() {
//        Intent intent = new Intent(CocaColaActivity.this, MainActivity.class);
//        startActivity(intent);
//    }
    private void displayName(){
        TextView welcomeText = findViewById(R.id.welcome_name);
        String welcome = "Welcome Corporate Overlord";
        welcomeText.setText(welcome);
    }

    private void openActivitySeeOrder() {
        Intent intent = new Intent(CocaColaActivity.this, CocaColaSeeInvoices.class);
        startActivity(intent);
    }

    private void openActivitySetting() {
        Intent intent = new Intent(CocaColaActivity.this, CocaColaSettings.class);
        startActivity(intent);
    }

    private void openActivityLogOut() {
        Intent intent = new Intent(CocaColaActivity.this, LoginActivity.class);
        //erases the history of pages from last session
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }
}
