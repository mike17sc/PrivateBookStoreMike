package com.training.model;

import javax.persistence.*;

/**
 * Created by Mschneider on 03-06-17.
 */
@Entity
public class BuyBook {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Client client;
    @OneToOne
    private Book book;
    private int quantity;
    private String delivery;

    public BuyBook() {
    }

    public BuyBook(Client client, Book book, int quantity,String delivery) {
        this.client = client;
        this.book = book;
        this.quantity = quantity;
        this.delivery=delivery;
    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }
}
