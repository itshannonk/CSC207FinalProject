package com.example.scotiabankpaymentsystem.login;
import android.app.Activity;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginInteractor {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private LoginView loginView;

    interface OnLoginFinishedListener {
        void onUsernameError();
        void onPasswordError();
        void onLoginError();
        void onSuccess();
    }

    public void login(Activity loginActivity, final String username, final String password, final OnLoginFinishedListener listener) {
        if (TextUtils.isEmpty(username) || username.trim().isEmpty() || (username.contains("@") && !Patterns.EMAIL_ADDRESS.matcher(username).matches())) {
            listener.onUsernameError();
            return;
        } else if (TextUtils.isEmpty(password)) {
            //  || password.trim().length() < 5
            listener.onPasswordError();
            return;
        } else {
            firebaseAuth.signInWithEmailAndPassword(username, password)
                    .addOnCompleteListener(loginActivity , new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()) {
                                Toast.makeText(loginActivity, task.getException().getMessage(),
                                        Toast.LENGTH_LONG).show();
                                listener.onLoginError();

                                return;
                            } else {
                                listener.onSuccess();
                            }
                        }
                    });

        }

    }
}

