package com.example.scotiabankpaymentsystem.businessowner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.CocaColaSeeDeliveries;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The main page of a SBO profile.
 */
public class SBOActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_home);

//        //Complete and destroy login activity once successful
//        finish();

        //Checking if the seeStatus in SBO page button has been pressed
        Button button = findViewById(R.id.SeeStatus);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for the SBO to seeStatus
                openActivitySeeStatus();
            }
        });

        //Checking if the placeOrder in SBO page button has been pressed
        Button buttonPlaceOrder = findViewById(R.id.PlaceOrder);
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for the SBO to placeOrder
                openActivityPlaceOrder();
            }
        });

        //Checking if the settings in SBO page button has been pressed
        Button buttonSettings = findViewById(R.id.Setting);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for the SBO to change settings
                openActivitySetting();
            }
        });

        //Checking if the logout in SBO page button has been pressed
        Button buttonLogOut = findViewById(R.id.LogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //clear session between app and FireBase database
                FirebaseAuth.getInstance().signOut();
                //go back to the original login page
                openActivityLogOut();
                //close the page
                finish();
                Toast.makeText(getApplicationContext(), "Goodbye :)", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openActivitySeeStatus() {
        Intent intent = new Intent(SBOActivity.this, CocaColaSeeDeliveries.class);
        startActivity(intent);
    }

    private void openActivityPlaceOrder() {
        Intent intent = new Intent(SBOActivity.this, SBOPlaceOrder.class);
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
        startActivity(intent);
    }
}
