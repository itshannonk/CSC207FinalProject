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

/**
 * A page for SBO to see all their invoices
 */
public class SBOSeeInvoicesActivity extends AppCompatActivity implements SBOSeeInvoicesView {
    private String userID;
    private SBOSeeInvoicesPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_seeinvoices);

        presenter = new SBOSeeInvoicesPresenter(this, new SBOSeeInvoicesInteractor());

        getUserID();
        retrieveInvoiceIDs(userID);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    /**
     * Retrieves the InvoiceIDs of the current user
     *
     * @param userID the userID of the current logged in user
     */
    @Override
    public void retrieveInvoiceIDs(String userID) {
        presenter.retrieveInvoiceIDs(userID, this);
    }


    /**
     * Creates the buttons from the SBO's invoiceIDs
     *
     * @param IDs the invoice IDs of the current logged in user
     */
    @Override
    public void createButtons(String[] IDs) {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        if (IDs[0].equals("")) {
            return;
        }

        for (int i = 0; i < IDs.length; i++) {

            Button btn = new Button(this);

            // setting both the text and tag
            btn.setText("Invoice Number: " + IDs[i]);
            btn.setTag("Invoice Number: " + IDs[i]);
            String invoiceID = IDs[i];

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(SBOSeeInvoicesActivity.this, SBODisplayInvoiceActivity.class);
                    Intent intent = getIntent();

                    // when we switch to the SBODisplayInvoice, it will pass in both the userID, invoiceID and userType
                    newIntent.putExtra("userID", userID);
                    newIntent.putExtra("invoiceID", invoiceID);
                    newIntent.putExtra("userType", intent.getStringExtra("userType"));
                    startActivity(newIntent);
                    finish();
                }
            });
            linearLayout.addView(btn);
        }

    }

    /**
     * Overriding the functionality of the buttons on the action bar
     *
     * @param item a button on the actionbar
     * @return a boolean on whether the action was successful
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Back button
            case android.R.id.home:
                Intent intent = getIntent();
                // See invoices activity of CocaCola and SBO are shared
                // This allows us to go back the the correct page
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

    /**
     * Get the UserID from the previous page
     */
    private void getUserID() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }
}
