/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao;

import com.robvangastel.kwetter.domain.Tweet;
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.TweetException;

import java.util.List;

/**
 *
 * @author Rob
 */
public interface ITweetDao {
    
    /***
     * Find Tweet by id
     * @param id
     * @return Found tweet or Null when 
     * the Tweet isn't found
     */
    Tweet findById(long id);

    /***
     * Find Tweet(s) by message
     * @param message
     * @return Found tweet(s) or Null when 
     * the Tweet(s) isn't found
     */
    List<Tweet> findByMessage(String message);
    
    /***
     * Find Tweet(s) by User
     * @param id User id
     * @return Found tweet(s) or Null when 
     * the Tweet(s) isn't found
     */
    List<Tweet> findByUser(long id);

    /***
     * Find Tweet(s) by Mention
     * @param mention
     * @return Found tweet(s) or Null when
     * the Tweet(s) isn't found
     */
    List<Tweet> findByMention(String mention);

    /***
     * Find trends
     * @return Trends by tweets
     */
    List<String> findTrends();

	/***
	 * Find tweets for User, includes tweets of his
	 * following list
	 * @param user
	 * @return Found tweet(s) or Null when
	 * the Tweet(s) isn't found
	 */
	List<Tweet> findForUser(User user);
    
    /***
     * 
     * @return return all tweets
     */
    List<Tweet> findAll();

	/***
     *
     * @return tweets orderd by Date
     */
    List<Tweet> findAllOrderedByDate();

    /***
     * Create a Tweet
     * @param entity Tweet to create
     * @return Created Tweet
     */
    Tweet create(Tweet entity) throws TweetException;

    /***
     * Delete Tweet
     * @param entity Tweet to delete
     */
    void delete(Tweet entity) throws TweetException;

    /***
     * Delete tweet by id
     * @param id id of the tweet
     */
    void deleteById(long id) throws TweetException;
}
