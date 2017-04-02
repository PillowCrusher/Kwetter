package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.exception.TweetException;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import com.robvangastel.kwetter.domain.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Rob on 20-Mar-17.
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    UserService userService;

    @Inject
    TweetService tweetService;

    private String username;
    private String email;
    private String password;
    private String message;

    private User user;

    public UserBean() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        setUsername(request.getRemoteUser());
    }

    public String Register() {
        try {
            User u = userService.create(new User(Role.USER, this.getEmail(), this.getUsername(), this.getPassword()));
            return "success";
        } catch (Exception e) {
            return "unsuccess";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/faces/index.xhtml";
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        user = userService.findByUsername(username);
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tweet> getTweetsUser() {
        user = userService.findByUsername(username);
        List<Tweet> tweets = tweetService.findByUser(user.getId());
        if(tweets != null) {
            return tweets;
        }
        return null;
    }

    public String getLastTweet() {
        user = userService.findByUsername(username);
        Tweet t = tweetService.findByUser(user.getId()).get(0);
        if(t != null) {
            return t.getMessage();
        }
        return "Create your first tweet";
    }

    public void sendTweet() throws Exception {
        user = userService.findByUsername(username);
        tweetService.create(new Tweet(message, user));
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
