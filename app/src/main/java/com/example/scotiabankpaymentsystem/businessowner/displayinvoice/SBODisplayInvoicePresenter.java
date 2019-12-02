package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;

import android.content.Context;

public class SBODisplayInvoicePresenter implements com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceInteractor.onDisplayDataFinishedListener {
    private SBODisplayInvoiceView sboDiaplayInvoiceView;
    private com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceInteractor sboDisplayInvoiceInteractor;

    SBODisplayInvoicePresenter(SBODisplayInvoiceView sboDiaplayInvoiceView, com.example.scotiabankpaymentsystem.businessowner.displayinvoice.SBODisplayInvoiceInteractor sboDisplayInvoiceInteractor) {
        this.sboDiaplayInvoiceView = sboDiaplayInvoiceView;
        this.sboDisplayInvoiceInteractor = sboDisplayInvoiceInteractor;
    }

    void onDestroy() {
        sboDiaplayInvoiceView = null;
    }

    @Override
    public void onChangePaySuccess() {
        sboDiaplayInvoiceView.changePayTrue();
    }

    @Override
    public void onChangePayError() {
        sboDiaplayInvoiceView.changePayError();
    }

    @Override
    public void onInvoiceRetriveSuccess(String[] info) {
        sboDiaplayInvoiceView.setInvoiceInfo(info);
    }

    @Override
    public void onPay(String userID, String invoiceID, Context context) {
        sboDisplayInvoiceInteractor.changePayBoolean(this, userID, invoiceID, context);
    }

    public void startSetInvoiceInfo(String userID, String invoiceID, Context context) {
        sboDisplayInvoiceInteractor.retrieveInvoice(this, userID, invoiceID, context);
    }


}