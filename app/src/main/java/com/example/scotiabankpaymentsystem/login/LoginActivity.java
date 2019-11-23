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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeActivity;
import com.example.scotiabankpaymentsystem.driver.home.DriverHomeActivity;
import com.example.scotiabankpaymentsystem.registration.RegisterActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

/**
 * This is the Activity and it implements the View methods
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private TextInputEditText email;
    private TextInputEditText  password;
    private ProgressBar progressBar;
    private LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email_edit_text);
        password = findViewById(R.id.password_edit_text);
        progressBar = findViewById(R.id.loading);
        findViewById(R.id.next_button).setOnClickListener(v -> validateCredentials());
        findViewById(R.id.cancel_button).setOnClickListener(v -> clearTextFields());
        findViewById(R.id.signup_tab).setOnClickListener(v -> switchTabs());
        presenter = new LoginPresenter(this, new LoginInteractor());
        testingFunctions();

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {
        email.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToSBOHome() {
        startActivity(new Intent(this, SBOHomeActivity.class));
        Toast.makeText(LoginActivity.this, "Welcome! :)",
                Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void navigateToTruckDriverHome() {
        startActivity(new Intent(this, DriverHomeActivity.class));
        Toast.makeText(LoginActivity.this, "Welcome! :)",
                Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void navigateToCocaColaHome() {
        startActivity(new Intent(this, CCHomeActivity.class));
        Toast.makeText(LoginActivity.this, "Welcome! :)",
                Toast.LENGTH_LONG).show();
        finish();
    }

    private void validateCredentials() {
        presenter.validateCredentials(this, email.getText().toString(), password.getText().toString());
    }

    private void clearTextFields() {
        email.getText().clear();
        password.getText().clear();
    }

    private void register() {
//        showProgress();
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void switchTabs(){
        register();
    }
    public void testingFunctions(){
        RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/testing";
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                //You can test it by printing response.substring(0,500) to the screen.
                System.out.println(response);
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
