package com.example.scotiabankpaymentsystem.cocacola.seecustomers;

import android.content.Context;

/**
 *  This is the Presenter for CocaCola.
 */
public class CCSeeCustomerPresenter implements CCSeeCustomerInteractor.onDisplayDataFinishedListener{
    private CCSeeCustomerView ccSeeCustomerView;
    private CCSeeCustomerInteractor ccSeeCustomerInteractor;

    CCSeeCustomerPresenter(CCSeeCustomerView ccSeeCustomerView, CCSeeCustomerInteractor ccSeeCustomerInteractor) {
        this.ccSeeCustomerView = ccSeeCustomerView;
        this.ccSeeCustomerInteractor = ccSeeCustomerInteractor;
    }

    /**
     * This is the ability of for the CocaCola to display the Customers.
     * @param context This is the Context for the method in which this Activity will be applied in.
     */
    public void displayCustomers(Context context) {
        ccSeeCustomerInteractor.displayCustomers(this, context);
    }

    @Override
    /**
     * This gives the ability to set the Customers on the CocaCola's Presenter.
     */
    public void setCustomers(String[] response) {
        ccSeeCustomerView.createButtons(response);
    }
}
