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

    /***
     *
     * @param tweet
     * @return Created tweet
     * @throws UserException When User doesnt exist
     * @throws TweetException WHen the message isnt allowed
     */
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

    /***
     *
     * @param id of Tweet
     * @param user That preformes the action
     * @throws TweetException When tweet doesnt exist
     * @throws UserException When the user isnt found
     */
	@RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
    public void delete(long id, User user) throws TweetException, UserException {
        Tweet entity = tweetDao.findById(id);
		if(entity != null) {
			if (entity.getUser().getId().equals(user.getId())) {
				tweetDao.delete(entity);
				return;
			}
		}
		throw new UserException("User not found Exception.");
    }

    /***
     *
     * @param id of tweet
     * @return tweet with the id or null when not found
     */
	@PermitAll
    public Tweet findById(long id) {
        return tweetDao.findById(id);
    }

    /***
     *
     * @param arg Message of tweet search argument
     * @return tweets with matching args or null when not found
     */
	@PermitAll
    public List<Tweet> findByMessage(String arg) {
        return tweetDao.findByMessage(arg);
    }

    /***
     *
     * @param id of the User that made the tweet
     * @return matching tweets with the same user or null when not found
     */
	@PermitAll
    public List<Tweet> findByUser(long id) {
        return tweetDao.findByUser(id);
    }

    /***
     *
     * @return All tweets
     */
	@PermitAll
    public List<Tweet> findAll() {
        return tweetDao.findAllOrderedByDate();
    }
    
}
