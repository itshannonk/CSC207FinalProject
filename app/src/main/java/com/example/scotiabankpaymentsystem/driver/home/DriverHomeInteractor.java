package com.example.scotiabankpaymentsystem.driver.home;


import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.Listener;


public class DriverHomeInteractor {
    APIFacadeDisplayName APIFacade;

    public DriverHomeInteractor(){
        APIFacade = new APIFacadeDisplayName();
    }
    interface onDisplayDataFinishedListener extends Listener {
        //successfully retrieves user information from database
        void onPageSuccess(String username);
    }

    public void displayName(final DriverHomeInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacade.displayName(listener, userID, context);
    }
}
