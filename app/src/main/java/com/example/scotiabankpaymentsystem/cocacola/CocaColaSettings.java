package com.example.scotiabankpaymentsystem.cocacola;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.data.ChangePassword;

/**
 * A page that has CocaCola's settings.
 */

public class CocaColaSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_settings);

        //Checking if the changePassword in SBO's Settings page button has been pressed
        findViewById(R.id.password).setOnClickListener(v -> {
            //open the next page for the SBO to chan
            openActivityChangePassword();
        });
    }

    public void openActivityChangePassword() {
        Intent intent = new Intent(com.example.scotiabankpaymentsystem.cocacola.CocaColaSettings.this, ChangePassword.class);
        startActivity(intent);
    }
}

