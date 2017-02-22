/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao;

import com.robvangastel.kwetter.domain.Tweet;

import java.util.List;

/**
 *
 * @author Rob
 */
public interface ITweetDao {
    
    /***
     * Find Tweet by id
     * @param id
     * @return Found tweet or Null when not found
     */
    Tweet findById(long id);

    /***
     * 
     * @return return all tweets
     */
    List<Tweet> findAll();

    /***
     * Create a Tweet
     * @param entity Tweet to create
     * @return Created Tweet
     */
    Tweet create(Tweet entity);

    /***
     * Update Tweet with same id
     * @param entity Tweet to update
     * @return Tweet updated or Null when
     * the user wasn't found
     */
    Tweet update(Tweet entity);

    /***
     * Delete Tweet
     * @param entity Tweet to delete
     */
    void delete(Tweet entity);

    /***
     * Delete tweet by id
     * @param id id of the tweet
     */
    void deleteById(long id);
}
