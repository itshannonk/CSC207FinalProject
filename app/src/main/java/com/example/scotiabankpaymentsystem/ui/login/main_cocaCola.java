package com.example.scotiabankpaymentsystem.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.scotiabankpaymentsystem.R;

public class main_cocaCola extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_coca_cola);

        openActivity2();
        //Complete and destroy login activity once successful
        finish();
    }

    public void openActivity2() {
        Intent intent = new Intent(main_cocaCola.this, MainActivity.class);
        startActivity(intent);
    }
}
