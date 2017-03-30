package com.robvangastel.kwetter.bean.model;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Created by Rob on 30-3-2017.
 */
@Named
@RequestScoped
public class Credentials {

    private String username;
    private String password;
    private String email;

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}