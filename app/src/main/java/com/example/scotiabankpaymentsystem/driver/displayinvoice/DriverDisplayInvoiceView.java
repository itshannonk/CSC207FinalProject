package com.example.scotiabankpaymentsystem.driver.displayinvoice;

public interface DriverDisplayInvoiceView {
    void navigateToDelivery();

    void startSetInvoiceInfo();

    void setInvoiceInfo(String[] info);

    void changeDeliveredTrue();

    void changeDeliveredError();
}
