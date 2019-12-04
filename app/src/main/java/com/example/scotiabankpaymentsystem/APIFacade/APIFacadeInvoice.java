package com.example.scotiabankpaymentsystem.APIFacade;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.Listener;
import com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceInteractor;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeInteractor;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesInteractor;
import com.example.scotiabankpaymentsystem.cocacola.createinvoice.CCCreateInvoiceInteractor;
import com.example.scotiabankpaymentsystem.cocacola.home.CCHomeInteractor;
import com.example.scotiabankpaymentsystem.cocacola.seecustomers.CCSeeCustomerInteractor;
import com.example.scotiabankpaymentsystem.driver.displayinvoice.DriverDisplayInvoiceInteractor;
import com.example.scotiabankpaymentsystem.driver.seeinvoices.DriverSeeInvoicesInteractor;

public class APIFacadeInvoice {
    String currentInvoiceID;

    public void changePayBoolean(final SBODisplayInvoiceInteractor.onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/set_invoice_status?userid="+userID+"&invoiceid="+invoiceID+"&statustype="+"paid"+"&newvalue="+true;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onChangePaySuccess();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                listener.onChangePayError();
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }
    public void retrieveInvoice(final SBODisplayInvoiceInteractor.onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        { com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_invoice_information?userID="+userID+"&invoiceID="+invoiceID;
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //returns all the invoice info in a string so it now has to be parsed
                    String[] info = response.split(",");
                    //now we know how many invoices do we need to create the same number of buttons
                    listener.onInvoiceRetriveSuccess(info);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }
    }
    public void retrieveInvoiceID(final SBOSeeInvoicesInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_list_of_invoice_ids?userID=" + userID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //returns all the invoice ID's in a string so it now has to be parsed
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                listener.onInvoicesRetrievalSuccess(IDs);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }

    public void createInvoice(final CCCreateInvoiceInteractor.onDisplayDataFinishedListener listener, String
            item, String price, String quantity, String userID, Context context) {

        com.android.volley.RequestQueue CreateInvoiceRequestQueue = Volley.newRequestQueue(context);
        com.android.volley.RequestQueue GetCurrentIDRequestQueue = Volley.newRequestQueue(context);
        if (item.equals("") || price.equals("") || quantity.equals("")) {
            listener.onCreateInvoiceError();
        } else {
            // Retrieving current invoice ID
            String getcurrentID = "https://us-central1-csc207-tli.cloudfunctions.net/get_current_invoiceID";
            StringRequest GetCurrentIDStringRequest = new StringRequest(Request.Method.GET, getcurrentID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    currentInvoiceID = response;
                    System.out.println(currentInvoiceID);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            CreateInvoiceRequestQueue.add(GetCurrentIDStringRequest);
            System.out.println(currentInvoiceID);
            // Incrementing the current invoice ID by 1

            String setcurrentID = "https://us-central1-csc207-tli.cloudfunctions.net/increment_current_invoiceID";
            StringRequest SetCurrentIDStringRequest = new StringRequest(Request.Method.POST, setcurrentID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            CreateInvoiceRequestQueue.add(SetCurrentIDStringRequest);

            // Creating the new invoice
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/create_invoice?userid=" + userID + "&invoiceid=invoice"
                    + currentInvoiceID + "&item=" + item + "&quantity=" + quantity + "&price=" + price;
            StringRequest CreateInvoiceStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    listener.onCreateInvoiceSuccess();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            CreateInvoiceRequestQueue.add(CreateInvoiceStringRequest);
        }
    }
    public void changeDeliveredBoolean(final DriverDisplayInvoiceInteractor.onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/set_invoice_status?userid=" + userID + "&invoiceid=" + invoiceID + "&statustype=" + "delivered" + "&newvalue=" + true;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onChangeDeliveredSuccess();
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
                listener.onChangeDeliveredError();
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }
    public void retrieveInvoice(final DriverDisplayInvoiceInteractor.onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        { com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_invoice_information?userID="+userID+"&invoiceID="+invoiceID;
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //returns all the invoice info in a string so it now has to be parsed
                    String[] info = response.split(",");
                    //now we know how many invoices do we need to create the same number of buttons
                    listener.onInvoiceRetriveSuccess(info);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }
    }
    public void retrieveInvoiceID(final DriverSeeInvoicesInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        //now using the API
        com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_assigned_invoices?userID=" + userID;
        StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //returns all customers and selected invoiced that they were assigned
                String[] IDs = response.split(",");
                //now we know how many invoices do we need to create the same number of buttons
                listener.onInvoicesRetrievalSuccess(IDs);
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        ExampleRequestQueue.add(ExampleStringRequest);
    }
}
