/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.serviceTest;

import com.robvangastel.kwetter.domain.Role;
import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.service.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author robvangastel
 */
public class UserServiceTest {
    
    @Inject
    private UserService userService;
    
    @Test
    public void createTest() {
//        User user = new User(1L, )
//        Long id, Role role, String email, String username, String password
    }
    
    @Test
    public void deleteTest() {

    }
    
    @Test
    public void updateTest() {

    }
   
    @Test
    public void findByIdTest() {

    }
    
    @Test
    public void findAllTest() {

    }
}
