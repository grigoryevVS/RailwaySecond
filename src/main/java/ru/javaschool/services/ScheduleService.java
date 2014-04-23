package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.model.entities.Schedule;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

//    public List<Schedule> getRevisedScheduleList(ScheduleConstraints constraints) {
//
//    }

    /**
     * Get list of schedules.
     *
     * @return - list of schedules.
     */
    @SuppressWarnings("unchecked")
    public List<ScheduleDto> getAllSchedule() {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        List<Schedule> scheduleList = scheduleDao.findAll(Schedule.class);
        for (Schedule sch : scheduleList) {
            scheduleDtos.add(new ScheduleDto(sch));
        }
        return scheduleDtos;

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

    /**
     * Update schedule.
     * @param schedule - target schedule to update.
     * @return - true if update successful, else return false.
     */
    public boolean updateSchedule(Schedule schedule) {

        return true;
    }

    public Schedule findSchedule(Long scheduleId) {
        return null;
    }
}
