//package com.robvangastel.kwetter.serviceTest;
//
//import com.robvangastel.kwetter.dao.IUserDao;
//import com.robvangastel.kwetter.domain.Role;
//import com.robvangastel.kwetter.domain.Tweet;
//import com.robvangastel.kwetter.domain.User;
//import com.robvangastel.kwetter.service.UserService;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static junit.framework.TestCase.assertNotNull;
//import static junit.framework.TestCase.assertNull;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotEquals;
//import static org.mockito.Mockito.*;
//import static org.mockito.MockitoAnnotations.initMocks;
//
///**
// * Created by robvangastel on 13/03/2017.
// */
//public class UserServiceTest {
//
//	@InjectMocks
//	private UserService userService;
//
//	@Mock
//	private IUserDao userDao;
//
//	@Before
//	public void setUp() throws Exception {
//		initMocks(this);
//	}
//
//	@Test
//	public void ServiceTest() {
//		assertNotNull(userService);
//	}
//
//	// @PermitAll
//	// public User create(User entity) throws UserException;
//	@Test
//	public void createTest1() throws Exception {
//		// Case 1 - Existing Username
//		User user = new User(Role.USER, "email@mail.com", "username", "password");
//		user.setId(1l);
//
//		when(userDao.findByUsername(user.getUsername())).thenReturn(null);
//		when(userDao.findByEmail(user.getEmail())).thenReturn(null);
//
//		userService.create(user);
//		verify(userDao, atLeastOnce()).create(user);
//	}
//
//	// @PermitAll
//	// public User create(User entity) throws UserException;
//	@Test
//	public void createTest2() throws Exception {
//		// Case 2 - Existing Email
//		User user = new User(Role.USER, "email@mail.com", "username", "password");
//		user.setId(1l);
//
//		when(userDao.findByUsername(user.getUsername())).thenReturn(null);
//		when(userDao.findByEmail(user.getEmail())).thenReturn(user);
//
//		userService.create(user);
//		verify(userDao, never()).create(user);
//	}
//
//	// @PermitAll
//	// public User create(User entity) throws UserException;
//	@Test
//	public void createTest3() throws Exception {
//		// Case 3 - Non-existing Email & Username
//		User user = new User(Role.USER, "email@mail.com", "username", "password");
//		user.setId(1l);
//
//		when(userDao.findByUsername(user.getUsername())).thenReturn(user);
//		when(userDao.findByEmail(user.getEmail())).thenReturn(user);
//
//		userService.create(user);
//		verify(userDao, never()).create(user);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void updateUsername(String username, long id);
//	@Test
//	public void updateUsernameTest1() throws Exception {
//		// Case 1 - Update new username
//		User user = new User(Role.USER, "user113@mail.com", "user113", "password");
//		user.setId(1l);
//		String newUsername = "username2";
//
//		when(userDao.findById(1l)).thenReturn(user);
//		when(userDao.findByUsername(newUsername)).thenReturn(null);
//
//		userService.updateUsername(newUsername, user.getId());
//		verify(userDao, atLeastOnce()).update(user);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void updateUsername(String username, long id);
//	@Test
//	public void updateUsernameTest2() throws Exception {
//		// Case 2 - Update to empty username
//		User user = new User(Role.USER, "user113@mail.com", "user113", "password");
//		user.setId(1l);
//		String newUsername = "";
//
//		when(userDao.findById(1l)).thenReturn(user);
//		when(userDao.findByUsername(newUsername)).thenReturn(null);
//
//		userService.updateUsername(newUsername, user.getId());
//		verify(userDao, never()).update(user);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void updateUsername(String username, long id);
//	@Test
//	public void updateUsernameTest3() throws Exception {
//		// Case 3 - Update to existing username
//		User user = new User(Role.USER, "user113@mail.com", "user113", "password");
//		user.setId(1l);
//		String newUsername = "";
//
//		when(userDao.findById(1l)).thenReturn(user);
//		when(userDao.findByUsername(newUsername)).thenReturn(user);
//
//		userService.updateUsername(newUsername, user.getId());
//		verify(userDao, never()).update(user);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void addFollowing(long followingId, long id) throws Exception;
//	@Test
//	public void addFollowingTest1() throws Exception {
//		// Case 1 - Add another user to your following list
//		User user1 = new User(Role.USER, "user500@mail.com", "user500", "password");
//		User user2 = new User(Role.USER, "user510@mail.com", "user510", "password");
//		user1.setId(1l);
//		user2.setId(2l);
//
//		when(userDao.findById(1l)).thenReturn(user1);
//		when(userDao.findById(2l)).thenReturn(user2);
//
//
//		userService.addFollowing(user1.getId(), user2.getId());
//
//		verify(userDao, atLeastOnce()).update(user1);
//		verify(userDao, atLeastOnce()).update(user2);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void addFollowing(long followingId, long id) throws Exception;
//	@Test
//	public void addFollowingTest2() throws Exception {
//		// Case 2 - Add yourself to your following list
//		User user1 = new User(Role.USER, "user500@mail.com", "user500", "password");
//		user1.setId(1l);
//
//		when(userDao.findById(1l)).thenReturn(user1);
//
//		userService.addFollowing(user1.getId(), user1.getId());
//		verify(userDao, never()).update(user1);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void addFollowing(long followingId, long id) throws Exception;
//	@Test
//	public void addFollowingTest3() throws Exception {
//		// Case 3 - Add an not existing id to your following list
//		User user1 = new User(Role.USER, "user500@mail.com", "user500", "password");
//		user1.setId(1l);
//
//		when(userDao.findById(1l)).thenReturn(user1);
//		when(userDao.findById(3l)).thenReturn(null);
//
//		userService.addFollowing(user1.getId(), 3l);
//		verify(userDao, never()).update(user1);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void removeFollowing(long followingId, long id) throws Exception;
//	@Test
//	public void removeFollowingTest1() throws Exception {
//		// Case 1 - Remove another user from your following list
//		User user1 = new User(Role.USER, "user500@mail.com", "user500", "password");
//		User user2 = new User(Role.USER, "user510@mail.com", "user510", "password");
//		user1.setId(1l);
//		user2.setId(2l);
//
//		when(userDao.findById(1l)).thenReturn(user1);
//		when(userDao.findById(2l)).thenReturn(user2);
//
//		userService.removeFollowing(user1.getId(), user2.getId());
//		verify(userDao, atLeastOnce()).update(user1);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void removeFollowing(long followingId, long id) throws Exception;
//	@Test
//	public void removeFollowingTest2() throws Exception {
//		// Case 2 - Remove yourself from your following list
//		User user1 = new User(Role.USER, "user500@mail.com", "user500", "password");
//		user1.setId(1l);
//
//		when(userDao.findById(1l)).thenReturn(user1);
//
//		userService.removeFollowing(user1.getId(), user1.getId());
//		verify(userDao, never()).update(user1);
//	}
//
//	// @RolesAllowed({"USER","ADMINISTRATOR", "MODERATOR"})
//	// public void removeFollowing(long followingId, long id) throws Exception;
//	@Test
//	public void removeFollowingTest3() throws Exception {
//		// Case 3 - Remove an not existing id from your following list
//		User user1 = new User(Role.USER, "user500@mail.com", "user500", "password");
//		user1.setId(1l);
//
//		when(userDao.findById(1l)).thenReturn(user1);
//		when(userDao.findById(3l)).thenReturn(null);
//
//		userService.removeFollowing(user1.getId(), 3l);
//		verify(userDao, never()).update(user1);
//	}
//}
