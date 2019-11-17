package com.example.scotiabankpaymentsystem.Item;

public class Item {
    private String name;
    private float price;
    private int id;

    public Item() {

    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }
}
