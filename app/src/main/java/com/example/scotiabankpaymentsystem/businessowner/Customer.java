package com.example.scotiabankpaymentsystem.businessowner;

import java.util.ArrayList;
import com.example.scotiabankpaymentsystem.invoice.Invoice;

public class Customer {
    private String name;
    private String address;
    private static int counter;
    private int id;
    private ArrayList<Invoice> invoice;

    public Customer() {
        this.id = counter++;
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

    public void setInvoice(ArrayList<Invoice> invoice)
    {//why is this a set invoice not addint it to an array

        // DON'T FORGET TO ADD THIS INVOICE by calling the addInvoiceToFirebaseMethod or it might cause problems
        this.invoice = invoice;
    }

    public Invoice receiveInvoice() {
        // Save invoice to database under current customer's name
        return null;
    }

    public void payInvoice() {
        // Update invoice status locally and in remote database
    }
    public void addInvoiceToFireBase(Invoice invoice){

    }
}
