package com.robvangastel.kwetter.persistenceTest;

/**
 * Created by robvangastel on 09/03/2017.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import com.robvangastel.kwetter.dao.facade.TweetDaoJPAImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoJPAImpl;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.MockitoAnnotations.initMocks;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import org.mockito.InjectMocks;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Spy;
import org.junit.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


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
	private UserDaoJPAImpl userDao;

	@Spy
	private TweetDaoJPAImpl tweetDao;

	@PersistenceContext(unitName ="KwetterPU")
	private EntityManager em;

	@Before
	public void setUp() {
		initMocks(this);

		userDao = new UserDaoJPAImpl(em);
		userDao.setEntityManager(em);

		tweetDao = new TweetDaoJPAImpl(em);
		tweetDao.setEntityManager(em);
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
		User user12 = userService.create(new User(Role.USER, "user991@mail.com", "user991", "password"));

		Tweet tweet = new Tweet("Hello world!", new Date(1L), user12);
		Tweet createdTweet = tweetService.create(tweet);

		Tweet tweetFound = tweetService.findById(createdTweet.getId());
		assertEquals(createdTweet, tweetFound);

		//Case 2 - Find a not existing tweet by id
		Tweet tweet2Found = tweetService.findById(createdTweet.getId()+1);
		assertNull(tweet2Found);
	}

	// public List<Tweet> findByMessage(String arg);
	@Test
	public void findByMessageTest() {
		//Case 1 - Find an existing tweet by message
		User user1 = userService.create(new User(Role.USER, "user1wq1@mail.com", "user1wq1", "password"));

		Tweet tweet = new Tweet("Hello world!", new Date(1L), user1);
		Tweet createdTweet = tweetService.create(tweet);

		List<Tweet> tweetFound = tweetService.findByMessage("Hello world!");

		assertEquals(1, tweetFound.size());

		//Casee 2 - Find a not existing tweet by message
		Tweet tweet2 = new Tweet("Hello world!", new Date(1L), user1);
		tweetService.create(tweet2);

		List<Tweet> tweet2Found = tweetService.findByMessage("!dlorw elloh");
		List<Tweet> emptyList = new ArrayList<>();

		assertEquals(emptyList, tweet2Found);
	}

	// public List<Tweet> findByUser(long id);
	@Test
	public void findTweetByUserTest() {
		//Case 1 - Find all existing tweets by user
		User user1 = userService.create(new User(Role.USER, "user1wq@mail.com", "user1wq", "password"));
		Tweet tweet1 = new Tweet("Hello world!", new Date(1L), user1);
		Tweet tweet2 = new Tweet("Hello world!", new Date(2L), user1);

		List<Tweet> tweets = new ArrayList<>();
		tweets.add(tweet1);
		tweets.add(tweet2);

		tweetService.create(tweet1);
		tweetService.create(tweet2);
		List<Tweet> tweetsService = tweetService.findByUser(user1.getId());

		assertEquals(tweets.size(), tweetsService.size());
	}

	// User Dao Test
	@Test
	public void updateUsernameTest() {
		//Case 1 - Update new username
		User user1 = userService.create(new User(Role.USER, "user113@mail.com", "user113", "password"));
		String newUsername = "username2";

		userService.updateUsername(newUsername, user1.getId());
		User user1Found = userService.findById(user1.getId());

		assertEquals(user1Found.getId(), user1.getId());
		assertEquals(user1Found.getUsername(), newUsername);

		//Case 2 - Update to existing username
		User user2 = userService.create(new User(Role.USER, "user2@mail.com", "user2", "password"));

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
		User user1 = userService.create(new User(Role.USER, "user123@mail.com", "user123", "password"));
		User user1Found = userService.findById(user1.getId());

		assertEquals(user1, user1Found);

		//Case 2 - Create user with the same email
		User user2 = userService.create(new User(Role.USER, "user123@mail.com", "user2", "password"));
		assertNull(user2);

		//Case 3 - Create user with the same username
		User user3 = userService.create(new User(Role.USER, "user3@mail.com", "user123", "password"));
		assertNull(user3);

		//Case 4 - Create user with empty password
		User user4 = userService.create(new User(Role.USER, "user4@mail.com", "user4", ""));
		assertNull(user4);

	}

	// public void addFollowing(long followingId, long id);
	@Test
	public void addFollowingTest() {
		//Case 1 - Add another user to your following list
		User user1 = userService.create(new User(Role.USER, "user500@mail.com", "user500", "password"));
		User user2 = userService.create(new User(Role.USER, "user510@mail.com", "user510", "password"));

		userService.addFollowing(user1.getId(), user2.getId());
		User userfound = userService.findById(user1.getId());

		assertNotNull(userfound.getFollowing());

		//Case 2 - Add yourself to your following list
		User user3 = userService.create(new User(Role.USER, "user530@mail.com", "user530", "password"));

		userService.addFollowing(user3.getId(), user3.getId());
		User user3found = userService.findById(user3.getId());
		List<User> EmptyUserList = new ArrayList<>();

		assertEquals(EmptyUserList, user3found.getFollowing());

		//Case 3 - Add an not existing id to your following list
		userService.addFollowing(user3.getId(), user3.getId()+1L);
		user3found = userService.findById(user3.getId());

		assertEquals(EmptyUserList, user3found.getFollowing());
	}


	// public void removeFollowing(long followingId, long id);
	@Test
	public void removeFollowingTest() {
		//Case 1 - Remove another user from your following list
		List<User> EmptyUserList = new ArrayList<>();

		User user1 = userService.create(new User(Role.USER, "user500@mail.com", "user500", "password"));
		User user2 = userService.create(new User(Role.USER, "user510@mail.com", "user510", "password"));

		userService.addFollowing(user1.getId(), user2.getId());
		userService.removeFollowing(user1.getId(), user2.getId());
		User userfound = userService.findById(user1.getId());

		assertEquals(EmptyUserList, userfound.getFollowing());

		//Case 2 - Remove yourself from your following list
		User user3 = userService.create(new User(Role.USER, "user530@mail.com", "user530", "password"));

		userService.removeFollowing(user3.getId(), user3.getId());
		User user3found = userService.findById(user3.getId());

		assertEquals(EmptyUserList, user3found.getFollowing());

		//Case 3 - Remove an not existing id from your following list
		userService.addFollowing(user3.getId(), user3.getId()+1L);
		user3found = userService.findById(user3.getId());

		assertEquals(EmptyUserList, user3found.getFollowing());
	}

	@Test
	public void findByUsernameTest() {
		//Case 1 - Find by existing username
		User user1 = userService.create(new User(Role.USER, "user121@mail.com", "user121", "password"));
		User user1Found = userService.findByUsername("user121");

		assertEquals(user1, user1Found);

		//Case 2 - Find by not existing username
		User user2Found = userService.findById(user1.getId() + 1L);

		assertNull(user2Found);
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

		assertEquals(users.size(), usersService.size());
	}
}