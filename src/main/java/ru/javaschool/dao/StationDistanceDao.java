package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Station;

import java.util.List;

@Repository
public class StationDistanceDao {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public boolean isStationDistance(Station station) {
        List<Station> stationList = sessionFactory.getCurrentSession().createQuery("from StationDistance where station.stationId=" + station.getStationId()).list();
        return (!stationList.isEmpty());
    }
}
