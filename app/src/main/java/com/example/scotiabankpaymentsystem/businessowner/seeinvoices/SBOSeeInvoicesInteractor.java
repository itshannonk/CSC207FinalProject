package com.example.scotiabankpaymentsystem.businessowner.seeinvoices;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

class SBOSeeInvoicesInteractor {
    interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onInvoicesRetrievalSuccess(String[] IDs);
    }

    void retrieveInvoiceID(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_list_of_invoice_ids?userID=" + userID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //returns all the invoice ID's in a string so it now has to be parsed
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                listener.onInvoicesRetrievalSuccess(IDs);
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



