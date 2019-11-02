package com.example.scotiabankpaymentsystem.businessowner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.data.change_password;

/**
 * A page that has SBO's settings.
 */
public class SBOSettings extends AppCompatActivity {
    private Button button_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_settings);
        button_password = findViewById(R.id.Change_password);
        button_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //initiate the changing of the page since the button is clicked
                openActivity();
            }
        });
    }
    // go back to the settings page
    public void openActivity() {
        Intent intent = new Intent(SBOSettings.this, change_password.class);
        startActivity(intent);
    }


}
