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
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rob
 */

@JPA
@Stateless
public class TweetDaoJPAImpl extends AbstractJPADao<Tweet> implements ITweetDao {

	@PersistenceContext(unitName ="KwetterPU")
	private EntityManager entityManager;

    public TweetDaoJPAImpl() {
        super();
        setClassObj(Tweet.class);
    }

	public TweetDaoJPAImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
		setClassObj(Tweet.class);
	}

    @Override
    public List<Tweet> findByMessage(String message) {
	    Query query = entityManager.createQuery(
			    "SELECT t FROM Tweet t WHERE t.message = :message")
			    .setParameter("message", message);
	    return query.getResultList();
    }

    @Override
    public List<Tweet> findByUser(long id) {
	    Query query = entityManager.createQuery(
			    "SELECT t FROM Tweet t WHERE t.user_id = :id")
			    .setParameter("id", id);
	    return query.getResultList();
    }
}
