/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.domainTest;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Domain test on User and Tweet
 * @author robvangastel
 */
public class DomainTest {

    // private User user;
    @Test
    public void userTest() {
        // Case 1 - Check if user is the same

        // Constr Role role, String email, String username, String password
        User user = new User(Role.USER, "user@mail.nl", "username", "password");
        // Constr String message, Date timeStamp, User user
        Tweet tweet = new Tweet("Tweet message", user);

        assertEquals(user, tweet.getUser());
    }


    @Test
    public void ConstructorTest() {
        //Case 1 - Check if Hashtags from message created
	    User user = new User(Role.USER, "testUser@mail.com", "testUser", "password");
	    Tweet tweet = new Tweet("Message #cool #swag", user);

	    assertEquals(2, tweet.getHashtags().size());
	    assertEquals(0, tweet.getMentions().size());

	    //Case 2 - Check if mentions from message created
	    Tweet tweet2 = new Tweet("Message @cool @swag", user);

	    assertEquals(2, tweet2.getMentions().size());
	    assertEquals(0, tweet2.getHashtags().size());

	    //Case 3 - Check if Hashtags and Mentions are empty when created
	    Tweet tweetEmpty = new Tweet("Message", user);

	    assertEquals(0, tweetEmpty.getHashtags().size());
	    assertEquals(0, tweetEmpty.getMentions().size());
    }
}
