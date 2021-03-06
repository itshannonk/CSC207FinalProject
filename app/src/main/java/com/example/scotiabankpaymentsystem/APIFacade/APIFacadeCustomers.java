package com.example.scotiabankpaymentsystem.APIFacade;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.cocacola.seecustomers.CCSeeCustomerInteractor;

/**
 * APIFacadeCustomers is used for clarity and to simply the code for readability by implementing a Facade
 *
 */
public class APIFacadeCustomers {
    /**
     * This is what displays the list of buttons with the corresonding number of customers that are present in Firebase.
     *
     * @param listener The Listener that is used for the Customer User.
     * @param context The Context in which the API is associated with.
     */
    public void displayCustomers(final CCSeeCustomerInteractor.onDisplayDataFinishedListener listener, Context context){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_customers";
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //returns all the invoice ID's in a string so it now has to be parsed
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                listener.setCustomers(IDs);
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
