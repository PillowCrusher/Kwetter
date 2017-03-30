/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.dao.JPA;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.UserException;

import java.io.Serializable;
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
public class UserService implements Serializable {
    
    @Inject @JPA
    private IUserDao dao;

	@PermitAll
    public User create(User entity) throws UserException {
        if(dao.findByUsername(entity.getUsername()) == null
                && dao.findByEmail(entity.getEmail()) == null) {
                return dao.create(entity);
        }
        return null;
    }

	/***
	 *
	 * @param id of the User
	 * @throws UserException When the User doesnt exist
     */
	@RolesAllowed({"ADMINISTRATOR"})
    public void delete(long id) throws UserException {
        User entity = dao.findById(id);
        dao.delete(entity);
    }

	/***
	 *
	 * @param entity User to update
	 * @throws Exception When User doesnt exist
     */
	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void update(User entity) throws Exception {
        User user = dao.findById(entity.getId());
		if(user != null) {
			dao.update(entity);
		}
    }

	/***
	 *
	 * @param username New username
	 * @param user User making the request
	 * @throws UserException When User doesnt exist or username is in use
     */
	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void updateUsername(String username, User user) throws UserException {
	    if (dao.findByUsername(username) == null && !username.isEmpty()) {
		    user.setUsername(username);
		    dao.update(user);
	    }
    }

	/***
	 *
	 * @param role New Role
	 * @param user User to change the role
	 * @throws UserException When not authorized or user doesnt exist
     */
	@RolesAllowed({"ADMINISTRATOR"})
	public void updateRole(Role role, User user) throws UserException {
		user.setRole(role);
		dao.updateRole(user);
	}

	/***
	 *
	 * @param id of the User
	 * @return User found or null when not found
     */
	@PermitAll
    public User findById(long id) {
        return dao.findById(id);
    }

	/***
	 *
	 * @param username of the User
	 * @return User found or null when not found
     */
	@PermitAll
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

	/***
	 *
	 * @param email of the User
	 * @return User found or null when not found
     */
	@PermitAll
	public User findByEmail(String email) {
		return dao.findByEmail(email);
	}

	/***
	 *
	 * @param followingId of the User following
	 * @param id of the User being followed
	 * @throws UserException When User doesnt exist
     */
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

	/***
	 *
	 * @param followerId of the User being followed
	 * @param id of the User making the request
	 * @throws UserException When the User doesnt exist
     */
	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void removeFollowing(long followerId, long id) throws UserException {
	    if(followerId != id) {
		    User user = dao.findById(id);
		    User followingUser = dao.findById(followerId);
		    if(user != null && followingUser != null) {
			    user.removeFollowing(followingUser);
			    followingUser.removeFollower(user);

			    dao.update(user);
			    dao.update(followingUser);
		    }
	    }
    }

	@PermitAll
	public void authenticate(String username, String password) throws UserException {
		User u = dao.findByUsername(username);
		if(u.equals(null)) {
			throw new UserException("User not found exception");
		}
		if(!u.getPassword().equals(password)) {
			throw new UserException("Password does not match exception");
		}
	}

	/***
	 *
	 * @return all Users
     */
	@PermitAll
    public List<User> findAll() {
        return dao.findAll();
    }
}
