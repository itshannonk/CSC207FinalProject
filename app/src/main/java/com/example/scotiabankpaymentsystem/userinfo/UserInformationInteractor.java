package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.Listener;

/**
 * A class that allows the user to retrieve information from the backend
 */
class UserInformationInteractor {

    private APIFacadeDisplayName APIFacadeDisplayName;

    interface onDisplayDataFinishedListener extends Listener {
        void onPageSuccess(String info);
    }

    UserInformationInteractor() {
        APIFacadeDisplayName = new APIFacadeDisplayName();
    }

    /**
     * Retrieves the information from backend
     *
     * @param listener an instance of UserInformationPresenter
     * @param userID   the userID of the user we're retrieving information from
     * @param context  an instance of UserInformationView
     */
    void retrieveUserInformation(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacadeDisplayName.retrieveUserInformation(listener, userID, context);

    }
}
