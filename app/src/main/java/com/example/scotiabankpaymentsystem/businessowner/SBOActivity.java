package com.example.scotiabankpaymentsystem.businessowner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

/**
 * The main page of a SBO profile.
 */
public class SBOActivity extends AppCompatActivity {

    //userToken;
    String userName;
    String userEmail;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_home);

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
            //go back to the original login page
            openActivityLogOut();
            //close the SBO page
            finish();
            Toast.makeText(getApplicationContext(), "Goodbye :)", Toast.LENGTH_LONG).show();
        });
    }
    private void displayName(String username, String email){
        this.userName = username;
        this.userEmail = email;
        TextView welcomeText = findViewById(R.id.welcome_name);
        String welcome = "Welcome " + userName;
        welcomeText.setText(welcome);
    }
    private void openActivitySeeStatus() {
        Intent intent = new Intent(SBOActivity.this, SBODisplayInvoice.class);
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
        //FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }
}

