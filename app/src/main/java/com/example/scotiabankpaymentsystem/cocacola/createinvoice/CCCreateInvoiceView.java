package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

/**
 * This is the View interface for the creation of invoices for CocaCola.
 */
interface CCCreateInvoiceView {
    void inputError();

    void invoiceSuccess();

    void createInvoice(String item, String price, String quantity, String userID);

}
