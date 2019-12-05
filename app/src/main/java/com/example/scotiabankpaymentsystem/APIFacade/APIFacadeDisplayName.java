package com.example.scotiabankpaymentsystem.APIFacade;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.Listener;

public class APIFacadeDisplayName {
    /**
     * This is used to display the name of the user that has logged in and is presented in the home page.
     *
     * @param listener This is a Listener that is implemented in SBO and the Truck Driver.
     * @param userID This is the userID that is implemented in the Firebase Auth, which is the identifier of who is using the app.
     * @param context This is the Context of what the API should be associated with.
     */
    public void displayName(final Listener listener, final String userID, Context context) {
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_display_name?userID=" + userID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onPageSuccess(response);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }

    /**
     * Retrieve the logged in user basic information in the order of: Address, Email and Name
     *
     * @param listener This is a Listener that is implemented in SBO and the Truck Driver.
     * @param userID This is the userID that is implemented in the Firebase Auth, which is the identifier of who is using the app.
     * @param context This is the Context of what the API should be associated with.
     */
    public void retrieveUserInformation(final Listener listener, final String userID, Context context) {
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_user_information?userID=" + userID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onPageSuccess(response);
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
