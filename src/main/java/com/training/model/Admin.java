package com.training.model;

/**
 * Created by Mschneider on 03-06-17.
 */
public class Admin extends User {
    public Admin() {
    }

    public Admin(long id,String username, String password, String name) {
        super(id,username, password, name);
    }

}
