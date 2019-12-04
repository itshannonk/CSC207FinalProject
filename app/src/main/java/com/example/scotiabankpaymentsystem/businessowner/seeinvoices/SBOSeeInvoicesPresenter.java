package com.example.scotiabankpaymentsystem.businessowner.seeinvoices;

import android.content.Context;

/**
 * This is the presenter of SBOSeeInvoices, which acts as a middle man between frontend and backend
 */
public class SBOSeeInvoicesPresenter implements SBOSeeInvoicesInteractor.onDisplayDataFinishedListener {
    private SBOSeeInvoicesView view;
    private SBOSeeInvoicesInteractor interactor;

    SBOSeeInvoicesPresenter(SBOSeeInvoicesView view, SBOSeeInvoicesInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    void onDestroy() {
        view = null;
    }

    /**
     * Retrieves the InvoiceIDs of SBO
     *
     * @param userID  the userID of the current logged in user
     * @param context an instance of SBOSeeInvoicesActivity/View
     */
    void retrieveInvoiceIDs(String userID, Context context) {
        interactor.retrieveInvoiceID(this, userID, context);
    }

    /**
     * InvoiceIDs are successfully retrieved and it gets passed back to the View to display(Frontend)
     *
     * @param IDs the invoiceIDs of the SBO
     */
    @Override
    public void onInvoicesRetrievalSuccess(String[] IDs) {
        view.createButtons(IDs);
    }

}
