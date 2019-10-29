package com.example.scotiabankpaymentsystem.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.scotiabankpaymentsystem.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.scotiabankpaymentsystem.data.model.BusinessOwner;
import com.example.scotiabankpaymentsystem.ui.login.LoginActivity;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //registerViewModel = new RegisterViewModel();
        final EditText emailEditText = findViewById(R.id.email);
        final EditText passwordEditText = findViewById(R.id.password);
        final EditText firstNameEditText = findViewById(R.id.First_Name);
        final EditText lastNameEditText = findViewById(R.id.lastName);
        final EditText addressEditText = findViewById(R.id.Address);
        final Button registerButton = findViewById(R.id.register);
        final ProgressBar loadingProgressBar = findViewById(R.id.loading);
        final Spinner roleSelectionSpinner = findViewById(R.id.role);
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<String>(RegisterActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.roles));
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roleSelectionSpinner.setAdapter(roleAdapter);

        mAuth = FirebaseAuth.getInstance();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
//                registerViewModel.register(emailEditText.getText().toString(),
//                        passwordEditText.getText().toString(),
//                        firstNameEditText.getText().toString(),
//                        lastNameEditText.getText().toString(),
//                        addressEditText.getText().toString());
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                //updating the firstbase system
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                BusinessOwner User = new BusinessOwner(firstNameEditText.getText().toString() + " " + lastNameEditText.getText().toString(), passwordEditText.getText().toString(), addressEditText.getText().toString());
                DatabaseReference myRef = database.getReference(firstNameEditText.getText().toString() + " " + lastNameEditText.getText().toString());
                HashMap<String, Object> myMap = new HashMap<String, Object>();
                myMap.put("Name",firstNameEditText.getText().toString() + " " + lastNameEditText.getText().toString());
                myMap.put("Address", addressEditText.getText().toString());
                myMap.put("Password", passwordEditText.getText().toString());
                myMap.put("Email", emailEditText.getText().toString());
                myMap.put("Role", roleSelectionSpinner.getSelectedItem().toString());
                myRef.setValue(addressEditText.getText().toString());
                myRef.updateChildren(myMap);

                mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loadingProgressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(RegisterActivity.this, "Hello, please log in here", Toast.LENGTH_LONG).show();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}
