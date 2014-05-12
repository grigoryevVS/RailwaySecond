package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;

import java.util.List;

@Repository
public class ScheduleDao extends GenericDao<Schedule, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private StationDistanceDao distanceDao;

    /**
     * Get list of schedules, with date constraints.
     *
     * @param date - target constraints
     * @return - list of schedules.
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleListByDate(final String date) {
        return sessionFactory.getCurrentSession().createQuery("from Schedule where DATE_FORMAT(dateTrip,'%Y-%m-%d')='" + date + "'").list();
    }

    /**
     * Checks existence of train in schedule.
     * Used by trainService method deleteTrain - if exist, it can't be deleted.
     *
     * @param key - trainId, by which will be execute search.
     * @return - true, if it was found, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isTrainInSchedule(final Long key) {
        List<Schedule> scheduleList = sessionFactory.getCurrentSession().createQuery("from Schedule where train.trainId=" + key).list();
        return (!scheduleList.isEmpty());
    }

    /**
     * Get schedule list, which contains target route
     * method need to choose, what schedules need to be
     * updated, if target route was updated.
     *
     * @param routeId - identifier of target route
     * @return - list of concrete schedules to update.
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleListByRoute(final Long routeId) {
        return sessionFactory.getCurrentSession().createQuery("from Schedule where route.routeId=" + routeId).list();
    }

    /**
     * Help method, to check, if target schedule list
     * contains schedules, on which tickets has been bought already.
     *
     * @param scheduleList - target schedule list.
     * @return - true, if we can update schedules,
     * false - if someone already bought ticket on any of items of target schedule list.
     */
    public boolean isTicketListEmpty(final List<Schedule> scheduleList) {
        for (Schedule sch : scheduleList) {
            if (!sch.getTicketList().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks that target train departure will be more then in 10 minutes.
     *
     * @param schedule - target schedule to check.
     * @return - true if it's too late to buy tickets on it, else return false.
     */
    public boolean isTooLate(final Schedule schedule) {

        Duration interval = new Duration(600 * 1000L);
        DateTime departureTime = new DateTime(schedule.getDateTrip());
        DateTime currentTime = new DateTime();
        List<StationDistance> distanceList = distanceDao.getStationsInRoute(schedule.getRoute());
        LocalTime departTime = new LocalTime(distanceList.get(0).getAppearTime());
        departureTime = departureTime.plus(departTime.getMillisOfDay());
        Duration duration = new Duration(currentTime, departureTime);
        return duration.isShorterThan(interval);
    }

    /**
     * Get list of schedule by train
     *
     * @param trainId - train identifier
     * @return - list of schedule
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleListByTrain(final Long trainId) {
        return sessionFactory.getCurrentSession().createQuery("from Schedule where train.trainId=" + trainId).list();
    }

    /**
     * Check of uniqueness schedule. Need to be dateTrip, trains name and route title unique together
     *
     * @param scheduleDto - target dto instance
     * @return - true, if schedule unique within target day. else return false
     */
    @SuppressWarnings("unchecked")
    public boolean isUniqueSchedule(final ScheduleDto scheduleDto) {
        List<Schedule> scheduleList = sessionFactory.getCurrentSession().createQuery("from Schedule where dateTrip='" + scheduleDto.getDate() +
                "' and train.name='" + scheduleDto.getTrainName() + "' and route.title='" + scheduleDto.getRouteName() + "'").list();
        return scheduleList.isEmpty();
    }

    /**
     * Get list of schedule by train
     *
     * @param trainName - target train name
     * @return - list of schedule
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getScheduleListTodayByTrain(final String trainName) {
        String queryString = "from Schedule where train.name='" + trainName + "'";
        return sessionFactory.getCurrentSession().createQuery(queryString).list();

    }
}
