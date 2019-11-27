package com.example.scotiabankpaymentsystem.driver.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.ClickInvoices;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;
import com.example.scotiabankpaymentsystem.driver.DriverSeeDeliveries;
import com.example.scotiabankpaymentsystem.driver.DriverSetting;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.example.scotiabankpaymentsystem.model.Distributer;
//import com.google.firebase.auth.FirebaseAuth;

public class DriverHomeActivity extends AppCompatActivity implements DriverHomeView {

    private DriverHomePresenter presenter;
    private Button seeDeliveryButton;
    private Button settingsButton;
    private Button logoutButton;
    private TextView welcomeText;
    private Distributer distributer;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_home);

        settingsButton = findViewById(R.id.Setting);
        seeDeliveryButton = findViewById(R.id.SeeDelivery);
        logoutButton = findViewById(R.id.LogOut);
        welcomeText = findViewById(R.id.welcome_name);
        findViewById(R.id.Setting).setOnClickListener(v -> navigateToActivitySettings());
        findViewById(R.id.SeeDelivery).setOnClickListener(v -> navigateToActivitySeeDelivery());
        findViewById(R.id.LogOut).setOnClickListener(v -> navigateToActivityLogOut());
        presenter = new DriverHomePresenter(this, new DriverHomeInteractor());

        displayName();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void navigateToActivitySettings() {
        Intent intent = new Intent(DriverHomeActivity.this, DriverSetting.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }

    @Override
    public void navigateToActivitySeeDelivery() {
        Intent intent = new Intent(DriverHomeActivity.this, DriverSeeDeliveries.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
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
}
