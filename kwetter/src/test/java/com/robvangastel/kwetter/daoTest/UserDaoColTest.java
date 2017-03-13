package com.robvangastel.kwetter.daoTest;

import com.robvangastel.kwetter.dao.facade.UserDaoColImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoJPAImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.TweetException;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.utils.StringUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.*;

/**
 * Created by robvangastel on 13/03/2017.
 */
public class UserDaoColTest {

	private UserDaoColImpl dao = new UserDaoColImpl();

	// @Column(nullable = false, unique = true, length = 20)
	// private String username;
	@Test(expected=UserException.class)
	public void usernameTest1() throws Exception {
		// Case 1 - Check if String can be empty
		User user = dao.create(new User(Role.USER, "user@mail.nl", "", "password"));
		assertNull(user);
	}

	// @Column(nullable = false, unique = true, length = 20)
	// private String username;
	@Test
	public void usernameTest2() throws Exception {
		// Case 2 - Check for String length of 20
		String message20Char = "Hello World Hello Wo"; //20 characters
		User user2 = dao.create(new User(Role.USER, "user@mail.nl", message20Char, "password"));
		assertNotNull(user2);
	}

	// @Column(nullable = false, unique = true, length = 20)
	// private String username;
	@Test(expected=UserException.class)
	public void usernameTest3() throws Exception {
		// Case 3 - Check for String length of 21
		String message21Char = "Hello World Hello Wor"; //21 characters
		User user3 = dao.create(new User(Role.USER, "user@mail.nl", message21Char, "password"));
		assertNull(user3);
	}

	// @JsonIgnore
	// @Column(nullable = false, length = 20)
	// private String password;
	@Test(expected=UserException.class)
	public void passwordTest1() throws Exception {
		// Case 1 - Check if String can be empty
		User user = dao.create(new User(Role.USER, "user@mail.nl", "username", ""));
		assertNull(user);
	}

	// @JsonIgnore
	// @Column(nullable = false, length = 20)
	// private String password;
	@Test
	public void passwordTest2() throws Exception {
		// Case 2 - Check for String length of 20
		String message20Char = "Hello World Hello Wo"; //20 characters
		User user2 = dao.create(new User(Role.USER, "user@mail.nl", "username", message20Char));
		assertNotNull(user2);
	}

	// @JsonIgnore
	// @Column(nullable = false, length = 20)
	// private String password;
	@Test(expected=UserException.class)
	public void passwordTest3() throws Exception {
		// Case 3 - Check for String length of 21
		String message21Char = "Hello World Hello Wor"; //21 characters
		User user3 = dao.create(new User(Role.USER, "user@mail.nl", "username", message21Char));
		assertNull(user3);
	}

	// @Column(length = 160)
	// private String bio;
	@Test
	public void bioTest1() throws Exception {
		User user = dao.create(new User(Role.USER, "user@mail.nl", "username", "password"));

		// Case 1 - Check for String length of 160
		String message160Char = "Hello World! Hello World! Hello World! Hello "
				+ "World! Hello World! Hello World! Hello World! Hello World! "
				+ "Hello World! Hello World! Hello World! "
				+ "Hello World! Hell"; //160 characters
		user.setBio(message160Char);
		dao.update(user);

		assertEquals(message160Char, user.getBio());
	}

	// @Column(length = 160)
	// private String bio;
	@Test(expected=UserException.class)
	public void bioTest2() throws Exception {
		// Case 2 - Check for String length of 161
		User user2 = dao.create(new User(Role.USER, "user@mail.nl", "username", "password"));
		String message161Char = "Hello World! Hello World! Hello World! Hello "
				+ "World! Hello World! Hello World! Hello World! Hello World! "
				+ "Hello World! Hello World! Hello World! "
				+ "Hello World! Hello"; //161 characters
		user2.setBio(message161Char);
		dao.update(user2);

		assertTrue(StringUtil.isEmpty(user2.getBio())==false);
	}

	/***
	 * Delete user with the same id
	 * @param id id to delete
	 *
	 * void deleteById(long id) throws UserException;
	 */
	@Test
	public void deleteByIdTest1() throws Exception {
		//Case 1 - Delete existing user
		User user = dao.create(new User(Role.USER, "user@mail.com", "user", "password"));
		dao.deleteById(user.getId());

		User foundUser = dao.findById(user.getId());
		Assert.assertNull(foundUser);
	}

	@Test(expected = UserException.class)
	public void deleteByIdTest2() throws Exception {
		//Case 2 - Delete not existing id
		User user2 = dao.create(new User(Role.USER, "user2@mail.com", "user2", "password"));
		dao.deleteById(user2.getId() + 1L);

		User foundUser2 = dao.findById(user2.getId());
		assertNotNull(foundUser2);
	}

	/***
	 * Find all Users
	 * @return All Users
	 *
	 * List<User> findAll();
	 */
	@Test
	public void findAllTest() throws Exception {
		//Case 1 - Find all users
		User user = new User(Role.USER, "user@mail.com", "user", "password");
		User user2 = new User(Role.USER, "user@mail.com2", "user2", "password");

		List<User> users = new ArrayList<>();
		users.add(user);
		users.add(user2);

		dao.create(user);
		dao.create(user2);
		List<User> usersService = dao.findAll();

		assertEquals(users.size(), usersService.size());
	}

	/***
	 * Find User by id
	 * @param id
	 * @return Found User or Null when
	 * The User isn't found
	 *
	 * User findById(long id);
	 */
	@Test
	public void findByIdTest() throws Exception {
		//Case 1 - Find by existing username
		User user = dao.create(new User(Role.USER, "user121@mail.com", "user121", "password"));
		User userFound = dao.findById(user.getId());
		assertEquals(user, userFound);

		//Case 2 - Find by not existing username
		User user1Found = dao.findById(user.getId() + 1L);
		assertNull(user1Found);
	}

	 /***
	 * Find User by username
	 * @param username
	 * @return Found User or Null when
	 * The User isn't found
	 *
	 * User findByUsername(String username);
	 */
	@Test
	public void findByUsernameTest() throws Exception {
		//Case 1 - Find by existing username
		User user = dao.create(new User(Role.USER, "user121@mail.com", "user121", "password"));
		User userFound = dao.findByUsername("user121");
		assertEquals(user, userFound);

		//Case 2 - Find by not existing username
		User user1Found = dao.findByUsername("user121" + "user121");
		assertNull(user1Found);
	}

	/**
	 * Find User by email
	 * @param email
	 * @return Found User or Null when
	 * The User isn't found
	 *
	 * User findByEmail(String email);
	 */
	@Test
	public void findByEmail() throws Exception {
		//Case 1 - Find by existing username
		User user = dao.create(new User(Role.USER, "user121@mail.com", "user121", "password"));
		User userFound = dao.findByEmail("user121@mail.com");
		assertEquals(user, userFound);

		//Case 2 - Find by not existing username
		User user1Found = dao.findByEmail("user121@mail.com" + ".com");
		assertNull(user1Found);
	}
}
