package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Ticket;
import ru.javaschool.model.entities.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDao extends GenericDao<Ticket, Long> {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Checks that ticket was already bought such passenger on concrete schedule.
     * One passenger can have only one ticket on concrete schedule.
     *
     * @param schedule - target schedule
     * @param user     - target passenger.
     * @return - true, if such passenger already have ticket, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isExistPassenger(Schedule schedule, User user) {
        String queryString = "from Ticket where user.userId=" + user.getUserId() + " and schedule.scheduleId=" + schedule.getScheduleId();
        List<Ticket> ticketList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        return (!ticketList.isEmpty());
    }

    /**
     * Checks that target train, in this schedule is already full.
     *
     * @param schedule - target schedule.
     * @return - true if train full, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isTrainFull(Schedule schedule) {
        String queryString = "from Ticket where schedule.scheduleId=" + schedule.getScheduleId();
        List<Ticket> ticketList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        int soldTickets = ticketList.size();
        int trainCapacity = schedule.getTrain().getNumberOfSeats();
        return soldTickets >= trainCapacity;
    }

    /**
     * Get all registered passengers on target train
     *
     * @param schedule - target schedule.
     * @return - list
     */
    @SuppressWarnings("unchecked")
    public List<User> getAllPassengersOnTrain(Schedule schedule) {
        String queryString = "from Ticket where schedule.scheduleId=" + schedule.getScheduleId();
        List<Ticket> ticketList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        List<User> passengerList = new ArrayList<>();
        for (Ticket t : ticketList) {
            passengerList.add(t.getUser());
        }
        return passengerList;
    }

    /**
     * Get ticket by user identifier and schedule identifier
     *
     * @param userId     - target userId
     * @param scheduleId - target scheduleId
     * @return - instance of ticket.
     */
    public Ticket getTicket(Long userId, Long scheduleId) {
        String queryString = "from Ticket where user.userId=" + userId + " and schedule.scheduleId=" + scheduleId;
        return (Ticket) sessionFactory.getCurrentSession().createQuery(queryString).uniqueResult();
    }

    /**
     * Get users ticket
     *
     * @param userId - target user identifier
     * @return - list of tickets
     */
    @SuppressWarnings("unchecked")
    public List<Ticket> findByUser(Long userId) {
        return sessionFactory.getCurrentSession().createQuery("from Ticket where user.userId=" + userId).list();
    }

    /**
     * Get ticket list by schedule identifier
     *
     * @param sch - target schedule
     * @return - list of tickets
     */
    @SuppressWarnings("unchecked")
    public List<Ticket> findBySchedule(Schedule sch) {
        return sessionFactory.getCurrentSession().createQuery("from Ticket where schedule.scheduleId=" + sch.getScheduleId()).list();
    }
}
