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
import com.robvangastel.kwetter.exception.UserException;

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
	public User create(User entity) throws UserException {
		checkCreate(entity);
		entityManager.persist(entity);
		return entity;
	}

	public User update(User entity) throws UserException {
		checkUpdate(entity);
		return entityManager.merge(entity);
	}

	@Override
	public User updateRole(User entity) throws UserException {
		return entityManager.merge(entity);
	}

	@Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username")
                .setParameter("username", username);

	    if(query.getResultList().isEmpty()) {
		    return null;
	    } else {
		    return (User) query.getSingleResult();
	    }
    }

    @Override
    public User findByEmail(String email) {
	    Query query = entityManager.createQuery(
			    "SELECT u FROM User u WHERE u.email = :email")
			    .setParameter("email", email);

	    if(query.getResultList().isEmpty()) {
		    return null;
	    } else {
		    return (User) query.getSingleResult();
	    }
    }

	@Override
	public void deleteById(long id) throws UserException {
		final User entity = findById(id);

		if(entity == null) {
			throw new UserException("User is not found.");
		} else {
			delete(entity);
		}
	}

	private void checkCreate(User entity) throws UserException {
		if(entity.getUsername().length() > 20 || entity.getUsername().length() < 0 || entity.getUsername().isEmpty()) {
			throw new UserException("Username has an invalid length");
		}

		if(entity.getPassword().length() > 20 || entity.getPassword().length() < 0 || entity.getPassword().isEmpty()) {
			throw new UserException("Email has an invalid length");
		}
	}

	private void checkUpdate(User entity) throws UserException {
		if(entity.getUsername().length() > 20 || entity.getUsername().length() < 0 || entity.getUsername().isEmpty()) {
			throw new UserException("Username has an invalid length");
		}

		if(entity.getPassword().length() > 20 || entity.getPassword().length() < 0 || entity.getPassword().isEmpty()) {
			throw new UserException("Email has an invalid length");
		}

		if(entity.getBio().length() > 160 || entity.getBio().length() < 0 || entity.getBio().isEmpty()) {
			throw new UserException("Email has an invalid length");
		}
	}
}
