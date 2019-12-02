package com.example.scotiabankpaymentsystem.driver.displayinvoice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class DriverDisplayInvoiceInteractor {
    interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onChangeDeliveredSuccess();
        void onChangeDeliveredError();
        void onInvoiceRetriveSuccess(String[] info);
        void onDelivered(String userID, String invoiceID, Context context);
    }

    public void changeDeliveredBoolean(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/set_invoice_status?userid=" + userID + "&invoiceid=" + invoiceID + "&statustype=" + "delivered" + "&newvalue=" + true;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onChangeDeliveredSuccess();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                listener.onChangeDeliveredError();
                System.out.println("there is pay error");
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);

    }

    public void retrieveInvoice(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        { com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_invoice_information?userID="+userID+"&invoiceID="+invoiceID;
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //returns all the invoice info in a string so it now has to be parsed
                    String[] info = response.split(",");
                    //now we know how many invoices do we need to create the same number of buttons
                    listener.onInvoiceRetriveSuccess(info);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println("error for response");
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }

    }


}
