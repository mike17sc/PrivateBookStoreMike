package com.training.model;

/**
 * Created by Mschneider on 03-06-17.
 */
public class Pages {
    private int number;
    private String title;
    private Content content;

    public Pages() {
    }

    public Pages(int number, String title, Content content) {
        this.number = number;
        this.title = title;
        this.content = content;
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
