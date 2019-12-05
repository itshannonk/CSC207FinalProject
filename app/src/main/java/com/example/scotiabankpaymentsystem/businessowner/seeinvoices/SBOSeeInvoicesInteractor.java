package com.example.scotiabankpaymentsystem.businessowner.seeinvoices;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;

public class SBOSeeInvoicesInteractor {
    private APIFacadeInvoice APIFacadeInvoice;

    SBOSeeInvoicesInteractor() {
        APIFacadeInvoice = new APIFacadeInvoice();
    }

    public interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onInvoicesRetrievalSuccess(String[] IDs);
    }

    /**
     * retrieve invoice id from from backend
     * @param listener interface inside interactor
     * @param userID id of current user
     * @param context
     */
    void retrieveInvoiceID(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacadeInvoice.retrieveInvoiceID(listener, userID, context);
    }
}



