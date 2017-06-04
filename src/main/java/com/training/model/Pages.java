package com.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Mschneider on 03-06-17.
 */
@Entity
public class Pages {
    @Id
    @GeneratedValue
    private int number;
    private String title;
    @ManyToOne
    private Book book;

    public Pages() {
    }

    public Pages(int number, String title, Book book) {
        this.number = number;
        this.title = title;
        this.book = book;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
