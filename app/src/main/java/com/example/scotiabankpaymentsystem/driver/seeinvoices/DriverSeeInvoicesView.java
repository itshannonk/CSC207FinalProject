package com.example.scotiabankpaymentsystem.driver.seeinvoices;

/**
 * The interface Driver see invoices view.
 */
public interface DriverSeeInvoicesView {
    /**
     * Create buttons.
     *
     * @param IDs the ds
     */
    void createButtons(String[] IDs);

    /**
     * Retrieve invoice id.
     *
     * @param userID the user id
     */
    void retrieveInvoiceID(String userID);
}
