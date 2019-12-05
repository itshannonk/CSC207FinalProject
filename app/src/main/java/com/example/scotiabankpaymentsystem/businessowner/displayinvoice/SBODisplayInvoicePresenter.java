package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;

import android.content.Context;


public class SBODisplayInvoicePresenter implements SBODisplayInvoiceInteractor.onDisplayDataFinishedListener {
    private SBODisplayInvoiceView view;
    private SBODisplayInvoiceInteractor interactor;

    SBODisplayInvoicePresenter(SBODisplayInvoiceView view, com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    void onDestroy() {
        view = null;
    }
<<<<<<< HEAD
    
=======

    /**
     * After the backend Change Pay boolean success, change front end pay boolean
     */
>>>>>>> f4058c89e177be83dc920b2901f76b0ab3ed118e
    @Override
    public void onChangePaySuccess() {
        view.changePayTrue();
    }

    /**
     * if change pay boolean in backend has error, display the error message in frontend
     */
    @Override
    public void onChangePayError() {
        view.changePayError();
    }

    /**
     * pass the retrieve invoice to view from presenter
     *
     * @param info a String array "Issued, Paid, Delivered, total price, item, price, quantity"
     */
    @Override
    public void onInvoiceRetriveSuccess(String[] info) {
        view.setInvoiceInfo(info);
    }

    /**
     * pass the signal to interactor in order to tell backend start to change pay boolean
     * @param userID id of user
     * @param invoiceID id of invoice
     * @param context the context of method that happening
     */
    @Override
    public void onPay(String userID, String invoiceID, Context context) {
        interactor.changePayBoolean(this, userID, invoiceID, context);
    }

    /**
     * pass the signal to interactor in order to tell backend start to retrieve invoice
     *
     * @param userID id of user
     * @param invoiceID id of invoice
     * @param context the context of method that happening
     */
    public void startSetInvoiceInfo(String userID, String invoiceID, Context context) {
        interactor.retrieveInvoice(this, userID, invoiceID, context);
    }

}