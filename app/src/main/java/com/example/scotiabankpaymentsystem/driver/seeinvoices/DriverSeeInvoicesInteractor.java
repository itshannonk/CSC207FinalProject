package com.example.scotiabankpaymentsystem.driver.seeinvoices;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

/**
 * The type Driver see invoices interactor.
 */
public class DriverSeeInvoicesInteractor {

    /**
     * The Api facade invoice.
     */
    APIFacadeInvoice APIFacadeInvoice;

    /**
     * Instantiates a new Driver see invoices interactor.
     */
    public DriverSeeInvoicesInteractor(){
        APIFacadeInvoice = new APIFacadeInvoice();
    }

    /**
     * The interface On display data finished listener.
     */
    public interface onDisplayDataFinishedListener {
        /**
         * On invoices retrieval success.
         *
         * @param IDs the ds
         */
//successfully retrieves user information from database
        void onInvoicesRetrievalSuccess(String[] IDs);
    }

    /**
     * Retrieve invoice id.
     *
     * @param listener the listener
     * @param userID   the user id
     * @param context  the context
     */
    void retrieveInvoiceID(final DriverSeeInvoicesInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        //now using the API
        APIFacadeInvoice.retrieveInvoiceID(listener, userID, context);
    }
}
