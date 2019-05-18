package com.team.nebula.petpujodelivery.model;

public class OrderItems {

    private String foodname;
    private int price;
    private int quantity;

    OrderItems(){}
    OrderItems( String foodname,
             int price,
             int quantity){}

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}