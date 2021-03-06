package com.example.scotiabankpaymentsystem.cocacola;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesActivity;
import com.example.scotiabankpaymentsystem.cocacola.createinvoice.CCCreateInvoiceActivity;
import com.example.scotiabankpaymentsystem.cocacola.seecustomers.CCSeeCustomerActivity;
import com.example.scotiabankpaymentsystem.userinfo.UserInformationActivity;

public class CCInvoiceSeeOrCreate extends AppCompatActivity {
    private String userID;


    /**
     * This is the actionbar for the CocaColaSeeOrCreate.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_invoice_seeorcreate);
        findViewById(R.id.see_invoice).setOnClickListener(v -> navigateToSeeInvoice());
        findViewById(R.id.create_invoice).setOnClickListener(v -> navigateToActivityCreateInvoice());
        findViewById(R.id.see_customer_info).setOnClickListener(v -> navigateToActivityCustomerInfo());
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }


    /**
     * Navigate to CCCreateInvoiceActivity and puts in the UserID that was originally passed on from
     * the login or registration.
     */
    public void navigateToActivityCreateInvoice() {

        Intent intent = new Intent(CCInvoiceSeeOrCreate.this, CCCreateInvoiceActivity.class);
        intent.putExtra("userID", userID);
        startActivity(intent);
    }

    public void navigateToActivityCustomerInfo() {

        Intent intent = new Intent(CCInvoiceSeeOrCreate.this, UserInformationActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("userType", "Coke");
        startActivity(intent);
    }

    /**
     * Navigate to SBOSeeInvoiceActivity and puts in the UserID that was originally passed on from
     * the login or registration.
     */
    public void navigateToSeeInvoice(){
        Intent intent = new Intent(CCInvoiceSeeOrCreate.this, SBOSeeInvoicesActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("userType", "Coke");
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(CCInvoiceSeeOrCreate.this, CCSeeCustomerActivity.class);
                        newIntent.putExtra("userID", userID);
                        startActivity(newIntent);
                        finish();
                    }
                }, 200);
                break;
        }
        return true;
    }
}
