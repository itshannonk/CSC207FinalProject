package com.example.scotiabankpaymentsystem.cocacola.home;


import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

/**
 * This is the Interactor of the CocaCola's Home.
 */
public class CCHomeInteractor {
    private APIFacadeDisplayName APIFacade;
    public CCHomeInteractor(){
        APIFacade = new APIFacadeDisplayName();
    }

    /**
     * This is the Listener interface for CocaCola.
     */
    public interface onDisplayDataFinishedListener extends Listener {
        //successfully retrieves user information from database
        void onPageSuccess(String username);
    }

    /**
     * This gives the ability to display the invoices onto the HomePage.
     * @param listener The listener for the CocaCola.
     * @param userID The userID of the user is logged in.
     * @param context This is the Context in which the Activity needs to be applied in.
     */
    public void displayName(final CCHomeInteractor.onDisplayDataFinishedListener listener, final String userID, Context context){
        APIFacade.displayName(listener, userID, context);
    }
}

