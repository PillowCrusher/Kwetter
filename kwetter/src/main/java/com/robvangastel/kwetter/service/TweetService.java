/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.dao.JPA;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;

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
public class TweetService {
    
    @Inject @JPA
    private ITweetDao tweetDao;

    @Inject @JPA
    private IUserDao userDao;

    public TweetService() {
        super();
    }

	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public Tweet create(Tweet tweet) {
        User user = userDao.findById(tweet.getUser().getId());
        tweet = tweetDao.create(tweet);

        user.addTweet(tweet);
        userDao.update(user);

        return tweet;
    }

	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void delete(long id, long userId) {
        Tweet entity = tweetDao.findById(id);
	    if(entity.getUser().getId().equals(userId)) {
		    tweetDao.delete(entity);
	    }
    }

	@PermitAll
    public Tweet findById(long id) {
        return tweetDao.findById(id);
    }

	@PermitAll
    public List<Tweet> findByMessage(String arg) {
        return tweetDao.findByMessage(arg);
    }

	@PermitAll
    public List<Tweet> findByUser(long id) {
        return tweetDao.findByUser(id);
    }

	@PermitAll
    public List<Tweet> findAll() {
        return tweetDao.findAll();
    }
    
}
