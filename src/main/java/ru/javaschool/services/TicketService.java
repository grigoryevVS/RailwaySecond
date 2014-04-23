package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.PassengerDao;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.TicketDao;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Ticket;
import ru.javaschool.model.entities.User;

import java.util.List;

@Service
@Transactional
public class TicketService {

    @Autowired
    private TicketDao ticketDao;

    @Autowired
    private PassengerDao passengerDao;

    @Autowired
    private ScheduleDao scheduleDao;

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
     * @param scheduleId  - target schedules identifier.
     * @param user - target passenger.
     * @return - true if buying ticket successful, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean buyTicket(Long scheduleId, User user) {
        Schedule schedule = (Schedule) scheduleDao.findByPK(Schedule.class, scheduleId);
        if (!passengerDao.isRegistrationPass(user)) {
            passengerDao.create(user);
        }
        if (!ticketDao.isExistPassenger(schedule, user)) {
            if (!ticketDao.isTrainFull(schedule)) {
                if (!ticketDao.isTooLate(schedule)) {
                    Ticket ticket = new Ticket();
                    ticket.setUser(user);
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
    @SuppressWarnings("unchecked")
    public List<User> getAllRegisteredOnTrain(Long scheduleId) {
        Schedule schedule = (Schedule) scheduleDao.findByPK(Schedule.class, scheduleId);
        return ticketDao.getAllPassengersOnTrain(schedule);
    }
}
