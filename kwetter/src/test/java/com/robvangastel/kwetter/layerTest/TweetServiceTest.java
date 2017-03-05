/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.layerTest;

import com.robvangastel.kwetter.dao.facade.TweetDaoColImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoColImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author robvangastel
 */

@RunWith(MockitoJUnitRunner.class)
public class TweetServiceTest {
    
    @InjectMocks
    private TweetService tweetService;
    
    @InjectMocks
    private UserService userService;
    
    @Spy
    private UserDaoColImpl userDao;
    
    @Spy
    private TweetDaoColImpl tweetDao;
    
    private User user;
    private boolean isInitialized = false;
    
    @Before
    public void initialize() {
        if(!isInitialized) {
            userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
            isInitialized = true;
        }
    }
    
    @Test
    public void createTest() {
        //Case 1 - Create a Tweet retrieve the Tweet and 
        //AssertEquals
        
        Tweet tweet = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet createdTweet = tweetService.create(tweet, user.getId());
        
        Tweet tweetFound = tweetService.findById(createdTweet.getId());
        assertEquals(createdTweet, tweetFound);
        
        //Case 2 - Create a new Tweet with the same values
        //AssertNotEquals with the old Tweet
        Tweet tweet2 = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet createdTweet2 = tweetService.create(tweet2, user.getId());
        
        assertNotEquals(createdTweet2, tweetFound);
        
        //Case 3 - Create a tweet with 161 characters
        String message = "Hello World! Hello World! Hello World! "
                + "Hello World! Hello World! Hello World! Hello World! "
                + "Hello World! Hello World! Hello World! Hello World! "
                + "Hello World! Hello"; //161 characters
        
        Tweet tweet3 = new Tweet(message, new Date(1L), userService.findById(1));
        Tweet createdTweet3 = tweetService.create(tweet3, user.getId());
        
        assertNotEquals(createdTweet3, tweetFound);
        
        //Case 4 - Create a tweet with 0 characters
        String emptyMessage = ""; //0 characters
        
        Tweet tweet4 = new Tweet(emptyMessage, new Date(1L), userService.findById(1));
        Tweet createdTweet4 = tweetService.create(tweet4, user.getId());
        
        assertNotEquals(createdTweet4, tweetFound);
    }
    
    @Test
    public void deleteTest() {
        //Case 1 - Delete created Tweet assertNull on deleted tweet
        Tweet tweet = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet createdTweet = tweetService.create(tweet, user.getId());
        
        tweetService.delete(createdTweet.getId());
        
        Tweet tweetFound = tweetService.findById(createdTweet.getId());
        assertNull(tweetFound);
        
        //Case 2 - Delete an id not existing AssertNotNull on created
        //tweet
        Tweet tweet2 = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet createdTweet2 = tweetService.create(tweet2, user.getId());
        
        tweetService.delete(createdTweet2.getId() + 1L);
        
        Tweet tweetFound2 = tweetService.findById(createdTweet2.getId());
        assertNotNull(tweetFound2);
    }
    
    @Test
    public void updateTest() {
        //Case 1 - Update an existing tweet
        Tweet tweet = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        tweetService.create(tweet, user.getId());
        
        tweet.setMessage("world Hello!");
        tweetService.update(tweet);
        
        Tweet tweetFound = tweetService.findById(1L);
        assertEquals(tweet.getMessage(), tweetFound.getMessage());
        
        //Case 2 - Update an not existing tweet
    }
   
    @Test
    public void findByIdTest() {
        //Case 1 - Find an existing tweet by id
        Tweet tweet = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet createdTweet = tweetService.create(tweet, user.getId());
        
        Tweet tweetFound = tweetService.findById(createdTweet.getId());
        assertEquals(createdTweet, tweetFound); 
        
        //Case 2 - Find a not existing tweet by id
        Tweet tweet2Found = tweetService.findById(createdTweet.getId()+1);
        assertNull(tweet2Found); 
    }
    
    @Test
    public void findByMessageTest() {
        //Case 1 - Find an existing tweet by message
        Tweet tweet = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet createdTweet = tweetService.create(tweet, user.getId());
        
        List<Tweet> tweetFound = tweetService.findByMessage("Hello world!");
                
        assertEquals(createdTweet, tweetFound.get(0));
        
        //Casee 2 - Find a not existing tweet by message
        Tweet tweet2 = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        tweetService.create(tweet2, user.getId());
        
        List<Tweet> tweet2Found = tweetService.findByMessage("!dlorw elloh");
                
        assertNull(tweet2Found);
    }
    
    @Test
    public void findByUserTest() {
        //Case 1 - Find all existing tweets by user
        Tweet tweet1 = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet tweet2 = new Tweet("Hello world!", new Date(2L), userService.findById(1));
        
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        
        tweetService.create(tweet1, user.getId());
        tweetService.create(tweet2, user.getId());
        List<Tweet> tweetsService = tweetService.findByUser(1L);
        
        assertEquals(tweets, tweetsService);
    }
    
    @Test
    public void findAllTest() {
        //Case 1 - Find all existing tweets
        Tweet tweet1 = new Tweet("Hello world!", new Date(1L), userService.findById(1));
        Tweet tweet2 = new Tweet("Hello world!", new Date(2L), userService.findById(1));
        
        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);
        
        tweetService.create(tweet1, user.getId());
        tweetService.create(tweet2, user.getId());
        List<Tweet> tweetsService = tweetService.findAll();
        
        assertEquals(tweets, tweetsService);
    }
}
