package com.example.scotiabankpaymentsystem.driver.displayinvoice;

public interface DriverDisplayInvoiceView {
    /**
     * Start to change delivered boolean
     */
    void navigateToDelivery();

    /**
     * Start display the retrieve invoice in the format we want
     */
    void navigateToSeeSBOInfo();

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
     * change delivered from false to true in the xml
     */
    void changeDeliveredTrue();

    /**
     * display the toast error message if there is an error in change delivered boolean
     */
    void changeDeliveredError();
}
