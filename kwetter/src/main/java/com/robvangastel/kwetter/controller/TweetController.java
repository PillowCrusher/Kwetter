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
    @Path("/{id}")
    public Tweet getById(@PathParam("id") long id) {
        Tweet tweet = tweetService.findById(id);
        if(tweet == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return tweet;
    }

    @GET
    @Path("/mention")
    public List<Tweet> getByMention(@QueryParam("mention") String mention) {
        List<Tweet> tweets = tweetService.findByMention(mention);
        return tweets;
    }

	@GET
    @Path("/message")
	public List<Tweet> getByMessage(@QueryParam("arg") String arg) {
		return tweetService.findByMessage(arg);
	}

    @POST
    public Tweet post(@QueryParam("message") String message) throws Exception {
        User user = userService.findByUsername(context.getCallerPrincipal().getName());
        if(user == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
	    return tweetService.create(new Tweet(message, user));
    }
    
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") long id) throws Exception {
        User user = userService.findByUsername(context.getCallerPrincipal().getName());
        if(user == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        tweetService.delete(id, user);
    }
}
