package com.example.scotiabankpaymentsystem.cocacola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeActivity;

public class CCClickCustomers extends AppCompatActivity {
    String userID;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(CCClickCustomers.this, CCHomeActivity.class);
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
        System.out.println(userID + "this is the clickCustomer userID");

        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_customers";
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //returns all the invoice ID's in a string so it now has to be parsed
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                createButtons(IDs);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }

    public void createButtons(String[] response){
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);

        for(int i = 0; i<response.length;i++){
            System.out.println("this is how many buttons there are");
            //creating a new button
            Button btn = new Button(this);
            // setting both the text and tag
            btn.setText("Customer UserID: " + response[i]);
            btn.setTag("Customer UserID: " + response[i]);
            String finalID = response[i];
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(CCClickCustomers.this, CCInvoiceManipulationChoice.class);
                    // when we switch to the SBOSeeOrder, it will pass in both the userID and invoiceID
                    System.out.println(userID + "inside the click for clickCustomer");
                    newIntent.putExtra("userID", finalID);
                    startActivity(newIntent);
                }
            });

            ll.addView(btn);
        }

    }
}
