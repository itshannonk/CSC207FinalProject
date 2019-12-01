package com.example.scotiabankpaymentsystem.driver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.driver.seeinvoices.DriverSeeInvoicesActivity;

public class DriverDisplayInvoice extends AppCompatActivity {
    private String userID;
    private String invoiceID;
    private String customerID;
    private Button changeDeliveredButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_displayinvoice);

        getUserID();
        getInvoiceID();
        getCustomerID();
        System.out.println("customer id:" + customerID);
        System.out.println("invoiceid:" + invoiceID);
        changeDeliveredButton = findViewById(R.id.changeDeliveredStatus);
        findViewById(R.id.changeDeliveredStatus).setOnClickListener(v -> changeDeliveredStatus());

        //api calls
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_invoice_information?userID="+customerID+"&invoiceID="+invoiceID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // setting the information in TextView according to the firebase
                System.out.println(response + "ahh");
                System.out.println("did it come here22222222" + response);
                if(!(response.equals(""))) {
                    System.out.println("AHH");
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
                    if((invoiceIDs[2]).toLowerCase().equals("false")){
                        changeDeliveredButton.setVisibility(View.GONE);
                    }
                    if ((invoiceIDs[0]).toLowerCase().equals("true")){
                        changeDeliveredButton.setVisibility(View.GONE);

                    }
                    //showing the total price
                    TextView invoiceTextPrice = findViewById(R.id.totalPrice);
                    inputText ="Total Price: " + invoiceIDs[3];
                    invoiceTextPrice.setText(inputText);
                } else {
                    System.out.println("why did it come here?");
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


    public void changeDeliveredStatus(){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/set_invoice_status?userid=" + customerID + "&invoiceid=" + invoiceID + "&statustype=" + "delivered" + "&newvalue=" + true;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView invoiceTextPaid = findViewById(R.id.Delivered);
                invoiceTextPaid.setText("Delivered: True");
                // makes the payment option invisible if it has been paid
                changeDeliveredButton.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Ready for your next delivery?", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                Toast.makeText(getApplicationContext(), "Oops, there has been an error in the system", Toast.LENGTH_LONG).show();
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);    }

    public void getUserID(){
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }

    public void getInvoiceID(){
        Intent intent = getIntent();
        invoiceID = intent.getStringExtra("invoiceID");
    }

    public void getCustomerID(){
        Intent intent = getIntent();
        customerID = intent.getStringExtra("customerID");
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
                        Intent newIntent = new Intent(DriverDisplayInvoice.this, DriverSeeInvoicesActivity.class);
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
