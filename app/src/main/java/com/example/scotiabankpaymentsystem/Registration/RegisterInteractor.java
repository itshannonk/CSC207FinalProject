package com.example.scotiabankpaymentsystem.Registration;

import android.app.Activity;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterInteractor {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private boolean noProblems = true;
    interface OnRegisterFinishedListener{
        void onFirstNameError();
        void onLastNameError();
        void onPasswordError();
        void onEmailError();
        void onAddressError();
        void onRoleError();
        void onAccountAlreadyExistsError();
        void onSuccess();
    }
    public void register(Activity registerActivity, final String firstName, final String lastName, final String password, final String email, final String address, final String role, final OnRegisterFinishedListener listener) {
        noProblems = true;
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
        if (address.trim().isEmpty()) {
            listener.onAddressError();
            noProblems = false;
        }
        if (role.equals("Choose your role")) {
            listener.onRoleError();
            noProblems = false;
        }
        if(noProblems){
            //authenticating the user into firebase
            System.out.println("authenticating the user");
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // authenticated new user so this will so this will now be added to the firebase database
                            if (task.isSuccessful()) {
                                System.out.println("no errors and it is now updating the firebase");
                                // Sign in success, update UI with the signed-in user's information
                                Toast.makeText(registerActivity, "Welcome " + firstName + " " + lastName, Toast.LENGTH_LONG).show();
                                //Toast.makeText(RegisterActivity.this, "Hello, please log in here", Toast.LENGTH_LONG);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference(FirebaseAuth.getInstance().getCurrentUser().getUid());
                                HashMap<String, Object> thisUserInfo = new HashMap<String, Object>();
                                thisUserInfo.put("Name", firstName + " " + lastName);
                                thisUserInfo.put("Address", address);
                                thisUserInfo.put("Password", password);
                                thisUserInfo.put("Email", email);
                                thisUserInfo.put("Role", role);
                                myRef.updateChildren(thisUserInfo);
                                listener.onSuccess();

                            } else {
                                // If sign in fails, display a message to the user. This includes if the user already has an email.
                                Toast.makeText(registerActivity, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                listener.onAccountAlreadyExistsError();
                            }
                        }
                    });
        }
    }
}
