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

    /**
     * After the backend Change delivered boolean success, change front end delivered boolean
     */
    @Override
    public void onChangeDeliveredSuccess() {
        driverDisplayInvoiceView.changeDeliveredTrue();
    }

    /**
     * if change delivered boolean in backend has error, display the error message in frontend
     */
    @Override
    public void onChangeDeliveredError() {
        driverDisplayInvoiceView.changeDeliveredError();
    }

    /**
     * pass the retrieve invoice to view from presenter
     *
     * @param info a String array "Issued, Paid, Delivered, total price, item, price, quantity"
     */
    @Override
    public void onInvoiceRetrievalSuccess(String[] info) {
        driverDisplayInvoiceView.setInvoiceInfo(info);
    }

    /**
     * pass the signal to interactor in order to tell backend start to change pay boolean
     * @param userID id of user
     * @param invoiceID id of invoice
     * @param context the context of method that happening
     */
    @Override
    public void onDelivered(String userID, String invoiceID, Context context) {
        driverDisplayInvoiceInteractor.changeDeliveredBoolean(this, userID, invoiceID, context);
    }

    /**
     * pass the signal to interactor in order to tell backend start to retrieve invoice
     *
     * @param userID id of user
     * @param invoiceID id of invoice
     * @param context the context of method that happening
     */
    public void startSetInvoiceInfo(String userID, String invoiceID, Context context) {
        driverDisplayInvoiceInteractor.retrieveInvoice(this, userID, invoiceID, context);
    }

}
