package ru.javaschool.dao;


import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.model.entities.Passenger;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Ticket;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TicketDao extends GenericDao {

    @Autowired
    private SessionFactory sessionFactory;

    /**
     * Checks that ticket was already bought such passenger on concrete schedule.
     * One passenger can have only one ticket on concrete schedule.
     *
     * @param schedule  - target schedule
     * @param passenger - target passenger.
     * @return - true, if such passenger already have ticket, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean isExistPassenger(Schedule schedule, Passenger passenger) {
        String queryString = "from Ticket where passenger='" + passenger + "' and schedule='" + schedule + "'";
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
        String queryString = "from Ticket where schedule=" + schedule;
        List<Ticket> ticketList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        int soldTickets = ticketList.size();
        int trainCapacity = schedule.getTrain().getNumberOfSeats();
        return soldTickets >= trainCapacity;
    }

    /**
     * Checks that target train departure will be more then in 10 minutes.
     *
     * @param schedule - target schedule to check.
     * @return - true if it's too late to buy tickets on it, else return false.
     */
    public boolean isTooLate(Schedule schedule) {
        Duration interval = new Duration(600 * 1000L);
        DateTime departureTime = new DateTime(schedule.getDateTrip());
        DateTime currentTime = new DateTime();
        LocalTime departTime = new LocalTime(schedule.getRoute().getStationDistances().get(0).getAppearTime()); // TODO only from the start station!
        departureTime = departureTime.plus(departTime.getMillisOfDay());
        Duration duration = new Duration(currentTime, departureTime);
        return duration.isShorterThan(interval);
    }

    /**
     * Get all registered passengers on target train
     * @param schedule - target schedule.
     * @return - list
     */
    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassengersOnTrain(Schedule schedule) {
        String queryString = "from Ticket where schedule=" + schedule;
        List<Ticket> ticketList = sessionFactory.getCurrentSession().createQuery(queryString).list();
        List<Passenger> passengerList = new ArrayList<>();
        for (Ticket t : ticketList) {
            passengerList.add(t.getPassenger());
        }
        return passengerList;
    }


}
