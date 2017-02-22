/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao.facade;

import com.robvangastel.kwetter.dao.AbstractJPADao;
import com.robvangastel.kwetter.dao.ITweetDao;
import com.robvangastel.kwetter.dao.JPA;
import com.robvangastel.kwetter.domain.Tweet;
import java.util.List;

import javax.ejb.Stateless;

/**
 *
 * @author Rob
 */

@Stateless @JPA
public class TweetDaoJPAImpl extends AbstractJPADao<Tweet> implements ITweetDao {
        
    public TweetDaoJPAImpl() {
        super();
        setClassObj(Tweet.class);
    }

    @Override
    public List<Tweet> findByMessage(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tweet> findByUser(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
