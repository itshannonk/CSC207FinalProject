package com.example.scotiabankpaymentsystem.driver.displayinvoice;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

public class DriverDisplayInvoiceInteractor {
    private APIFacadeInvoice APIFacadeInvoice;
    public DriverDisplayInvoiceInteractor(){
        APIFacadeInvoice = new APIFacadeInvoice();
    }
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
         */
        void onInvoiceRetrievalSuccess(String[] info);

        /**
         *Start to change delivered
         *
         * @param userID id of user
         * @param invoiceID id of invoice
         * @param context the context of method that happening
         */
        void onDelivered(String userID, String invoiceID, Context context);
    }

    /**
     * Change the delivered boolean in backend
     *
     * @param listener an interface inside  interactor
     * @param userID   the userID of the current logged in user
     * @param context context of the activity has happened
     */
    public void changeDeliveredBoolean(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        APIFacadeInvoice.changeDeliveredBoolean(listener, userID, invoiceID, context);
    }

    /**
     * Retrieves the current invoice of the current logged in user from the database
     *
     * @param listener an interface inside  interactor
     * @param userID the userID of the current logged in user
     * @param invoiceID the id of invoice
     * @param context context of the activity has happened
     */
    public void retrieveInvoice(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        APIFacadeInvoice.retrieveInvoice(listener, userID, invoiceID, context);
    }


}
