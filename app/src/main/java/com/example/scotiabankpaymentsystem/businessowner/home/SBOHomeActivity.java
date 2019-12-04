package com.example.scotiabankpaymentsystem.businessowner.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesActivity;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.example.scotiabankpaymentsystem.userinfo.UserInformationActivity;

/**
 * Small Business Owner's Home Page (Frontend)
 */
public class SBOHomeActivity extends AppCompatActivity implements SBOHomeView {

    private Button seeInvoicesButton;
    private Button seeUserInfoButton;

    private Button logoutButton;
    private TextView welcomeText;
    private SBOHomePresenter presenter;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_home);

        seeInvoicesButton = findViewById(R.id.SeeInvoices);
        seeUserInfoButton = findViewById(R.id.SeeInfo);
        logoutButton = findViewById(R.id.LogOut);
        welcomeText = findViewById(R.id.welcome_name);

        seeInvoicesButton.setOnClickListener(v -> navigateToActivitySeeInvoices());
        seeUserInfoButton.setOnClickListener(v -> navigateToActivitySeeUserInformation());
        logoutButton.setOnClickListener(v -> navigateToActivityLogOut());

        presenter = new SBOHomePresenter(this, new SBOHomeInteractor());

        getUserID();
        displayName();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Navigate to SeeInvoices
     */
    @Override
    public void navigateToActivitySeeInvoices() {
        // go to SeeInvoices page
        Intent newIntent = new Intent(SBOHomeActivity.this, SBOSeeInvoicesActivity.class);

        // pass in userID and userType to SeeInvoices page
        newIntent.putExtra("userID", userID);
        newIntent.putExtra("userType", "SBO");
        startActivity(newIntent);

        // close SBO home page
        finish();
    }

    /**
     * Navigate to SeeUSerInformation
     */
    @Override
    public void navigateToActivitySeeUserInformation() {
        // go to SeeInvoices page
        Intent newIntent = new Intent(SBOHomeActivity.this, UserInformationActivity.class);

        // pass in userID to SeeInvoices page
        newIntent.putExtra("userID", userID);
        startActivity(newIntent);

        // close SBO home page
        finish();
    }


    /**
     * Navigate the the Login page
     */
    @Override
    public void navigateToActivityLogOut() {
        // go back to the original login page
        Intent intent = new Intent(SBOHomeActivity.this, LoginActivity.class);

        // erases the history of pages from last session
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

        // close the SBO home page
        Toast.makeText(getApplicationContext(), "Goodbye :)", Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * Queries firebase for the name of the current user
     */
    @Override
    public void displayName() {
        presenter.displayName(userID, this);
    }

    /**
     * Displays the name of the current user
     *
     * @param username the first name and last name of the current user
     */
    @Override
    public void setDisplayName(String username) {
        welcomeText = findViewById(R.id.welcome_name);
        String welcome = "Welcome " + username;
        welcomeText.setText(welcome);
    }

    /**
     * Retrieves the userID from the previous activity
     */
    private void getUserID() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }
}
