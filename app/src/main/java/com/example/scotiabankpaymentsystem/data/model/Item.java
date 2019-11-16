package com.example.scotiabankpaymentsystem.data.model;

public class Item {
    private String name;
    private double price;
    private int itemID;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}
