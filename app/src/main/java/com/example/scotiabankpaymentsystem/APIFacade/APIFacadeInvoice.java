package com.example.scotiabankpaymentsystem.APIFacade;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceInteractor;
import com.example.scotiabankpaymentsystem.businessowner.seeinvoices.SBOSeeInvoicesInteractor;
import com.example.scotiabankpaymentsystem.cocacola.createinvoice.CCCreateInvoiceInteractor;
import com.example.scotiabankpaymentsystem.driver.displayinvoice.DriverDisplayInvoiceInteractor;
import com.example.scotiabankpaymentsystem.driver.seeinvoices.DriverSeeInvoicesInteractor;

public class APIFacadeInvoice {
    /**
     *
     * @param listener This is the Listener that is used in SBO.
     * @param userID This is the userID that is implemented in the Firebase Auth, which is the identifier of who is using the app.
     * @param invoiceID This is the invoiceID that is currently being viewed by the user.
     * @param context This is the Context of what the API should be associated with.
     */
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

    /**
     * It retrieves the invoice information for SBO according to the invoiceID that has been inputed and
     * that will then report the booleans/strings of the corresponding invoice's attributions
     * in the following order: delivered, issued, paid, price, total price, item, price, quantity
     *
     * @param listener This is the Listener that is used in SBO.
     * @param userID This is the userID that is implemented in the Firebase Auth, which is the identifier of who is using the app.
     * @param invoiceID This is the invoiceID that is currently being viewed by the user.
     * @param context This is the Context of what the API should be associated with.
     */
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

    /**
     * This retrieves a list of all the invoiceID's that correspond to the UserID that has been
     * associated with this user.
     *
     * @param listener  This is the Listener that is used in SBO.
     * @param userID This is the userID that is implemented in the Firebase Auth, which is the identifier of who is using the app.
     * @param context This is the Context of what the API should be associated with.
     */
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

    /**
     * This method allows for the creation of the invoice with the given information of the parameters.
     * This will allow for the invoice to be added into Firebase with the corresponding
     *
     * @param listener This is the Listener that is used in Customer.
     * @param item This is the item name to be ordered.
     * @param price This is the individiual price of an individual item.
     * @param quantity This is the quantity of how many of the items that is to be purchased.
     * @param userID This is the userID that has been assigned to the invoice.
     * @param context This is the Context of what the API should be associated with.
     */

    public void createInvoice(final CCCreateInvoiceInteractor.onDisplayDataFinishedListener listener, String
            item, String price, String quantity, String userID, Context context) {

        com.android.volley.RequestQueue CreateInvoiceRequestQueue = Volley.newRequestQueue(context);
        com.android.volley.RequestQueue GetCurrentIDRequestQueue = Volley.newRequestQueue(context);
        if (item.equals("") || price.equals("") || quantity.equals("")) {
            listener.onCreateInvoiceError();
        } else {
            // Retrieving current invoice ID
            String getcurrentID = "https://us-central1-csc207-tli.cloudfunctions.net/increment_current_invoiceID";
            StringRequest GetCurrentIDStringRequest = new StringRequest(Request.Method.GET, getcurrentID, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    System.out.println(response);
                    helperCreateInvoice(listener, item,  price, quantity, userID,  context, response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            CreateInvoiceRequestQueue.add(GetCurrentIDStringRequest);

            // Creating the new invoice
        }
    }

    /**
     * This is a helper function Customer for the creation of the invoice by incrementing a number in Firebase
     * which will denote a unique invoiceID such that there will be no repetitive invoiceIDs.
     * @param listener This is the Listener that is used in Customer.
     * @param item This is the item name to be ordered.
     * @param price This is the individiual price of an individual item.
     * @param quantity This is the quantity of how many of the items that is to be purchased.
     * @param userID This is the userID that has been assigned to the invoice.
     * @param context This is the Context of what the API should be associated with.
     * @param invoiceID This is the invoiceID that is associated with this user.
     */
    public void helperCreateInvoice(final CCCreateInvoiceInteractor.onDisplayDataFinishedListener listener, String
            item, String price, String quantity, String userID, Context context, String invoiceID){
        com.android.volley.RequestQueue CreateInvoiceRequestQueue = Volley.newRequestQueue(context);
        com.android.volley.RequestQueue GetCurrentIDRequestQueue = Volley.newRequestQueue(context);
        String url = "https://us-central1-csc207-tli.cloudfunctions.net/create_invoice?userid=" + userID + "&invoiceid=invoice"
                + invoiceID+ "&item=" + item + "&quantity=" + quantity + "&price=" + price;
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

    /**
     *  This gives the ability to change the boolean of the delivered aspect in the invoice
     *
     * @param listener This is the Listener that is used in Customer.
     * @param userID This is the userID that has been assigned to the invoice.
     * @param invoiceID This is the invoiceID that is associated with this user.
     * @param context This is the Context of what the API should be associated with.
     */
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

    /**
     * This is a method to retrieve the Invoice for Driver according to the invoiceID that has been
     * inputed and that will then report the booleans/strings of the corresponding invoice's
     * attributions in the following order: delivered, issued, paid, price, total price, item,
     * price, quantity
     *
     * @param listener This is the Listener that is used in SBO.
     * @param userID This is the userID that is implemented in the Firebase Auth, which is the identifier of who is using the app.
     * @param invoiceID This is the invoiceID that is currently being viewed by the user.
     * @param context This is the Context of what the API should be associated with.
     */
    public void retrieveInvoice(final DriverDisplayInvoiceInteractor.onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        { com.android.volley.RequestQueue ExampleRequestQueue = Volley.newRequestQueue(context);
            String url = "https://us-central1-csc207-tli.cloudfunctions.net/get_invoice_information?userID="+userID+"&invoiceID="+invoiceID;
            StringRequest ExampleStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //returns all the invoice info in a string so it now has to be parsed
                    String[] info = response.split(",");
                    //now we know how many invoices do we need to create the same number of buttons
                    System.out.println(info);
                    listener.onInvoiceRetrievalSuccess(info);
                }
            }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            ExampleRequestQueue.add(ExampleStringRequest);
        }
    }

    /**
     * Retrieve all the assigned invoices for the Driver to see what has been assigned to them.
     *
     * @param listener This is the Listener that is used in SBO.
     * @param userID  This is the userID that is implemented in the Firebase Auth, which is the identifier of who is using the app.
     * @param context This is the Context of what the API should be associated with.
     */
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
