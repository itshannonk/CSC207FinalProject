package com.example.scotiabankpaymentsystem.driver.home;

import android.content.Context;

public class DriverHomePresenter implements DriverHomeInteractor.onDisplayDataFinishedListener {

    private DriverHomeView driverHomeView;
    private DriverHomeInteractor driverHomeInteractor;

    DriverHomePresenter(DriverHomeView driverHomeView, DriverHomeInteractor driverHomeInteractor) {
        this.driverHomeView = driverHomeView;
        this.driverHomeInteractor = driverHomeInteractor;
    }

    public void displayName(String userID, Context context) {
        driverHomeInteractor.displayName(this, userID, context);
    }

    void onDestroy() {
        driverHomeView = null;
    }

    @Override
    public void onHomePageSuccess(String username) {
        driverHomeView.setDisplayName(username);
    }
}
