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
    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        return stationDao.findAll(Station.class);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public void createStation(Station station) {
        stationDao.create(station);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public void deleteStation(Long key){
        stationDao.deleteObject(Station.class, key );
    }
}
