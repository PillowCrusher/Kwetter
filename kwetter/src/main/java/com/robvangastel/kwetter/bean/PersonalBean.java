package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Rob on 23-3-2017.
 */

@Named(value = "personalBean")
@SessionScoped
public class PersonalBean implements Serializable {

    @Inject
    private TweetService tweetService;

    @Inject
    private UserService userService;

    private String username;
    private String searchQuery;

    @LoggedIn
    private User currentUser;

    private String query;


    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
