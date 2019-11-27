package com.example.scotiabankpaymentsystem.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
//import com.google.firebase.auth.FirebaseAuth;


public class DriverSeeDeliveries extends AppCompatActivity {
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_seedeliveries);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");

        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_assigned_customers?userID=" + userID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("backend works!");
                //returns all customers and selected invoiced that they were assigned
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                createButtons(IDs);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                System.out.println("backend doesn't work :(");
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }

    // for now we're assume driver already has customers assigned to him
    public void createButtons(String[] response){
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearlayout);


        for(int i = 0; i<response.length;i++){
            //creating a new button
            Button btn = new Button(this);
            // setting both the text and tag
            btn.setText(response[i]);
            btn.setTag(response[i]);

            String finalID = response[i];

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent newIntent = new Intent(DriverSeeDeliveries.this, DriverDisplayInvoice.class);
                    // when we switch to the SBOSeeOrder, it will pass in both the userID and invoiceID
                    newIntent.putExtra("userID", finalID);
                    startActivity(newIntent);
                }
            });

            ll.addView(btn);
        }

    }
}
