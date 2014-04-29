package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.RouteDao;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.TrainDao;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.dto.ScheduleFilterDto;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;
import ru.javaschool.model.entities.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private TrainDao trainDao;

    /**
     * Get list of schedules.
     *
     * @return - list of schedules.
     */
    @SuppressWarnings("unchecked")
    public List<ScheduleDto> getAllSchedule() {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        List<Schedule> scheduleList = scheduleDao.findAll(Schedule.class);
        if (!scheduleList.isEmpty()) {
            for (Schedule sch : scheduleList) {
                // TODO check date - if old, delete schedule!
                scheduleDtos.add(new ScheduleDto(sch));
            }
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
        schedule.setTicketList(new ArrayList<Ticket>());
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
    public boolean deleteSchedule(Long key) {
        Schedule schedule = scheduleDao.findByPK(Schedule.class, key);
        if (schedule.getTicketList().isEmpty()) {
            scheduleDao.delete(schedule);
            return true;
        }
        return false;
    }

    /**
     * Update schedule.
     *
     * @param schedule - target schedule to update.
     * @return - true if update successful, else return false.
     */
    public boolean updateSchedule(Schedule schedule) {
        if (schedule.getTicketList().isEmpty()) {
            scheduleDao.update(schedule);
            return true;
        }
        return false;
    }

    /**
     * Find target schedule by identifier
     *
     * @param scheduleId - schedule id.
     * @return - schedule instance
     */
    public Schedule findSchedule(Long scheduleId) {
        return scheduleDao.findByPK(Schedule.class, scheduleId);
    }

    /**
     * Set instance of schedule and create it, from scheduleDto object.
     *
     * @param scheduleDto - scheduleDto object
     * @return - true, if creating target schedule successful, else return false.
     */
    public boolean wrapAndCreateSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        schedule.setRoute(routeDao.findByName(scheduleDto.getRouteName()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            schedule.setDateTrip(sdf.parse(scheduleDto.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        schedule.setTrain(trainDao.findByName(scheduleDto.getTrainName()));
        schedule.setTicketList(new ArrayList<Ticket>());
        schedule = scheduleDao.create(schedule);
        return schedule != null;

    }

    /**
     * This method looks, which filter were initialized and gets schedule list with that filter.
     *
     * @param filter - station from and/or station to going train, and date when it is going.
     * @return - list of schedule, which succeed this filter.
     */
    public List<ScheduleDto> getFilteredSchedule(ScheduleFilterDto filter) {

        List<Schedule> scheduleListDate;

        // first of all, checks date, do we need to set this constraint, and getting first intermediate list.
        if (!filter.getDate().equals("")) {
            scheduleListDate = scheduleDao.getScheduleListByDate(filter.getDate());
        } else {
            scheduleListDate = scheduleDao.findAll(Schedule.class);
        }
        // for second check, if we set station from.
        if (!filter.getStationFromName().equals("")) {
            List<Schedule> res = new ArrayList<Schedule>();
            for (Schedule schedule : scheduleListDate) {
                boolean flag = false;
                // TODO get stationDistances for route
                List<StationDistance> distanceList = schedule.getRoute().getStationDistances();
                for (StationDistance s : distanceList)
                    // check conditions, that its not the last station in the route, and its equal to the constraint
                    if (s.getSequenceNumber() != distanceList.size() &&
                            s.getStation().getName().equals(filter.getStationFromName()))
                        flag = true;
                if (flag)
                    res.add(schedule);
            }
            scheduleListDate = res;
        }
        // the last check constraint of the station to.
        if (!filter.getStationToName().equals("")) {
            List<Schedule> res = new ArrayList<Schedule>();
            for (Schedule schedule : scheduleListDate) {
                boolean flag = false;
                List<StationDistance> distanceList = schedule.getRoute().getStationDistances();
                for (StationDistance s : distanceList)
                    // check conditions that its not the first station in the route and its equal to the constraint
                    if (s.getSequenceNumber() > 1 && s.getStation().getName().equals(filter.getStationToName()))
                        flag = true;
                if (flag)
                    res.add(schedule);
            }
            scheduleListDate = res;
        }
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        if (!scheduleListDate.isEmpty()) {
            for (Schedule sch : scheduleListDate) {
                scheduleDtos.add(new ScheduleDto(sch));
            }
        }
        return scheduleDtos;

    }
}
