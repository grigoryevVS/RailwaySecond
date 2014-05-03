package ru.javaschool.services;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.RouteDao;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.StationDistanceDao;
import ru.javaschool.dao.TrainDao;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.dto.ScheduleFilterDto;
import ru.javaschool.model.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private StationDistanceDao distanceDao;

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
                if (sch.getDateTrip().after(new LocalDate().minusDays(1).toDate())) {
                    scheduleDtos.add(new ScheduleDto(sch));
                }
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
     * @param schedule - target schedule.
     * @return - true, if delete successful, else return false.
     */
    public boolean deleteSchedule(Schedule schedule) {
        if (schedule.getTicketList().isEmpty()) {   // && проверка что расписание уже не прошлое, если прошлое то делит вместе с билетами.
            scheduleDao.delete(schedule);
            return true;
        }
        return false;
    }

    /**
     * Update schedule.
     *
     * @param scheduleDto - target schedule to update.
     * @return - true if update successful, else return false.
     */
    public String updateSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleDao.findByPK(Schedule.class, scheduleDto.getId());
        if (schedule == null) {
            return "Wrong sch!";
        }
        if (schedule.getTicketList().isEmpty()) {
            Route route = routeDao.findByName(scheduleDto.getRouteName());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateToCheck;
            try {
                dateToCheck = sdf.parse(scheduleDto.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
                return "Wrong date format!";
            }
            StationDistance sd = distanceDao.getStationsInRoute(route).get(0);
            Date timeToCheck = sd.getAppearTime();
            if (isCorrectDate(dateToCheck, timeToCheck)) {
                if (isUniqueWithinDay(scheduleDto)) {

                    String arrivalCheck = getArrivalStation(scheduleDto);
                    String departureCheck = getDepartureStation(scheduleDto);
                    if (arrivalCheck.equals("none") ||departureCheck.equals("none")) {
                        schedule.setRoute(route);
                        schedule.setDateTrip(dateToCheck);
                        schedule.setTrain(trainDao.findByName(scheduleDto.getTrainName()));
                        schedule.setTicketList(new ArrayList<Ticket>());
                        scheduleDao.update(schedule);
                        return "Success!";
                    }
                    return "Train can go to the " + departureCheck + " or from the " + arrivalCheck + " !";
                }
                return "Such schedule already exist in this day.";
            }
            return "Date must be in the future!";
        }
        return "There are some tickets on it!";
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
    public String unWrapAndCreateSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule();
        Route route = routeDao.findByName(scheduleDto.getRouteName());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateToCheck;
        try {
            dateToCheck = sdf.parse(scheduleDto.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return "Wrong date format!";
        }
        StationDistance sd = distanceDao.getStationsInRoute(route).get(0);
        Date timeToCheck = sd.getAppearTime();
        if (isCorrectDate(dateToCheck, timeToCheck)) {
            if (isUniqueWithinDay(scheduleDto)) {
                String arrivalCheck = getArrivalStation(scheduleDto);
                String departureCheck = getDepartureStation(scheduleDto);
                if (arrivalCheck.equals("none") || departureCheck.equals("none")) {
                    schedule.setRoute(route);
                    schedule.setDateTrip(dateToCheck);
                    schedule.setTrain(trainDao.findByName(scheduleDto.getTrainName()));
                    schedule.setTicketList(new ArrayList<Ticket>());
                    scheduleDao.create(schedule);
                    return  "Success!";
                }
                return "Train can go to the " + departureCheck + " or from the " + arrivalCheck + " !";
            }
            return "Such schedule already exist in this day.";
        }
        return "Date must be in the future!";
    }

    /**
     * This method implements validation of schedule.
     * Date, train and time validity.
     *
     * @param scheduleDto - target dto object
     * @return - result message
     */
    public String getArrivalStation(ScheduleDto scheduleDto) {
        // find all schedule with target train
        List<Schedule> schedules = scheduleDao.getScheduleListTodayByTrain(scheduleDto.getTrainName());
        Route route = routeDao.findByName(scheduleDto.getRouteName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // target date
        Date dateToCheck;
        try {
            dateToCheck = sdf.parse(scheduleDto.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return "Wrong date format!";
        }
        List<Schedule> scheduleList = new ArrayList<>();
        // if departure day equals to the departure day of target schedule add to result scheduleList
        if (!schedules.isEmpty()) {
            for (Schedule sch : schedules) {
                if (dateToCheck.compareTo(sch.getDateTrip()) == 0) {
                    scheduleList.add(sch);
                }
            }
        }
        // if schedules with target train not exist, return that it is.
        else {
            return "none";
        }
        StationDistance sd = distanceDao.getStationsInRoute(route).get(0);  // target  departure station
        Date targetDateFrom = sd.getAppearTime();
        // if there were no schedules with this train within target day return that it is.
        if (scheduleList.isEmpty()) {
            return "none";
        }
        // check if arrival time of previous stations after target departure time, if it is wrong, save name of the previous station.
        else {
            Date controlDate = null;
            String resultStation = "Wrong!";
            for (Schedule schedule : scheduleList) {
                List<StationDistance> distanceList = distanceDao.getStationsInRoute(schedule.getRoute());
                StationDistance previousStationTo = distanceList.get(distanceList.size() - 1);  // previous station
                Date previousTimeTo = previousStationTo.getAppearTime();        // previousTime
                if (controlDate == null) {
                    controlDate = previousTimeTo;
                    resultStation = previousStationTo.getStation().getName();
                }
                if (controlDate.before(previousTimeTo) || controlDate.equals(previousTimeTo)) {   // or equals
                    controlDate = previousTimeTo;
                    resultStation = previousStationTo.getStation().getName();
                }
            }
            // if previous stations name not equals targets departure station, return different stations
            // else check time, if departure time after previous station arrival time - good case, add, else return message wrong time.
            if (sd.getStation().getName().equals(resultStation)) {
                if (controlDate != null) {
                    if (controlDate.before(targetDateFrom)) {
                        return "none";
                    }
                    return "Wrong time, need to be later then previous route arrival time!";
                }
                return "Wrong!";
            }
            return " station " + resultStation;
        }
    }


    /**
     * This method implements validation of schedule.
     * Date, train and time validity.
     *
     * @param scheduleDto - target dto object
     * @return - result message
     */
    public String getDepartureStation(ScheduleDto scheduleDto) {
        // find all schedule with target train
        List<Schedule> schedules = scheduleDao.getScheduleListTodayByTrain(scheduleDto.getTrainName());
        Route route = routeDao.findByName(scheduleDto.getRouteName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // target date
        Date dateToCheck;
        try {
            dateToCheck = sdf.parse(scheduleDto.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
            return "Wrong date format!";
        }
        List<Schedule> scheduleList = new ArrayList<>();
        // if departure day equals to the departure day of target schedule add to result scheduleList
        if (!schedules.isEmpty()) {
            for (Schedule sch : schedules) {
                if (dateToCheck.compareTo(sch.getDateTrip()) == 0) {
                    scheduleList.add(sch);
                }
            }
        }
        // if schedules with target train not exist, return that it is.
        else {
            return "none";
        }
        List<StationDistance> distanceListDepart = distanceDao.getStationsInRoute(route);
        StationDistance sd = distanceListDepart.get(distanceListDepart.size() - 1);  // target  arrival station
        Date targetTimeTo = sd.getAppearTime();
        // if there were no schedules with this train within target day return that it is.
        if (scheduleList.isEmpty()) {
            return "none";
        }
        // check if departure time of next stations before target arrival time, if it is wrong, save name of the next station.
        else {
            Date controlTime = null;
            String resultStation = "Wrong!";
            for (Schedule schedule : scheduleList) {
                List<StationDistance> distanceList = distanceDao.getStationsInRoute(schedule.getRoute());
                StationDistance previousStationFrom = distanceList.get(0);  // previous station departure
                Date previousTimeFrom = previousStationFrom.getAppearTime();        // previousTime departure
                if (controlTime == null) {
                    controlTime = previousTimeFrom;
                    resultStation = previousStationFrom.getStation().getName();     //  departure station of next schedule
                }
                if (controlTime.after(previousTimeFrom) || controlTime.equals(previousTimeFrom)) {   // or equals
                    controlTime = previousTimeFrom;
                    resultStation = previousStationFrom.getStation().getName();
                }
            }
            // if previous stations name not equals targets departure station, return different stations
            // else check time, if departure time after previous station arrival time - good case, add, else return message wrong time.
            if (sd.getStation().getName().equals(resultStation)) {
                if (controlTime != null) {
                    if (controlTime.after(targetTimeTo)) {
                        return "none";
                    }
                    return "Wrong time, need to be later then previous route arrival time!";
                }
                return "Wrong!";
            }
            return " station " + resultStation;
        }
    }

    /**
     * Check uniqueness of schedule within target day.
     *
     * @param scheduleDto - target object
     * @return - true if schedule unique ( i.e. train and route ) within target day. else return false
     */
    private boolean isUniqueWithinDay(ScheduleDto scheduleDto) {
        return scheduleDao.isUniqueSchedule(scheduleDto);
    }

    /**
     * Check departure date and time to create or not schedule
     *
     * @param dateToCheck - dateTrip
     * @param timeToCheck - appearTimeFrom station( just first one)
     * @return - true if correct future date, false - if date in the past.
     */
    private boolean isCorrectDate(Date dateToCheck, Date timeToCheck) {
        LocalDate localDate = new LocalDate();
        int compareResult = localDate.toDate().compareTo(dateToCheck);
        if (compareResult > 0) {
            return false;
        } else if (compareResult < 0) {
            return true;
        } else {
            DateTime departureTime = new DateTime(timeToCheck);
            departureTime.plus(dateToCheck.getTime());
            DateTime currentTime = new DateTime();
            return (currentTime.getHourOfDay() < departureTime.getHourOfDay() || currentTime.getMinuteOfDay()
                    < departureTime.getMinuteOfDay() || currentTime.getSecondOfDay() < departureTime.getSecondOfDay());
        }
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
            List<Schedule> res = new ArrayList<>();
            for (Schedule schedule : scheduleListDate) {
                boolean flag = false;
                List<StationDistance> distanceList = schedule.getRoute().getStationDistances();
                for (StationDistance s : distanceList)
                    // check conditions, that its not the last station in the route, and it is equal to the constraint
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
            List<Schedule> res = new ArrayList<>();
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
                if (sch.getDateTrip().after(new LocalDate().minusDays(1).toDate())) {
                    scheduleDtos.add(new ScheduleDto(sch, filter));
                }
            }
        }
        return scheduleDtos;
    }

    /**
     * Get list of schedules.
     *
     * @return - list of schedules.
     */
    @SuppressWarnings("unchecked")
    public List<ScheduleDto> getFullSchedule() {
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        List<Schedule> scheduleList = scheduleDao.findAll(Schedule.class);
        if (!scheduleList.isEmpty()) {
            for (Schedule sch : scheduleList) {
                scheduleDtos.add(new ScheduleDto(sch));
            }
        }
        return scheduleDtos;
    }
}
