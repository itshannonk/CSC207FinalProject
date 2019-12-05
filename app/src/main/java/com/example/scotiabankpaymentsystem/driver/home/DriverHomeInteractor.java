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

    /**
     * Retrieves the name of the current logged in user from the database
     *
     * @param listener an instance of SBOHomePresenter
     * @param userID   the userID of the current logged in user
     * @param context  an instance of SBOHomeView
     */
    public void displayName(final DriverHomeInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacade.displayName(listener, userID, context);
    }
}
