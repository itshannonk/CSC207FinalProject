package com.example.scotiabankpaymentsystem.cocacola;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

/**
 * The main page of CocaCola's profile.
 */
public class CocaColaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocacola_home);

//        //Complete and destroy login activity once successful
//        finish();

        //Checking if the seeDeliveries in CocaCola page button has been pressed
        Button button = findViewById(R.id.Order);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for CocaCola to seeStatus
                openActivitySeeOrder();
            }
        });

        //Checking if the settings in CocaCola page button has been pressed
        Button buttonSettings = findViewById(R.id.Setting);
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //open the next page for CocaCola to change settings
                openActivitySetting();
            }
        });

        //Checking if the logout in CocaCola page button has been pressed
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

//        openActivity2();
    }
//    public void openActivity2() {
//        Intent intent = new Intent(CocaColaActivity.this, MainActivity.class);
//        startActivity(intent);
//    }

    private void openActivitySeeOrder() {
        Intent intent = new Intent(CocaColaActivity.this, CocaColaSeeDeliveries.class);
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
        startActivity(intent);
    }
}
