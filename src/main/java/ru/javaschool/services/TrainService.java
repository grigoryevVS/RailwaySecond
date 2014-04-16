package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.TrainDao;
import ru.javaschool.model.entities.Train;

import java.util.List;

/**
 * This class implements transaction actions with trains.
 *
 */
@Service
public class TrainService {

    /**
     * Autowired dao layer class, which implements working with database.
     */
    @Autowired
    TrainDao trainDao;

    /**
     * Get all trains, which exists in our db
     * @return list of trains.
     */
    @Transactional
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
    @Transactional
    @SuppressWarnings("unchecked")
    public void createTrain(Train train) {
        trainDao.create(train);
    }

    /**
     * delete concrete train from the database,
     * what train we need to delete passed as parameter.
     * @param key - primary key( unique identifier of concrete train, to delete.
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public void deleteTrain(Long key){
        trainDao.deleteObject(Train.class, key );
    }

    /**
     *
     * @param key
     */
    @Transactional
    @SuppressWarnings("unchecked")
    public void updateTrain(Long key) {
        Train train = (Train) trainDao.findByPK(Train.class, key);
        trainDao.update(train);
    }
}
