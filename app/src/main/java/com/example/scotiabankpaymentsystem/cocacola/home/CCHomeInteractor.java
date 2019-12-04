package com.example.scotiabankpaymentsystem.cocacola.home;


import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;


public class CCHomeInteractor {
    private APIFacadeDisplayName APIFacade;
    public CCHomeInteractor(){
        APIFacade = new APIFacadeDisplayName();
    }
    public interface onDisplayDataFinishedListener extends Listener {
        //successfully retrieves user information from database
        void onHomePageSuccess(String username);
    }


    public void displayName(final CCHomeInteractor.onDisplayDataFinishedListener listener, final String userID, Context context){
        APIFacade.displayName(listener, userID, context);
    }
}

