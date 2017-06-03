package com.training.model;

import java.util.HashSet;

/**
 * Created by Mschneider on 03-06-17.
 */
public class Client extends User {
    private String address;
    private String email;

    public Client() {
    }

    public Client(long id,String username, String password, String name, String address, String email) {
        super(id,username, password, name);
        this.address = address;
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
