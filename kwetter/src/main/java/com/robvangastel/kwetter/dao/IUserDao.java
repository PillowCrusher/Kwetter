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
     * @return Found User or Null when
     * The User wasn't found
     */
    User findById(long id);

    /***
     * Find User by username
     * @param username
     * @return Found User or Null when
     * The User wasn't found
     */
    User findByUsername(String username);
    
    /***
     * Find all Users
     * @return All Users
     */
    List<User> findAll();

    /***
     * Create User
     * @param entity User to create
     */
    void create(User entity);

    /***
     * Update User with the same id
     * @param entity User to update
     * @return User updated or Null when 
     * the user wasn't found
     */
    User update(User entity);

    /***
     * Delete user
     * @param entity User to delete
     */
    void delete(User entity);

    /***
     * Delete user with the same id
     * @param id id to delete
     */
    void deleteById(long id);
}
