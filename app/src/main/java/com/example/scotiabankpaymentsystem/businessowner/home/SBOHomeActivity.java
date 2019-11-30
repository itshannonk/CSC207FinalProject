package com.example.scotiabankpaymentsystem.businessowner.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.SBOSeeOrders;
import com.example.scotiabankpaymentsystem.model.Customer;
import com.example.scotiabankpaymentsystem.businessowner.SBOSettings;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
//import com.google.firebase.auth.FirebaseAuth;

/**
 * Frontend!!
 */
public class SBOHomeActivity extends AppCompatActivity implements SBOHomeView {


    private Button settingsButton;
    private Button seeStatusButton;
    private Button logoutButton;
    private TextView welcomeText;
    private SBOHomePresenter presenter;
    private Customer customer;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_home);

        settingsButton = findViewById(R.id.Setting);
        seeStatusButton = findViewById(R.id.SeeStatus);
        logoutButton = findViewById(R.id.LogOut);
        welcomeText = findViewById(R.id.welcome_name);
        findViewById(R.id.Setting).setOnClickListener(v -> navigateToActivitySettings());
        findViewById(R.id.SeeStatus).setOnClickListener(v -> navigateToActivitySeeStatus());
        findViewById(R.id.LogOut).setOnClickListener(v -> navigateToActivityLogOut());
        presenter = new SBOHomePresenter(this, new SBOHomeInteractor());
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        displayName();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void navigateToActivitySettings() {
        Intent newIntent = new Intent(SBOHomeActivity.this, SBOSettings.class);
        newIntent.putExtra("userID", userID);
        startActivity(newIntent);
    }

    @Override
    public void navigateToActivitySeeStatus() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent newIntent = new Intent(SBOHomeActivity.this, SBOSeeOrders.class);
                newIntent.putExtra("userID", userID);
                newIntent.putExtra("userType", "SBO");
                startActivity(newIntent);
            }
        }, 200);
    }

    @Override
    public void navigateToActivityLogOut() {
        //clear session between app and FireBase database
        //FirebaseAuth.getInstance().signOut();
        //go back to the original login page
        Intent intent = new Intent(SBOHomeActivity.this, LoginActivity.class);
        //erases the history of pages from last session
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //close the SBO page
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
