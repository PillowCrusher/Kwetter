/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.service.TweetService;
import java.sql.Date;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author robvangastel
 */
public class TweetServiceTest {
    
    private TweetService tweetService;
    
    @BeforeClass
    public static void startUp() {
        
    }
        
    /***
     * Create Tweet test
     */
    @Test
    public void createTest() {
        Tweet tweet = new Tweet(1L, "Hello world!", new Date(1L));
        tweetService.create(tweet);
        
        Tweet tweetFound = tweetService.findById(1L);
        assertEquals(tweet, tweetFound);
    }
}
