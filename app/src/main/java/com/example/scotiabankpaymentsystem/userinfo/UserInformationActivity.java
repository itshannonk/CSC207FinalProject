package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;
import com.example.scotiabankpaymentsystem.cocacola.CCInvoiceSeeOrCreate;
import com.example.scotiabankpaymentsystem.driver.displayinvoice.DriverDisplayInvoiceActivity;


/**
 * Displays the User's information (email, name, address)
 */
public class UserInformationActivity extends AppCompatActivity implements UserInformationView {
    private String userID;
    private String truckID;
    private String invoiceID;
    private UserInformationPresenter presenter;
    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_information);

        presenter = new UserInformationPresenter(this, new UserInformationInteractor());
        //retrieving the information passed on from the previous activity
        Intent intent = getIntent();

        if ((intent.getStringExtra("userType")).equals("SBO")) {
            userID = intent.getStringExtra("userID");
        } else if ((intent.getStringExtra("userType")).equals("Driver")) {
            userID = intent.getStringExtra("customerID");
            truckID = intent.getStringExtra("userID");
            invoiceID = intent.getStringExtra("invoiceID");
        } else {
            userID = intent.getStringExtra("userID");
        }

        startSetUserInfo();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Retrieves user information using presenter
     */
    @Override
    public void startSetUserInfo() {
        presenter.startSetUserInfo(userID, this);
    }

    /**
     * Receives the user's information and set in TextViews
     *
     * @param info the user's information
     */
    @Override
    public void setUserInfo(String info) {
        String[] user_info = info.split(",");
        String inputText = "";

        // address, email, name
        TextView addressText = findViewById(R.id.address);
        inputText = "Address: " + user_info[0];
        addressText.setText(inputText);

        TextView emailText = findViewById(R.id.paid);
        inputText = "Email: " + user_info[1];
        emailText.setText(inputText);

        TextView nameText = findViewById(R.id.issued);
        inputText = "Name: " + user_info[2];
        nameText.setText(inputText);
    }

    /**
     * for the back button
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = getIntent();
                // User information activity of CocaCola and SBO are shared
                // This allows us to go back the the correct page
                if ((intent.getStringExtra("userType")).equals("SBO")) {
                    Intent newIntent = new Intent(UserInformationActivity.this, SBOHomeActivity.class);
                    newIntent.putExtra("userID", userID);
                    startActivity(newIntent);
                } else if ((intent.getStringExtra("userType")).equals("Driver")) {
                    Intent newIntent = new Intent(UserInformationActivity.this, DriverDisplayInvoiceActivity.class);
                    newIntent.putExtra("customerID", userID);
                    newIntent.putExtra("userID", truckID);
                    newIntent.putExtra("invoiceID", invoiceID);
                    startActivity(newIntent);
                } else {
                    Intent newIntent = new Intent(UserInformationActivity.this, CCInvoiceSeeOrCreate.class);
                    newIntent.putExtra("userID", userID);
                    startActivity(newIntent);
                }
                finish();
                return true;
        }
        return true;
    }

}
