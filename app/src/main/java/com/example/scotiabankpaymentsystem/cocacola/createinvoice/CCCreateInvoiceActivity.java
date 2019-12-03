package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.CCInvoiceManipulationChoice;

public class CCCreateInvoiceActivity extends AppCompatActivity implements CCCreateInvoiceView {
    private static int invoiceID = 0;
    private String userID;
    private EditText item;
    private EditText price;
    private EditText quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_createinvoice);
        findViewById(R.id.create_invoice).setOnClickListener(v -> createInvoice());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent newIntent = new Intent(CCCreateInvoiceActivity.this, CCInvoiceManipulationChoice.class);
                newIntent.putExtra("userID", userID);
                System.out.println("ccClickCustomer" + userID);
                startActivity(newIntent);
                finish();
                break;
        }
        return true;
    }


    private void inputError() {
        Toast.makeText(getApplicationContext(), "Enter all the inputs!", Toast.LENGTH_LONG).show();
    }

    private void createInvoice() {
        System.out.println("why wont it work");
        item = findViewById(R.id.input_item_name);
        price = findViewById(R.id.input_price);
        quantity = findViewById(R.id.input_quantity);

        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        invoiceID += 1;
        if (item.equals("") || price.equals("") || quantity.equals("")) {
            inputError();
        } else {
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
