/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.robvangastel.kwetter.dao.facade;

import com.robvangastel.kwetter.dao.AbstractJPADao;
import com.robvangastel.kwetter.dao.IUserDao;
import com.robvangastel.kwetter.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rob
 */
public class UserDaoJPAImpl extends AbstractJPADao<User> implements IUserDao {

    @PersistenceContext
    private EntityManager entityManager;
        
    public UserDaoJPAImpl() {
        super();
        setClassObj(User.class);
    }
}
