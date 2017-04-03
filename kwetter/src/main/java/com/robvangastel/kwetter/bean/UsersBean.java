package com.robvangastel.kwetter.bean;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.service.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Rob on 2-4-2017.
 */

@Named(value = "usersBean")
@SessionScoped
public class UsersBean implements Serializable {

    @Inject
    private UserService userService;

    public List<User> getUsers() {
        return userService.findAll();
    }

    public void deleteUser(long id) throws UserException {
        userService.delete(id);
    }

    public void setRole(String role, long id) throws UserException {
        Role r = Role.valueOf(role);
        User user = userService.findById(id);
        userService.updateRole(r, user);
    }
}