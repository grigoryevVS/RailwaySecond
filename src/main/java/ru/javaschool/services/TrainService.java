package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.TrainDao;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Train;

import java.util.List;

/**
 * This class implements transaction actions with trains.
 */
@Service
@Transactional
public class TrainService {

    /**
     * Autowired dao layer classes, which implements working with database.
     */
    @Autowired
    TrainDao trainDao;

    @Autowired
    ScheduleDao scheduleDao;

    /**
     * Get all trains, which exists in our db
     *
     * @return list of trains.
     */
    public List<Train> getAllTrains() {
        return trainDao.findAll(Train.class);
    }

    /**
     * Creating new train if it is not exist yet.
     * Validate correctness train capacity and name uniqueness.
     *
     * @param train - what train we need to create,
     *              getting from the employee, who fills all its fields.
     */
    public boolean createTrain(final Train train) {
        int capacity = train.getNumberOfSeats();
        if (capacity < 1 || capacity > 400 || trainDao.isTrainExist(train)) {
            return false;
        }
        trainDao.create(train);
        return true;
    }

    /**
     * Delete target train from the database,
     * what train we need to delete passed as parameter.
     *
     * @param key - primary key( unique identifier of concrete train, to delete.
     */
    public boolean deleteTrain(final Long key) {
        Train train = trainDao.findByPK(Train.class, key);
        if (train != null) {
            if (!scheduleDao.isTrainInSchedule(key)) {
                trainDao.delete(train);
                return true;
            }
        }
        return false;
    }

    /**
     * Update target trains name and/or capacity.
     * Validate correctness of train capacity and compares
     * new capacity with bought ticketList size
     *
     * @param train - target train to update.
     */
    public boolean updateTrain(final Train train) {
        int capacity = train.getNumberOfSeats();
        if (capacity < 1 || capacity > 400) {
            return false;
        }
        List<Schedule> scheduleList = scheduleDao.getScheduleListByTrain(train.getTrainId());
        for (Schedule schedule : scheduleList) {
            if (capacity < schedule.getTicketList().size()) {
                return false;
            }
        }
        trainDao.update(train);
        return true;
    }

    /**
     * Getting count of maximum bought tickets on any of the target scheduleList
     *
     * @param trainId - target train id to check its schedule
     * @return - counted tickets.
     */
    public int maxBoughtTicketsOnTrain(final Long trainId) {
        List<Schedule> scheduleList = scheduleDao.getScheduleListByTrain(trainId);
        int actualTickets = 0;
        for (Schedule schedule : scheduleList) {
            if (actualTickets <= schedule.getTicketList().size()) {
                actualTickets = schedule.getTicketList().size();
            }
        }
        return actualTickets;
    }

    /**
     * Find train by its primary key.
     *
     * @param key - primary key.
     * @return - instance of target train.
     */
    public Train findTrain(final Long key) {
        return trainDao.findByPK(Train.class, key);
    }

    /**
     * Get train by its name
     *
     * @param name - target trains name
     * @return - train instance
     */
    public Train getTrainByName(final String name) {
        return trainDao.findByName(name);
    }
}
