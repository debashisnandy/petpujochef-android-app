package com.team.nebula.petpujodelivery.model;

import java.util.ArrayList;

public class ChefOrders {

    ChefOrders(){}

    private int date ;
    private ArrayList<OrderItems> orderItems;
    private String roll;
    private String time;
    private int totalAmount;
    private String  transactionId;
    private String user;
    private String orderStatus;
    private String orderType;

    ChefOrders( int date,
                ArrayList<OrderItems> orderItems,
                String roll,
                String time,
                int totalAmount,
                String  transactionId,
                String user,
                String orderStatus,
                String orderType){}



    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public ArrayList<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOrderStatus(){ return orderStatus;}

    public void setOrderStatus(){ this.orderStatus= orderStatus;}

    public String getOrderType(){ return orderType;}

    public void setOrderType(){ this.orderType=orderType; }
}
