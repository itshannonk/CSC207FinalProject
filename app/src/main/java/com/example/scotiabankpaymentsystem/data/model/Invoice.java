package com.example.scotiabankpaymentsystem.data.model;

import java.util.List;

public class Invoice {
    private List<Item> listOfItems;
    private double totalPrice;
    private boolean statusOfInvoice;
    private int ID;

    void setStatusOfInvoice(boolean status){
        this.statusOfInvoice = status;
    }
    boolean getStatusOfInvoice(){
        return this.statusOfInvoice;
    }
    void addItem(Item newItem){
        listOfItems.add(newItem);
    }

    List<Item> getListOfOrder(){
        return this.listOfItems;
    }
    double getTotalPrice(){
        return totalPrice;
    }

    void setTotalPrice(double newPrice){
        this.totalPrice = newPrice;
    }

    int getID(){
        return this.ID;
    }
}
