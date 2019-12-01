package com.example.scotiabankpaymentsystem.driver.seeinvoices;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.driver.DriverDisplayInvoice;
import com.example.scotiabankpaymentsystem.driver.home.DriverHomeActivity;


public class DriverSeeInvoicesActivity extends AppCompatActivity implements DriverSeeInvoicesView {
    private String userID;
    private DriverSeeInvoicesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_seeinvoices);
        presenter = new DriverSeeInvoicesPresenter(this, new DriverSeeInvoicesInteractor());

        getUserID();
        retrieveInvoiceID(userID);
    }

    @Override
    public void retrieveInvoiceID(String userID) {
        presenter.retrieveInvoiceID(userID, this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    public void createButtons(String[] response) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout);

        for (int i = 0; i < response.length; i++) {
            //creating a new button
            Button btn = new Button(this);

            // IDs[1] = invoice ID, IDs[2] =
            String[] IDs = response[i].split(":");
            String invoiceID = IDs[0];
            String customerID = IDs[1];
            // setting both the text and tag
            btn.setText(invoiceID);
            btn.setTag(invoiceID);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(DriverSeeInvoicesActivity.this, DriverDisplayInvoice.class);
                    // when we switch to the SBODisplayInvoice, it will pass in both the userID and invoiceID
                    newIntent.putExtra("userID", userID);
                    newIntent.putExtra("invoiceID", invoiceID);
                    newIntent.putExtra("customerID", customerID);
                    startActivityForResult(newIntent, 1);
                }
            });
            linearLayout.addView(btn);
        }
    }

    // this override is to override the action bar back button so that it passes around the userID
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent newIntent = new Intent(DriverSeeInvoicesActivity.this, DriverHomeActivity.class);
                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
                finish();
                break;
        }
        return true;
    }

    public void getUserID() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}