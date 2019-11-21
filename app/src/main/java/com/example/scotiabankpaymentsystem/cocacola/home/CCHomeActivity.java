package com.example.scotiabankpaymentsystem.cocacola.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.CocaColaSettings;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;

public class CCHomeActivity extends AppCompatActivity implements CCHomeView {

    private CCHomePresenter presenter;
    private Button seeInvoicesButton;
    private Button settingsButton;
    private Button logoutButton;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cocacola_home);

        settingsButton = findViewById(R.id.Setting);
        seeInvoicesButton = findViewById(R.id.SeeInvoice);
        logoutButton = findViewById(R.id.LogOut);
        welcomeText = findViewById(R.id.welcome_name);
        findViewById(R.id.Setting).setOnClickListener(v -> navigateToActivitySettings());
        findViewById(R.id.SeeInvoice).setOnClickListener(v -> navigateToActivitySeeInvoice());
        findViewById(R.id.LogOut).setOnClickListener(v -> navigateToActivityLogOut());
        presenter = new CCHomePresenter(this, new CCHomeInteractor());

        displayName();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void navigateToActivitySettings() {
        Intent intent = new Intent(CCHomeActivity.this, CocaColaSettings.class);
        startActivity(intent);
    }

    @Override
    public void navigateToActivitySeeInvoice() {
        Intent intent = new Intent(CCHomeActivity.this, CocaColaSettings.class);
        startActivity(intent);
    }

    @Override
    public void navigateToActivityLogOut() {
        //clear session between app and FireBase database
        FirebaseAuth.getInstance().signOut();
        //go back to the original login page
        Intent intent = new Intent(CCHomeActivity.this, LoginActivity.class);
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
        presenter.displayName();
    }

    @Override
    public void setDisplayName(String username) {
        TextView welcomeText = findViewById(R.id.welcome_name);
        String welcome = "Welcome " + username;
        welcomeText.setText(welcome);
    }
}