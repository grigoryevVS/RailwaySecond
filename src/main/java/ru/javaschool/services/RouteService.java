package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.RouteDao;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.StationDistanceDao;
import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.StationDistance;

import java.util.List;

/**
 * Service which implements actions with routes.
 */
@Service
@Transactional
public class RouteService {

    @Autowired
    private RouteDao routeDao;

    @Autowired
    private ScheduleDao scheduleDao;

    @Autowired
    private StationDistanceDao distanceDao;

    /**
     * Getting all routes from the database.
     *
     * @return - list of all routes.
     */
    @SuppressWarnings("unchecked")
    public List<Route> getAllRoutes() {
        return routeDao.findAll(Route.class);
    }

    /**
     * This method creates a new route, if such name is
     * not exist in the database yet.
     * First create route, then create all stationDistances of this route.
     *
     * @param route - concrete route, which we need to create.
     * @return - true if insert successful, else another way.
     */
    @SuppressWarnings("unchecked")
    public boolean createRoute(Route route) {
        if (routeDao.isRouteExist(route.getTitle())) {
            return false;
        }
        List<StationDistance> distanceList = route.getStationDistances();
        if (distanceList != null && distanceList.size() > 1) {
            routeDao.create(route);
            for (StationDistance sd : distanceList) {
                distanceDao.create(sd);
            }
        }
        return true;
    }

    /**
     * Delete route from the database,
     * if such route didn't yet include is the schedule.
     * First of all, delete all station distances, which were
     * exist in this route, then this empty route would be deleted.
     *
     * @param key - unique identifier of the concrete route.
     * @return - true, if delete successful, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean deleteRoute(Long key) {
        Route route = (Route) routeDao.findByPK(Route.class, key);
        if (route != null) {
            if (!scheduleDao.isRouteInSchedule(key)) {
                List<StationDistance> distanceList = route.getStationDistances();
                for (StationDistance sd : distanceList) {
                    distanceDao.delete(sd);
                }
                routeDao.delete(route);
                return true;
            }
        }
        return false;
    }



}
