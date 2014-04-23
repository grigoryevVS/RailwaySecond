package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Station;

import java.util.List;

@Repository
public class StationDao extends GenericDao<Station, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Find existence of station ,which name passed as parameter, in the database
     *
     * @param name - concrete stations name to check its existence
     * @return - true if it is exist, false another way.
     */
    @SuppressWarnings("unchecked")
    public boolean isStationExist(String name) {
        List<Station> stationList = (List<Station>) sessionFactory.getCurrentSession().createQuery("from Station where name='" + name + "'").list();
        return (!stationList.isEmpty());
    }

    /**
     * Get station by name from the database.
     *
     * @param name - target name
     * @return - instance of target station.
     */
    public Station findByName(String name) {
        return (Station) sessionFactory.getCurrentSession().createQuery("from Station where name='" + name + "'").uniqueResult();

    }

}
