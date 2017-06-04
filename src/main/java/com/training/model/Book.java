package com.training.model;

import javax.persistence.*;

/**
 * Created by Mschneider on 03-06-17.
 */
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private int price;
    private int quantity;
    private Boolean status;

    public Book() {

    }

    public Book(int price, int quantity, Boolean status) {
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
