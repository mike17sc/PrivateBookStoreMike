package com.training.model;

import javax.persistence.Entity;

/**
 * Created by Mschneider on 03-06-17.
 */
@Entity
public class Admin extends User {
    public Admin() {

    }

    ;

    public Admin(String username, String password, String name) {
        super(username, password, name);
    }
}
