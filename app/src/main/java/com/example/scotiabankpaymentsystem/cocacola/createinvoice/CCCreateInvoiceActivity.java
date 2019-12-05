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
                price.getText().toString(), quantity.getText().toString(), userID));

    }

    @Override
    /**
     * This retrieves the previously saved instant states of item, price and quantity.
     */
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        item.setText(savedInstanceState.get("item").toString());
        price.setText(savedInstanceState.get("price").toString());
        quantity.setText(savedInstanceState.get("quantity").toString());
    }

    @Override
    /**
     * This saves the current item, price and quantity for retrieval the next time it is required.
     */
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("item", item.getText().toString().trim());
        outState.putString("price", price.getText().toString().trim());
        outState.putString("quantity", quantity.getText().toString().trim());
        super.onSaveInstanceState(outState);
    }

    @Override
    /**
     * This is the actionbar that is needed to go back a page.
     */
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
    /**
     * This is the input error for create invoice when the user inputs nothing into any of the
     * invoice tabs.
     */
    public void inputError() {
        Toast.makeText(getApplicationContext(), "Enter all the inputs!", Toast.LENGTH_LONG).show();
    }

    @Override
    /**
     * This is the ability to createa n invoice.
     */
    public void createInvoice(String item, String price, String quantity, String userID) {
        presenter.createInvoice(item, price, quantity, userID, this);
    }

    @Override
    /**
     * This is called when the creation of an invoice has been a success and creates a Toast text.
     */
    public void invoiceSuccess() {
        Toast.makeText(getApplicationContext(), "Successfully created!", Toast.LENGTH_LONG).show();
    }


    private void getUserID() {
        /**
         * This is the ability to get the UserID that has been passed on when the user first logged
         * in or when they registered.
         */
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
    }
}
