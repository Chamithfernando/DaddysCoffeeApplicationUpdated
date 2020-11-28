package com.daddysCoffee.application.Entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    @NotNull
    private String customerUserName;
    @NotNull
    private String customerPassword;


    //Non args constructor
    public Customers() {
    }

    // All args constructor
    public Customers(int customerId, String customerUserName, String customerPassword) {
        this.customerId = customerId;
        this.customerUserName = customerUserName;
        this.customerPassword = customerPassword;
    }

    //Getters & Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUserName() {
        return customerUserName;
    }

    public void setCustomerUserName(String customerUserName) {
        this.customerUserName = customerUserName;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }


}
