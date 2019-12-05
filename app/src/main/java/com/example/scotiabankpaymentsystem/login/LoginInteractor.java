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
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeLogin;
import com.example.scotiabankpaymentsystem.Listener;
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

    //private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private APIFacadeLogin APIFacade;

    /**
     * Instantiates a new Login interactor.
     */
    public LoginInteractor(){
        APIFacade = new APIFacadeLogin();
    }

    /**
     * The interface On login finished listener.
     */
    public interface OnLoginFinishedListener {
        /**
         * On username error.
         */
        void onUsernameError();

        /**
         * On password error.
         */
        void onPasswordError();

        /**
         * On login error.
         */
        void onLoginError();

        /**
         * On sbo success.
         *
         * @param userID the user id
         */
        void onSBOSuccess(String userID);

        /**
         * On truck driver success.
         *
         * @param userID the user id
         */
        void onTruckDriverSuccess(String userID);

        /**
         * On coca cola success.
         *
         * @param userID the user id
         */
        void onCocaColaSuccess(String userID);
    }

    /**
     * This is when the user wants to login.
     *
     * @param loginActivity the login activity
     * @param username      the username
     * @param password      the password
     * @param listener      the listener
     * @param context       the context
     */
    public void login(Activity loginActivity, final String username, final String password, final OnLoginFinishedListener listener, final Context context) {
        if (TextUtils.isEmpty(username) || username.trim().isEmpty() || (username.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(username).matches())) {
            listener.onUsernameError();
        } else if (TextUtils.isEmpty(password) || password.trim().length() < 5) {
            //  || password.trim().length() < 5
            listener.onPasswordError();
        } else {
            APIFacade.login(loginActivity, username, password, listener, context);
        }
    }

}

