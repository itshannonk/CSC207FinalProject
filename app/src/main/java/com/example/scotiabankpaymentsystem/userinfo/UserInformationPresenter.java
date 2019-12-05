package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Context;

/**
 * The type User information presenter.
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

    @Override
    public void onPageSuccess(String info) {
        view.setUserInfo(info);
    }

    /**
     * Start set user info.
     *
     * @param userID  the user id
     * @param context the context
     */
    public void startSetUserInfo(String userID, Context context) {
        interactor.retrieveUserInformation(this, userID, context);
    }

}