package com.revature.blazinhot.models;

public class User {
    private String id;
    private String username;
    private String password;
    private String role = "DEFAULT";
    private String email;
    private String cart_id;

    public User() {

    }

    public User(String id, String username, String password, String email, String cart_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.cart_id = cart_id;
    }

    public User(String id, String username, String password, String email, String cart_id, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;
        this.cart_id = cart_id;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String toFileString() {
        return id + ":" + username + ":" + password + ":" + role + "\n";
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", cart_id='" + cart_id + '\'' +
                '}';
    }
}
