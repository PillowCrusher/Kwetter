/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.controller;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import java.util.List;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

/**
 *
 * @author robvangastel
 */

@Stateless
@Path("/tweet")
@Produces({MediaType.APPLICATION_JSON})
public class TweetController {

	@Resource
	private SessionContext context;

    @Inject
    private TweetService tweetService;

	@Inject
	private UserService userService;

    @GET
    public List<Tweet> get() {
        return tweetService.findAll();
    }

	@GET
	public List<Tweet> getByMessage(@QueryParam("arg") String arg) {
		return tweetService.findByMessage(arg);
	}

    @GET
    @Path("/{id}")
    public Tweet getById(@PathParam("id") long id) {
        return tweetService.findById(id);
    }

    @POST
    public Tweet post(@FormParam("") Tweet tweet) {
	    return tweetService.create(tweet);
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id, @QueryParam("userid") long userid) {

	    System.out.println(context.getCallerPrincipal().getName());
	    User user = userService.findById(userid);
	    if(user != null) {
		    tweetService.delete(id, user.getId());
	    }
	    return Response.status(404).build();
    }
}
