/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.domainTest;

import com.robvangastel.kwetter.utils.StringUtil;
import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 * Domain test on User and Tweet
 * @author robvangastel
 */
public class DomainTest {

    // User Domain tests

    // @Column(nullable = false, unique = true, length = 20)
    // private String username;
    @Test
    public void usernameTest() {
        // Case 1 - Check if String can be empty
        User user = new User(Role.USER, "user@mail.nl", "", "password");
        assertNull(user);

        // Case 2 - Check for String length of 20
        String message20Char = "Hello World Hello Wo"; //20 characters
        User user2 = new User(Role.USER, "user@mail.nl", message20Char, "password");

        assertNotNull(user2);

        // Case 3 - Check for String length of 21
        String message21Char = "Hello World Hello Wor"; //21 characters
        User user3 = new User(Role.USER, "user@mail.nl", message21Char, "password");

        assertNull(user3);
    }

    // @JsonIgnore
    // @Column(nullable = false, length = 20)
    // private String password;
    @Test
    public void passwordTest() {
        // Case 1 - Check if String can be empty
        User user = new User(Role.USER, "user@mail.nl", "username", "");
        assertNull(user);

        // Case 2 - Check for String length of 20
        String message20Char = "Hello World Hello Wo"; //20 characters
        User user2 = new User(Role.USER, "user@mail.nl", "username", message20Char);

        assertNotNull(user2);

        // Case 3 - Check for String length of 21
        String message21Char = "Hello World Hello Wor"; //21 characters
        User user3 = new User(Role.USER, "user@mail.nl", "username", message21Char);

        assertNull(user3);
    }

    // @Column(length = 160)
    // private String bio;
    @Test
    public void bioTest() {
        // Constr Role role, String email, String username, String password
        User user = new User(Role.USER, "user@mail.nl", "username", "password");

        // Case 1 - Check for String length of 160
        String message160Char = "Hello World! Hello World! Hello World! Hello "
            + "World! Hello World! Hello World! Hello World! Hello World! "
            + "Hello World! Hello World! Hello World! "
            + "Hello World! Hell"; //160 characters
        user.setBio(message160Char);

        assertEquals(message160Char, user.getBio());

        // Case 2 - Check for String length of 161
        User user2 = new User(Role.USER, "user@mail.nl", "username", "password");
        String message161Char = "Hello World! Hello World! Hello World! Hello "
            + "World! Hello World! Hello World! Hello World! Hello World! "
            + "Hello World! Hello World! Hello World! "
            + "Hello World! Hello"; //161 characters

        user2.setBio(message161Char);

        assertTrue(StringUtil.isEmpty(user2.getBio())==false);
    }

    // Tweet Domain tests

    // @Column(length = 140)
    // private String message;
    @Test
    public void messageTest() {
        // Constr Role role, String email, String username, String password
        User user = new User(Role.USER, "user@mail.nl", "username", "password");
        // Constr String message, Date timeStamp, User user

        // Case 1 - Create tweet with message
        Tweet tweet = new Tweet("Tweet message", new Date(1L), user);
        assertNotNull(tweet);

        // Case 2 - Check if message can be empty
        Tweet tweetEmpty = new Tweet("", new Date(1L), user);
        assertNull(tweetEmpty);

        // Case 3 - Check if message can be 140 Characters
        String message140Char = "Hello World! Hello World! Hello World! Hello "
            + "World! Hello World! Hello World! Hello World! Hello World! "
            + "Hello World! Hello World! Hello Worl"; //140 characters
        Tweet tweet140Chars = new Tweet(message140Char, new Date(1L), user);
        assertNull(tweet140Chars);

        // Case 4 - Check if message can be 141 Characters
        String message141Char = "Hello World! Hello World! Hello World! Hello "
            + "World! Hello World! Hello World! Hello World! Hello World! "
            + "Hello World! Hello World! Hello World"; //141 characters
        Tweet tweet141Chars = new Tweet(message141Char, new Date(1L), user);
        assertNull(tweet141Chars);
    }

    // private User user;
    @Test
    public void userTest() {
        // Case 1 - Check if user is the same

        // Constr Role role, String email, String username, String password
        User user = new User(Role.USER, "user@mail.nl", "username", "password");
        // Constr String message, Date timeStamp, User user
        Tweet tweet = new Tweet("Tweet message", new Date(1L), user);

        assertEquals(user, tweet.getUser());
    }
}
