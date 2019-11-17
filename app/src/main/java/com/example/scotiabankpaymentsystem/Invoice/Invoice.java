package com.example.scotiabankpaymentsystem.Invoice;

import com.example.scotiabankpaymentsystem.Order.Order;
import com.example.scotiabankpaymentsystem.Status.Status;

import java.util.ArrayList;

public class Invoice {
    private ArrayList<Order> orders;
    private int price;
    private Status status;
    private int id;

    public Invoice() {

    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public int getPrice() {
        return price;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void changeStatus() {

    }
}
