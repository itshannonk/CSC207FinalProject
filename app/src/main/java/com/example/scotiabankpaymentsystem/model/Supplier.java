package com.example.scotiabankpaymentsystem.model;

import java.util.ArrayList;

public class Supplier {
    private ArrayList<Customer> customers;

    public Supplier() {

    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public Invoice createInvoice() {
        return null;
    }

    public void setPaidStatus() {
        // Update invoice status remotely and locally
        for (Customer c : customers) {
            for (Invoice i : c.getInvoice()) {
                i.getStatus().setPaid(true);
            }
        }
    }
}