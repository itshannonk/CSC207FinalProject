package com.example.scotiabankpaymentsystem.cocacola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeActivity;

public class CCCreateInvoice extends AppCompatActivity {
    private static int invoiceID = 0;
    private String userID;
    private EditText item;
    private EditText price;
    private EditText quantity;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(CCCreateInvoice.this, CCInvoiceManipulationChoice.class);
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
        setContentView(R.layout.activity_cc_create_invoice);
        System.out.println("on create for create invoice");
        findViewById(R.id.create_invoice).setOnClickListener(v -> createInvoiceButtonClicked());

    }
    private void inputError(){
        Toast.makeText(getApplicationContext(), "Enter all the inputs!", Toast.LENGTH_LONG).show();
    }
    private void createInvoiceButtonClicked(){
        System.out.println("why wont it work");
        item = findViewById(R.id.input_item_name);
        price = findViewById(R.id.input_price);
        quantity = findViewById(R.id.input_quantity);

        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        invoiceID+=1;
        if(item.equals("")||price.equals("")||quantity.equals("")) {
            inputError();
        }
        else {
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/create_invoice?userid=" + userID + "&invoiceid=invoice"
                    + String.valueOf(invoiceID) + "&item=" + item.getText() + "&quantity=" + quantity.getText() + "&price=" + price.getText();
            System.out.println(url);
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println("you were able to create an invoice");
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }
    }
}
