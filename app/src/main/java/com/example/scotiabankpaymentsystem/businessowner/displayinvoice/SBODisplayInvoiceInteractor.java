package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeInteractor;

public class SBODisplayInvoiceInteractor {
    interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onChangePaySuccess();
        void onChangePayError();
        void onInvoiceRetriveSuccess(String[] info);
        void onPay(String userID, String invoiceID, Context context);
    }


    public void changePayBoolean(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/set_invoice_status?userid="+userID+"&invoiceid="+invoiceID+"&statustype="+"paid"+"&newvalue="+true;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onChangePaySuccess();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                listener.onChangePayError();
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
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }

    }


}
