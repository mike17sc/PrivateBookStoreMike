package com.training.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Mschneider on 03-06-17.
 */
@Entity
public class Content {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    private Date lastUpdateDate;
    @ManyToOne
    private Pages pages;

    public Content() {
    }

    public Content(Long id, String text, Date lastUpdateDate, Pages pages) {
        this.id = id;
        this.text = text;
        this.lastUpdateDate = lastUpdateDate;
        this.pages = pages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Pages getPages() {
        return pages;
    }

    public void setPages(Pages pages) {
        this.pages = pages;
    }
}
