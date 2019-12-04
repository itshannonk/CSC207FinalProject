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
        //successfully retrieves user information from database
        void onChangeDeliveredSuccess();
        void onChangeDeliveredError();
        void onInvoiceRetriveSuccess(String[] info);
        void onDelivered(String userID, String invoiceID, Context context);
    }

    public void changeDeliveredBoolean(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        APIFacadeInvoice.changeDeliveredBoolean(listener, userID, invoiceID, context);
    }

    public void retrieveInvoice(final onDisplayDataFinishedListener listener, final String userID, final String invoiceID, Context context){
        APIFacadeInvoice.retrieveInvoice(listener, userID, invoiceID, context);
    }


}
