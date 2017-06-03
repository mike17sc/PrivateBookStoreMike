package com.training.model;

import java.util.Date;

/**
 * Created by Mschneider on 03-06-17.
 */
public class Content {
    private String text;
    private Date lastUpdateDate;

    public Content() {
    }

    public Content(String text, Date lastUpdateDate) {
        this.text = text;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
