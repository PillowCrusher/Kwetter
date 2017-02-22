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
import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author robvangastel
 */
@RunWith(WeldJUnit4Runner.class)
public class TweetServiceTest {
    
    @Inject
    private TweetService tweetService;
        
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
