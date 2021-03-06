package ru.javaschool.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.Station;
import ru.javaschool.model.entities.StationDistance;

import java.util.List;

@Repository
public class StationDistanceDao extends GenericDao<StationDistance, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Check, is target station included in some route, by stationDistances.
     * Help method for StationService method (deleteStation).
     *
     * @param key - identifier of target station.
     * @return - true, if such station included into some route, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isStationDistance(final Long key) {
        String queryString = "from StationDistance where station.stationId=" + key;
        List<Station> stationList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!stationList.isEmpty());
    }

    /**
     * Check, is target stationDistance is already exist in the current route.
     *
     * @param sequenceNumber - number of target station in the route distance.
     * @param route          - target route, which needs to check.
     * @return - true, if stationDistance exist in the route, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isExistInRoute(final Long sequenceNumber, final Route route) {
        String queryString = "from StationDistance where route='" + route + "'" + "and sequenceNumber=" + sequenceNumber;
        List<StationDistance> distanceList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!distanceList.isEmpty());
    }

    /**
     * Get station distances, which are included in target route.
     *
     * @param route - target route
     * @return - list of station distances.
     */
    @SuppressWarnings("unchecked")
    public List<StationDistance> getStationsInRoute(final Route route) {
        return sessionFactory.getCurrentSession().createQuery("from StationDistance where route='" + route + "'").list();
    }

    /**
     * Get stationDistance by name of target station
     *
     * @param arrivalStationName - arrival station name
     * @return - stationDistance instance.
     */
    public StationDistance getStationByName(final String arrivalStationName) {
        return (StationDistance) sessionFactory.getCurrentSession().createQuery("from StationDistance where station.name='" + arrivalStationName + "'").uniqueResult();
    }

    /**
     * Method delete for route update
     * @param distance - station distance need to be deleted
     */
    public void deleteToUpd(final StationDistance distance) {
            sessionFactory.getCurrentSession().delete(distance);
            sessionFactory.getCurrentSession().flush();
            sessionFactory.getCurrentSession().clear();
    }
}
