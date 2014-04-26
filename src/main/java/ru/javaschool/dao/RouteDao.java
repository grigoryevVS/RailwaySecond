package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Route;

import java.util.List;

/**
 * Generic ao layer class, for implementing route actions, such
 * as create, insert, delete, update.
 */
@Repository
public class RouteDao extends GenericDao<Route, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Find existence of route, which name passed as parameter, in the database
     *
     * @param name - concrete routes name to check its existence
     * @return - true if it is exist, false another way.
     */
    @SuppressWarnings("unchecked")
    public boolean isRouteExist(String name) {
        List<Route> routeList = (List<Route>) sessionFactory.getCurrentSession().createQuery("from Route where title='" + name + "'").list();
        return (!routeList.isEmpty());
    }

    /**
     * To create schedule, we need to find target route of it.
     * @param routeName - title of route
     * @return -  route instance.
     */
    public Route findByName(String routeName) {
        return (Route) sessionFactory.getCurrentSession().createQuery("from Route where title='" + routeName + "'").uniqueResult();
    }
}

//    /**
//     * Getting schedule of all trains at current station, which we are giving as a parameter
//     *
//     * @param stationDepart - station, from which we need to give a schedule.
//     * @return - returns List of trains.
//     */
//    public Long getStationDeparture(Long stationDepart) {
//
//        Query query = getEm().createQuery("select s.stationId from Station s where s.stationId =:departure");
//        query.setParameter("departure", stationDepart);
//        return (Long) query.getSingleResult();
//    }
