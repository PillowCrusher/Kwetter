package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.bean.model.Credentials;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Produces;
import java.io.Serializable;

/**
 * Created by Rob on 23-3-2017.
 */

@Named(value = "indexBean")
@SessionScoped
public class IndexBean implements Serializable {

    @Inject
    private UserService userService;

    @Inject
    Credentials credentials;

    private User user;
    private String query;

    public String Register() {
        try {
            User u = userService.create(new User(Role.USER, credentials.getEmail(),
                    credentials.getUsername(), credentials.getPassword()));
            if(u != null)
            {
                user = u;
                return "/index.xhtml";
            }
            return "Oops, something went wrong!";

        } catch (Exception ex) {
            return "Oops, something went wrong!";
        }
    }

    public String Login() {
        try {
            userService.authenticate(credentials.getUsername(), credentials.getPassword());
            User u = userService.findByUsername(credentials.getUsername());

            return "/index.xhtml";

        } catch (Exception ex) {
            return "Oops, something went wrong!";
        }
    }

    public boolean isLoggedIn() {
        return user!=null;
    }

    public void logout() {
        user = null;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Produces
    @LoggedIn
    public User getCurrentUser() {
        return user;
    }
}
