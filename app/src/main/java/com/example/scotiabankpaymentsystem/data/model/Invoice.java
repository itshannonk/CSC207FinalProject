package com.example.scotiabankpaymentsystem.data.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<Order> listOfOrders = new ArrayList<Order>();
    private double totalPrice;
    private boolean statusOfInvoice = false;
    private int ID = -1;

    public Invoice(){
        ID += 1;
        listOfOrders.add(new Order());
        listOfOrders.add(new Order());
        statusOfInvoice = false;
    }
    void setStatusOfInvoice(boolean status) {
        this.statusOfInvoice = status;
    }

    boolean getStatusOfInvoice() {
        return this.statusOfInvoice;
    }

    public void addOrder(Order newOrder) {
        listOfOrders.add(newOrder);
    }

    List<Order> getListOfOrder() {
        return this.listOfOrders;
    }

    double getTotalPrice() {
        return totalPrice;
    }

    void setTotalPrice(double newPrice) {
        this.totalPrice = newPrice;
    }

    public int getID() {
        return this.ID;
    }
}
