package com.training.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Mschneider on 04-06-17.
 */
@Entity
public class LoginLog {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade={CascadeType.ALL})
    private User user;
    private Date logon;
    private Date logout;
    private long duration;

    public LoginLog() {
    }

    public LoginLog(User user, Date logon) {
        this.user = user;
        this.logon = logon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLogon() {
        return logon;
    }

    public void setLogon(Date logon) {
        this.logon = logon;
    }

    public Date getLogout() {
        return logout;
    }

    public void setLogout(Date logout) {
        this.logout = logout;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
