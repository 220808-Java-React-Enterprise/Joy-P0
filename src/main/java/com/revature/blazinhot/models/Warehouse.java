package com.revature.blazinhot.models;

public class Warehouse {
    private String id;
    private String city;
    private String state;
    private String name;
    private String hotsauce_id;
    private int quantity;

    public Warehouse(String id, String city, String state, String name, String hotsauce_id, int quantity) {
        this.id = id;
        this.city = city;
        this.state = state;
        this.name = name;
        this.hotsauce_id = hotsauce_id;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", name='" + name + '\'' +
                ", hotsauce_id='" + hotsauce_id + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHotsauce_id() {
        return hotsauce_id;
    }

    public void setHotsauce_id(String hotsauce_id) {
        this.hotsauce_id = hotsauce_id;
    }

}
