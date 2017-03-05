/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao.facade;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.domain.Tweet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;

/**
 *
 * @author Rob
 */

@Default
@Stateless
public class TweetDaoColImpl implements ITweetDao {

    private final CopyOnWriteArrayList<Tweet> tweets = new CopyOnWriteArrayList<>();
    private static int INCREMENT = 0;
    
    
    private long getIncrement() {
        INCREMENT++;
        return (long) INCREMENT;
    }
    
    @Override
    public Tweet findById(long id) {
        for(Tweet tweet : tweets) {
            if(tweet.getId().equals(id)) {
                return tweet;
            }
        }
        return null;
    }
    
    @Override
    public List<Tweet> findByMessage(String message) {
        List<Tweet> tweetsFound = new ArrayList<>();
        
        for(Tweet tweet : tweets) {
            if(tweet.getMessage().equals(message)) {
                tweets.add(tweet);
            }
        }
        return tweetsFound;
    }
    
    @Override
    public List<Tweet> findByUser(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tweet> findAll() {
        return tweets;
    }

    @Override
    public Tweet create(Tweet entity) {
        entity.setId(getIncrement());
        tweets.add(entity);
        return entity;
    }

    @Override
    public Tweet update(Tweet entity) {
       for(Tweet tweet : tweets) {
            if(tweet.getId().equals(entity.getId())) {
                tweet = entity;
            }
        }
        return null;
    }

    @Override
    public void delete(Tweet entity) {
        tweets.remove(entity);
    }

    @Override
    public void deleteById(long id) {
        for(Tweet tweet : tweets) {
            if(tweet.getId().equals(id)) {
                tweets.remove(tweet);
            }
        }
    }
}
