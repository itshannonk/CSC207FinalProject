package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesActivity;

public class UserInformationActivity extends AppCompatActivity implements UserInformationView {
    private String userID;
    private UserInformationPresenter presenter;
    private TextView welcomeText ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        presenter = new UserInformationPresenter(this, new UserInformationInteractor());
        //retrieving the information passed on from the previous activity
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        startSetUserInfo();
        displayContext();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void startSetUserInfo() {
        presenter.startSetUserInfo(userID, this);
    }

    @Override
    public void displayContext() {
        welcomeText = findViewById(R.id.welcome_name);
        String welcome = "See Account Information Here";
        welcomeText.setText(welcome);
    }

    @Override
    public void setUserInfo(String info) {
        String[] user_info = info.split(",");
        String inputText = "";
        // showing if it is delivered

        // address, email, name

        TextView addressText = findViewById(R.id.address);
        inputText = "Address: " + user_info[0];
        addressText.setText(inputText);

        TextView emailText = findViewById(R.id.email);
        inputText = "Email: " + user_info[1];
        emailText.setText(inputText);

        TextView nameText = findViewById(R.id.name);
        inputText = "Name: " + user_info[2];
        nameText.setText(inputText);
    }

    // for the back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent newIntent = new Intent(UserInformationActivity.this, SBOHomeActivity.class);
                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
                finish();
                break;

        }
        return true;
    }

}
