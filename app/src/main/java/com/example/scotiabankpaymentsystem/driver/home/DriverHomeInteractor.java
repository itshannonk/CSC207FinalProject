package com.example.scotiabankpaymentsystem.driver.home;


import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.Listener;


/**
 * The type Driver home interactor.
 */
public class DriverHomeInteractor {
    /**
     * The Api facade.
     */
    APIFacadeDisplayName APIFacade;

    /**
     * Instantiates a new Driver home interactor.
     */
    public DriverHomeInteractor(){
        APIFacade = new APIFacadeDisplayName();
    }

    /**
     * The interface On display data finished listener.
     */
    interface onDisplayDataFinishedListener extends Listener {
        //successfully retrieves user information from database
        void onPageSuccess(String username);
    }

    /**
<<<<<<< HEAD
     * Retrieves the name of the current logged in user from the database
     *
     * @param listener an instance of SBOHomePresenter
     * @param userID   the userID of the current logged in user
     * @param context  an instance of SBOHomeView
=======
     * Display name.
     *
     * @param listener the listener
     * @param userID   the user id
     * @param context  the context
>>>>>>> e6816833580e7ae14f61730be69fa507da7b8f94
     */
    public void displayName(final DriverHomeInteractor.onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacade.displayName(listener, userID, context);
    }
}
