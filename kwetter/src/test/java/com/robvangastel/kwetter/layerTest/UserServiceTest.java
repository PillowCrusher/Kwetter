/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.layerTest;

import com.robvangastel.kwetter.dao.facade.UserDaoColImpl;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;

import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.runner.RunWith;
import org.junit.Test;

/**
 *
 * @author robvangastel
 */

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    
    @InjectMocks
    private UserService userService;
    
    @Spy
    private UserDaoColImpl userDao;
    
    @Test
    public void createTest() {
        //Case 1 - Create user and check if the existing user is equal
        User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
        User userFound = userService.findById(user.getId());
        
        assertEquals(user, userFound);
        
        //Case 2 - Create user with the same email
        User user2 = userService.create(new User(Role.USER, "user@mail.com", "user2", "password"));
        assertNull(user2);
        
        //Case 3 - Create user with the same username
        User user3 = userService.create(new User(Role.USER, "user3@mail.com", "user", "password"));
        assertNull(user3);
        
        //Case 4 - Create user with empty password
        User user4 = userService.create(new User(Role.USER, "user4@mail.com", "user4", ""));
        assertNull(user4);
    }
    
    @Test
    public void deleteTest() {
        //Case 1 - Delete existing user
        User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
        userService.delete(user.getId());
        
        User foundUser = userService.findById(user.getId());
        assertNull(foundUser);
        
        //Case 2 - Delete not existing id
        User user2 = userService.create(new User(Role.USER, "user2@mail.com", "user2", "password"));
        userService.delete(user2.getId() + 1L);
        
        User foundUser2 = userService.findById(user2.getId());
        assertNotNull(foundUser2);
        
    }
    
    @Test
    public void updateTest() {
        //Case 1 - Update bio, website, location
        String bio = "Hello world, Bio";
        String location = "Hello world, Location";
        String websiteUrl = "https://hello.world";
        
        User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
        
        user.setBio(bio);
        user.setLocation(location);
        user.setWebsiteUrl(websiteUrl);
        
        userService.update(user);
        
        User userFound = userService.findById(user.getId());
        
        assertEquals(user.getBio(), userFound.getBio());
        assertEquals(user.getLocation(), userFound.getLocation());
        assertEquals(user.getWebsiteUrl(), userFound.getWebsiteUrl());
        
        //Case 2 - Check for update on password
        User user2 = userService.create(new User(Role.USER, "user2@mail.com", "user2", "password"));
        
        user2.setBio(bio);
        user2.setLocation(location);
        user2.setWebsiteUrl(websiteUrl);
        
        //Should be not possible
        user2.setEmail("user2@mail.com");
        user2.setPassword("password2");
        user2.setUsername("user3");
        user2.setRole(Role.ADMINISTRATOR);
        
        userService.update(user);
        
        User user2Found = userService.findById(user2.getId());
        
        assertEquals(user2.getBio(), user2Found.getBio());
        assertEquals(user2.getLocation(), user2Found.getLocation());
        assertEquals(user2.getWebsiteUrl(), user2Found.getWebsiteUrl());
        
        //Should've not changed
        assertEquals(user2.getEmail(), user2Found.getEmail());
        assertEquals(user2.getPassword(), user2Found.getPassword());
        assertEquals(user2.getRole(), user2Found.getRole());
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
   
    @Test
    public void findByIdTest() {
        //Case 1 - Find by existing id
        User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
        User userFound = userService.findById(user.getId());
        
        assertEquals(user, userFound);
        
        //Case 2 - Find not existing id
        userFound = userService.findById(user.getId() + 1L);
        
        assertNull(userFound);
    }
    
    @Test
    public void findByUsernameTest() {
        //Case 1 - Find by existing username
        User user = userService.create(new User(Role.USER, "user@mail.com", "user", "password"));
        User userFound = userService.findByUsername("user");
        
        assertEquals(user, userFound);
        
        //Case 2 - Find by not existing username
        userFound = userService.findById(user.getId() + 1L);
        
        assertNull(userFound);
    }
    
    @Test
    public void addFollowingTest() {
        //Case 1 - Add another user to your following list
        
        //Case 2 - Add yourself to your following list
        
        //Case 3 - Add an not existing id to your following list
    }
    
    @Test
    public void removeFollowingTest() {
        //Case 1 - Remove another user from your following list
        
        //Case 2 - Remove yourself from your following list
        
        //Case 3 - Remove an not existing id from your following list
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
