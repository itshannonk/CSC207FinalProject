package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.scotiabankpaymentsystem.R;
import com.example.scotiabankpaymentsystem.cocacola.CCInvoiceSeeOrCreate;

public class CCCreateInvoiceActivity extends AppCompatActivity implements CCCreateInvoiceView {
    private static int invoiceID = 0;
    private String userID;
    private EditText item;
    private EditText price;
    private EditText quantity;
    private CCCreateInvoicePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cc_createinvoice);
        presenter = new CCCreateInvoicePresenter(this, new CCCreateInvoiceInteractor());
        item = findViewById(R.id.input_item_name);
        price = findViewById(R.id.input_price);
        quantity = findViewById(R.id.input_quantity);

        getUserID();

        findViewById(R.id.create_invoice).setOnClickListener(v -> createInvoice(item.getText().toString(),
                price.getText().toString(), quantity.getText().toString(), userID, invoiceID));

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        item.setText(savedInstanceState.get("item").toString());
        price.setText(savedInstanceState.get("price").toString());
        quantity.setText(savedInstanceState.get("quantity").toString());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("item", item.getText().toString().trim());
        outState.putString("price", price.getText().toString().trim());
        outState.putString("quantity", quantity.getText().toString().trim());
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent newIntent = new Intent(CCCreateInvoiceActivity.this, CCInvoiceSeeOrCreate.class);
                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void inputError() {
        Toast.makeText(getApplicationContext(), "Enter all the inputs!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void createInvoice(String item, String price, String quantity, String userID, int invoiceID) {
        presenter.createInvoice(item, price, quantity, userID, invoiceID, this);
    }

    @Override
    public void invoiceSuccess() {
        setInvoiceID(invoiceID + 1);
        Toast.makeText(getApplicationContext(), "Successfully created!", Toast.LENGTH_LONG).show();
    }

    public void setInvoiceID(int newID) {
        invoiceID = newID;
    }

    private void getUserID() {
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }
}
