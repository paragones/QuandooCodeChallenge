package com.paularagones.quandoocodechallenge.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by Mack and Aragones on 30/08/2016.
 */

@RealmClass
public class Customer extends RealmObject {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;

    @SerializedName("customerLastName")
    @Expose
    private String customerLastName;

    @SerializedName("customerFirstName")
    @Expose
    private String customerFirstName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    @Override
    public String toString() {
        return customerFirstName + " " + customerLastName;
    }
}
