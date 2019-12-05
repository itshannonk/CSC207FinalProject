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
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeActivity;
import com.example.scotiabankpaymentsystem.driver.home.DriverHomeActivity;
import com.example.scotiabankpaymentsystem.registration.RegisterActivity;
import com.google.android.material.textfield.TextInputEditText;

/**
 * This is the Activity and it implements the View methods
 */
public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText email;
    private EditText password;
    private ProgressBar progressBar;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        email = findViewById(R.id.email_edit_text);
        password = findViewById(R.id.password_edit_text);
        progressBar = findViewById(R.id.loading);
        findViewById(R.id.next_button).setOnClickListener(v -> validateCredentials());
        findViewById(R.id.cancel_button).setOnClickListener(v -> clearTextFields());
        findViewById(R.id.signup_tab).setOnClickListener(v -> switchTabs());
        presenter = new LoginPresenter(this, new LoginInteractor());

    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    /**
     * This restores the saved state that was previous saved.
     */
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        email.setText(savedInstanceState.get("email").toString());
        password.setText(savedInstanceState.get("password").toString());
    }

    @Override
    /**
     * This saves the instance state.
     */
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("email", email.getText().toString().trim());
        outState.putString("password", password.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    /**
     * This shows the progress bar.
     */
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    /**
     * This hides the progress bar.
     */
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    /**
     * This causes the userName Error when the username is not complete or not in firebase.
     */
    public void setUsernameError() {
        email.setError(getString(R.string.username_error));
    }

    @Override
    /**
     * This is the password error when the username exists in firebase but the password is wrong.
     */
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    /**
     * This navigates to SBOHomeActivity.
     */
    public void navigateToSBOHome(String userID) {
        final Handler handler = new Handler();
        Intent newIntent = new Intent(this, SBOHomeActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
                Toast.makeText(LoginActivity.this, "Welcome! :)",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }, 200);
    }

    @Override
    /**
     * This navigates to DriverHomeActivity.
     */
    public void navigateToTruckDriverHome(String userID) {
        final Handler handler = new Handler();
        Intent newIntent = new Intent(this, DriverHomeActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
                Toast.makeText(LoginActivity.this, "Welcome! :)",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }, 200);
    }

    @Override
    /**
     * This navigates to CCHomeActivity.
     */
    public void navigateToCocaColaHome(String userID) {
        final Handler handler = new Handler();
        Intent newIntent = new Intent(this, CCHomeActivity.class);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms

                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
                Toast.makeText(LoginActivity.this, "Welcome! :)",
                        Toast.LENGTH_LONG).show();
                finish();
            }
        }, 200);
    }

    private void validateCredentials() {
        presenter.validateCredentials(this, email.getText().toString(), password.getText().toString(), this);
    }

    private void clearTextFields() {
        email.getText().clear();
        password.getText().clear();
    }

    /**
     * This is where you register and where the method is called when you register.
     */
    private void register() {
//        showProgress();
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        finish();
    }
    /**
     * This is when you want to switchTabs.
     */
    private void switchTabs() {
        register();
    }
}


