/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao.facade;

import com.robvangastel.kwetter.dao.AbstractJPADao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.dao.JPA;
import com.robvangastel.kwetter.domain.User;
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
public class UserDaoJPAImpl extends AbstractJPADao<User> implements IUserDao {

    @PersistenceContext(unitName ="KwetterPU")
    private EntityManager entityManager;
        
    public UserDaoJPAImpl() {
        super();
        setClassObj(User.class);
    }

	public UserDaoJPAImpl(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
		setClassObj(User.class);
	}

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.Username = :username")
                .setParameter("username", username);
        return (User) query.getSingleResult();
    }

    @Override
    public User findByEmail(String email) {
	    Query query = entityManager.createQuery(
			    "SELECT u FROM User u WHERE u.Email = :email")
			    .setParameter("email", email);
	    return (User) query.getSingleResult();
    }
}
