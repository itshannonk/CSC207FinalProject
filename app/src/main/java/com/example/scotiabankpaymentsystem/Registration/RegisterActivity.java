package com.example.scotiabankpaymentsystem.Registration;

import android.content.Intent;
import android.graphics.Color;
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
import com.example.scotiabankpaymentsystem.businessowner.SBOActivity;
import com.example.scotiabankpaymentsystem.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

// TODO: MVP model for Register
// TODO: Add constraints to data entered by user (ie cannot be empty)
// TODO: Check if email is already in database! (idk if Firebase already have this feature??)
/**
 * Class that registers the users when they sign up and this uses Firebase's authentication
 */
public class RegisterActivity extends AppCompatActivity implements RegistrationView{

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
        System.out.println("it has created the register page");

        progressBar = findViewById(R.id.loadingRegister);
        firstName = findViewById(R.id.First_Name);
        lastName = findViewById(R.id.lastName);
        password = findViewById(R.id.password);
        address = findViewById(R.id.Address);
        email = findViewById(R.id.email);

        registerButton = findViewById(R.id.register);
        loadingProgressBar = findViewById(R.id.loading);

        roleSelectionSpinner = findViewById(R.id.role);
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roles));
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSelectionSpinner.setAdapter(roleAdapter);
        System.out.println("this is the role adapter " + roleSelectionSpinner.getSelectedItem().toString());
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
        findViewById(R.id.register).setOnClickListener(v -> {
            register();
        });
        presenter = new RegisterPresenter(this, new RegisterInteractor());
    }

    private void register() {
        System.out.println("it is trying to register");
        System.out.println("this is the role " + role);
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
        ((TextView)roleSelectionSpinner.getSelectedView()).setError("Error message");

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
        startActivity(new Intent(this, SBOActivity.class));
        Toast.makeText(RegisterActivity.this, "Welcome, " + firstName.getText().toString().trim() + " " + lastName.getText().toString().trim() + " :)",
                Toast.LENGTH_LONG).show();
        finish();
    }
}
