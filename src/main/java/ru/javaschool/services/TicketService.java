package ru.javaschool.services;


import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.StationDistanceDao;
import ru.javaschool.dao.TicketDao;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;
import ru.javaschool.model.entities.Ticket;
import ru.javaschool.model.entities.User;

import java.util.List;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private StationDistanceDao distanceDao;

    /**
     * This method implements buying ticket on target schedule by target passenger(both passes as a parameters).
     * Before check ticket credentials, check was target passenger buy ticket on any schedule,
     * if he did it, such passenger already exist in the database,
     * else, it will be create.
     * Then checks, if passenger already bought ticket on this train,
     * checks, if train is already full,
     * and checks, if it is too late to buy tickets on this schedule,
     * it must be more then 10 minutes, before departure.
     *
     * @param schedule - target schedule.
     * @param user     - target passenger.
     * @return - true if buying ticket successful, else return false.
     */
    public String buyTicket(final User user, final Schedule schedule, final String stationFrom, final String stationTo) {
        if (isCorrectDate(schedule)) {
            if (!ticketDao.isExistPassenger(schedule, user)) {
                if (!ticketDao.isTrainFull(schedule)) {
                    if (!scheduleDao.isTooLate(schedule)) {
                        Ticket ticket = new Ticket();
                        ticket.setUser(user);
                        schedule.getRoute().setStationDistances(distanceDao.getStationsInRoute(schedule.getRoute()));
                        ticket.setSchedule(schedule);
                        List<StationDistance> distanceList = schedule.getRoute().getStationDistances();

                        if (!stationFrom.equals("")) {
                            ticket.setStationFrom(stationFrom);
                        } else {
                            ticket.setStationFrom(distanceList.get(0).getStation().getName());
                        }
                        if (!stationTo.equals("")) {
                            ticket.setStationTo(stationTo);
                        } else {
                            ticket.setStationTo(distanceList.get(distanceList.size() - 1).getStation().getName());
                        }

                        ticketDao.create(ticket);
                        return "Buy ticket success!";
                    }
                    return "Sorry, it's too late, only 10 minutes before departure!";
                }
                return "Sorry, the train is full!";
            }
            return "Sorry, but you've" +
                    " already bought ticket on train " + schedule.getTrain().getName() + " at " + schedule.getDateTrip();
        }
        return "Input date in the past!";
    }

    private boolean isCorrectDate(final Schedule schedule) {
        return schedule.getDateTrip().after(new LocalDate().minusDays(1).toDate());
    }

    /**
     * Get all registered passengers on target train,
     * i.e. who bought ticket on it.
     *
     * @param schedule - target schedule.
     * @return - list of all passengers, who bought tickets on target train.
     */
    public List<User> getAllRegisteredOnTrain(final Schedule schedule) {
        return ticketDao.getAllPassengersOnTrain(schedule);
    }

    /**
     * Get ticket of target user on target schedule
     *
     * @param userId     - user identifier
     * @param scheduleId - schedule identifier
     * @return - ticket instance.
     */
    public Ticket getTicket(final Long userId, final Long scheduleId) {
        return ticketDao.getTicket(userId, scheduleId);
    }

    /**
     * Delete target ticket
     *
     * @param ticket - target ticket to delete
     */
    public void deleteTicket(final Ticket ticket) {
        ticketDao.delete(ticket);
    }

    /**
     * This method implements check of the sold tickets on target schedules in the target route.
     *
     * @param routeId - target route, to check tickets on it
     * @return - true, if there are some tickets sold, else return false.
     */
    public boolean isSoldTicketsOnSchedule(final Long routeId) {
        List<Schedule> scheduleList = scheduleDao.getScheduleListByRoute(routeId);
        if (!scheduleList.isEmpty()) {
            for (Schedule sch : scheduleList) {
                if (ticketDao.isTicketsOnTrain(sch)) {
                    return true;
                }
            }
        }
        return false;
    }
}
