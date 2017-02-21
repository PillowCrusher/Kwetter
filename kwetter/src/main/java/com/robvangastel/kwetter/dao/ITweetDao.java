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
    
    Tweet findById(long id);

    List<Tweet> findAll();

    void create(Tweet entity);

    Tweet update(Tweet entity);

    void delete(Tweet entity);

    void deleteById(long Id);
}
