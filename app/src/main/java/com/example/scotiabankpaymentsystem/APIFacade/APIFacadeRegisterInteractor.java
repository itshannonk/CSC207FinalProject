package com.example.scotiabankpaymentsystem.APIFacade;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.registration.RegisterInteractor;

public class APIFacadeRegisterInteractor {
    public void register(final String firstName, final String lastName, final String password, final String email, final String address, final String role, final RegisterInteractor.OnRegisterFinishedListener listener, Context context) {
        boolean noProblems = true;
        if (firstName.trim().isEmpty() || (firstName.contains("@"))) {
            listener.onFirstNameError();
            noProblems = false;
        }
        if (lastName.trim().isEmpty() || (lastName.contains("@"))) {
            listener.onLastNameError();
            noProblems = false;
        }
        if (password.trim().isEmpty() || password.length() < 6) {
            listener.onPasswordError();
            noProblems = false;
        }
        if (email.trim().isEmpty() || !(email.contains("@")) || !(email.toLowerCase().contains(".ca")) && !(email.toLowerCase().contains(".com"))) {
            listener.onEmailError();
            noProblems = false;
        }
        if (address.trim().isEmpty() && role.equals("a Business Owner")) {
            listener.onAddressError();
            noProblems = false;
        }
        if (role.equals("Are you...")) {
            // the user has not selected a role and it is still at the default of "choose your role"
            listener.onRoleError();
            noProblems = false;
        }
        if (noProblems) {
            com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
            String url;
            if (role.equals("a Business Owner")) {
                url = "https://us-central1-csc207-tli.cloudfunctions.net/create_user?address=" + address + "&email=" + email +
                        "&name=" + firstName + "%20" + lastName + "&password=" + password + "&role=" + "a%20Business%20Owner";
            } else {
                url = "https://us-central1-csc207-tli.cloudfunctions.net/create_user?address=" + "" + "&email=" + email +
                        "&name=" + firstName + "%20" + lastName + "&password=" + password + "&role=" + role;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    listener.onSuccess(response);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            ExampleRequestQueue.add(stringRequest);
        }
    }
}
