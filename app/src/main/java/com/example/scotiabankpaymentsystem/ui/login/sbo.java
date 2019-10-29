package com.example.scotiabankpaymentsystem.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scotiabankpaymentsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class sbo extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sbo);
        //checking if the seeStatus in SBO page button has been pressed
        Button button = findViewById(R.id.SeeStatus);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for the SBO to seeStatus
                openActivitySeeStatus();
                //close the previous page
                //finish();
            }
        });
        //checking if the placeOrder in SBO page button has been pressed
        Button buttonPlaceOrder = findViewById(R.id.PlaceOdder);
        buttonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for the SBO to placeOrder
                openActivityPlaceOrder();
                //close the previous page that is no longer needed
                //finish();
            }
        });

        Button buttonSettings = findViewById(R.id.Setting);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for the SBO to placeOrder
                openActivitySetting();
                //close the previous page that is no longer needed
                //finish();
            }
        });

        Button buttonLogOut = findViewById(R.id.LogOut);
        buttonLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // clear session between app and firebase database
                FirebaseAuth.getInstance().signOut();
                // go back to the original login page
                openActivityLogOut();
                //close the page
                finish();
                Toast.makeText(getApplicationContext(), "Goodbye :)", Toast.LENGTH_LONG).show();

            }
        });

    }

    private void openActivitySeeStatus() {
        Intent intent = new Intent(sbo.this, coca_seeDeliver_hasDelivers.class);
        startActivity(intent);
    }

    private void openActivityPlaceOrder() {
        Intent intent = new Intent(sbo.this, SBOPlacingOrder.class);
        startActivity(intent);
    }
        private void openActivitySetting() {
        Intent intent = new Intent(sbo.this, SBOsetting.class);
        startActivity(intent);
    }

    private void openActivityLogOut() {
        Intent intent = new Intent(sbo.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
