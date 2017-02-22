/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.configuration.WeldJUnit4Runner;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.service.TweetService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;


/**
 *
 * @author robvangastel
 */
@RunWith(WeldJUnit4Runner.class)
public class TweetServiceTest {
    
    @Inject
    private TweetService tweetService;
        
    @Test
    public void createTest() {
        //Case 1 - Create a Tweet retrieve the Tweet and 
        //AssertEquals
        
        Tweet tweet = new Tweet("Hello world!", new Date(1L));
        Tweet createdTweet = tweetService.create(tweet);
        
        Tweet tweetFound = tweetService.findById(createdTweet.getId());
        assertEquals(createdTweet, tweetFound);
        
        //Case 2 - Create a new Tweet with the same values
        //AssertNotEquals with the old Tweet
        Tweet tweet2 = new Tweet("Hello world!", new Date(1L));
        Tweet createdTweet2 = tweetService.create(tweet2);
        
        assertNotEquals(createdTweet2, tweetFound);
    }
    
    @Test
    public void deleteTest() {
        //Case 1 - Delete created Tweet assertNull on deleted Tweet
        Tweet tweet = new Tweet("Hello world!", new Date(1L));
        Tweet createdTweet = tweetService.create(tweet);
        
        tweetService.delete(createdTweet.getId());
        
        Tweet tweetFound = tweetService.findById(createdTweet.getId());
        assertNull(tweetFound);
        
        //Case 2 - Delete an id not existing AssertNotNull on created
        //Tweet
        Tweet tweet2 = new Tweet("Hello world!", new Date(1L));
        Tweet createdTweet2 = tweetService.create(tweet2);
        
        tweetService.delete(createdTweet2.getId() + 1L);
        
        Tweet tweetFound2 = tweetService.findById(createdTweet2.getId());
        assertNotNull(tweetFound2);
    }
    
    @Test
    public void updateTest() {
        //Case 1 - 
        Tweet tweet = new Tweet("Hello world!", new Date(1L));
        tweetService.create(tweet);
        
        tweet.setMessage("world Hello!");
        tweetService.update(tweet);
        
        Tweet tweetFound = tweetService.findById(1L);
        assertEquals(tweet.getMessage(), tweetFound.getMessage());
    }
   
    @Test
    public void findByIdTest() {
        Tweet tweet = new Tweet("Hello world!", new Date(1L));
        Tweet createdTweet = tweetService.create(tweet);
        
        Tweet tweetFound = tweetService.findById(createdTweet.getId());
        assertEquals(createdTweet, tweetFound); 
    }
    
    @Test
    public void findAllTest() {
        Tweet tweet1 = new Tweet("Hello world!", new Date(1L));
        Tweet tweet2 = new Tweet("Hello world!", new Date(2L));
        
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        
        tweetService.create(tweet1);
        tweetService.create(tweet2);
        List<Tweet> tweetsService = tweetService.findAll();
        
        assertEquals(tweets, tweetsService);
    }
}
