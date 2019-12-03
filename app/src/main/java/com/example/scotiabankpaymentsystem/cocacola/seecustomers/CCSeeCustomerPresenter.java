package com.example.scotiabankpaymentsystem.cocacola.seecustomers;

import android.content.Context;

public class CCSeeCustomerPresenter implements CCSeeCustomerInteractor.onDisplayDataFinishedListener{
    private CCSeeCustomerView ccSeeCustomerView;
    private CCSeeCustomerInteractor ccSeeCustomerInteractor;

    CCSeeCustomerPresenter(CCSeeCustomerView ccSeeCustomerView, CCSeeCustomerInteractor ccSeeCustomerInteractor) {
        this.ccSeeCustomerView = ccSeeCustomerView;
        this.ccSeeCustomerInteractor = ccSeeCustomerInteractor;
    }

    public void displayCustomers(Context context) {
        ccSeeCustomerInteractor.displayCustomers(this, context);
    }

    @Override
    public void setCustomers(String[] response) {
        ccSeeCustomerView.createButtons(response);
    }
}
