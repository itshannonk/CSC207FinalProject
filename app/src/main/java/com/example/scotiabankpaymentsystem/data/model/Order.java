package com.example.scotiabankpaymentsystem.data.model;

public class Order {
    private int orderID = -1;
    //the first index will be the Order, the second will be the amount, the third will be the price
    private Object[] listOfInfo;

    public Order(){
        orderID =+ 1;
    }
    public Object[] getListOfInfo() {
        return listOfInfo;
    }

    int getItemID() {
        return this.orderID;
    }

    public void setListOfInfo(Item item, int amount, double price) {
        this.listOfInfo[0] = item;
        this.listOfInfo[1] = amount;
        this.listOfInfo[2] = price;
    }
}
