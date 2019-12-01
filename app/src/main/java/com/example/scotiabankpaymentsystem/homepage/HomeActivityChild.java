//package com.example.scotiabankpaymentsystem.businessowner.home;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.scotiabankpaymentsystem.R;
//import com.example.scotiabankpaymentsystem.model.Customer;
//import com.example.scotiabankpaymentsystem.businessowner.SBODisplayInvoice;
//import com.example.scotiabankpaymentsystem.businessowner.SBOSettings;
//import com.example.scotiabankpaymentsystem.homepage.HomeActivity;
//import com.example.scotiabankpaymentsystem.login.LoginActivity;
//import com.google.firebase.auth.FirebaseAuth;
//
///**
// * Frontend!!
// */
//public class HomeActivityChild extends HomeActivity implements SBOHomeView {
//
//
//    private Button settingsButton;
//    private Button seeStatusButton;
//    private Button logoutButton;
//    private TextView welcomeText;
//    private SBOHomePresenter presenter;
//    private Customer customer;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_businessowner_home);
//
//        settingsButton = findViewById(R.id.Setting);
//        seeStatusButton = findViewById(R.id.SeeStatus);
//        logoutButton = findViewById(R.id.LogOut);
//        welcomeText = findViewById(R.id.welcome_name);
//        findViewById(R.id.Setting).setOnClickListener(v -> navigateToActivitySettings());
//        findViewById(R.id.SeeStatus).setOnClickListener(v -> navigateToActivitySeeInvoices());
//        findViewById(R.id.LogOut).setOnClickListener(v -> navigateToActivityLogOut());
//        presenter = new SBOHomePresenter(this, new SBOHomeInteractor());
//
//        displayName();
//    }
//
//    @Override
//    public void navigateToActivitySettings() {
//        Intent intent = new Intent(HomeActivityChild.this, SBOSettings.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public void navigateToActivitySeeInvoices() {
//        Intent intent = new Intent(HomeActivityChild.this, SBODisplayInvoice.class);
//        startActivity(intent);
//    }
//
//    @Override
//    public void setDisplayName(String username) {
//        TextView welcomeText = findViewById(R.id.welcome_name);
//        String welcome = "Welcome " + username;
//        welcomeText.setText(welcome);
//    }
//}
