package ru.javaschool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.StationDao;
import ru.javaschool.dao.StationDistanceDao;
import ru.javaschool.model.entities.Station;

import java.util.List;

@Service
@Transactional
public class StationService {

    @Autowired
    StationDao stationDao;

    @Autowired
    StationDistanceDao distanceDao;

    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        return stationDao.findAll(Station.class);
    }

    /**
     * Creating new station if database don't contains such station already.
     *
     * @param station - which station we need check, and if there wasn't such
     *                station yet create it in the database.
     * @return - true if creating success, another way - false.
     */
    @SuppressWarnings("unchecked")
    public boolean createStation(Station station) {
        if (stationDao.isStationExist(station)) {
            return false;
        } else {
            stationDao.create(station);
            return true;
        }
    }

    /**
     * This method deletes concrete station from the database,
     * if such station doesn't exist in working route, and it could be deleted
     * without any harm to our schedule.
     *
     * @param key - concrete identifier of station,
     *            which need to be deleted.
     * @return - true if such station successfully deleted.
     * false - if such station included in some routes.
     */
    @SuppressWarnings("unchecked")
    public boolean deleteStation(Long key) {
        Station station = (Station) stationDao.findByPK(Station.class, key);
        if (station != null) {
            if (!distanceDao.isStationDistance(station)) {
                stationDao.deleteObject(Station.class, key);
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public void updateStation(Station station) {
        stationDao.update(station);
    }

    @SuppressWarnings("unchecked")
    public Station findStation(Long key) {
        return (Station) stationDao.findByPK(Station.class, key);
    }
}
