package com.example.scotiabankpaymentsystem.businessowner.home;

public class SBOHomePresenter implements SBOHomeInteractor.OnLoginFinishedListener {
    private SBOHomeView sboHomeView;
    private SBOHomeInteractor sboHomeInteractor;

    SBOHomePresenter(SBOHomeView sboHomeView, SBOHomeInteractor sboHomeInteractor) {
        this.sboHomeView = sboHomeView;
        this.sboHomeInteractor = sboHomeInteractor;
    }

    public void displayName() {
        System.out.println("reached presenter display name");
        sboHomeInteractor.displayName(this);
    }

    void onDestroy() {
        sboHomeView = null;
    }

    @Override
    public void onHomePageSuccess(String username) {
        System.out.println("reached presenter success");
        sboHomeView.setDisplayName(username);
    }
}
