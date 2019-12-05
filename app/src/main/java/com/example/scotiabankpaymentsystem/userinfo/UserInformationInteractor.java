package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

/**
 * The type User information interactor.
 */
public class UserInformationInteractor {

    private APIFacadeDisplayName APIFacadeDisplayName;

    /**
     * Instantiates a new User information interactor.
     */
    UserInformationInteractor() {
        APIFacadeDisplayName = new APIFacadeDisplayName();
    }

    /**
     * The interface On display data finished listener.
     */
    public interface onDisplayDataFinishedListener extends Listener {
        //successfully retrieves user information from database
        void onPageSuccess(String info);
    }

    /**
     * Retrieve user information.
     *
     * @param listener the listener
     * @param userID   the user id
     * @param context  the context
     */
    void retrieveUserInformation(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacadeDisplayName.retrieveUserInformation(listener, userID, context);

    }
}
