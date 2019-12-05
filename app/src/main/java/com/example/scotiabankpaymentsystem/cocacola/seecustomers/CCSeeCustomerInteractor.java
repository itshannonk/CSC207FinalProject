package com.example.scotiabankpaymentsystem.cocacola.seecustomers;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeCustomers;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;

/**
 * This is the Interactor for CocaCola.
 */
public class CCSeeCustomerInteractor {
    private APIFacadeCustomers APIFacade;
    public CCSeeCustomerInteractor(){
        APIFacade = new APIFacadeCustomers();
    }

    /**
     * This is the Listener for CocaCola.
     */
    public interface onDisplayDataFinishedListener {
        //successfully retrieves user information from database
        void setCustomers(String[] response);
    }

    /**
     * This is the ability for the customers to be displayed on the CCSeeCustomers in button format.
     *
     * @param listener This is the listener for CocaCola.
     * @param context This Context is where the Activity should be implemented in.
     */
    public void displayCustomers(final onDisplayDataFinishedListener listener, Context context){
        APIFacade.displayCustomers(listener, context);
    }
}
