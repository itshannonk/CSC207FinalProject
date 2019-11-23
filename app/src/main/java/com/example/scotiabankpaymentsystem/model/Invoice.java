package com.example.scotiabankpaymentsystem.model;

import com.example.scotiabankpaymentsystem.model.Order;
import com.example.scotiabankpaymentsystem.model.Status;

import java.util.ArrayList;

// IS IT BAD TO HAVE STATIC VARIABLES????
public class Invoice {
    private ArrayList<Order> orders;
    private int price;
    private Status status;
    private static int counter = 0;
    private int id;

    public Invoice() {
        this.status = new Status();
        this.id = counter++;
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
        // Update invoice status locally and remotely
    }
}
