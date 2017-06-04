package com.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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

    public BuyBook() {
    }

    public BuyBook(Long id, Client client, Book book) {
        this.id = id;
        this.client = client;
        this.book = book;
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
}
