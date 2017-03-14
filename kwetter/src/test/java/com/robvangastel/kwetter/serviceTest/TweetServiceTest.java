/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.service.TweetService;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;


/**
 *
 * @author robvangastel
 */

public class TweetServiceTest {

    @InjectMocks
    private TweetService tweetService;

	@Mock
    private ITweetDao tweetDao;

	@Mock
	private IUserDao userDao;

    @Before
    public void setUp() {
	    initMocks(this);
    }

	@Test
	public void ServiceTest() {
		assertNotNull(tweetService);
	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public Tweet create(Tweet tweet) throws Exception;
	@Test
	public void createTest() throws Exception {
		// Case 1 - Existing User
		User user = new User(Role.USER, "email@mail.com", "username", "password");
		when(userDao.findById(1l)).thenReturn(user);
		user.setId(1l);
		tweetService.create(new Tweet("message", user));
		verify(userDao, atLeastOnce()).update(user);
	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public Tweet create(Tweet tweet) throws Exception;
	@Test
	public void createTest2() throws Exception {
		// Case 2 - Non-existing User
		User user = new User(Role.USER, "email@mail.com", "username", "password");
		when(userDao.findById(1l)).thenReturn(null);
		user.setId(1l);
		tweetService.create(new Tweet("message", user));
		verify(userDao, never()).update(user);
	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public void delete(long id, long userId) throws Exception;
	@Test
	public void deleteTest() throws Exception {
		// Case 1 - Existing User
		User user = new User(Role.USER, "email@mail.com", "username", "password");
		Tweet tweet = new Tweet("message", user);
		user.setId(1l);
		tweet.setId(1l);

		when(tweetDao.findById(tweet.getId())).thenReturn(tweet);
		tweetService.delete(1l, user.getId());
		verify(tweetDao, atLeastOnce()).delete(tweet);
	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public void delete(long id, long userId) throws Exception;
	@Test(expected=UserException.class)
	public void deleteTest2() throws Exception {
		// Case 2 - Non-existing User
		User user = new User(Role.USER, "email@mail.com", "username", "password");
		Tweet tweet = new Tweet("message", user);
		user.setId(1l);
		tweet.setId(1l);

		when(tweetDao.findById(tweet.getId())).thenReturn(null);
		tweetService.delete(1l, user.getId());
		verify(tweetDao, never()).delete(tweet);
	}
}
