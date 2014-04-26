package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.StationDistanceDao;
import ru.javaschool.dao.TicketDao;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Ticket;
import ru.javaschool.model.entities.User;

import java.util.List;

//import ru.javaschool.dao.PassengerDao;

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
    public boolean buyTicket(User user, Schedule schedule) {
        if (!ticketDao.isExistPassenger(schedule, user)) {
            if (!ticketDao.isTrainFull(schedule)) {
                if (!scheduleDao.isTooLate(schedule)) {
                    Ticket ticket = new Ticket();
                    ticket.setUser(user);
                    schedule.getRoute().setStationDistances(distanceDao.getStationsInRoute(schedule.getRoute()));
                    ticket.setSchedule(schedule);
                    ticketDao.create(ticket);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Get all registered passengers on target train,
     * i.e. who bought ticket on it.
     *
     * @param scheduleId - target schedules primary key.
     * @return - list of all passengers, who bought tickets on target train.
     */
    public List<User> getAllRegisteredOnTrain(Long scheduleId) {
        Schedule schedule = scheduleDao.findByPK(Schedule.class, scheduleId);
        return ticketDao.getAllPassengersOnTrain(schedule);
    }
}
