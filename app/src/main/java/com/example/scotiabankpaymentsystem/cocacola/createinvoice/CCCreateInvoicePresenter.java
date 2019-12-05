package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

import android.content.Context;

/**
 * This is the presenter for the creation of the invoices for CocaCola.
 */
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

    /**
     * This is the ability to create an invoice.
     *
     * @param item This is the item name that needs to be added for the new invoice.
     * @param price This is the individual price of the item that is required for the invoice.
     * @param quantity This is the amount that the user requested for the particular item.
     * @param userID This is the UserID of what user needs to be associated with this invoice.
     * @param context This is the Context in which this Activity is happening.
     */
    void createInvoice(String item, String price, String quantity, String userID, Context context) {
        ccCreateInvoiceInteractor.createInvoice(this, item, price, quantity, userID, context);
    }

    @Override
    /**
     * This is when the Invoice creation has been a success.
     */
    public void onCreateInvoiceSuccess() {
        ccCreateInvoiceView.invoiceSuccess();

    }

    @Override
    /**
     * This is when the Invoice creation has failed.
     */
    public void onCreateInvoiceError() {
        ccCreateInvoiceView.inputError();
    }

}
