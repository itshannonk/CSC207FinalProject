package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesActivity;

public class SBODisplayInvoiceActivity extends AppCompatActivity implements SBODisplayInvoiceView {
    private Button payButton;
    private String userID;
    private String invoiceID;
    private SBODisplayInvoicePresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_displayinvoice);
        // this will initiate the pay button
        payButton = findViewById(R.id.Pay);
        payButton.setOnClickListener(v -> navigateToPay());

        presenter = new SBODisplayInvoicePresenter(this, new com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceInteractor());

        //retrieving the information passed on from the previous activity
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        invoiceID = intent.getStringExtra("invoiceID");

        if(!(intent.getStringExtra("userType").equals("SBO"))){
            payButton.setVisibility(View.GONE);
        }
        startSetInvoiceInfo();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void navigateToPay() {
        presenter.onPay(userID, invoiceID, this);
    }

    @Override
    public void startSetInvoiceInfo(){
        presenter.startSetInvoiceInfo(userID, invoiceID, this);
    }

    /**
     * @param menu is the Menu of what to add.
     * @return Boolean of whether or not.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    /**
     * @param: Inserts the parameter of the info of the Delivered boolean, Issued boolean, Paid boolean, totalPrice boolean, Item String
     */
    public void setInvoiceInfo(String[] info) {
        String inputText = "";
        // showing if it is delivered

        TextView invoiceTextDeliever = findViewById(R.id.delivered);
        inputText ="Delivered: " + info[0];
        invoiceTextDeliever.setText(inputText);
        //showing if it's issued
        TextView invoiceTextIssued = findViewById(R.id.issued);
        inputText ="Issued: " + info[1];
        invoiceTextIssued.setText(inputText);
        //showing if it's paid
        TextView invoiceTextPaid = findViewById(R.id.paid);
        inputText ="Paid: " + info[2];
        invoiceTextPaid.setText(inputText);
        //checks if it has already been paid so it determines if the pay button should be there
        if((info[2]).toLowerCase().equals("true")){
            payButton.setVisibility(View.GONE);
        }
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
        inputText ="Quantity:" + info[6];
        invoiceTextQuantity.setText(inputText);
    }

    @Override
    public void changePayTrue() {
        TextView invoiceTextPaid = findViewById(R.id.paid);
        invoiceTextPaid.setText("Paid: True");
        // makes the payment option invisible if it has been paid
        payButton.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Your order has been processed.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void changePayError(){
        Toast.makeText(getApplicationContext(), "Oops, there has been an error in the payment", Toast.LENGTH_LONG).show();
    }

    // for the back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent newIntent = new Intent(com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceActivity.this, SBOSeeInvoicesActivity.class);
                newIntent.putExtra("userID", userID);
                Intent intent = getIntent();
                newIntent.putExtra("userType", intent.getStringExtra("userType"));
                startActivity(newIntent);
                finish();
                break;
        }
        return true;
    }
}
