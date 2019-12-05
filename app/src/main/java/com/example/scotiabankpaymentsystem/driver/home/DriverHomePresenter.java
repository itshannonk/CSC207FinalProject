package com.example.scotiabankpaymentsystem.driver.home;

import android.content.Context;

/**
 * The type Driver home presenter.
 */
public class DriverHomePresenter implements DriverHomeInteractor.onDisplayDataFinishedListener {

    private DriverHomeView driverHomeView;
    private DriverHomeInteractor driverHomeInteractor;

    /**
     * Instantiates a new Driver home presenter.
     *
     * @param driverHomeView       the driver home view
     * @param driverHomeInteractor the driver home interactor
     */
    DriverHomePresenter(DriverHomeView driverHomeView, DriverHomeInteractor driverHomeInteractor) {
        this.driverHomeView = driverHomeView;
        this.driverHomeInteractor = driverHomeInteractor;
    }

    /**
     * Display name.
     *
     * @param userID  the user id
     * @param context the context
     */
    public void displayName(String userID, Context context) {
        driverHomeInteractor.displayName(this, userID, context);
    }

    /**
     * On destroy.
     */
    void onDestroy() {
        driverHomeView = null;
    }

    /**
     * after the page success
     * @param username name of current user
     */
    @Override
    public void onPageSuccess(String username) {
        driverHomeView.setDisplayName(username);
    }
}
