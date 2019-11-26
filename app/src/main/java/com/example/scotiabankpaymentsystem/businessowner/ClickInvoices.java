package com.example.scotiabankpaymentsystem.businessowner;

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
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;

public class ClickInvoices extends AppCompatActivity {
    private String userID;

    // this override is to override the action bar back button so that it passes around the userID
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(ClickInvoices.this, SBOHomeActivity.class);
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
        setContentView(R.layout.activity_businessowner_click_invoices);
        //this is the go back button


        // receiving the information that was sent from the previous page/activity
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_list_of_invoice_ids?userID="+userID;
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
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll);

        for(int i = 0; i<response.length;i++){
            System.out.println("this is how many buttons there are");
            //creating a new button
            Button btn = new Button(this);
            // setting both the text and tag
            btn.setText("Invoice Number: " + response[i]);
            btn.setTag("Invoice Number: " + response[i]);
            String finalID = response[i];
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(ClickInvoices.this, SBOSeeOrder.class);
                    // when we switch to the SBOSeeOrder, it will pass in both the userID and invoiceID
                    newIntent.putExtra("userID", userID);
                    newIntent.putExtra("invoiceID", finalID);
                    startActivity(newIntent);
                }
            });

            ll.addView(btn);
        }

    }
}
