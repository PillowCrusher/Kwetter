package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Rob on 2-4-2017.
 */
@Named(value = "detailBean")
@RequestScoped
public class DetailBean implements Serializable {

    @Inject
    private UserService userService;

    private User user;
    private int id;

    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        id = getIdParam(fc);
        user = userService.findById(id);
    }

    public int getIdParam(FacesContext fc) {
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return Integer.parseInt((params.get("id")));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
