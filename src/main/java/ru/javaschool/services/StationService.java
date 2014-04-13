package ru.javaschool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.StationDao;
import ru.javaschool.model.entities.Station;

import java.util.List;

@Service
public class StationService {

    @Autowired
    StationDao stationDao;

    @Transactional
    public List<Station> getAllStations() {
        return stationDao.findAll();
    }

    @Transactional
    public void createStation(Station station) {
        stationDao.create(station);
    }

    @Transactional
    public void deleteStation(Long key){
        stationDao.deleteObject(Station.class, key );
    }
}
