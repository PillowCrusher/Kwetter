/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao;

import com.robvangastel.kwetter.domain.User;

import java.util.List;

/**
 *
 * @author Rob
 */
public interface IUserDao {

    /***
     * Find User by id
     * @param id
     * @return 
     */
    User findById(long id);

    List<User> findAll();

    void create(User entity);

    User update(User entity);

    void delete(User entity);

    void deleteById(long id);
}
