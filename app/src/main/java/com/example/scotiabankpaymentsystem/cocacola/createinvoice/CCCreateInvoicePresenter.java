package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

import android.content.Context;

class CCCreateInvoicePresenter implements CCCreateInvoiceInteractor.onDisplayDataFinishedListener {
    private CCCreateInvoiceView ccCreateInvoiceView;
    private CCCreateInvoiceInteractor ccCreateInvoiceInteractor;

    CCCreateInvoicePresenter(CCCreateInvoiceView ccCreateInvoiceView, CCCreateInvoiceInteractor ccCreateInvoiceInteractor) {
        this.ccCreateInvoiceInteractor = ccCreateInvoiceInteractor;
        this.ccCreateInvoiceView = ccCreateInvoiceView;
    }

    void onDestroy() {
        ccCreateInvoiceView = null;
    }

    void createInvoice(String item, String price, String quantity, String userID, Context context) {
        ccCreateInvoiceInteractor.createInvoice(this, item, price, quantity, userID, context);
    }

    @Override
    public void onCreateInvoiceSuccess() {
        ccCreateInvoiceView.invoiceSuccess();

    }

    @Override
    public void onCreateInvoiceError() {
        ccCreateInvoiceView.inputError();
    }

}
