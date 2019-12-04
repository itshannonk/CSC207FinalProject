package com.example.scotiabankpaymentsystem.businessowner.home;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.Listener;

/**
 * SBO home page interactor (Backend)
 */
public class SBOHomeInteractor {
    private APIFacadeDisplayName APIFacade;

    SBOHomeInteractor() {
        APIFacade = new APIFacadeDisplayName();
    }

    interface onDisplayDataFinishedListener extends Listener {
        // successfully retrieves user information from database
        void onPageSuccess(String username);
    }

    /**
     * Retrieves the name of the current logged in user from the database
     *
     * @param listener an instance of SBOHomePresenter
     * @param userID   the userID of the current logged in user
     * @param context  an instance of SBOHomeView
     */
    void displayName(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacade.displayName(listener, userID, context);
    }

}
