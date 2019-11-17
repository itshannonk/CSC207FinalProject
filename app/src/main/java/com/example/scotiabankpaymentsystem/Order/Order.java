package com.example.scotiabankpaymentsystem.Order;

import com.example.scotiabankpaymentsystem.Item.Item;

import java.util.ArrayList;

public class Order {
    private ArrayList<Item> items;
    private int id;

    public Order() {

    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setId(int id) {
        this.id = id;
    }
}
