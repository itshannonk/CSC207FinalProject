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
import com.google.firebase.database.FirebaseDatabase;

//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

public class RegisterInteractor {
    //private final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://csc207-tli.firebaseio.com");
    private boolean testing2 = false;

    interface OnRegisterFinishedListener {
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
                    System.out.println(response);

                    listener.onSuccess(response);
//                    Handler handler = new Handler();
//                    handler.postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//
//                        }
//                    }, 5000);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            ExampleRequestQueue.add(stringRequest);
        }

        //authenticating the user into firebase
//            firebaseAuth.createUserWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            // authenticated new user so this will so this will now be added to the firebase database
//                            if (task.isSuccessful() & !testing2) {
//                                DatabaseReference roleDatabaseReference = database.getReference(role);
//
//                                // this can be used to Observer design pattern since it will
//                                // check for changes and then take a snapshot
//                                //database.getReference(role).addValueEventListener(new ValueEventListener() {
//
//                                    /**
//                                     * This method will be invoked any time the data on the database changes.
//                                     * Additionally, it will be invoked as soon as we connect the listener, so that we can get an initial snapshot of the data on the database.
//                                     * @param dataSnapshot
//                                     */
//                                 //   @Override
//                                 //   public void onDataChange(DataSnapshot dataSnapshot) {
//                                        // get all of the children at this level.
//                                   //     Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//
//                                  //      for (DataSnapshot child : children) {
//                                  //          System.out.println(child.getValue() + "this is the work");
//                                   //     }
//                                  //  }
//
//                                  //  @Override
//                                   // public void onCancelled(DatabaseError databaseError) {
//
//                                  //  }
//                                //});
//
//
//                                // Sign in success, update UI with the signed-in user's information
//                                Toast.makeText(registerActivity, "Welcome " + firstName + " " + lastName, Toast.LENGTH_LONG).show();
//                                DatabaseReference myRef = roleDatabaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                                HashMap<String, Object> thisUserInfo = new HashMap<String, Object>();
//                                thisUserInfo.put("Name", firstName + " " + lastName);
//                                thisUserInfo.put("Address", address);
//                                thisUserInfo.put("Password", password);
//                                thisUserInfo.put("Email", email);
//                                Gson gson = new Gson();
//
//                                // this is converting the invoice to a json format
//                                // which will make it a string and then it can be inputed
//                                if(role.equals("Business Owner")) {
//                                    Invoice invoice = new Invoice();
//                                    String myJSON = gson.toJson(invoice);
//
//                                    // get second invoice
//                                    Invoice invoice2 = new Invoice();
//                                    String myJSON2 = gson.toJson(invoice);
//
//                                    // third invoice mock
//                                    Invoice invoice3 = new Invoice();
//                                    String myJSON3 = gson.toJson(invoice);
//
//                                    // fourth invoice mock
//                                    Invoice invoice4 = new Invoice();
//                                    String myJSON4 = gson.toJson(invoice);
//                                    //
//                                    HashMap<String, String> invoiceList = new HashMap<String, String>();
//                                    invoiceList.put(Integer.toString(invoice.getId()), myJSON);
//                                    invoiceList.put(Integer.toString(invoice2.getId()), myJSON2);
//                                    invoiceList.put(Integer.toString(invoice3.getId()), myJSON3);
//                                    invoiceList.put(Integer.toString(invoice4.getId()), myJSON4);
//                                    thisUserInfo.put("Invoices", invoiceList);
//                                    database.getReference("Invoices").setValue(invoiceList);
//                                }
//                                else{
//
//                                    Customer customer = new Customer();
//                                    String myJSON = gson.toJson(customer);
//
//
//                                    Customer customer2 = new Customer();
//                                    String myJSON2 = gson.toJson(customer);
//
//                                    Customer customer3 = new Customer();
//                                    String myJSON3 = gson.toJson(customer);
//
//                                    Customer customer4 = new Customer();
//                                    String myJSON4 = gson.toJson(customer);
//
//                                    HashMap<String, String> invoiceList = new HashMap<String, String>();
//                                    invoiceList.put(Integer.toString(customer.getId()), myJSON);
//                                    invoiceList.put(Integer.toString(customer2.getId()), myJSON2);
//                                    invoiceList.put(Integer.toString(customer3.getId()), myJSON3);
//                                    invoiceList.put(Integer.toString(customer4.getId()), myJSON4);
//                                    thisUserInfo.put("Customers", invoiceList);
//                                }
//                                //thisUserInfo.put("Role", role);
//                                myRef.updateChildren(thisUserInfo);
//                                testing2 = true;
//                                listener.onSuccess();
//                            } else {
//                                // If sign in fails, display a message to the user. This includes if the user already has an email.
//                                Toast.makeText(registerActivity, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                                listener.onAccountAlreadyExistsError();
//                            }
//                        }
//                    });
    }
}

