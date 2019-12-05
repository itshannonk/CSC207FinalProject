package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.Listener;

/**
<<<<<<< HEAD
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
=======
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
>>>>>>> f4058c89e177be83dc920b2901f76b0ab3ed118e
     */
    void retrieveUserInformation(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacadeDisplayName.retrieveUserInformation(listener, userID, context);

    }
}
