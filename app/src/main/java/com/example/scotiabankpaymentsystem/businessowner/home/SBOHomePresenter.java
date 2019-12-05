package com.example.scotiabankpaymentsystem.businessowner.home;

import android.content.Context;

public class SBOHomePresenter implements SBOHomeInteractor.onDisplayDataFinishedListener {
    private SBOHomeView sboHomeView;
    private SBOHomeInteractor sboHomeInteractor;


    SBOHomePresenter(SBOHomeView sboHomeView, SBOHomeInteractor sboHomeInteractor) {
        this.sboHomeView = sboHomeView;
        this.sboHomeInteractor = sboHomeInteractor;
    }

    /**
     * sent signal to interactor start to display name
     * @param userID id of user
     * @param context context of the the activity happened
     */
    void displayName(String userID, Context context) {
        sboHomeInteractor.displayName(this, userID, context);
    }

    void onDestroy() {
        sboHomeView = null;
    }

    /**
     * if the page success
     * @param username name of current user
     */
    @Override
    public void onPageSuccess(String username) {
        sboHomeView.setDisplayName(username);
    }
}
