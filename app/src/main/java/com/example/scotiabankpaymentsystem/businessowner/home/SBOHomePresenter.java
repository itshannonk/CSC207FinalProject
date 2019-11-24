package com.example.scotiabankpaymentsystem.businessowner.home;

public class SBOHomePresenter implements SBOHomeInteractor.onDisplayDataFinishedListener {
    private SBOHomeView sboHomeView;
    private SBOHomeInteractor sboHomeInteractor;

    SBOHomePresenter(SBOHomeView sboHomeView, SBOHomeInteractor sboHomeInteractor) {
        this.sboHomeView = sboHomeView;
        this.sboHomeInteractor = sboHomeInteractor;
    }

    //public void displayName() {
        //sboHomeInteractor.displayName(this);
    //}

    void onDestroy() {
        sboHomeView = null;
    }

    @Override
    public void onHomePageSuccess(String username) {
        sboHomeView.setDisplayName(username);
    }
}
