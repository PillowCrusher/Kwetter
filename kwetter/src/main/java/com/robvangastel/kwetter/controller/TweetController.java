/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.controller;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.service.TweetService;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author robvangastel
 */

@Stateless
@Path("/tweet")
@Produces({MediaType.APPLICATION_JSON})
public class TweetController {
    
    @Inject
    private TweetService tweetService;
    
    @GET
    public List<Tweet> get() {
        return tweetService.findAll();
    }
    
    @POST
    public Tweet post() {
        return null;
    }
    
    @DELETE
    public boolean delete() {
        return false;
    }
    
    @PUT
    public boolean update() {
        return false;
    }
}
