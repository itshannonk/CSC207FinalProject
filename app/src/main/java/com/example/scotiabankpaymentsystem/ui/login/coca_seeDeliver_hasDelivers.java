package com.example.scotiabankpaymentsystem.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.scotiabankpaymentsystem.R;

public class coca_seeDeliver_hasDelivers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coca_see_deliver_has_delivers);
    }

    public void openActivity2() {
        Intent intent = new Intent(coca_seeDeliver_hasDelivers.this, MainActivity.class);
        startActivity(intent);
    }
}
