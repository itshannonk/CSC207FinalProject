package com.example.scotiabankpaymentsystem.data.model;

/**
 * A supplier. A subclass of User.
 */
public class Supplier extends User {

    public Supplier(String name, String password, String address) {
        super(name, password, address);
    }

    /**
     * Receive an order from a business owner
     */
    void receiveOrder() {

    }

    /**
     * Change the status of ________?
     */
    void changeStatus() {
        // 1. pay
        // 2. receive receipt
    }
}
