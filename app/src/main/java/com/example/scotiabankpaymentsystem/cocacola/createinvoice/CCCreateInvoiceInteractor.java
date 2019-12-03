package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

class CCCreateInvoiceInteractor {
    interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onCreateInvoiceSuccess();

        void onCreateInvoiceError();
    }

    void createInvoice(final onDisplayDataFinishedListener listener, String item, String price, String quantity, String userID, int invoiceID, Context context) {
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        if (item.equals("") || price.equals("") || quantity.equals("")) {
            listener.onCreateInvoiceError();
        } else {
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/create_invoice?userid=" + userID + "&invoiceid=invoice"
                    + String.valueOf(invoiceID + 1) + "&item=" + item + "&quantity=" + quantity + "&price=" + price;
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    listener.onCreateInvoiceSuccess();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }
    }
}
