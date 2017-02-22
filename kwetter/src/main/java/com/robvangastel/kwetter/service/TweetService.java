/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.service;

import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.domain.Tweet;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

/**
 *
 * @author robvangastel
 */

@Stateless
public class TweetService {
    
    @Inject @Default
    private ITweetDao dao;

    public TweetService() {
        super();
    }

    public Tweet create(Tweet entity) {
        return dao.create(entity);
    }
    
    public void delete(long id) {
        Tweet entity = dao.findById(id);
        dao.delete(entity);
    }
    
    public void update(Tweet entity) {
        dao.update(entity);
    }

    public Tweet findById(long id) {
        return dao.findById(id);
    }

    public List<Tweet> findAll() {
        return dao.findAll();
    }
    
}
