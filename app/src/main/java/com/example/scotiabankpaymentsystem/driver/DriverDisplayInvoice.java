package com.example.scotiabankpaymentsystem.driver;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;

public class DriverDisplayInvoice extends AppCompatActivity {
    private String userID;
    private Button changeStatusButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_displayinvoice);
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        changeStatusButton = findViewById(R.id.changeStatus);
        findViewById(R.id.changeStatus).setOnClickListener(v -> changeStatus());

    }

    public void changeStatus(){
        //TODO: implement this!
    }

}
