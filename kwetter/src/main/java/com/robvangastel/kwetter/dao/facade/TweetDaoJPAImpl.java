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
import com.robvangastel.kwetter.domain.User;
import com.robvangastel.kwetter.exception.TweetException;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
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
	public Tweet create(Tweet entity) {
		if(!entity.getMessage().isEmpty()) {
			if(entity.getMessage().length() < 141 && entity.getMessage().length() > 0) {
				entityManager.persist(entity);
				return entity;
			}
		}
		return null;
	}

    @Override
    public List<Tweet> findByMessage(String message) {
    	message = "%" + message + "%";
	    Query query = entityManager.createQuery(
			    "SELECT t FROM Tweet t WHERE t.message like :message")
			    .setParameter("message", message);
	    return query.getResultList();
    }

    @Override
    public List<Tweet> findByUser(long id) {
	    Query query = entityManager.createQuery(
			    "SELECT t FROM Tweet t WHERE t.user.id = :id ORDER BY timeStamp DESC")
			    .setParameter("id", id);
	    return query.getResultList();
    }

    @Override
	public List<Tweet> findByMention(String mention) {
		Query query = entityManager.createQuery( //"from User as user where 'ADMIN' in elements(user.roles)";
				"FROM Tweet as t WHERE :mention in elements(t.mentions) ORDER BY timeStamp DESC")
				.setParameter("mention", mention);
		return (List<Tweet>) query.getResultList();
	}

	@Override
	public List<Tweet> findForUser(User user) {
		ArrayList<Long> timelineUserIds = new ArrayList<>();

		for(User u: user.getFollowing()) {
			timelineUserIds.add(u.getId());
		}

		timelineUserIds.add(user.getId());
		Query query = entityManager.createQuery("from Tweet where user.id in :following order by id desc ").setParameter("following", timelineUserIds);
		ArrayList<Tweet> result = (ArrayList<Tweet>)query.getResultList();
		return result;
	}

	@Override
	public List<Tweet> findAllOrderedByDate() {
		Query query = entityManager.createQuery(
				"SELECT t FROM Tweet t ORDER BY timeStamp DESC");
		return query.getResultList();
	}

	@Override
	public void deleteById(long id) throws TweetException {
		final Tweet entity = findById(id);

		if(entity == null) {
			throw new TweetException("Tweet is not found.");
		} else {
			delete(entity);
		}
	}
}
