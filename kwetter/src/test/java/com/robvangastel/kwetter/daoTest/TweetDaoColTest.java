package com.robvangastel.kwetter.daoTest;

import com.robvangastel.kwetter.dao.facade.TweetDaoColImpl;
import com.robvangastel.kwetter.dao.facade.TweetDaoJPAImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoColImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.TweetException;
import com.robvangastel.kwetter.utils.StringUtil;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by robvangastel on 13/03/2017.
 */
public class TweetDaoColTest {

	private TweetDaoColImpl dao = new TweetDaoColImpl();
	private UserDaoColImpl userDao = new UserDaoColImpl();

	private User user;
	private boolean isInitialized = false;

	@Before
	public void setUp() throws Exception {
		if(!isInitialized) {
			user = new User(Role.USER, "user@mail.com", "user", "password");
			userDao.create(user);
		}
	}

	/***
	 * Create a Tweet
	 * @param entity Tweet to create
	 * @return Created Tweet
	 *
	 * Tweet create(Tweet entity) throws TweetException;
	 */
	@Test
	public void createTest() throws Exception {
		// Constr String message, Date timeStamp, User user
		// Case 1 - Create tweet with message
		Tweet tweet = dao.create(new Tweet("Tweet message", new Date(1L), user));
		assertNotNull(tweet);

		// Case 2 - Check if message can be empty
		Tweet tweetEmpty = dao.create(new Tweet("", new Date(1L), user));
		TestCase.assertNull(tweetEmpty);

		// Case 3 - Check if message can be 140 Characters
		String message140Char = "Hello World! Hello World! Hello World! Hello "
				+ "World! Hello World! Hello World! Hello World! Hello World! "
				+ "Hello World! Hello World! Hello Worl"; //140 characters
		Tweet tweet140Chars = dao.create(new Tweet(message140Char, new Date(1L), user));
		assertNotNull(tweet140Chars);

		// Case 4 - Check if message can be 141 Characters
		String message141Char = "Hello World! Hello World! Hello World! Hello "
				+ "World! Hello World! Hello World! Hello World! Hello World! "
				+ "Hello World! Hello World! Hello World"; //141 characters
		Tweet tweet141Chars = dao.create(new Tweet(message141Char, new Date(1L), user));
		TestCase.assertNull(tweet141Chars);
	}

	/***
	 * Find Tweet by id
	 * @param id
	 * @return Found tweet or Null when
	 * the Tweet isn't found
	 *
	 * Tweet findById(long id);
	 */
	@Test
	public void findByIdTest() throws Exception {
		//Case 1 - Find an existing tweet by id
		Tweet tweet = new Tweet("Hello world!", new Date(1L), user);
		Tweet createdTweet = dao.create(tweet);

		Tweet tweetFound = dao.findById(createdTweet.getId());
		assertEquals(createdTweet, tweetFound);

		//Case 2 - Find a not existing tweet by id
		Tweet tweet2Found = dao.findById(createdTweet.getId()+1);
		TestCase.assertNull(tweet2Found);
	}

	/***
	 * Find Tweet(s) by message
	 * @param message
	 * @return Found tweet(s) or Null when
	 * the Tweet(s) isn't found
	 * List<Tweet> findByMessage(String message);
	 */
	@Test
	public void findByMessageTest() throws Exception {
		//Case 1 - Find an existing tweet by message
		Tweet tweet = new Tweet("Hello world!", new Date(1L), user);
		Tweet createdTweet = dao.create(tweet);

		List<Tweet> tweetFound = dao.findByMessage("Hello world!");
		assertEquals(1, tweetFound.size());

		//Casee 2 - Find a not existing tweet by message
		Tweet tweet2 = new Tweet("Hello world!", new Date(1L), user);
		dao.create(tweet2);

		List<Tweet> tweet2Found = dao.findByMessage("!dlorw elloh");
		List<Tweet> emptyList = new ArrayList<>();

		assertEquals(emptyList, tweet2Found);
	}

	/***
	 * Find Tweet(s) by User
	 * @param id User id
	 * @return Found tweet(s) or Null when
	 * the Tweet(s) isn't found
	 *
	 * List<Tweet> findByUser(long id);
	 */
	@Test
	public void findByUserTest() throws Exception {
		//Case 1 - Find all existing tweets by user
		Tweet tweet1 = new Tweet("Hello world!", new Date(1L), user);
		Tweet tweet2 = new Tweet("Hello world!", new Date(2L), user);

		List<Tweet> tweets = new ArrayList<>();
		tweets.add(tweet1);
		tweets.add(tweet2);

		dao.create(tweet1);
		dao.create(tweet2);

		Long id = user.getId();
		List<Tweet> tweetsService = dao.findByUser(user.getId());

		assertEquals(tweets.size(), tweetsService.size());
	}

	/***
	 * Delete tweet by id
	 * @param id id of the tweet
	 *
	 * void deleteById(long id) throws TweetException;
	 */
	@Test
	public void deleteByIdTest() throws Exception {
		//Case 1 - Delete an existing Tweet
		Tweet tweet = new Tweet("Hello world!", new Date(1L), user);
		Tweet createdTweet = dao.create(tweet);

		dao.deleteById(tweet.getId());

		assertNull(dao.findById(tweet.getId()));
	}

	/***
	 * Delete tweet by id
	 * @param id id of the tweet
	 *
	 * void deleteById(long id) throws TweetException;
	 */
	@Test(expected=TweetException.class)
	public void deleteByIdExceptionTest() throws Exception {
		//Case 2 - Delete a non-existing Tweet
		Tweet tweet2 = new Tweet("Hello world!", new Date(1L), user);
		Tweet created2Tweet = dao.create(tweet2);

		dao.deleteById(tweet2.getId()+1l);
	}
}
