package com.example.scotiabankpaymentsystem.driver.seeinvoices;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

public class DriverSeeInvoicesInteractor {

    APIFacadeInvoice APIFacadeInvoice;

    public DriverSeeInvoicesInteractor(){
        APIFacadeInvoice = new APIFacadeInvoice();
    }

    public interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void onInvoicesRetrievalSuccess(String[] IDs);
    }

    void retrieveInvoiceID(final DriverSeeInvoicesInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        //now using the API
        APIFacadeInvoice.retrieveInvoiceID(listener, userID, context);
    }
}
