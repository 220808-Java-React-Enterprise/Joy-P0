package com.revature.blazinhot.models;

public class Order {
    private String id;
    private String user_id;
    private String hotsauce_id;
    private String cart_id;
    private int amount;
    private double total;

    public Order(String id, String user_id, String hotsauce_id, String cart_id, int amount, double total) {
        this.id = id;
        this.user_id = user_id;
        this.hotsauce_id = hotsauce_id;
        this.amount = amount;
        this.total = total;
        this.cart_id = cart_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getHotsauce_id() {
        return hotsauce_id;
    }

    public void setHotsauce_id(String hotsauce_id) {
        this.hotsauce_id = hotsauce_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", hotsauce_id='" + hotsauce_id + '\'' +
                ", cart_id='" + cart_id + '\'' +
                ", amount=" + amount +
                ", total=" + total +
                '}';
    }

}
