package com.example.scotiabankpaymentsystem.cocacola.seecustomers;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeCustomers;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;

public class CCSeeCustomerInteractor {
    private APIFacadeCustomers APIFacade;
    public CCSeeCustomerInteractor(){
        APIFacade = new APIFacadeCustomers();
    }
    public interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void setCustomers(String[] response);
    }
    public void displayCustomers(final onDisplayDataFinishedListener listener, Context context){
        APIFacade.displayCustomers(listener, context);
    }
}
