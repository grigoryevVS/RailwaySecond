package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;
import ru.javaschool.model.entities.Train;

import java.util.*;

@Repository
public class ScheduleDao extends GenericDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get list of schedules, with date constraints.
     * @param date - target constraints
     * @return - list of schedules.
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleListByDate(Date date) {
        return sessionFactory.getCurrentSession().createQuery("from Schedule where dateTrip=" + date).list();
    }

    /**
     * This method getting set of schedules, with constraints from and/or to stations.
     *
     * @param from - station from will depart target train.
     * @param to - station where will arrive target train.
     * @return - set of schedules, which are satisfy the conditions.
     */
    public Set<Schedule> getScheduleSetByStation(String from, String to) {  // TODO wrong, passed parameters from first part!

        List<Schedule> scheduleList = new ArrayList<Schedule>();
        Set<Schedule> resultSet = new HashSet<Schedule>();
        String stationFrom = "";
        String stationTo = "";
        if (!(to.equals("not selected"))) {
            for (Schedule s : scheduleList) {
                List<StationDistance> distanceList = s.getRoute().getStationDistances();
                for (StationDistance sd : distanceList) {
                    if (stationFrom.equals("") && sd.getStation().getName().equals(from)) {
                        stationFrom = sd.getStation().getName();
                    }
                    if (stationTo.equals("") && sd.getStation().getName().equals(to)) {
                        stationTo = sd.getStation().getName();
                    }
                }
                if (!stationFrom.equals("") && (!stationTo.equals(""))) {
                    resultSet.add(s);
                }
            }
        } else {
            for (Schedule s : scheduleList) {
                List<StationDistance> distanceList = s.getRoute().getStationDistances();
                for (StationDistance sd : distanceList) {
                    if (stationFrom.equals("") && sd.getStation().getName().equals(from)) {
                        stationFrom = sd.getStation().getName();
                    }
                }
                if (!stationFrom.equals("")) {
                    resultSet.add(s);
                }
            }
        }
        return resultSet;
    }

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
