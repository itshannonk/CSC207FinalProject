package com.example.scotiabankpaymentsystem.cocacola;

import com.example.scotiabankpaymentsystem.businessowner.Customer;
import com.example.scotiabankpaymentsystem.Invoice.Invoice;

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
    }
}
