package com.example.scotiabankpaymentsystem.businessowner.seeinvoices;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

public class SBOSeeInvoicesInteractor {
    private APIFacadeInvoice APIFacadeInvoice;
    SBOSeeInvoicesInteractor(){
        APIFacadeInvoice = new APIFacadeInvoice();
    }
    public interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onInvoicesRetrievalSuccess(String[] IDs);
    }

    void retrieveInvoiceID(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacadeInvoice.retrieveInvoiceID(listener, userID, context);
    }
}



