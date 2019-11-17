package com.example.scotiabankpaymentsystem.businessowner;

import java.util.ArrayList;
import com.example.scotiabankpaymentsystem.Invoice.Invoice;

public class Customer {
    private String name;
    private String address;
    private int id;
    private ArrayList<Invoice> invoice;

    public Customer() {

    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Invoice> getInvoice() {
        return invoice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInvoice(ArrayList<Invoice> invoice) {
        this.invoice = invoice;
    }

    public Invoice receiveInvoice() {
        return null;
    }

    public void payInvoice() {

    }
}
