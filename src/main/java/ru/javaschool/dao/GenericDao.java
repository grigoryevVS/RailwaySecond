package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract generic class with crud operations
 *
 * @param <T>
 * @param <PK>
 */
@Repository
public abstract class GenericDao<T extends Serializable, PK extends Serializable> {

    @Autowired
    private SessionFactory sessionFactory;

    public T create(final T t) {
        sessionFactory.getCurrentSession().persist(t);
        return t;
    }

    @SuppressWarnings("unchecked")
    public T findByPK(Class<T> entity, final PK key) {
        return (T) sessionFactory.getCurrentSession().get(entity, key);
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(Class<T> entityClass) {
        return sessionFactory.getCurrentSession().createQuery("FROM " + entityClass.getSimpleName()).list();
    }


    @SuppressWarnings("unchecked")
    public void update(final T t) {
        sessionFactory.getCurrentSession().merge(t);
    }

    public void delete(final T t) {
        sessionFactory.getCurrentSession().delete(t);
    }

    public void deleteObject(Class<T> entity, final PK key) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(entity, key));
    }

}
