package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;


import android.content.Context;

/**
 * This is the View and it calls a presenter method every time
 */

public interface SBODisplayInvoiceView {
    void navigateToPay();

    void startSetInvoiceInfo();

    void setInvoiceInfo(String[] info);

    void changePayTrue();

    void changePayError();
}
