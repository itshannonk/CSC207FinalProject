package com.example.scotiabankpaymentsystem.cocacola.ccclickcustomers;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.CCInvoiceManipulationChoice;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeActivity;

public class CCClickActivity extends AppCompatActivity implements CCClickView{
    String userID;
    private CCClickPresenter presenter;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(CCClickActivity.this, CCHomeActivity.class);
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
        setContentView(R.layout.activity_cc_click_customers);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        presenter = new CCClickPresenter(this, new CCClickInteractor());
        displayCustomers();
    }

    @Override
    public void displayCustomers() {
        presenter.displayCustomers(this);
    }

    @Override
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
                    Intent newIntent = new Intent(CCClickActivity.this, CCInvoiceManipulationChoice.class);
                    // when we switch to the SBODisplayInvoice, it will pass in both the userID and invoiceID
                    System.out.println(userID + "inside the click for clickCustomer");
                    newIntent.putExtra("userID", finalID);
                    startActivity(newIntent);
                }
            });

            ll.addView(btn);
        }
    }
}
