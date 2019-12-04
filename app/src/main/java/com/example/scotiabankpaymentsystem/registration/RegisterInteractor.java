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


public class RegisterInteractor {

    private APIFacadeRegisterInteractor APIFacade;

    public RegisterInteractor(){
        APIFacade = new APIFacadeRegisterInteractor();
    }

    public interface OnRegisterFinishedListener {
        void onFirstNameError();

        void onLastNameError();

        void onPasswordError();

        void onEmailError();

        void onAddressError();

        void onRoleError();

        void onAccountAlreadyExistsError();

        void onSuccess(String userID);
    }

    public void register(final String firstName, final String lastName, final String password, final String email, final String address, final String role, final OnRegisterFinishedListener listener, Context context) {
        APIFacade.register(firstName, lastName, password, email, address, role, listener, context);
    }
}

