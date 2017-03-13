/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.controller;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 *
 * @author robvangastel
 */

@Stateless
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {

	@Inject
	private UserService userService;

	@Inject
	private TweetService tweetService;

	@GET
	public List<User> get() {
		return userService.findAll();
	}

	@GET
	@Path("/{id}/tweet")
	public List<Tweet> getTweetsByUser(@PathParam("id") Long id) {
		return tweetService.findByUser(id);
	}

	@GET
	@Path("/{id}")
	public User getById(@PathParam("id") long id) {
		return userService.findById(id);
	}

	@GET
	public User getByUsername(@QueryParam("username") String username) {
		return userService.findByUsername(username);
	}

	@GET
	public User getByEmail(@QueryParam("email") String email) {
		return userService.findByEmail(email);
	}

	@POST
	public User post(@FormParam("") User user) throws Exception {
		return userService.create(user);
	}

	@PUT
	public void update(@FormParam("") User user) throws Exception {
		userService.update(user);
	}

	@PUT
	@Path("/{id}/username")
	public void updateUsername(@PathParam("id") long id, @QueryParam("username") String username) throws UserException {
		userService.updateUsername(username, id);
	}

	@PUT
	@Path("{id}/following/{followingId}")
	public void updateFollowing(@PathParam("id") long id, @PathParam("followingId") long followingId) throws Exception {
		userService.addFollowing(followingId, id);
	}

	@PUT
	@Path("{id}/follower/{followerId}")
	public void updateFollower(@PathParam("id") long id, @PathParam("followerId") long followerId) throws Exception {
		userService.removeFollowing(followerId, id);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) throws Exception {
		userService.delete(id);
	}
}
