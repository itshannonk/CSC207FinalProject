package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;

public class SBODisplayInvoiceInteractor {
    private APIFacadeInvoice APIFacadeInvoice;

    public SBODisplayInvoiceInteractor() {
        APIFacadeInvoice = new APIFacadeInvoice();
    }

    public interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onChangePaySuccess();

        void onChangePayError();

        void onInvoiceRetriveSuccess(String[] info);

        void onPay(String userID, String invoiceID, Context context);
    }

    void changePayBoolean(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context) {
        APIFacadeInvoice.changePayBoolean(listener, userID, invoiceID, context);
    }

    void retrieveInvoice(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context) {
        APIFacadeInvoice.retrieveInvoice(listener, userID, invoiceID, context);

    }
}
