package com.example.scotiabankpaymentsystem.driver.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.driver.seeinvoices.DriverSeeInvoicesActivity;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
//import com.google.firebase.auth.FirebaseAuth;

public class DriverHomeActivity extends AppCompatActivity implements DriverHomeView {

    private DriverHomePresenter presenter;
    private Button seeDeliveryButton;
    private Button logoutButton;
    private TextView welcomeText;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        seeDeliveryButton = findViewById(R.id.SeeDelivery);
        logoutButton = findViewById(R.id.LogOut);
        welcomeText = findViewById(R.id.welcome_name);
        findViewById(R.id.SeeDelivery).setOnClickListener(v -> navigateToActivitySeeDelivery());
        findViewById(R.id.LogOut).setOnClickListener(v -> navigateToActivityLogOut());
        presenter = new DriverHomePresenter(this, new DriverHomeInteractor());
        getUserID();
        displayName();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void navigateToActivitySeeDelivery() {
        Intent intent = new Intent(DriverHomeActivity.this, DriverSeeInvoicesActivity.class);
        intent.putExtra("userID", userID);
        startActivityForResult(intent, 1);
        finish();
    }

    @Override
    public void navigateToActivityLogOut() {
        //clear session between app and FireBase database
        //FirebaseAuth.getInstance().signOut();
        //go back to the original login page
        Intent intent = new Intent(DriverHomeActivity.this, LoginActivity.class);
        //erases the history of pages from last session
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //close the Driver page
        Toast.makeText(getApplicationContext(), "Goodbye :)", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void displayName() {
        presenter.displayName(userID, this);
    }

    @Override
    public void setDisplayName(String username) {
        TextView welcomeText = findViewById(R.id.welcome_name);
        String welcome = "Welcome " + username;
        welcomeText.setText(welcome);
    }

    public void getUserID(){
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }

    public void setUserID(String userID){
        this.userID = userID;
    }
}
