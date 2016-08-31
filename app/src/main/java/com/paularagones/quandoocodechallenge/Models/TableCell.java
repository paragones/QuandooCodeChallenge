package com.paularagones.quandoocodechallenge.Models;

import io.realm.RealmObject;

/**
 * Created by Mack and Aragones on 30/08/2016.
 */
public class TableCell extends RealmObject{

    private int id;
    private Boolean isReserved = true;
    private String customerName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean isReserved() {
        return isReserved;
    }

    public void setReserved(Boolean reserved) {
        isReserved = reserved;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "TableCell{" +
                ", isReserved=" + isReserved +
                '}';
    }
}
