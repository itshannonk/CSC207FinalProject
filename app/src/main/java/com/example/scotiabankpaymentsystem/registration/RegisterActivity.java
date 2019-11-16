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

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.SBOActivity;
import com.example.scotiabankpaymentsystem.cocacola.CocaColaActivity;
import com.example.scotiabankpaymentsystem.driver.DriverActivity;

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
    private ProgressBar loadingProgressBar;
    private Spinner roleSelectionSpinner;
    private String role;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // it is setting the inputs to a variable for easier access
        progressBar = findViewById(R.id.loadingRegister);
        firstName = findViewById(R.id.First_Name);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        address = findViewById(R.id.Address);
        email = findViewById(R.id.email);

        registerButton = findViewById(R.id.register);
        loadingProgressBar = findViewById(R.id.loading);

        // now it is setting the role to the associated selected role. It has to detect when the user changes the selected drop down item.
        roleSelectionSpinner = findViewById(R.id.role);
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roles));
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSelectionSpinner.setAdapter(roleAdapter);
        //when the user changes their input for the selected role
        roleSelectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                role = roleSelectionSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                role = "";
            }
        });
        // the register button has to register if there is any click at anytime
        findViewById(R.id.register).setOnClickListener(v -> {
            register();
        });
        // the presenter that is associated with the RegisterActivity
        presenter = new RegisterPresenter(this, new RegisterInteractor());
    }

    private void register() {
        presenter.registerUser(this, firstName.getText().toString(), lastName.getText().toString(), password.getText().toString(), email.getText().toString(), role, address.getText().toString());
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
    public void setFirstnameError() {
        firstName.setError(getString(R.string.first_name_error));
    }

    @Override
    public void setRoleError() {
        ((TextView) roleSelectionSpinner.getSelectedView()).setError("Select a role");
    }

    @Override
    public void setLastnameError() {
        lastName.setError(getString(R.string.last_name_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void setAccountAlreadyExistsError() {
        email.setError(getString(R.string.email_exists));
    }


    @Override
    public void setEmailError() {
        email.setError(getString(R.string.email_error));
    }

    @Override
    public void setAddressError() {
        address.setError(getString(R.string.address_error));
    }

    @Override
    public void navigateToHome() {
        if(role.equals("Business Owner")) {
            startActivity(new Intent(this, SBOActivity.class));
        }
        else if (role.equals("CocaCola")){
            startActivity(new Intent(this, CocaColaActivity.class));
        }
        else{
            startActivity(new Intent(this, DriverActivity.class));
        }
        Toast.makeText(RegisterActivity.this, "Welcome, " + firstName.getText().toString().trim() + " " + lastName.getText().toString().trim() + " :)",
                Toast.LENGTH_LONG).show();
        finish();
    }
}
