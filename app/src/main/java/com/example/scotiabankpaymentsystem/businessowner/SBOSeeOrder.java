package com.example.scotiabankpaymentsystem.businessowner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.R;
import android.os.Handler;
//import com.google.firebase.auth.FirebaseAuth;


// TODO: differentiate if SBO has orders or not

/**
 * A page that contains SBO's orders
 */
public class SBOSeeOrder extends AppCompatActivity {
    private static boolean testing = false;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessowner_seestatus_has);

        findViewById(R.id.GoBack).setOnClickListener(v -> navigateToHome());

        Intent intent = getIntent();
        userID = intent.getStringExtra("userID");
        String invoiceID = intent.getStringExtra("invoiceID");
        System.out.println(userID);
        System.out.println(invoiceID);
        TextView invoiceText = findViewById(R.id.totalPrice);
        String tempForInvoice = "this is the userID: " + userID + "this is the invoice ID: " + invoiceID;
        invoiceText.setText(tempForInvoice);

        System.out.println("did it come here");
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(this);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_invoice_information?userID="+userID+"&invoiceID="+invoiceID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("did it come here22222222" + response);
                if(!(response.equals(""))) {
                    String[] invoiceIDs = response.split(",");
                    String inputText = "";
                    // showing if it is delivered
                    TextView invoiceTextDeliever = findViewById(R.id.Delivered);
                    inputText ="Delievered: " + invoiceIDs[0];
                    invoiceTextDeliever.setText(inputText);
                    //showing if it's issued
                    TextView invoiceTextIssued = findViewById(R.id.Issued);
                    inputText ="Issued: " + invoiceIDs[1];
                    invoiceTextIssued.setText(inputText);
                    //showing if it's paid
                    TextView invoiceTextPaid = findViewById(R.id.Paid);
                    inputText ="Paid: " + invoiceIDs[2];
                    invoiceTextPaid.setText(inputText);
                    //showing the total price
                    TextView invoiceTextPrice = findViewById(R.id.totalPrice);
                    inputText ="Total Price " + invoiceIDs[3];
                    invoiceTextPrice.setText(inputText);
                }
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
        //DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        // Get the information of the current logged in user from database
        //databaseReference.addValueEventListener(new ValueEventListener() {
           // @Override
            //public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                //String newInvoiceString = dataSnapshot.child("Business Owner").child(userID).child("Invoices").getValue(String.class);
//                TextView invoiceText = findViewById(R.id.Invoice);
//                System.out.println("invoice check once");
//                if(!(newInvoiceString.equals(""))){
//                    invoiceText.setText(newInvoiceString);
//                }
//                if(!testing) {
//                    //This is how you convert from a json file to an object
//                    Gson gson = new Gson();
//                    String json = newInvoiceString;
//                    Invoice invoice = gson.fromJson(json, Invoice.class);
//                    //we can now do methods around the invoices
//                    invoice.setId(5);
//                    invoice.getStatus().setIssued(true);
//                    invoice.getStatus().setDelivered(true);
//                    invoice.getStatus().setPaid(true);
//                    //changing it back to an String to push back to the firebase
//                    Gson gsonChanged = new Gson();
//                    String mynewJSON = gsonChanged.toJson(invoice);
//                    FirebaseDatabase database = FirebaseDatabase.getInstance("https://csc207-tli.firebaseio.com");
//                    DatabaseReference roleDatabaseReference = database.getReference("Business Owner");
//                    DatabaseReference myRef = roleDatabaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
//                    DatabaseReference RefToReplace = myRef.child("Invoices");
//                    //slight delay for the display of the data to mimic realtime
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            RefToReplace.setValue(mynewJSON);
//                        }
//                    }, 2000);
//                    //testing boolean is for testing purposes so that it only executes this once.
//                    testing = true;
//                }
            //}

            //@Override
            //public void onCancelled(@NonNull DatabaseError databaseError) {
            //}
        //});
    }
    public void navigateToHome() {
        // since it takes a while for the information (like 1 second) so there is a delay
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent newIntent = new Intent(SBOSeeOrder.this, ClickInvoices.class);
                newIntent.putExtra("userID", userID);
                startActivity(newIntent);
            }
        }, 200);
    }
}
