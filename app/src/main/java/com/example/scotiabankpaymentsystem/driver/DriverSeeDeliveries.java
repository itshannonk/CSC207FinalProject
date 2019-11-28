package com.example.scotiabankpaymentsystem.driver;

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
import com.example.scotiabankpaymentsystem.driver.home.DriverHomeActivity;

import java.util.Dictionary;
//import com.google.firebase.auth.FirebaseAuth;


public class DriverSeeDeliveries extends AppCompatActivity {
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_seedeliveries);

        getUserID();

        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_assigned_invoices?userID=" + userID;
        System.out.println(url);
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                System.out.println("backend works!");
                //returns all customers and selected invoiced that they were assigned
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                createButtons(IDs);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.toString());
                //This code is executed if there is an error.
                System.out.println("backend doesn't work :(");
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }

    // for now we're assume driver already has customers assigned to him
    // this displays the list of customers
    // TODO: ALSO RETURN INVOICE ID
    public void createButtons(String[] response){
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);

        System.out.println("reached create buttons");
        for(int i = 0; i < response.length ; i++){
            //creating a new button
            Button btn = new Button(this);

            // IDs[1] = invoice ID, IDs[2] =
            String[] IDs = response[i].split(":");
            System.out.println(IDs);
            String invoiceID = IDs[0];
            String customerID = IDs[1];
            // setting both the text and tag
            System.out.println(IDs);
            btn.setText(invoiceID);
            btn.setTag(invoiceID);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(DriverSeeDeliveries.this, DriverDisplayInvoice.class);
                    // when we switch to the SBODisplayInvoice, it will pass in both the userID and invoiceID
                    newIntent.putExtra("userID", userID);
                    newIntent.putExtra("invoiceID", invoiceID);
                    newIntent.putExtra("customerID", customerID);
                    startActivityForResult(newIntent, 1);
                }
            });

            ll.addView(btn);
        }
    }

    public void getUserID(){
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }


    public void setUserID(String userID){
        this.userID = userID;
    }


    // this override is to override the action bar back button so that it passes around the userID
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(DriverSeeDeliveries.this, DriverHomeActivity.class);
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
