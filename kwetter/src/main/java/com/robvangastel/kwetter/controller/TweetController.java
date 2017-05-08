/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.controller;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.interceptor.TweetInterceptor;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import com.robvangastel.kwetter.websockets.SocketController;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

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
    public Response get() {
        List<Tweet> tweets = tweetService.findAll();

        return Response.ok(tweets)
                .header("total-count", tweets.size())
                .build();
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
    public Response getByMention(@QueryParam("mention") String mention) {
        List<Tweet> tweets = tweetService.findByMention(mention);

        return Response.ok(tweets)
                .header("total-count", tweets.size())
                .build();
    }

    @GET
    @Path("/trends")
    public List<String> getTrends() {
        return tweetService.findTrends();
    }

	@GET
    @Path("/message")
	public List<Tweet> getByMessage(@QueryParam("arg") String arg) {
		return tweetService.findByMessage(arg);
	}

    @POST
    @Interceptors(TweetInterceptor.class)
    public Tweet post(@QueryParam("message") String message) throws Exception {
        User user = userService.findByUsername(context.getCallerPrincipal().getName());
        if(user == null) {
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }
        Tweet tweet = new Tweet(message, user);
        List<User> users = new ArrayList<User>();
        users.addAll(user.getFollowing());
        users.add(user);

        SocketController.send(tweet, users);
	    return tweetService.create(tweet);
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
