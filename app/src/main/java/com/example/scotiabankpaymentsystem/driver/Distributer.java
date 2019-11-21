package com.example.scotiabankpaymentsystem.driver;

import com.example.scotiabankpaymentsystem.invoice.Invoice;
import com.example.scotiabankpaymentsystem.businessowner.Customer;

import java.util.ArrayList;

public class Distributer {
    private ArrayList<Customer> customers;

    public Distributer() {

    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public boolean checkStatus(Customer customer) {
        return false;
    }

    public void deliverGoods(Customer customer) {
        // Update invoice status locally and remotely
    }

    public void updateStatus(Customer customer) {
        ArrayList<Invoice> invoice_list = customer.getInvoice();
        for (Invoice i : invoice_list){
            i.getStatus().setDelivered(true);
        }
    }
}
