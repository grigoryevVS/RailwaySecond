package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;

import java.util.*;

@Repository
public class ScheduleDao extends GenericDao<Schedule, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Get list of schedules, with date constraints.
     *
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
     * @param to   - station where will arrive target train.
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
     * Used by trainService method deleteTrain - if exist, it can't be deleted.
     *
     * @param key - trainId, by which will be execute search.
     * @return - true, if it was found, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isTrainInSchedule(Long key) {
        List<Schedule> scheduleList = sessionFactory.getCurrentSession().createQuery("from Schedule where train.trainId=" + key).list();
        return (!scheduleList.isEmpty());
    }

    /**
     * Get schedule list, which contains target route
     * method need to choose, what schedules need to be
     * updated, if target route was updated.
     * @param routeId - identifier of target route
     * @return - list of concrete schedules to update.
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleListByRoute(Long routeId){
        return sessionFactory.getCurrentSession().createQuery("from Schedule where route.routeId=" + routeId).list();
    }

    /**
     * Help method, to check, if target schedule list
     * contains schedules, on which tickets has been bought already.
     * @param scheduleList - target schedule list.
     * @return - true, if we can update schedules,
     *          false - if someone already bought ticket on any of items of target schedule list.
     */
    public boolean isTicketListEmpty(List<Schedule> scheduleList) {
        for (Schedule sch : scheduleList) {
            if(!sch.getTicketList().isEmpty()){
                return false;
            }
        }
        return true;
    }
}
