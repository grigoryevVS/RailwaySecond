package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.TrainDao;
import ru.javaschool.model.entities.Train;

import java.util.List;

/**
 * This class implements transaction actions with trains.
 */
@Service
@Transactional
public class TrainService {

    /**
     * Autowired dao layer class, which implements working with database.
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
    @SuppressWarnings("unchecked")
    public List<Train> getAllTrains() {
        return trainDao.findAll(Train.class);
    }

    /**
     * Creating new train if it is not exist yet.
     *
     * @param train - what train we need to create,
     *              getting from the employee, who fills all its fields.
     */
    @SuppressWarnings("unchecked")
    public boolean createTrain(Train train) {
        if (trainDao.isTrainExist(train)) {
            return false;
        } else {
            trainDao.create(train);
            return true;
        }
    }

    /**
     * Delete concrete train from the database,
     * what train we need to delete passed as parameter.
     *
     * @param key - primary key( unique identifier of concrete train, to delete.
     */
    @SuppressWarnings("unchecked")
    public void deleteTrain(Long key) {
        Train train = (Train) trainDao.findByPK(Train.class, key);
        if (train != null) {
            if (!scheduleDao.isTrainInSchedule(train)) {
                trainDao.delete(train);
            }
        }
    }


    @SuppressWarnings("unchecked")
    public void updateTrain(Train train) {
        trainDao.update(train);
    }

    @SuppressWarnings("unchecked")
    public Train findTrain(Long key) {
        return (Train) trainDao.findByPK(Train.class, key);
    }
}
