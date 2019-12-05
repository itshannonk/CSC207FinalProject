package com.example.scotiabankpaymentsystem.businessowner.seeinvoices;

public interface SBOSeeInvoicesView {
    /**
     * create button shows user ids
     * @param IDs
     */
    void createButtons(String[] IDs);

    /**
     * retrieve invpice id from backend
     * @param userID id of user
     */
    void retrieveInvoiceIDs(String userID);
}
