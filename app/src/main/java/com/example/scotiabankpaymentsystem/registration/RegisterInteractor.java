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
package com.example.scotiabankpaymentsystem.registration;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeRegisterInteractor;
import com.example.scotiabankpaymentsystem.Listener;
import com.google.firebase.database.FirebaseDatabase;


/**
 * The type Register interactor.
 */
public class RegisterInteractor {

    private APIFacadeRegisterInteractor APIFacade;

    /**
     * Instantiates a new Register interactor.
     */
    public RegisterInteractor(){
        APIFacade = new APIFacadeRegisterInteractor();
    }

    /**
     * The interface On register finished listener.
     */
    public interface OnRegisterFinishedListener {
        /**
         * On first name error.
         */
        void onFirstNameError();

        /**
         * On last name error.
         */
        void onLastNameError();

        /**
         * On password error.
         */
        void onPasswordError();

        /**
         * On email error.
         */
        void onEmailError();

        /**
         * On address error.
         */
        void onAddressError();

        /**
         * On role error.
         */
        void onRoleError();

        /**
         * On account already exists error.
         */
        void onAccountAlreadyExistsError();

        /**
         * On success.
         *
         * @param userID the user id
         */
        void onSuccess(String userID);
    }

    /**
     * Register.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param password  the password
     * @param email     the email
     * @param address   the address
     * @param role      the role
     * @param listener  the listener
     * @param context   the context
     */
    public void register(final String firstName, final String lastName, final String password, final String email, final String address, final String role, final OnRegisterFinishedListener listener, Context context) {
        APIFacade.register(firstName, lastName, password, email, address, role, listener, context);
    }
}

