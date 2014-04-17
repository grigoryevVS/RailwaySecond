package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Station;

import java.util.List;

@Repository
public class StationDao extends GenericDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Find existence of station passed as parameter in the database
     *
     * @param station - concrete station to check its existence
     * @return - true if it is exist, false another way.
     */
    @SuppressWarnings("unchecked")
    public boolean isStationExist(Station station) {
        List<Station> stationList = (List<Station>) sessionFactory.getCurrentSession().createQuery("from Station where name='"+ station.getName() + "'").list();
        return (!stationList.isEmpty());
    }

}
