package com.example.scotiabankpaymentsystem.status;

public class Status {
    private boolean issued = false;
    private boolean paid = false;
    private boolean delivered = false;

    public Status() {

    }

    public boolean isIssued() {
        return issued;
    }

    public boolean isPaid() {
        return paid;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }
}
