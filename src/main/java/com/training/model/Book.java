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
    private String status;

    public Book() {

    }

    public Book(int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
        if(quantity<=0){
            this.status="OUT_OF_STOCK";
        }
        else{
            this.status="AVAILABLE";
        }
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
        if (quantity==0){
            this.status="OUT_OF_STOCK";
        }
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
