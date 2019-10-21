package com.example.scotiabankpaymentsystem.data.model;

public class Item {
    private int itemID;
    //the first index will be the Item string, the second will be the amount the third will be the price
    private String[] listOfInfo;

    int getItemID(){
        return this.itemID;
    }

    void setListOfInfo(String item, String amount, String price){
        this.listOfInfo[0] = item;
        this.listOfInfo[1] = amount;
        this.listOfInfo[2] = price;
    }
}
