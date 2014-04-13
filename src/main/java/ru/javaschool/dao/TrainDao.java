package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Train;

import java.util.List;

@Repository
public class TrainDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Train create(final Train train) {
        sessionFactory.getCurrentSession().persist(train);
        return train;
    }

    @SuppressWarnings("unchecked")
    public Train findByPK(Class<Train> entity, final Long key) {
        return (Train) sessionFactory.getCurrentSession().get(entity, key);
    }

    @SuppressWarnings("unchecked")
    public List<Train> findAll(){
        return sessionFactory.getCurrentSession().createQuery("FROM Train").list();
    }


    @SuppressWarnings("unchecked")
    public Train update(final Train train){
        return (Train) sessionFactory.getCurrentSession().merge(train);
    }

    public void delete(final Train train){
        sessionFactory.getCurrentSession().delete(train);
    }

    public void deleteObject(Class<Train> entity, final Long key) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(entity,key));
    }
}
