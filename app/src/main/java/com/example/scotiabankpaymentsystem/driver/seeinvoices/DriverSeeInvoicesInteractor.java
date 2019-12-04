package com.example.scotiabankpaymentsystem.driver.seeinvoices;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

class DriverSeeInvoicesInteractor {

    interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onInvoicesRetrievalSuccess(String[] IDs);
    }

    void retrieveInvoiceID(final DriverSeeInvoicesInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_assigned_invoices?userID=" + userID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //returns all customers and selected invoiced that they were assigned
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                listener.onInvoicesRetrievalSuccess(IDs);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }
}
