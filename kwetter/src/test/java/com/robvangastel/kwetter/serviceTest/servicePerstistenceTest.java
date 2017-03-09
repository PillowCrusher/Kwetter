/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.dao.facade.TweetDaoColImpl;
import com.robvangastel.kwetter.dao.facade.TweetDaoJPAImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoColImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoJPAImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.TweetService;
import com.robvangastel.kwetter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 *
 * @author robvangastel
 */

public class servicePerstistenceTest {

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
		//tweetDao.setEm(Mock(EntityManager.class);
		//userDao.setEm(Mock(EntityManager.class);

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

		//Case 2 - Find a not existing tweet by id
	}

	// public List<Tweet> findByMessage(String arg);
	@Test
	public void findByMessageTest() {

	}

	// public List<Tweet> findByUser(long id);
	@Test
	public void findTweetByUserTest() {

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

	// public Tweet create(Tweet tweet, long id);
	@Test
	public void createTweetTest() {

	}

	// public void addFollowing(long followingId, long id);
	@Test
	public void addFollowingTest() {

	}

	// public void removeFollowing(long followingId, long id);
	@Test
	public void removeFollowingTest() {

	}
}
