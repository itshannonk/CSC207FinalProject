package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Context;

import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeDisplayName;
import com.example.scotiabankpaymentsystem.APIFacade.APIFacadeInvoice;
import com.example.scotiabankpaymentsystem.Listener;

public class UserInformationInteractor {

    private APIFacadeDisplayName APIFacadeDisplayName;

    UserInformationInteractor() {
        APIFacadeDisplayName = new APIFacadeDisplayName();
    }

    public interface onDisplayDataFinishedListener extends Listener {
        //successfully retrieves user information from database
        void onPageSuccess(String info);
    }

    void retrieveUserInformation(final onDisplayDataFinishedListener listener, final String userID, Context context) {
        APIFacadeDisplayName.retrieveUserInformation(listener, userID, context);

    }
}
