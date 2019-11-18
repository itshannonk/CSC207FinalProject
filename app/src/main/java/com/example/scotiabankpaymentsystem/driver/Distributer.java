package com.example.scotiabankpaymentsystem.driver;

import android.printservice.CustomPrinterIconCallback;

import com.example.scotiabankpaymentsystem.Invoice.Invoice;
import com.example.scotiabankpaymentsystem.businessowner.Customer;

import java.security.KeyStore;
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

    }

    public void updateStatus(Customer customer) {
        ArrayList<Invoice> invoice_list = customer.getInvoice();
        for (Invoice i : invoice_list){
            i.getStatus().setDelivered(true);
        }
    }
}
