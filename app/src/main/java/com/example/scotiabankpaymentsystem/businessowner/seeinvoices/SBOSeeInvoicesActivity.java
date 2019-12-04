package com.example.scotiabankpaymentsystem.businessowner.seeinvoices;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceActivity;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;
import com.example.scotiabankpaymentsystem.cocacola.CCInvoiceSeeOrCreate;

public class SBOSeeInvoicesActivity extends AppCompatActivity implements SBOSeeInvoicesView {
    private String userID;
    private SBOSeeInvoicesPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_seeinvoices);
        presenter = new SBOSeeInvoicesPresenter(this, new SBOSeeInvoicesInteractor());

        // receiving the information that was sent from the previous page/activity
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

    @Override
    public void createButtons(String[] response) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        if (response[0].equals("")){
            return;
        }
        for (int i = 0; i < response.length; i++) {
            //creating a new button
            Button btn = new Button(this);
            // setting both the text and tag
            btn.setText("Invoice Number: " + response[i]);
            btn.setTag("Invoice Number: " + response[i]);
            String finalID = response[i];
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(SBOSeeInvoicesActivity.this, SBODisplayInvoiceActivity.class);
                    Intent intent = getIntent();
                    // when we switch to the SBODisplayInvoice, it will pass in both the userID and invoiceID
                    newIntent.putExtra("userID", userID);
                    newIntent.putExtra("invoiceID", finalID);
                    newIntent.putExtra("userType", intent.getStringExtra("userType"));
                    startActivity(newIntent);
                    finish();
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
                Intent intent = getIntent();
                if ((intent.getStringExtra("userType")).equals("SBO")) {
                    Intent newIntent = new Intent(SBOSeeInvoicesActivity.this, SBOHomeActivity.class);
                    newIntent.putExtra("userID", userID);
                    startActivity(newIntent);
                } else {
                    Intent newIntent = new Intent(SBOSeeInvoicesActivity.this, CCInvoiceSeeOrCreate.class);
                    newIntent.putExtra("userID", userID);
                    startActivity(newIntent);
                }
                finish();

                return true;
        }
        return false;
    }

    private void getUserID() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }
}
