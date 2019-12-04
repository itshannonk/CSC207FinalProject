package com.example.scotiabankpaymentsystem.driver.displayinvoice;

import android.content.Context;

public class DriverDisplayInvoicePresenter implements DriverDisplayInvoiceInteractor.onDisplayDataFinishedListener {
    private DriverDisplayInvoiceView driverDisplayInvoiceView;
    private DriverDisplayInvoiceInteractor driverDisplayInvoiceInteractor;

    DriverDisplayInvoicePresenter(DriverDisplayInvoiceView driverDisplayInvoiceView, DriverDisplayInvoiceInteractor driverDisplayInvoiceInteractor) {
        this.driverDisplayInvoiceView = driverDisplayInvoiceView;
        this.driverDisplayInvoiceInteractor = driverDisplayInvoiceInteractor;
    }

    void onDestroy() {
        driverDisplayInvoiceView = null;
    }

    @Override
    public void onChangeDeliveredSuccess() {
        driverDisplayInvoiceView.changeDeliveredTrue();
    }

    @Override
    public void onChangeDeliveredError() {
        driverDisplayInvoiceView.changeDeliveredError();
    }

    @Override
    public void onInvoiceRetriveSuccess(String[] info) {
        driverDisplayInvoiceView.setInvoiceInfo(info);
    }

    @Override
    public void onDelivered(String userID, String invoiceID, Context context) {
        driverDisplayInvoiceInteractor.changeDeliveredBoolean(this, userID, invoiceID, context);
    }

    public void startSetInvoiceInfo(String userID, String invoiceID, Context context) {
        driverDisplayInvoiceInteractor.retrieveInvoice(this, userID, invoiceID, context);
    }

}
