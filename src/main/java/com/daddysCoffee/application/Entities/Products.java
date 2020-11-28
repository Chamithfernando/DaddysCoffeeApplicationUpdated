package com.daddysCoffee.application.Entities;

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productId;
    @NotNull
    private String productName;
    private String productDiscription;

    //Non args constructor
    public Products() {

    }

   // All args constructor
    public Products(int productId, String productName, String productDiscription) {
        this.productId = productId;
        this.productName = productName;
        this.productDiscription = productDiscription;
    }

    //Getters & Setters

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDiscription() {
        return productDiscription;
    }

    public void setProductDiscription(String productDiscription) {
        this.productDiscription = productDiscription;
    }

}
