package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.PassengerDao;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.TicketDao;
import ru.javaschool.model.entities.Passenger;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Ticket;

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
     * This method implements buying ticket on target schedule by target passenger.
     * Before check ticket credentials, check was passenger buy ticket someday,
     * if he did it, such passenger already exist in the database,
     * else, it will be create.
     * First checks, if passenger already bought ticket on this train,
     * then checks, if train is already full,
     * and checks, if it is too late to buy tickets on this schedule,
     * it must be more then 10 minutes, before departure.
     *
     * @param scheduleId  - target schedules identifier.
     * @param passenger - target passenger.
     * @return - true if buying ticket successful, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean buyTicket(Long scheduleId, Passenger passenger) {
        Schedule schedule = (Schedule) scheduleDao.findByPK(Schedule.class, scheduleId);
        if (!passengerDao.isRegistrationPass(passenger)) {
            passengerDao.create(passenger);
        }
        if (!ticketDao.isExistPassenger(schedule, passenger)) {
            if (!ticketDao.isTrainFull(schedule)) {
                if (!ticketDao.isTooLate(schedule)) {
                    Ticket ticket = new Ticket();
                    ticket.setPassenger(passenger);
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
    public List<Passenger> getAllRegisteredOnTrain(Long scheduleId) {
        Schedule schedule = (Schedule) scheduleDao.findByPK(Schedule.class, scheduleId);
        return ticketDao.getAllPassengersOnTrain(schedule);
    }
}
