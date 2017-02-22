/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.domain.User;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author robvangastel
 */

@Stateless
public class UserService {
    
    @Inject
    private IUserDao dao;

    public UserService() {
        super();
    }

    public void create(User entity) {
        dao.create(entity);
    }
    
    public void delete(long id) {
        User entity = dao.findById(id);
        dao.delete(entity);
    }
    
    public void update(User entity) {
        dao.update(entity);
    }

    public User findById(long id) {
        return dao.findById(id);
    }

    public List<User> findAll() {
        return dao.findAll();
    }
}
