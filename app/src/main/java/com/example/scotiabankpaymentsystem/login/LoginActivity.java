package com.example.scotiabankpaymentsystem.login;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.SBOActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.loading);
        username = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();
        findViewById(R.id.login).setOnClickListener(v -> validateCredentials());
        findViewById(R.id.register).setOnClickListener(v -> register());
        presenter = new LoginPresenter(this, new LoginInteractor());
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
        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, SBOActivity.class));
        Toast.makeText(LoginActivity.this, "Welcome! :)",
                Toast.LENGTH_LONG).show();

        finish();
    }

    private void validateCredentials() {
        presenter.validateCredentials(this, username.getText().toString(), password.getText().toString());

    }

    private void register() {
        showProgress();
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));

    }
}
