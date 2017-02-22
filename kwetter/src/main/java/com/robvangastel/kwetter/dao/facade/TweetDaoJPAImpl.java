/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao.facade;

import com.robvangastel.kwetter.dao.AbstractJPADao;
import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.domain.Tweet;

/**
 *
 * @author Rob
 */
public class TweetDaoJPAImpl extends AbstractJPADao<Tweet> implements ITweetDao {
        
    public TweetDaoJPAImpl() {
        super();
        setClassObj(Tweet.class);
    }
}
