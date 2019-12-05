package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;

import android.content.Context;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;


public class SBODisplayInvoiceInteractor {
    private APIFacadeInvoice APIFacadeInvoice;

    public SBODisplayInvoiceInteractor() {
        APIFacadeInvoice = new APIFacadeInvoice();
    }

    public interface onDisplayDataFinishedListener {
        /**
         * successfully change pay boolean
         */
        void onChangePaySuccess();

        /**
         * Change pay boolean has error
         */
        void onChangePayError();

        /**
         * successfully retrieves user information from database
         */
        void onInvoiceRetriveSuccess(String[] info);

        /**
         *
         * @param userID id of user
         * @param invoiceID id of invoice
         * @param context the context of method that happening
         */
        void onPay(String userID, String invoiceID, Context context);
    }

    /**
     * Change the Pay boolean in backend
     *
     * @param listener an interface inside  interactor
     * @param userID   the userID of the current logged in user
     * @param context context of the activity has happened
     */
    void changePayBoolean(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context) {
        APIFacadeInvoice.changePayBoolean(listener, userID, invoiceID, context);
    }

    /**
     * Retrieves the current invoice of the current logged in user from the database
     *
     * @param listener an interface inside  interactor
     * @param userID the userID of the current logged in user
     * @param invoiceID the id of invoice
     * @param context context of the activity has happened
     */
    void retrieveInvoice(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context) {
        APIFacadeInvoice.retrieveInvoice(listener, userID, invoiceID, context);

    }
}
