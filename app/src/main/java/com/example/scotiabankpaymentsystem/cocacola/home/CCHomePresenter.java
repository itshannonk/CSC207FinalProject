package com.example.scotiabankpaymentsystem.cocacola.home;

public class CCHomePresenter implements CCHomeInteractor.onDisplayDataFinishedListener {

    private CCHomeView ccHomeView;
    private CCHomeInteractor ccHomeInteractor;

    CCHomePresenter(CCHomeView ccHomeView, CCHomeInteractor ccHomeInteractor) {
        this.ccHomeView = ccHomeView;
        this.ccHomeInteractor = ccHomeInteractor;
    }

    public void displayName() {
        //ccHomeInteractor.displayName(this);
    }

    void onDestroy() {
        ccHomeView = null;
    }

    @Override
    public void onHomePageSuccess(String username) {
        ccHomeView.setDisplayName(username);
    }
}
