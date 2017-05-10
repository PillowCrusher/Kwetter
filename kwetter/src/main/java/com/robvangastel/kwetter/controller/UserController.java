/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.controller;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * @author robvangastel
 */

@Stateless
@Path("/user")
@Produces({MediaType.APPLICATION_JSON})
public class UserController {

	@Resource
	private SessionContext context;

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
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}

	@GET
	@Path("/username")
	public User getByUsername(@QueryParam("username") String username) {
		User user = userService.findByUsername(username);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}

	@GET
	@Path("/email")
	public User getByEmail(@QueryParam("email") String email) {
		User user = userService.findByEmail(email);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return user;
	}

	@POST
	public User post(@QueryParam("email") String email,
					 @QueryParam("username") String username,
					 @QueryParam("password") String password) throws Exception {
		User user = userService.create(new User(Role.USER, email, username, password));
		if(user == null) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
		return user;
	}

	@POST
	@Path("/authenticate")
	public void post(@QueryParam("username") String username,
					 @QueryParam("password") String password) throws Exception {
		userService.authenticate(username, password);
	}

	@PUT
	public void update(@QueryParam("location") String location,
					   @QueryParam("websiteURL") String websiteURL,
					   @QueryParam("bio") String bio) throws Exception {
		User user = userService.findByUsername(context.getCallerPrincipal().getName());
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		user.setBio(bio);
		user.setWebsiteUrl(websiteURL);
		user.setLocation(location);

		userService.update(user);
	}

	@PUT
	@Path("/{id}")
	public void updateRole(@QueryParam("role") Role role, @PathParam("id") long id) throws Exception {
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		userService.updateRole(role, user);
	}

	@PUT
	@Path("/{id}/username")
	public void updateUsername(@QueryParam("username") String username, @PathParam("id") long id) throws Exception {
		User user = userService.findById(id);
		if(user == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		userService.updateUsername(username, user);
	}

	@PUT
	@Path("/following/{followingId}")
	public void updateFollowing(@PathParam("followingId") long followingId) throws Exception {
		User user = userService.findByUsername(context.getCallerPrincipal().getName());
		userService.addFollowing(followingId, user.getId());
	}

	@PUT
	@Path("/follower/{followerId}")
	public void updateFollower(@PathParam("followerId") long followerId) throws Exception {
		User user = userService.findByUsername(context.getCallerPrincipal().getName());
		userService.removeFollowing(followerId, user.getId());
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) throws Exception {
		userService.delete(id);
	}
}
