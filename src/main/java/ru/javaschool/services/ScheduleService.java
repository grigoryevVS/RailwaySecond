package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.model.entities.Schedule;

import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

//    public List<Schedule> getRevisedScheduleList(ScheduleConstraints constraints) {
//       // TODO impl
//    }

    /**
     * Get list of schedules.
     *
     * @return - list of schedules.
     */
    @SuppressWarnings("unchecked")
    public List<Schedule> getAllSchedule() {
        return scheduleDao.findAll(Schedule.class);
    }

    /**
     * Find schedule in the database, by its identifier.
     *
     * @param key - primary key( identifier )
     * @return - instance of target schedule.
     */
    @SuppressWarnings("unchecked")
    public Schedule findByPk(Long key) {
        return (Schedule) scheduleDao.findByPK(Schedule.class, key);
    }

    /**
     * Create new schedule.
     *
     * @param schedule - target schedule to create.
     */
    @SuppressWarnings("unchecked")
    public void createSchedule(Schedule schedule) {
        scheduleDao.create(schedule);
    }

    /**
     * Delete target schedule,
     * if there are no bought tickets on it.
     * If it is, we can't delete it.
     *
     * @param key - identifier of target schedule.
     * @return - true, if delete successful, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean deleteSchedule(Long key) {
        Schedule schedule = (Schedule) scheduleDao.findByPK(Schedule.class, key);
        if (schedule.getTicketList().isEmpty()) {
            scheduleDao.delete(schedule);
            return true;
        }
        return false;
    }

}
