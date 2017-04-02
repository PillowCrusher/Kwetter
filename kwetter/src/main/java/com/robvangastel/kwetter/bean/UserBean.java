package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.service.UserService;
import com.robvangastel.kwetter.domain.User;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * Created by Rob on 20-Mar-17.
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    public UserBean() {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        setUsername(request.getRemoteUser());
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/Kwetter/faces/index.xhtml";
    }

    @Inject
    UserService service;

    private String registrationError = null;
    private boolean registerSuccess = false;

    private String username;
    private String email;
    private String password;

    public String Register() {
        try {
            User u = service.create(new User(Role.USER, this.getEmail(), this.getUsername(), this.getPassword()));
            return "success";
        } catch (Exception e) {
            return "unsuccess";
        }
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
    public String getRegistrationError() {
        return registrationError;
    }

    public void setRegistrationError(String registrationError) {
        this.registrationError = registrationError;
    }

    public boolean isRegisterSuccess() {
        return registerSuccess;
    }

    public void setRegisterSuccess(boolean registerSuccess) {
        this.registerSuccess = registerSuccess;
    }


}
