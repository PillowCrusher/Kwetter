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
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author robvangastel
 */

@Stateless
public class UserService {
    
    @Inject @Default
    private IUserDao dao;

    public UserService() {
        super();
    }

    public User create(User entity) {
        if(dao.findByUsername(entity.getUsername()) == null
                && dao.findByEmail(entity.getEmail()) == null) {
            
            if(entity.getPassword() != null) {
                return dao.create(entity);
            }
        }
        return null;
    }
    
    public void delete(long id) {
        User entity = dao.findById(id);
        dao.delete(entity);
    }
    
    public void update(User entity) {
        User user = dao.findById(entity.getId());
        
        user.setBio(entity.getBio());
        user.setLocation(entity.getLocation());
        user.setWebsiteUrl(entity.getWebsiteUrl());

        dao.update(user);
    }
    
    public void updateUsername(String username, long id) {
        User user = dao.findById(id);
        
        if(dao.findByUsername(username) != null) {
            user.setUsername(username);
        }
    }

    public User findById(long id) {
        return dao.findById(id);
    }
    
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }
    
    public void addFollowing(long followingId, long id) {
        User user = dao.findById(id);
        User followingUser = dao.findById(followingId);
        
        user.addFollowing(followingUser);
        followingUser.addFollower(user);

        dao.update(user);
        dao.update(followingUser);
    }
    
    public void removeFollowing(long followingId, long id) {
        User user = dao.findById(id);
        User followingUser = dao.findById(followingId);
        
        user.removeFollowing(followingUser);
        followingUser.removeFollower(user);

        dao.update(user);
        dao.update(followingUser);
    }

    public List<User> findAll() {
        return dao.findAll();
    }
}
