package com.training.model;

import java.util.HashSet;

/**
 * Created by Mschneider on 03-06-17.
 */
public class Book {
    private Long id;
    private int price;
    private int quantity;
    private Pages pages;
    private Boolean status;

    public Book() {

    }

    public Book(Long id, int price, int quantity, Pages pages, Boolean status) {
        this.id = id;
        this.price = price;
        this.quantity = quantity;
        this.pages = pages;
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

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
