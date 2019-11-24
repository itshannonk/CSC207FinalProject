/*
 *
 *  * Copyright (C) 2018 Antonio Leiva Gordillo.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 * We modified the code.
 */

package com.example.scotiabankpaymentsystem.login;

import android.app.Activity;
import android.content.Context;
import android.net.http.RequestQueue;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Fragment;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This is the Model because it implements a use case (login)
 */
public class LoginInteractor{
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    interface OnLoginFinishedListener {
        void onUsernameError();
        void onPasswordError();
        void onLoginError();
        void onSBOSuccess();
        void onTruckDriverSuccess();
        void onCocaColaSuccess();
    }

    public void login(Activity loginActivity, final String username, final String password, final OnLoginFinishedListener listener, final Context context) {
        if (TextUtils.isEmpty(username) || username.trim().isEmpty() || (username.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(username).matches())) {
            listener.onUsernameError();
        } else if (TextUtils.isEmpty(password) || password.trim().length() < 5) {
            //  || password.trim().length() < 5
            listener.onPasswordError();
        } else {
            com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, "https://us-central1-csc207-tli.cloudfunctions.net/login_page_get?username="+username+"&password" + "=" + password, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //This code is executed if the server responds, whether or not the response contains data.
                    //The String 'response' contains the server's response.
                    //You can test it by printing response.substring(0,500) to the screen.
                    if(response.equals("Business Owner")){
                        listener.onSBOSuccess();
                    }
                    else if(response.equals("CocaCola")){
                        listener.onCocaColaSuccess();
                    }
                    else if(response.equals("")){
                        listener.onTruckDriverSuccess();
                    }
                    else{
                        Toast.makeText(loginActivity, "Login error",
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

