package com.example.scotiabankpaymentsystem.driver.seeinvoices;

import android.content.Context;

public class DriverSeeInvoicesPresenter implements DriverSeeInvoicesInteractor.onDisplayDataFinishedListener{

    private DriverSeeInvoicesView driverSeeInvoicesView;
    private DriverSeeInvoicesInteractor driverSeeInvoicesInteractor;

    DriverSeeInvoicesPresenter(DriverSeeInvoicesView driverSeeInvoicesView, DriverSeeInvoicesInteractor driverSeeInvoicesInteractor) {
        this.driverSeeInvoicesView = driverSeeInvoicesView;
        this.driverSeeInvoicesInteractor = driverSeeInvoicesInteractor;
    }

    void onDestroy() {
        driverSeeInvoicesView = null;
    }

    void retrieveInvoiceID(String userID, Context context) {
        driverSeeInvoicesInteractor.retrieveInvoiceID(this, userID, context);
    }

    @Override
    public void onInvoicesRetrievalSuccess(String[] IDs) {
        driverSeeInvoicesView.createButtons(IDs);
    }
}
