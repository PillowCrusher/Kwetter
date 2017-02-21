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

/**
 *
 * @author Rob
 */
public class TweetDaoColImpl implements ITweetDao {

    private final List<Tweet> tweets;
    
    public TweetDaoColImpl() {
        tweets = new ArrayList<>();
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
    public List<Tweet> findAll() {
        return tweets;
    }

    @Override
    public void create(Tweet entity) {
        tweets.add(entity);
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
