package com.daddysCoffee.application.Entities;


import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    @NotNull
    private int orderQuantity;
    @NotNull
    private Date orderDate;


    //Non args constructor
    public Orders() {
    }

    // All args constructor
    public Orders(int orderId, int orderQuantity, Date orderDate) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
        this.orderDate = orderDate;
    }

    //Getters & Setters
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
