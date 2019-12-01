package com.example.scotiabankpaymentsystem.cocacola.ccclickcustomers;

import android.content.Context;

import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeInteractor;
import com.example.scotiabankpaymentsystem.businessowner.home.SBOHomeView;

public class CCClickPresenter implements CCClickInteractor.onDisplayDataFinishedListener{
    private CCClickView ccClickView;
    private CCClickInteractor ccClickInteractor;

    CCClickPresenter(CCClickView ccClickView, CCClickInteractor ccClickInteractor) {
        this.ccClickView = ccClickView;
        this.ccClickInteractor = ccClickInteractor;
    }

    public void displayCustomers(Context context) {
        ccClickInteractor.displayCustomers(this, context);
    }

    @Override
    public void setCustomers(String[] response) {
        ccClickView.createButtons(response);
    }
}
