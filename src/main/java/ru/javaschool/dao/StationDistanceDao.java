package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Station;

import java.util.List;

@Repository
public class StationDistanceDao extends GenericDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Check is target station included in some route, by stationDistances.
     *
     * @param key - identifier of target station.
     * @return - true, if such station included into some route, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isStationDistance(Long key) {
        String queryString = "from StationDistance where station.stationId=" + key;
        List<Station> stationList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!stationList.isEmpty());
    }
}
