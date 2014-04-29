package ru.javaschool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.StationDao;
import ru.javaschool.dao.StationDistanceDao;
import ru.javaschool.model.entities.Station;

import java.util.List;

@Service
@Transactional
public class StationService {

    @Autowired
    private StationDao stationDao;

    @Autowired
    private StationDistanceDao distanceDao;

    @Autowired
    private ScheduleDao scheduleDao;

    /**
     * Get all stations from the database.
     *
     * @return - list of stations.
     */
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
        if (stationDao.isStationExist(station.getName())) {
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
     * @param station - concrete station,
     *            which need to be deleted.
     * @return - true if such station successfully deleted.
     * false - if such station included in some routes.
     */
    @SuppressWarnings("unchecked")
    public boolean deleteStation(Station station) {
        if (!distanceDao.isStationDistance(station.getStationId())) {
            stationDao.delete(station);
            return true;
        }
        return false;
    }

    /**
     * Update station name.
     *
     * @param station - concrete station, with unique contain key,
     *                and changed name.
     */
    @SuppressWarnings("unchecked")
    public void updateStation(Station station) {
        stationDao.update(station);
    }

    /**
     * Find station in the database.
     *
     * @param key - primary key of target station in the database
     * @return - instance of target station.
     */
    @SuppressWarnings("unchecked")
    public Station findStation(Long key) {
        return (Station) stationDao.findByPK(Station.class, key);
    }

    /**
     * Get station by name from the database.
     *
     * @param stationName - target name
     * @return - instance of target station.
     */
    public Station getStationByName(String stationName) {
        return stationDao.findByName(stationName);
    }
}
