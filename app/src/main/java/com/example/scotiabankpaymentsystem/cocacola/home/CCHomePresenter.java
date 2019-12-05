package com.example.scotiabankpaymentsystem.cocacola.home;

import android.content.Context;

/**
 * This is the CocaCola's presenter.
 */
public class CCHomePresenter implements CCHomeInteractor.onDisplayDataFinishedListener {

    private CCHomeView ccHomeView;
    private CCHomeInteractor ccHomeInteractor;

    CCHomePresenter(CCHomeView ccHomeView, CCHomeInteractor ccHomeInteractor) {
        this.ccHomeView = ccHomeView;
        this.ccHomeInteractor = ccHomeInteractor;
    }

    /**
     * This is ability to display the name for CocaCola.
     * @param userID This is the userID of the user that is logged in.
     * @param context This is the Context of the Activity in which this method needs to be applied
     *                in.
     */
    public void displayName(String userID, Context context) {
        ccHomeInteractor.displayName(this, userID, context);
    }

    void onDestroy() {
        ccHomeView = null;
    }

    @Override
    /**
     * Upon the success of the page where the displayName has been a success, this will be called.
     */
    public void onPageSuccess(String username) {
        ccHomeView.setDisplayName(username);
    }
}


