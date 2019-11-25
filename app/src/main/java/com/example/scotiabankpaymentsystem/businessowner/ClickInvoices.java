package com.example.scotiabankpaymentsystem.businessowner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;

public class ClickInvoices extends AppCompatActivity {
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("DID IT ENETER");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_invoices);
        //this is the go back button
        findViewById(R.id.GoBack).setOnClickListener(v -> navigateToHome());


        // receiving the information that was sent from the previous page/activity
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        System.out.println(userID + "userID");

        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_list_of_invoice_ids?userID="+userID;
        System.out.println(url);
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("qwhy dfkjskdjfsldkfsd");
                System.out.println(response);
                //returns all the invoice ID's in a string so it now has to be parsed
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                createButtons(IDs);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                System.out.println(error);
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }
    public void createButtons(String[] response){
        System.out.println(response);
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
    public void navigateToHome() {
        Intent newIntent = new Intent(ClickInvoices.this, SBOHomeActivity.class);
        newIntent.putExtra("userID", userID);
        startActivity(newIntent);
    }
}
