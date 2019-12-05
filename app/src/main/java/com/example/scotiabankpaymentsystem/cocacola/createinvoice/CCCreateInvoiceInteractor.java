package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

/**
 * This is the Interactor for the CocaCola's creation of the invoice.
 */
public class CCCreateInvoiceInteractor {
    private APIFacadeInvoice APIFacadeInvoice;
    public CCCreateInvoiceInteractor(){
        APIFacadeInvoice = new APIFacadeInvoice();
    }

    /**
     * Handles if the invoice creation has been correct or not.
     */
    public interface onDisplayDataFinishedListener{
        //successfully retrieves user information from database
        void onCreateInvoiceSuccess();

        void onCreateInvoiceError();
    }

    /**
     * This is the creation of the invoice.
     * @param listener This is the listener which is associated with the customer.
     * @param item This is the item name that needs to be added for the new invoice.
     * @param price This is the individual price of the item that is required for the invoice.
     * @param quantity This is the amount that the user requested for the particular item.
     * @param userID This is the UserID of what user needs to be associated with this invoice.
     * @param context This is the Context in which this Activity is happening.
     */

    void createInvoice(final onDisplayDataFinishedListener listener, String item, String price, String quantity, String userID, Context context) {
        APIFacadeInvoice.createInvoice(listener, item, price, quantity, userID, context);
    }
}
