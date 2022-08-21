package com.revature.blazinhot.models;

public class Hotsauce {
    private String id;
    private String name;
    private String spiciness;
    private double price;

    public Hotsauce(String id, String name, String spiciness, double price) {
        this.id = id;
        this.name = name;
        this.spiciness = spiciness;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Hotsauce{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", spiciness='" + spiciness + '\'' +
                ", price=" + price +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpiciness() {
        return spiciness;
    }

    public void setSpiciness(String spiciness) {
        this.spiciness = spiciness;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
