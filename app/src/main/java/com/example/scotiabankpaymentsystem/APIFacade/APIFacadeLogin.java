package com.example.scotiabankpaymentsystem.APIFacade;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.Listener;
import com.example.scotiabankpaymentsystem.login.LoginInteractor;

public class APIFacadeLogin {
    /**
     * @param loginActivity This is the Activity that is being used.
     * @param username This is the username that the user has inputed.
     * @param password This is the password that the user has inputed.
     * @param listener This is the Listener when the User completes the login.
     * @param context This is the Context in which the Activity will occur in.
     */
    public void login(Activity loginActivity, final String username, final String password, final LoginInteractor.OnLoginFinishedListener listener, final Context context) {
        if (TextUtils.isEmpty(username) || username.trim().isEmpty() || (username.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(username).matches())) {
            listener.onUsernameError();
        } else if (TextUtils.isEmpty(password) || password.trim().length() < 5) {
            //  || password.trim().length() < 5
            listener.onPasswordError();
        } else {
            RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/login_page_get?email="+username+"&password" + "=" + password;
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //This code is executed if the server responds, whether or not the response contains data.
                    //The String 'response' contains the server's response.
                    //You can test it by printing response.substring(0,500) to the screen.
                    try{
                        String[] HTTPresponse = response.split(",");
                        if(HTTPresponse[0].equals("Business Owner")){
                            listener.onSBOSuccess(HTTPresponse[1]);
                        }
                        else if(HTTPresponse[0].equals("CocaCola")){
                            listener.onCocaColaSuccess(HTTPresponse[1]);
                        }
                        else if(HTTPresponse[0].equals("Truck Driver")){
                            listener.onTruckDriverSuccess(HTTPresponse[1]);
                        }
                        else{
                            Toast.makeText(loginActivity, "Login error",
                                    Toast.LENGTH_LONG).show();
                            listener.onLoginError();
                        }
                    }
                    catch(Exception e){
                        Toast.makeText(loginActivity, "Login Error",
                                Toast.LENGTH_LONG).show();
                        listener.onLoginError();
                    }
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                    //This code is executed if there is an error.
                    Toast.makeText(loginActivity, "Login error",
                            Toast.LENGTH_LONG).show();
                    listener.onLoginError();
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }
    }
}
