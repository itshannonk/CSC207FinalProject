package com.example.scotiabankpaymentsystem.businessowner.seeinvoices;

import android.content.Context;

public class SBOSeeInvoicesPresenter implements SBOSeeInvoicesInteractor.onDisplayDataFinishedListener {
    private SBOSeeInvoicesView sboSeeInvoicesView;
    private SBOSeeInvoicesInteractor sboSeeInvoicesInteractor;

    SBOSeeInvoicesPresenter(SBOSeeInvoicesView sboSeeInvoicesView, SBOSeeInvoicesInteractor sboSeeInvoicesInteractor) {
        this.sboSeeInvoicesView = sboSeeInvoicesView;
        this.sboSeeInvoicesInteractor = sboSeeInvoicesInteractor;
    }

    void onDestroy() {
        sboSeeInvoicesView = null;
    }

    void retrieveInvoiceID(String userID, Context context) {
        sboSeeInvoicesInteractor.retrieveInvoiceID(this, userID, context);
    }

    @Override
    public void onInvoicesRetrievalSuccess(String[] IDs) {
        sboSeeInvoicesView.createButtons(IDs);
    }

}
