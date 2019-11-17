package com.example.scotiabankpaymentsystem.data.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice {
    private List<Order> listOfOrders = new ArrayList<Order>();
    private double totalPrice;
    private boolean statusOfInvoice = false;
    private int ID = -1;

    public void setListOfOrders(List<Order> listOfOrders) {
        this.listOfOrders = listOfOrders;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Order> getListOfOrders() {
        return listOfOrders;
    }

    public boolean isStatusOfInvoice() {
        return statusOfInvoice;
    }

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

    @Override
    public String toString(){
        return "invoice ID " + this.ID;
    }
}
