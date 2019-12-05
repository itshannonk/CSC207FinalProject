package com.example.scotiabankpaymentsystem.cocacola.seecustomers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.CCInvoiceSeeOrCreate;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeActivity;

/**
 * This is the Activity class for the CocaColaSee Customer.
 */
public class CCSeeCustomerActivity extends AppCompatActivity implements CCSeeCustomerView {
    String userID;
    private CCSeeCustomerPresenter presenter;

    @Override
    /**
     * This is the actionbar for the CCSeeCustomerActivity.
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(CCSeeCustomerActivity.this, CCHomeActivity.class);
                        newIntent.putExtra("userID", userID);
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
        setContentView(R.layout.activity_cc_seecustomers);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        presenter = new CCSeeCustomerPresenter(this, new CCSeeCustomerInteractor());
        displayCustomers();
    }

    @Override
    /**
     * This displays the customers.
     */
    public void displayCustomers() {
        presenter.displayCustomers(this);
    }

    @Override
    /**
     * This create the corresponding number of buttons for how many customers that are required
     * according to how many Customers are present so that CocaCola can select who they wish to see.
     */
    public void createButtons(String[] response) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);

        for(int i = 0; i<response.length;i++){
            //creating a new button
            Button btn = new Button(this);
            // setting both the text and tag
            String buttonText = "Customer UserID: " + response[i];
            btn.setText(buttonText);
            btn.setTag(buttonText);
            String finalID = response[i];
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(CCSeeCustomerActivity.this, CCInvoiceSeeOrCreate.class);
                    // when we switch to the SBODisplayInvoice, it will pass in both the userID and invoiceID
                    newIntent.putExtra("userID", finalID);
                    startActivity(newIntent);
                    finish();
                }
            });

            ll.addView(btn);
        }
    }
}
