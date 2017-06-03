package com.training.model;

/**
 * Created by Mschneider on 03-06-17.
 */
public class BuyBook {
    private long id;
    private Client client;
    private Book book;

    public BuyBook() {
    }

    public BuyBook(long id, Client client, Book book) {
        this.id = id;
        this.client = client;
        this.book = book;
    }

    public long getId() {
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
}
