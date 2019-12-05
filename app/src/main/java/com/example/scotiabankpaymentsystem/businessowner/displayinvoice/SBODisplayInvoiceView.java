package com.example.scotiabankpaymentsystem.businessowner.displayinvoice;


/**
 * This is the View and it calls a presenter method every time
 */

public interface SBODisplayInvoiceView {
    /**
     * Start to change pay boolean
     */
    void navigateToPay();

    /**
     * Start display the retrieve invoice in the format we want
     */
    void startSetInvoiceInfo();

    /**
     * Start display the retrieve invoice in the format we want
     *
     * @param info the invoice we retrieve, which in the format "delivered, issued, paid,
     *     total price, item, price, quantity"
     */
    void setInvoiceInfo(String[] info);

    /**
     * change paid from false to true in the xml
     */
    void changePayTrue();

    /**
     * display the toast error message if there is an error in change pay boolean
     */
    void changePayError();
}
