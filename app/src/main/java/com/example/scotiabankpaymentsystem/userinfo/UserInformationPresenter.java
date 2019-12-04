package com.example.scotiabankpaymentsystem.userinfo;

import android.content.Context;

public class UserInformationPresenter implements UserInformationInteractor.onDisplayDataFinishedListener {
    private UserInformationView view;
    private UserInformationInteractor interactor;

    UserInformationPresenter(UserInformationView view, UserInformationInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    void onDestroy() {
        view = null;
    }

    @Override
    public void onPageSuccess(String info) {
        view.setUserInfo(info);
    }

    public void startSetUserInfo(String userID, Context context) {
        interactor.retrieveUserInformation(this, userID, context);
    }

}