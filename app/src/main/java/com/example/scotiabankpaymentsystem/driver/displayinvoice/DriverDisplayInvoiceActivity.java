package com.example.scotiabankpaymentsystem.driver.displayinvoice;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.driver.seeinvoices.DriverSeeInvoicesActivity;

public class DriverDisplayInvoiceActivity extends AppCompatActivity implements DriverDisplayInvoiceView{
    private String userID;
    private String invoiceID;
    private String customerID;
    private Button changeDeliveredButton;
    private DriverDisplayInvoicePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_displayinvoice);
        // this will initiate the delivered button
        changeDeliveredButton = findViewById(R.id.changeDeliveredStatus);
        changeDeliveredButton.setOnClickListener(v -> navigateToDelivery());


        presenter = new DriverDisplayInvoicePresenter(this, new DriverDisplayInvoiceInteractor());

        //retrieving the information passed on from the previous activity
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        customerID = intent.getStringExtra("customerID");
        invoiceID = intent.getStringExtra("invoiceID");

        startSetInvoiceInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void navigateToDelivery() {
        presenter.onDelivered(customerID, invoiceID, this);
    }

    @Override
    public void startSetInvoiceInfo(){
        presenter.startSetInvoiceInfo(customerID, invoiceID, this);
    }

    @Override
    public void setInvoiceInfo(String[] info) {
        String inputText = "";
        // showing if it is delivered

        TextView invoiceTextDeliver = findViewById(R.id.Address);
        inputText = "Delivered: " + info[0];
        invoiceTextDeliver.setText(inputText);
        //checks if it has already been paid so it determines if the delivery button should be there
        if((info[0]).toLowerCase().equals("true")){
            changeDeliveredButton.setVisibility(View.GONE);
        }
        //showing if it's issued
        TextView invoiceTextIssued = findViewById(R.id.name);
        inputText ="Issued: " + info[1];
        invoiceTextIssued.setText(inputText);
        //showing if it's paid
        TextView invoiceTextPaid = findViewById(R.id.email);
        inputText ="Paid: " + info[2];
        invoiceTextPaid.setText(inputText);
        //showing the total price
        TextView invoiceTextPrice = findViewById(R.id.totalPrice);
        inputText ="Total Price: " + info[3];
        invoiceTextPrice.setText(inputText);

        //showing the item in order
        TextView invoiceTextItem = findViewById(R.id.Item);
        inputText ="Item: " + info[4];
        invoiceTextItem.setText(inputText);

        //showing the individual price of item in order
        TextView invoiceTextIndividualPrice = findViewById(R.id.individual_price);
        inputText ="Individual Price: " + info[5];
        invoiceTextIndividualPrice.setText(inputText);

        //showing the quantity of item in order
        TextView invoiceTextQuantity = findViewById(R.id.quantity);
        inputText ="Quantity: " + info[6];
        invoiceTextQuantity.setText(inputText);
    }

    @Override
    public void changeDeliveredTrue() {
        TextView invoiceTextPaid = findViewById(R.id.Address);
        invoiceTextPaid.setText("Delivered: True");
        // makes the payment option invisible if it has been paid
        changeDeliveredButton.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Your order has been delivered.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void changeDeliveredError() {
        Toast.makeText(getApplicationContext(), "Oops, there has been an error in the delivery", Toast.LENGTH_LONG).show();
    }

    // this override is to override the action bar back button so that it passes around the userID
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent newIntent = new Intent(DriverDisplayInvoiceActivity.this, DriverSeeInvoicesActivity.class);
                        newIntent.putExtra("userID", userID);
                        startActivity(newIntent);
                        finish();
                    }
                }, 200);
                break;
            case R.id.refresh:
                startSetInvoiceInfo();
                break;
        }
        return true;
    }
}
