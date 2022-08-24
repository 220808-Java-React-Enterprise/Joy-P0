package com.revature.blazinhot.models;

public class Transaction {
    private String id;
    private String warehouse_id;

    public Transaction(String id, String warehouse_id) {
        this.id = id;
        this.warehouse_id = warehouse_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", warehouse_id='" + warehouse_id + '\'' +
                '}';
    }
}
