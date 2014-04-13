package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Station;

import java.io.Serializable;
import java.util.List;

@Repository
public class StationDao<T extends Serializable, PK extends Serializable> {

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
    public List<Station> findAll(){
        return sessionFactory.getCurrentSession().createQuery("FROM Station").list();
    }


    @SuppressWarnings("unchecked")
    public T update(final T t){
        return (T) sessionFactory.getCurrentSession().merge(t);
    }

    public void delete(final T t){
        sessionFactory.getCurrentSession().delete(t);
    }

    public void deleteObject(Class<T> entity, final PK key) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(entity,key));
    }

}
