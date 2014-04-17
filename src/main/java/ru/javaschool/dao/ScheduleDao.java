package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.Train;

import java.util.List;

@Repository
public class ScheduleDao extends GenericDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Checks existence of train in schedule.
     *
     * @param key - trainId, by which will be execute search.
     * @return - true, if it was found, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isTrainInSchedule(Long key) {
        List<Train> trainList = sessionFactory.getCurrentSession().createQuery("from Schedule where train.trainId=" + key).list();
        return (!trainList.isEmpty());
    }

    /**
     * Checks existence of route in schedule.
     *
     * @param key - routeId, by which will be execute search.
     * @return - true, if it was found, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isRouteInSchedule(Long key) {
        List<Route> routeList = sessionFactory.getCurrentSession().createQuery("from Schedule where route.routeId=" + key).list();
        return (!routeList.isEmpty());
    }
}
