package com.robvangastel.kwetter.daoTest;

import com.robvangastel.kwetter.dao.facade.TweetDaoJPAImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoColImpl;
import com.robvangastel.kwetter.dao.facade.UserDaoJPAImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.UserException;
import com.robvangastel.kwetter.utils.StringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by robvangastel on 13/03/2017.
 */
public class UserDaoJPATest {

	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kwetterTestPU");
	private EntityManager entityManager;
	private EntityTransaction transaction;
	private UserDaoJPAImpl dao;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();

		dao = new UserDaoJPAImpl(entityManager);
		dao.setEntityManager(entityManager);
	}

	// @Column(nullable = false, unique = true, length = 20)
	// private String username;
	@Test(expected=UserException.class)
	public void usernameTest1() throws Exception {
		// Case 1 - Check if String can be empty
		transaction.begin();
		User user = dao.create(new User(Role.USER, "user@mail.nl", "", "password"));
		assertNull(user);
		transaction.commit();
	}

	// @Column(nullable = false, unique = true, length = 20)
	// private String username;
	@Test
	public void usernameTest2() throws Exception {
		// Case 2 - Check for String length of 20
		transaction.begin();
		String message20Char = "Hello World Hello Wo"; //20 characters
		User user2 = dao.create(new User(Role.USER, "user@mail.nl", message20Char, "password"));
		assertNotNull(user2);
		transaction.commit();
	}

	// @Column(nullable = false, unique = true, length = 20)
	// private String username;
	@Test(expected=UserException.class)
	public void usernameTest3() throws Exception {
		// Case 3 - Check for String length of 21
		transaction.begin();
		String message21Char = "Hello World Hello Wor"; //21 characters
		User user3 = dao.create(new User(Role.USER, "user@mail.nl", message21Char, "password"));
		assertNull(user3);
		transaction.commit();
	}

	// @JsonIgnore
	// @Column(nullable = false, length = 20)
	// private String password;
	@Test(expected=UserException.class)
	public void passwordTest1() throws Exception {
		// Case 1 - Check if String can be empty
		transaction.begin();
		User user = dao.create(new User(Role.USER, "user@mail.nl", "username", ""));
		assertNull(user);
		transaction.commit();
	}

	// @JsonIgnore
	// @Column(nullable = false, length = 20)
	// private String password;
	@Test
	public void passwordTest2() throws Exception {
		// Case 2 - Check for String length of 20
		transaction.begin();
		String message20Char = "Hello World Hello Wo"; //20 characters
		User user2 = dao.create(new User(Role.USER, "user@mail.nl", "username", message20Char));
		assertNotNull(user2);
		transaction.commit();
	}

	// @JsonIgnore
	// @Column(nullable = false, length = 20)
	// private String password;
	@Test(expected=UserException.class)
	public void passwordTest3() throws Exception {
		// Case 3 - Check for String length of 21
		transaction.begin();
		String message21Char = "Hello World Hello Wor"; //21 characters
		User user3 = dao.create(new User(Role.USER, "user@mail.nl", "username", message21Char));
		assertNull(user3);
		transaction.commit();
	}

	// @Column(length = 160)
	// private String bio;
	@Test
	public void bioTest1() throws Exception {
		transaction.begin();
		User user = dao.create(new User(Role.USER, "user@mail.nl", "username", "password"));
		transaction.commit();

		transaction.begin();
		// Case 1 - Check for String length of 160
		String message160Char = "Hello World! Hello World! Hello World! Hello "
				+ "World! Hello World! Hello World! Hello World! Hello World! "
				+ "Hello World! Hello World! Hello World! "
				+ "Hello World! Hell"; //160 characters
		user.setBio(message160Char);
		dao.update(user);

		assertEquals(message160Char, user.getBio());
		transaction.commit();
	}

	// @Column(length = 160)
	// private String bio;
	@Test(expected=UserException.class)
	public void bioTest2() throws Exception {
		// Case 2 - Check for String length of 161
		transaction.begin();
		User user2 = dao.create(new User(Role.USER, "user@mail.nl", "username", "password"));
		transaction.commit();

		transaction.begin();
		String message161Char = "Hello World! Hello World! Hello World! Hello "
				+ "World! Hello World! Hello World! Hello World! Hello World! "
				+ "Hello World! Hello World! Hello World! "
				+ "Hello World! Hello"; //161 characters
		user2.setBio(message161Char);
		dao.update(user2);

		assertTrue(StringUtil.isEmpty(user2.getBio())==false);
		transaction.commit();
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
		transaction.begin();
		User user = dao.create(new User(Role.USER, "user@mail.com", "user", "password"));
		transaction.commit();

		transaction.begin();
		dao.deleteById(user.getId());

		User foundUser = dao.findById(user.getId());
		assertNull(foundUser);
		transaction.commit();
	}

	@Test
	public void deleteByIdTest2() throws Exception {
		//Case 2 - Delete not existing id
		transaction.begin();
		User user2 = dao.create(new User(Role.USER, "user2@mail.com", "user2", "password"));
		transaction.commit();

		transaction.begin();
		try {
			dao.deleteById(user2.getId() + 1L);
		} catch(UserException e) {

		}

		User foundUser2 = dao.findById(user2.getId());
		assertNotNull(foundUser2);
		transaction.commit();
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

		transaction.begin();
		dao.create(user);
		dao.create(user2);
		List<User> usersService = dao.findAll();
		transaction.commit();

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
		transaction.begin();
		User user = dao.create(new User(Role.USER, "user121@mail.com", "user121", "password"));
		User userFound = dao.findById(user.getId());
		assertEquals(user, userFound);
		transaction.commit();

		//Case 2 - Find by not existing username
		transaction.begin();
		User user1Found = dao.findById(user.getId() + 1L);
		assertNull(user1Found);
		transaction.commit();
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
		transaction.begin();
		User user = dao.create(new User(Role.USER, "user121@mail.com", "user121", "password"));
		User userFound = dao.findByUsername("user121");
		assertEquals(user, userFound);
		transaction.commit();

		//Case 2 - Find by not existing username
		transaction.begin();
		User user1Found = dao.findByUsername("user121" + "user121");
		assertNull(user1Found);
		transaction.commit();
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
		transaction.begin();
		User user = dao.create(new User(Role.USER, "user121@mail.com", "user121", "password"));
		User userFound = dao.findByEmail("user121@mail.com");
		assertEquals(user, userFound);
		transaction.commit();

		//Case 2 - Find by not existing username
		transaction.begin();
		User user1Found = dao.findByEmail("user121@mail.com" + ".com");
		assertNull(user1Found);
		transaction.commit();
	}
}
