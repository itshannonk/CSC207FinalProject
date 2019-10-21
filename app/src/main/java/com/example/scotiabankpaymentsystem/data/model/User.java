package com.example.scotiabankpaymentsystem.data.model;

public abstract class User {
    private String name;
    private String password;
    private String address;
    private int customerID;
    // private List<Order> orders;
    // private Invoice invoice;

    public User(String name, String password, String address) {
        this.name = name;
        this.password = password;
        this.address = address;
    }

    public void seeStatus() {
        
    }
}
