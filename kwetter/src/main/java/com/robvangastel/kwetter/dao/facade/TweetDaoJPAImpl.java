/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao.facade;

import com.robvangastel.kwetter.dao.AbstractJPADao;
import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.domain.Tweet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rob
 */
public class TweetDaoJPAImpl extends AbstractJPADao<Tweet> implements ITweetDao {

    @PersistenceContext
    private EntityManager entityManager;
        
    public TweetDaoJPAImpl() {
        super();
        setClassObj(Tweet.class);
    }
}
