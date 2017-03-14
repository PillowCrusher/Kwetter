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
import com.robvangastel.kwetter.exception.TweetException;
import com.robvangastel.kwetter.exception.UserException;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
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
    public Tweet create(Tweet tweet) throws UserException, TweetException {
        User user = userDao.findById(tweet.getUser().getId());
		if(user != null) {
			tweet = tweetDao.create(tweet);
			user.addTweet(tweet);
			userDao.update(user);
			return tweet;
		}
		return null;
    }

	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void delete(long id, long userId) throws TweetException, UserException {
        Tweet entity = tweetDao.findById(id);
		if(entity != null) {
			if (entity.getUser().getId().equals(userId)) {
				tweetDao.delete(entity);
				return;
			}
		}
		throw new UserException("User not found Exception.");
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
        return tweetDao.findAllOrderedByDate();
    }
    
}
