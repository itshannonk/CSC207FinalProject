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

    @Override
    public void onChangePaySuccess() {
        view.changePayTrue();
    }

    @Override
    public void onChangePayError() {
        view.changePayError();
    }

    @Override
    public void onInvoiceRetriveSuccess(String[] info) {
        view.setInvoiceInfo(info);
    }

    @Override
    public void onPay(String userID, String invoiceID, Context context) {
        interactor.changePayBoolean(this, userID, invoiceID, context);
    }

    public void startSetInvoiceInfo(String userID, String invoiceID, Context context) {
        interactor.retrieveInvoice(this, userID, invoiceID, context);
    }

}