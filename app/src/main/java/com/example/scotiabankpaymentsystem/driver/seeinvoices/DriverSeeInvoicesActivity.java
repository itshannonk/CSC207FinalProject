package com.example.scotiabankpaymentsystem.driver.seeinvoices;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.driver.displayinvoice.DriverDisplayInvoiceActivity;
import com.example.scotiabankpaymentsystem.driver.home.DriverHomeActivity;

/**
 * This is the Activity for DriverSeeInvoices
 */
public class DriverSeeInvoicesActivity extends AppCompatActivity implements DriverSeeInvoicesView {
    private String userID;
    private String invoiceID;
    private String customerID;
    private DriverSeeInvoicesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_seeinvoices);
        presenter = new DriverSeeInvoicesPresenter(this, new DriverSeeInvoicesInteractor());

        getUserID();
        getInvoiceID();
        getCustomerID();
        retrieveInvoiceID(userID);
    }

    @Override
    /**
     * This retrieves the InvoiceID.
     */
    public void retrieveInvoiceID(String userID) {
        presenter.retrieveInvoiceID(userID, this);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * This creates the buttons for the Invoices that are present that the Driver needs to see
     * such that the number of buttons corresponds with this.
     * @param response The list of strings that corresponds with the Invoices that are present.
     */
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
                    Intent newIntent = new Intent(DriverSeeInvoicesActivity.this, DriverDisplayInvoiceActivity.class);
                    // when we switch to the SBODisplayInvoice, it will pass in both the userID and invoiceID
                    newIntent.putExtra("userID", userID);
                    newIntent.putExtra("invoiceID", invoiceID);
                    newIntent.putExtra("customerID", customerID);
                    startActivityForResult(newIntent, 1);
                    finish();
                }
            });
            linearLayout.addView(btn);
        }
    }

    // this override is to override the action bar back button so that it passes around the userID
    @Override
    /**
     * The actionbar for DriverSeeInvoicesActivity
     */
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

    /**
     * Get user id.
     */
    public void getUserID(){
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }

    /**
     * Get invoice id.
     */
    public void getInvoiceID(){
        Intent intent = getIntent();
        invoiceID = intent.getStringExtra("invoiceID");
    }

    /**
     * Get customer id.
     */
    public void getCustomerID(){
        Intent intent = getIntent();
        customerID = intent.getStringExtra("customerID");
    }
}
