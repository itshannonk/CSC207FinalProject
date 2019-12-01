package com.example.scotiabankpaymentsystem.cocacola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesActivity;
import com.example.scotiabankpaymentsystem.cocacola.ccclickcustomers.CCClickActivity;

public class CCInvoiceManipulationChoice extends AppCompatActivity {
    private String userID;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(CCInvoiceManipulationChoice.this, CCClickActivity.class);
                        newIntent.putExtra("userID", userID);
                        System.out.println("ccClickCustomer" + userID);
                        startActivity(newIntent);
                        finish();
                    }
                }, 200);
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ccinvoice_manipulation_choice);
        findViewById(R.id.see_invoice).setOnClickListener(v -> navigateToSeeInvoice());
        findViewById(R.id.create_invoice).setOnClickListener(v -> navigateToActivityCreateInvoice());
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }
    public void navigateToActivityCreateInvoice() {
        Intent intent = new Intent(CCInvoiceManipulationChoice.this, CCCreateInvoice.class);
        System.out.println(userID + "manipulationChoice");
        intent.putExtra("userID", userID);
        startActivity(intent);
    }
    public void navigateToSeeInvoice(){
        Intent intent = new Intent(CCInvoiceManipulationChoice.this, SBOSeeInvoicesActivity.class);
        intent.putExtra("userID", userID);
        intent.putExtra("userType", "Coke");
        System.out.println("clicked the see invoices");
        startActivity(intent);
    }
}
