package com.example.scotiabankpaymentsystem.data.model;

public class Status {
    private boolean issued;
    private boolean paid;
    private boolean delivered;
    private boolean signed;

    boolean getIssued(){
        return this.issued;
    }
    boolean getPaid(){
        return this.paid;
    }
    boolean getDelivered(){
        return this.delivered;
    }
    boolean getSigned(){
        return this.signed;
    }

    boolean setSigned(boolean signed){
        return this.signed = signed;
    }
    boolean setIssued(boolean issued){
        return this.signed = issued;
    }
    boolean setPaid(boolean paid){
        return this.signed = paid;
    }
    boolean setDelivered(boolean delivered){
        return this.signed = delivered;
    }
}
