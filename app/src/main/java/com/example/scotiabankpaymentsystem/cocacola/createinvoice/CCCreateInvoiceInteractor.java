package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

public class CCCreateInvoiceInteractor {
    private APIFacadeInvoice APIFacadeInvoice;
    public CCCreateInvoiceInteractor(){
        APIFacadeInvoice = new APIFacadeInvoice();
    }
    public interface onDisplayDataFinishedListener{
        //successfully retrieves user information from database
        void onCreateInvoiceSuccess();

        void onCreateInvoiceError();
    }

    void createInvoice(final onDisplayDataFinishedListener listener, String item, String price, String quantity, String userID, Context context) {
        APIFacadeInvoice.createInvoice(listener, item, price, quantity, userID, context);
    }
}
