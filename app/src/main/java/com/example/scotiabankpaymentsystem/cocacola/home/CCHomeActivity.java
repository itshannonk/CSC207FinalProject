package com.example.scotiabankpaymentsystem.cocacola.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.seecustomers.CCSeeCustomerActivity;
import com.example.scotiabankpaymentsystem.login.LoginActivity;

/**
 * This is the CocaCola's home
 */
public class CCHomeActivity extends AppCompatActivity implements CCHomeView {

    private CCHomePresenter presenter;
    private Button seeInvoicesButton;
    private Button logoutButton;
    private TextView welcomeText;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_home);

        seeInvoicesButton = findViewById(R.id.see_all_customers);
        logoutButton = findViewById(R.id.LogOut);
        welcomeText = findViewById(R.id.welcome_name);
        findViewById(R.id.see_all_customers).setOnClickListener(v -> navigateToActivitySeeCustomer());
        findViewById(R.id.LogOut).setOnClickListener(v -> navigateToActivityLogOut());
        presenter = new CCHomePresenter(this, new CCHomeInteractor());
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        System.out.print(userID + "userid!!!!!!!!!!!");
        displayName();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    /**
     * Navigate the intents to CCSeeCustomer while being able to pass on the necessary information
     * such as UserID.
     */
    public void navigateToActivitySeeCustomer() {
        final Handler handler = new Handler();
        Intent newIntent = new Intent(CCHomeActivity.this, CCSeeCustomerActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
                Toast.makeText(CCHomeActivity.this, "Welcome! :)",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }, 200);
    }

    @Override
    /**
     * This is the ability to LogOut for the user.
     */
    public void navigateToActivityLogOut() {
        //clear session between app and FireBase database
        //FirebaseAuth.getInstance().signOut();
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
    /**
     * This retrieves name of the user that is logged in.
     */
    public void displayName() {
        presenter.displayName(userID, this);
    }

    @Override
    /**
     * This sets the name of the user that is logged in and puts it on display on the home page.
     */
    public void setDisplayName(String username) {
        TextView welcomeText = findViewById(R.id.welcome_name);
        String welcome = "Welcome CocaCola";
        welcomeText.setText(welcome);
    }
}
