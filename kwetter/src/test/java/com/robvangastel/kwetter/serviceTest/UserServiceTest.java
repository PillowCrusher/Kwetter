package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by robvangastel on 13/03/2017.
 */
public class UserServiceTest {

	@InjectMocks
	private UserService userService;

	@Mock
	private IUserDao userDao;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
	}

	@Test
	public void ServiceTest() {
		assertNotNull(userService);
	}

	// @PermitAll
	// public User create(User entity) throws UserException;
	@Test
	public void createTest() {
		// Case 1 - Existing Username

		// Case 2 - Existing Email

		// Case 3 - Non-existing Email & Username

	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public void updateUsername(String username, long id);
	@Test
	public void updateUsernameTest() throws Exception {
		// Case 1 - Update new username
		User user1 = userService.create(new User(Role.USER, "user113@mail.com", "user113", "password"));
		String newUsername = "username2";

		userService.updateUsername(newUsername, user1.getId());
		User user1Found = userService.findById(user1.getId());

		assertEquals(user1Found.getId(), user1.getId());
		assertEquals(user1Found.getUsername(), newUsername);

		// Case 2 - Update to existing username
		User user2 = userService.create(new User(Role.USER, "user2@mail.com", "user2", "password"));

		userService.updateUsername(newUsername, user2.getId());
		User user2Found = userService.findById(user2.getId());

		assertNotEquals(user2Found.getUsername(), newUsername);

		// Case 3 - Update to empty username
		String emptyUsername = "";
		userService.updateUsername("", user2.getId());
		user2Found = userService.findById(user2.getId());

		assertNotEquals(user2Found.getUsername(), emptyUsername);
	}

	// @PermitAll
	// public User create(User entity) throws Exception;
	@Test
	public void createTweetTest() throws Exception {
		// Case 1 - Create user and check if the existing user is equal
		User user1 = userService.create(new User(Role.USER, "user123@mail.com", "user123", "password"));
		User user1Found = userService.findById(user1.getId());

		assertEquals(user1, user1Found);

		// Case 2 - Create user with the same email
		User user2 = userService.create(new User(Role.USER, "user123@mail.com", "user2", "password"));
		assertNull(user2);

		// Case 3 - Create user with the same username
		User user3 = userService.create(new User(Role.USER, "user3@mail.com", "user123", "password"));
		assertNull(user3);

		// Case 4 - Create user with empty password
		User user4 = userService.create(new User(Role.USER, "user4@mail.com", "user4", ""));
		assertNull(user4);

	}
	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public void addFollowing(long followingId, long id) throws Exception;
	@Test
	public void addFollowingTest() throws Exception {
		// Case 1 - Add another user to your following list
		User user1 = userService.create(new User(Role.USER, "user500@mail.com", "user500", "password"));
		User user2 = userService.create(new User(Role.USER, "user510@mail.com", "user510", "password"));

		userService.addFollowing(user1.getId(), user2.getId());
		User userfound = userService.findById(user1.getId());

		assertNotNull(userfound.getFollowing());

		// Case 2 - Add yourself to your following list
		User user3 = userService.create(new User(Role.USER, "user530@mail.com", "user530", "password"));

		userService.addFollowing(user3.getId(), user3.getId());
		User user3found = userService.findById(user3.getId());
		List<User> EmptyUserList = new ArrayList<>();

		assertEquals(EmptyUserList, user3found.getFollowing());

		// Case 3 - Add an not existing id to your following list
		userService.addFollowing(user3.getId(), user3.getId()+1L);
		user3found = userService.findById(user3.getId());

		assertEquals(EmptyUserList, user3found.getFollowing());
	}

	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
	// public void removeFollowing(long followingId, long id) throws Exception;
	@Test
	public void removeFollowingTest() throws Exception {
		// Case 1 - Remove another user from your following list
		List<User> EmptyUserList = new ArrayList<>();

		User user1 = userService.create(new User(Role.USER, "user500@mail.com", "user500", "password"));
		User user2 = userService.create(new User(Role.USER, "user510@mail.com", "user510", "password"));

		userService.addFollowing(user1.getId(), user2.getId());
		userService.removeFollowing(user1.getId(), user2.getId());
		User userfound = userService.findById(user1.getId());

		assertEquals(EmptyUserList, userfound.getFollowing());

		// Case 2 - Remove yourself from your following list
		User user3 = userService.create(new User(Role.USER, "user530@mail.com", "user530", "password"));

		userService.removeFollowing(user3.getId(), user3.getId());
		User user3found = userService.findById(user3.getId());

		assertEquals(EmptyUserList, user3found.getFollowing());

		// Case 3 - Remove an not existing id from your following list
		userService.addFollowing(user3.getId(), user3.getId()+1L);
		user3found = userService.findById(user3.getId());

		assertEquals(EmptyUserList, user3found.getFollowing());
	}
}
