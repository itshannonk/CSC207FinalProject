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
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.data.model.Invoice;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * This is the Model because it implements a use case (login)
 */
public class LoginInteractor {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    interface OnLoginFinishedListener {
        void onUsernameError();
        void onPasswordError();
        void onLoginError();
        void onSBOSuccess();
        void onTruckDriverSuccess();
        void onCocaColaSuccess();
    }

    public void login(Activity loginActivity, final String username, final String password, final OnLoginFinishedListener listener) {
        if (TextUtils.isEmpty(username) || username.trim().isEmpty() || (username.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(username).matches())) {
            listener.onUsernameError();
        } else if (TextUtils.isEmpty(password) || password.trim().length() < 5) {
            //  || password.trim().length() < 5
            listener.onPasswordError();
        } else {
            firebaseAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(loginActivity , task -> {
                        if(!task.isSuccessful()) {
                            Toast.makeText(loginActivity, task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            listener.onLoginError();
                        } else {
                            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

                            // Get the information of the current logged in user from database
                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

                                    if((dataSnapshot.child("Truck Driver").hasChild(userID))){
                                        System.out.println("entered truck");
                                        listener.onTruckDriverSuccess();
                                    }
                                    else if((dataSnapshot.child("Business Owner").hasChild(userID))){
                                        System.out.println("entered SBO");
                                        listener.onSBOSuccess();
                                    }
                                    else{
                                        System.out.println("entered coke");
                                        listener.onCocaColaSuccess();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });
                        }
                    });
        }
    }
}

