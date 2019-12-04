package com.example.scotiabankpaymentsystem.cocacola.createinvoice;

interface CCCreateInvoiceView {
    void inputError();

    void invoiceSuccess();

    void createInvoice(String item, String price, String quantity, String userID);

}
