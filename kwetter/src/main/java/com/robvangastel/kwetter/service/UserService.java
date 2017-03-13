/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.dao.JPA;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.UserException;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author robvangastel
 */

@Stateless
public class UserService {
    
    @Inject @JPA
    private IUserDao dao;

    public UserService() {
        super();
    }

	@PermitAll
    public User create(User entity) throws UserException {
        if(dao.findByUsername(entity.getUsername()) == null
                && dao.findByEmail(entity.getEmail()) == null) {
                return dao.create(entity);
        }
        return null;
    }

	@RolesAllowed({"ADMINISTRATOR"})
    public void delete(long id) throws UserException {
        User entity = dao.findById(id);
        dao.delete(entity);
    }

	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void update(User entity) throws Exception {
        User user = dao.findById(entity.getId());
        dao.update(user);
    }

	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void updateUsername(String username, long id) throws UserException {
        User user = dao.findById(id);

	    if (dao.findByUsername(username) == null && username.isEmpty()) {
		    user.setUsername(username);
	    }
		dao.update(user);
    }

	@PermitAll
    public User findById(long id) {
        return dao.findById(id);
    }

	@PermitAll
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

	@PermitAll
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void addFollowing(long followingId, long id) throws UserException {
	    if(followingId != id) {
		    User user = dao.findById(id);
		    User followingUser = dao.findById(followingId);
		    if(user != null && followingUser != null) {

			    user.addFollowing(followingUser);
			    followingUser.addFollower(user);

			    dao.update(user);
			    dao.update(followingUser);
		    }
	    }
    }

	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void removeFollowing(long followingId, long id) throws UserException {
	    if(followingId != id) {
		    User user = dao.findById(id);
		    User followingUser = dao.findById(followingId);
		    if(user != null && followingUser != null) {
			    user.removeFollowing(followingUser);
			    followingUser.removeFollower(user);

			    dao.update(user);
			    dao.update(followingUser);
		    }
	    }
    }

	@PermitAll
    public List<User> findAll() {
        return dao.findAll();
    }
}
