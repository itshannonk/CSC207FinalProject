package com.example.scotiabankpaymentsystem.businessowner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesActivity;

import android.os.Handler;
import android.widget.Toast;

/**
 * A page that contains SBO's orders
 */
public class SBODisplayInvoice extends AppCompatActivity {
    private String userID;
    private String invoiceID;
    private Button payButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_displayinvoice);
        // this will initiate the pay button
        payButton = (Button)findViewById(R.id.Pay);
        payButton.setOnClickListener(v -> changeBoolean());

        //retrieving the information passed on from the previous activity
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        invoiceID = intent.getStringExtra("invoiceID");

        TextView invoiceText = findViewById(R.id.totalPrice);
        String tempForInvoice = "this is the userID: " + userID + "this is the invoice ID: " + invoiceID;
        invoiceText.setText(tempForInvoice);

        if(!(intent.getStringExtra("userType").equals("SBO"))){
            payButton.setVisibility(View.GONE);
        }

        System.out.println("did it come here");
        //api calls
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_invoice_information?userID="+userID+"&invoiceID="+invoiceID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // setting the information in TextView according to the firebase
                System.out.println("did it come here22222222" + response);
                if(!(response.equals(""))) {
                    String[] invoiceIDs = response.split(",");
                    String inputText = "";
                    // showing if it is delivered
                    TextView invoiceTextDeliever = findViewById(R.id.Delivered);
                    inputText ="Delivered: " + invoiceIDs[0];
                    invoiceTextDeliever.setText(inputText);
                    //showing if it's issued
                    TextView invoiceTextIssued = findViewById(R.id.Issued);
                    inputText ="Issued: " + invoiceIDs[1];
                    invoiceTextIssued.setText(inputText);
                    //showing if it's paid
                    TextView invoiceTextPaid = findViewById(R.id.Paid);
                    inputText ="Paid: " + invoiceIDs[2];
                    invoiceTextPaid.setText(inputText);
                    System.out.println(invoiceIDs[2]+"prinout");
                    System.out.println(invoiceIDs[2].getClass()+"classsssss");
                    //checks if it has already been paid so it determines if the pay button should be there
                    if((invoiceIDs[2]).toLowerCase().equals("true")){
                        payButton.setVisibility(View.GONE);
                    }
                    //showing the total price
                    TextView invoiceTextPrice = findViewById(R.id.totalPrice);
                    inputText ="Total Price: " + invoiceIDs[3];
                    invoiceTextPrice.setText(inputText);
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }
    //changes the boolean for the payment
    private void changeBoolean(){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/set_invoice_status?userid="+userID+"&invoiceid="+invoiceID+"&statustype="+"paid"+"&newvalue="+true;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("did it come here for the pay button" + response);
                TextView invoiceTextPaid = findViewById(R.id.Paid);
                invoiceTextPaid.setText("Paid: True");
                // makes the payment option invisible if it has been paid
                payButton.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Your order has been processed.", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getApplicationContext(), "Oops, there has been an error in the payment", Toast.LENGTH_LONG).show();
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);

    }
    // for the back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(SBODisplayInvoice.this, SBOSeeInvoicesActivity.class);
                        newIntent.putExtra("userID", userID);
                        Intent intent = getIntent();
                        newIntent.putExtra("userType", intent.getStringExtra("userType"));
                        startActivity(newIntent);
                    }
                }, 200);
                break;
        }
        return true;
    }
}
