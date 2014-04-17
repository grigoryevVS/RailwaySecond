package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Generic ao layer class, for implementing route actions, such
 * as create, insert, delete, update.
 * @param <Route>
 * @param <Long>
 */
@Repository
public class RouteDao<Route, Long> extends GenericDao{

    @Autowired
    private SessionFactory sessionFactory;

}
