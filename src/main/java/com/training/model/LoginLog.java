package com.training.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * Created by Mschneider on 04-06-17.
 */
@Entity
public class LoginLog {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private Date logon;
    private Date logout;

    public LoginLog() {
    }

    public LoginLog(User user, Date logon, Date logout) {
        this.user = user;
        this.logon = logon;
        this.logout = logout;
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
}
