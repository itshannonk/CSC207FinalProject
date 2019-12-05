package com.example.scotiabankpaymentsystem.driver.displayinvoice;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

/**
 * The type Driver display invoice interactor.
 */
public class DriverDisplayInvoiceInteractor {
    private APIFacadeInvoice APIFacadeInvoice;

    /**
     * Instantiates a new Driver display invoice interactor.
     */
    public DriverDisplayInvoiceInteractor(){
        APIFacadeInvoice = new APIFacadeInvoice();
    }

    /**
     * The interface On display data finished listener.
     */
    public interface onDisplayDataFinishedListener {
        /**
         * successfully change delivered boolean
         */
        void onChangeDeliveredSuccess();

        /**
         * Change delivered boolean has error
         */
        void onChangeDeliveredError();

        /**
         * successfully retrieves user information from database
         *
         * @param info the info
         */
        void onInvoiceRetrievalSuccess(String[] info);

        /**
         * Start to change delivered
         *
         * @param userID    id of user
         * @param invoiceID id of invoice
         * @param context   the context of method that happening
         */
        void onDelivered(String userID, String invoiceID, Context context);
    }

    /**
     * Change the delivered boolean in backend
     *
     * @param listener  an interface inside  interactor
     * @param userID    the userID of the current logged in user
     * @param invoiceID the invoice id
     * @param context   context of the activity has happened
     */
    public void changeDeliveredBoolean(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        APIFacadeInvoice.changeDeliveredBoolean(listener, userID, invoiceID, context);
    }

    /**
     * Retrieves the current invoice of the current logged in user from the database
     *
     * @param listener  an interface inside  interactor
     * @param userID    the userID of the current logged in user
     * @param invoiceID the id of invoice
     * @param context   context of the activity has happened
     */
    public void retrieveInvoice(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        APIFacadeInvoice.retrieveInvoice(listener, userID, invoiceID, context);
    }


}
