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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeActivity;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeActivity;
import com.example.scotiabankpaymentsystem.driver.home.DriverHomeActivity;
import com.example.scotiabankpaymentsystem.login.LoginActivity;

/**
 * Class that registers the users when they sign up and this uses Firebase's authentication
 */
public class RegisterActivity extends AppCompatActivity implements RegistrationView {

    private ProgressBar progressBar;
    private EditText firstName;
    private EditText lastName;
    private EditText password;
    private EditText address;
    private EditText email;
    private Button registerButton;
    private Spinner roleSelectionSpinner;
    private String role;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        // it is setting the inputs to a variable for easier access
        progressBar = findViewById(R.id.loading);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        address = findViewById(R.id.delivered);
        email = findViewById(R.id.paid);


        registerButton = findViewById(R.id.next_button);

        // now it is setting the role to the associated selected role. It has to detect when the user changes the selected drop down item.
        roleSelectionSpinner = findViewById(R.id.role);
        roleSelectionSpinner.setPrompt("Are you...");

        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roles));


        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSelectionSpinner.setAdapter(roleAdapter);
        //when the user changes their input for the selected role
        roleSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                role = roleSelectionSpinner.getSelectedItem().toString();
                if (role.equals("a Business Owner")){
                    address.setEnabled(true);
                    findViewById(R.id.address_layout).setVisibility(View.VISIBLE);
                } else {
                    address.setEnabled(false);
                    findViewById(R.id.address_layout).setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                role = "";
            }
        });
        // the register button has to register if there is any click at anytime
        findViewById(R.id.next_button).setOnClickListener(v -> {
            register();
        });

        findViewById(R.id.cancel_button).setOnClickListener(v -> {
            clearTextFields();
        });

        findViewById(R.id.signin_tab).setOnClickListener(v -> {
            switchTabs();
        });


        // the presenter that is associated with the RegisterActivity
        presenter = new RegisterPresenter(this, new RegisterInteractor());
    }

    /**
     * restore instance state
     * @param savedInstanceState bundle
     */
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        firstName.setText(savedInstanceState.get("firstName").toString());
        lastName.setText(savedInstanceState.get("lastName").toString());
        password.setText(savedInstanceState.get("password").toString());
        email.setText(savedInstanceState.get("email").toString());
    }

    /**
     * save instance state
     * @param outState bundle
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("email", email.getText().toString().trim());
        outState.putString("password", password.getText().toString());
        outState.putString("firstName", firstName.getText().toString().trim());
        outState.putString("lastName", lastName.getText().toString().trim());
        super.onSaveInstanceState(outState);
    }

    /**
     * register
     */
    private void register() {
        presenter.registerUser(firstName.getText().toString(), lastName.getText().toString(), password.getText().toString(), email.getText().toString(), role, address.getText().toString(),  this);
        finish();
    }

    /**
     * progress for show
     */
    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    /**
     * progress for hide
     */
    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    /**
     * error for no input for first name
     */
    @Override
    public void setFirstnameError() {
        firstName.setError(getString(R.string.first_name_error));
    }

    /**
     * error for no choice of role
     */
    @Override
    public void setRoleError() {
        ((TextView) roleSelectionSpinner.getSelectedView()).setError("Select a role");
    }

    /**
     * error for no input for last name
     */
    @Override
    public void setLastnameError() {
        lastName.setError(getString(R.string.last_name_error));
    }

    /**
     * error for password
     */
    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    /**
     * error for account has exist
     */
    @Override
    public void setAccountAlreadyExistsError() {
        email.setError(getString(R.string.email_exists));
    }

    /**
     * email error
     */
    @Override
    public void setEmailError() {
        email.setError(getString(R.string.email_error));
    }

    /**
     * address error
     */
    @Override
    public void setAddressError() {
        address.setError(getString(R.string.address_error));
    }

    /**
     * navigate to activity home
     * @param userID the user id
     */
    @Override
    public void navigateToHome(String userID) {

        if (role.equals("a Business Owner")) {
            Intent newIntent = new Intent(this, SBOHomeActivity.class);
            newIntent.putExtra("userID", userID);
            startActivity(newIntent);

        } else if (role.equals("CocaCola")) {
            Intent newIntent = new Intent(this, CCHomeActivity.class);
            newIntent.putExtra("userID", userID);
            startActivity(newIntent);

        } else {
            Intent newIntent = new Intent(this, DriverHomeActivity.class);
            newIntent.putExtra("userID", userID);
            startActivity(newIntent);
        }
        Toast.makeText(RegisterActivity.this, "Welcome, " + firstName.getText().toString().trim() + " " + lastName.getText().toString().trim() + " :)",
                Toast.LENGTH_LONG).show();
        finish();
    }

    /**
     * switch tab
     */
    private void switchTabs(){
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }

    /**
     * clear Text Fields
     */
    private void clearTextFields() {
        firstName.getText().clear();
        lastName.getText().clear();
        address.getText().clear();
        email.getText().clear();
        password.getText().clear();
    }
}
