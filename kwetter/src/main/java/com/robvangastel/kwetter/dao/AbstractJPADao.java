package com.robvangastel.kwetter.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rob
 * @param <T>
 */

public abstract class AbstractJPADao<T extends Serializable> {

    private Class<T> classObj;

    @PersistenceContext(unitName ="KwetterPU")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) { this.entityManager = entityManager; }

    public final void setClassObj(final Class<T> classObjToSet) {
        this.classObj = classObjToSet;
    }

    public T findById(final long id) {
        return entityManager.find(classObj, id);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        return entityManager.createQuery("from " + classObj.getName()).getResultList();
    }

    public void delete(final T entity) {
        entityManager.remove(entity);
    }
}