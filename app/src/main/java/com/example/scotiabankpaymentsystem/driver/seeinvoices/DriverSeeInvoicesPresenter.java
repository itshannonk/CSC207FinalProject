package com.example.scotiabankpaymentsystem.driver.seeinvoices;

import android.content.Context;

/**
 * The type Driver see invoices presenter.
 */
public class DriverSeeInvoicesPresenter implements DriverSeeInvoicesInteractor.onDisplayDataFinishedListener{

    private DriverSeeInvoicesView driverSeeInvoicesView;
    private DriverSeeInvoicesInteractor driverSeeInvoicesInteractor;

    /**
     * Instantiates a new Driver see invoices presenter.
     *
     * @param driverSeeInvoicesView       the driver see invoices view
     * @param driverSeeInvoicesInteractor the driver see invoices interactor
     */
    DriverSeeInvoicesPresenter(DriverSeeInvoicesView driverSeeInvoicesView, DriverSeeInvoicesInteractor driverSeeInvoicesInteractor) {
        this.driverSeeInvoicesView = driverSeeInvoicesView;
        this.driverSeeInvoicesInteractor = driverSeeInvoicesInteractor;
    }

    /**
     * On destroy.
     */
    void onDestroy() {
        driverSeeInvoicesView = null;
    }

    /**
     * Retrieve invoice id.
     *
     * @param userID  the user id
     * @param context the context
     */
    void retrieveInvoiceID(String userID, Context context) {
        driverSeeInvoicesInteractor.retrieveInvoiceID(this, userID, context);
    }

    @Override
    /**
     * When the invoices have been succesfully retrieved, this method will be called.
     */
    public void onInvoicesRetrievalSuccess(String[] IDs) {
        driverSeeInvoicesView.createButtons(IDs);
    }
}
