package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Context;

/**
<<<<<<< HEAD
 * The middleman between UserInformationView and UserInformationInteractor
=======
 * The type User information presenter.
>>>>>>> f4058c89e177be83dc920b2901f76b0ab3ed118e
 */
public class UserInformationPresenter implements UserInformationInteractor.onDisplayDataFinishedListener {
    private UserInformationView view;
    private UserInformationInteractor interactor;

    /**
     * Instantiates a new User information presenter.
     *
     * @param view       the view
     * @param interactor the interactor
     */
    UserInformationPresenter(UserInformationView view, UserInformationInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    /**
     * On destroy.
     */
    void onDestroy() {
        view = null;
    }

    /**
     * Sets the user information on the page once the information has been retrieved
     *
     * @param info the user's profile
     */
    @Override
    public void onPageSuccess(String info) {
        view.setUserInfo(info);
    }

    /**
<<<<<<< HEAD
     * Starts the process for making a call to the backend
     *
     * @param userID  the userID of the user we want to retrieve information of
     * @param context an instance of UserInformationView
=======
     * Start set user info.
     *
     * @param userID  the user id
     * @param context the context
>>>>>>> f4058c89e177be83dc920b2901f76b0ab3ed118e
     */
    public void startSetUserInfo(String userID, Context context) {
        interactor.retrieveUserInformation(this, userID, context);
    }

}