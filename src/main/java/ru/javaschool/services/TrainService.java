package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.TrainDao;
import ru.javaschool.model.entities.Train;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainDao trainDao;

    @Transactional
    public List<Train> getAllTrains() {
        return trainDao.findAll();
    }

    @Transactional
    public void createTrain(Train train) {
        trainDao.create(train);
    }

    @Transactional
    public void deleteTrain(Long key){
        trainDao.deleteObject(Train.class, key );
    }
}
