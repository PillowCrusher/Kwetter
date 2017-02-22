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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Rob
 */

@Stateless @JPA
public class UserDaoJPAImpl extends AbstractJPADao<User> implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;
        
    public UserDaoJPAImpl() {
        super();
        setClassObj(User.class);
    }

    @Override
    public User findByUsername(String username) {
        Query query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.Username = :username")
                .setParameter("username", username);
        return (User) query.getSingleResult();
    }
}
