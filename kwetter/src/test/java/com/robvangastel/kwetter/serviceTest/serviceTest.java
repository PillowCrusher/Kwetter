/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.dao.facade.TweetDaoColImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoColImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 *
 * @author robvangastel
 */

public class serviceTest {
    
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
    public void setUp() {
	    initMocks(this);

        if(!isInitialized) {
	        user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
	        isInitialized = true;
        }
    }

	@Test
	public void ServiceTest() {
		assertNotNull(tweetService);
		assertNotNull(userService);
	}

    // Tweet Dao Test
    // public Tweet findById(long id);
    @Test
    public void findTweetByIdTest() {
        //Case 1 - Find an existing tweet by id
        Tweet tweet = new Tweet("Hello world!", new Date(1L), user);
        Tweet createdTweet = tweetService.create(tweet);

        Tweet tweetFound = tweetService.findById(createdTweet.getId());
        assertEquals(createdTweet, tweetFound);

        //Case 2 - Find a not existing tweet by id
        Tweet tweet2Found = tweetService.findById(createdTweet.getId()+1);
        assertNull(tweet2Found);
    }
    
    // public List<Tweet> findByMessage(String arg);
	// NOT IMPLEMENTED
    @Test
    public void findByMessageTest() {
		//Case 1 - Find an existing tweet by message
		Tweet tweet = new Tweet("Hello world!", new Date(1L), user);
		Tweet createdTweet = tweetService.create(tweet);

		List<Tweet> tweetFound = tweetService.findByMessage("Hello world!");

		assertEquals(createdTweet, tweetFound.get(0));

		//Casee 2 - Find a not existing tweet by message
		Tweet tweet2 = new Tweet("Hello world!", new Date(1L), user);
		tweetService.create(tweet2);

		List<Tweet> tweet2Found = tweetService.findByMessage("!dlorw elloh");

		assertNull(tweet2Found);
    }
    
    // public List<Tweet> findByUser(long id);
	// NOT IMPLEMENTED
    @Test
    public void findTweetByUserTest() {
        //Case 1 - Find all existing tweets by user
        Tweet tweet1 = new Tweet("Hello world!", new Date(1L), user);
        Tweet tweet2 = new Tweet("Hello world!", new Date(2L), user);

        List<Tweet> tweets = new ArrayList<>();
        tweets.add(tweet1);
        tweets.add(tweet2);

        tweetService.create(tweet1);
        tweetService.create(tweet2);
        List<Tweet> tweetsService = tweetService.findByUser(user.getId());

        assertEquals(tweets, tweetsService);
    }
    
    // User Dao Test
    // public User findByUsername(String username);
    @Test
    public void findTweetByUsernameTest() {
        
    }
    
    // public User findByEmail(String email);
    @Test
    public void findTweetByEmailTest() {
        
    }

	@Test
	public void updateUsernameTest() {
		//Case 1 - Update new username
		User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
		String newUsername = "username2";

		userService.updateUsername(newUsername, user.getId());
		User userFound = userService.findById(user.getId());

		assertEquals(userFound.getUsername(), newUsername);

		//Case 2 - Update to existing username
		User user2 = userService.create(new User(Role.USER, "user@mail.com", "user2", "password"));

		userService.updateUsername(newUsername, user2.getId());
		User user2Found = userService.findById(user2.getId());

		assertNotEquals(user2Found.getUsername(), newUsername);

		//Case 3 - Update to empty username
		String emptyUsername = "";
		userService.updateUsername("", user2.getId());
		user2Found = userService.findById(user2.getId());

		assertNotEquals(user2Found.getUsername(), emptyUsername);
	}

    // public Tweet create(Tweet tweet, long id);
    @Test
    public void createTweetTest() {
	    //Case 1 - Create user and check if the existing user is equal
	    User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
	    User userFound = userService.findById(user.getId());

	    assertEquals(user, userFound);

	    //Case 2 - Create user with the same email
	    User user2 = userService.create(new User(Role.USER, "user@mail.com", "user2", "password"));
	    Assert.assertNull(user2);

	    //Case 3 - Create user with the same username
	    User user3 = userService.create(new User(Role.USER, "user3@mail.com", "user", "password"));
	    Assert.assertNull(user3);

	    //Case 4 - Create user with empty password
	    User user4 = userService.create(new User(Role.USER, "user4@mail.com", "user4", ""));
	    Assert.assertNull(user4);
        
    }
    
    // public void addFollowing(long followingId, long id);
    @Test
    public void addFollowingTest() {
	    //Case 1 - Add another user to your following list

	    //Case 2 - Add yourself to your following list

	    //Case 3 - Add an not existing id to your following list
    }


	// public void removeFollowing(long followingId, long id);
	@Test
	public void removeFollowingTest() {
		//Case 1 - Remove another user from your following list

		//Case 2 - Remove yourself from your following list

		//Case 3 - Remove an not existing id from your following list
	}

	@Test
	public void findByUsernameTest() {
		//Case 1 - Find by existing username
		User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
		User userFound = userService.findByUsername("user");

		assertEquals(user, userFound);

		//Case 2 - Find by not existing username
		userFound = userService.findById(user.getId() + 1L);

		Assert.assertNull(userFound);
	}


	@Test
	public void deleteTest() {
		//Case 1 - Delete existing user
		User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
		userService.delete(user.getId());

		User foundUser = userService.findById(user.getId());
		Assert.assertNull(foundUser);

		//Case 2 - Delete not existing id
		User user2 = userService.create(new User(Role.USER, "user2@mail.com", "user2", "password"));
		userService.delete(user2.getId() + 1L);

		User foundUser2 = userService.findById(user2.getId());
		Assert.assertNotNull(foundUser2);
	}

	@Test
	public void findAllTest() {
		//Case 1 - Find all users
		User user = new User(Role.USER, "user@mail.com", "user", "password");
		User user2 = new User(Role.USER, "user@mail.com2", "user2", "password");

		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(user2);

		userService.create(user);
		userService.create(user2);
		List<User> usersService = userService.findAll();

		assertEquals(users, usersService);
	}
}
