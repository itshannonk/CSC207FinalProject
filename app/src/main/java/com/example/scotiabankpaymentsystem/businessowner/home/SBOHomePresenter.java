package com.example.scotiabankpaymentsystem.businessowner.home;

import android.content.Context;

public class SBOHomePresenter implements SBOHomeInteractor.onDisplayDataFinishedListener {
    private SBOHomeView sboHomeView;
    private SBOHomeInteractor sboHomeInteractor;

    SBOHomePresenter(SBOHomeView sboHomeView, SBOHomeInteractor sboHomeInteractor) {
        this.sboHomeView = sboHomeView;
        this.sboHomeInteractor = sboHomeInteractor;
    }

    public void displayName(String userID, Context context) {
        sboHomeInteractor.displayName(this, userID, context);
    }

    void onDestroy() {
        sboHomeView = null;
    }

    @Override
    public void onHomePageSuccess(String username) {
        sboHomeView.setDisplayName(username);
    }
}
