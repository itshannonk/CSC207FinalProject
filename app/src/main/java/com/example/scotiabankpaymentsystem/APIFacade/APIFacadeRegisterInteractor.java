package com.example.scotiabankpaymentsystem.APIFacade;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.registration.RegisterInteractor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class APIFacadeRegisterInteractor {
    /**
     *  This is the method that registers the user into the Firebase authentication and also logs
     *  them in once they are Firebase is completed with the authentication.
     *
     * @param firstName This is the first name that the user has inputed upon registration.
     * @param lastName This is the last name that the user has inputed upon registration.
     * @param password This is the password that the user has inputed upon registration.
     * @param email This is the email the user has inputed upon registration.
     * @param address This is the address the user has inputed upon registration.
     * @param role This is the role the user has chosen upon registration.
     * @param listener This is the listener that is associated with this method and will be used upon completion of the method.
     * @param context This is the Context in which the activity should occur in.
     */
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
                try {
                    // Encode the address into a supported url format
                    String encodedAddress = URLEncoder.encode(address, "UTF-8").replace("+", "%20");
                    url = "https://us-central1-csc207-tli.cloudfunctions.net/create_user?address=" + encodedAddress + "&email=" + email +
                            "&name=" + firstName + "%20" + lastName + "&password=" + password + "&role=" + "a%20Business%20Owner";
                } catch (UnsupportedEncodingException e) {
                    url = "https://us-central1-csc207-tli.cloudfunctions.net/create_user?address=" + "" + "&email=" + email +
                            "&name=" + firstName + "%20" + lastName + "&password=" + password + "&role=" + "a%20Business%20Owner";;
                }
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
