package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.dao.RouteDao;
import ru.javaschool.dao.ScheduleDao;
import ru.javaschool.dao.StationDao;
import ru.javaschool.dao.StationDistanceDao;
import ru.javaschool.dto.StationDistanceDto;
import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.StationDistance;

import java.sql.Time;
import java.util.ArrayList;
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

    @Autowired
    private StationDao stationDao;

    /**
     * Getting all routes from the database.
     *
     * @return - list of all routes.
     */
    @SuppressWarnings("unchecked")
    public List<Route> getAllRoutes() {
        return routeDao.findAll(Route.class);
    }

//    /**
//     * This method creates a new route, if such name is
//     * not exist in the database yet.
//     * First create route, then create all stationDistances of this route.
//     *
//     * @param route - concrete route, which we need to create.
//     * @return - true if insert successful, else another way.
//     */
//    @SuppressWarnings("unchecked")
//    public boolean createRoute(Route route) {
//        if (routeDao.isRouteExist(route.getTitle())) {
//            return false;
//        }
//        List<StationDistance> distanceList = route.getStationDistances();
//        if (distanceList != null && distanceList.size() > 1) {
//            routeDao.create(route);
//            for (StationDistance sd : distanceList) {
//                distanceDao.create(sd);
//            }
//        }
//        return true;
//    }

    /**
     * Delete route from the database,
     * if such route didn't yet include is the schedule.
     * First of all, delete all station distances, which were
     * exist in this route, then this empty route would be deleted.
     *
     * @param routeId - unique identifier of the concrete route.
     * @return - true, if delete successful, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean deleteRoute(Long routeId) {
        Route route = routeDao.findByPK(Route.class, routeId);
        if (route != null) {
            List<Schedule> scheduleList = scheduleDao.getScheduleListByRoute(routeId);
            if (!scheduleList.isEmpty()) {
                if (!scheduleDao.isTicketListEmpty(scheduleList)) {
                    return false;                                       // delete can't be done, if someone already bought ticket on target route.
                }
                for (Schedule sch : scheduleList) {
                    scheduleDao.delete(sch);
                }
            }
            List<StationDistance> distanceList = route.getStationDistances();
            routeDao.delete(route);                                     // mappedBy route, maybe need to delete stationDistances first.
            for (StationDistance sd : distanceList) {
                distanceDao.delete(sd);
            }
        }
        return true;
    }

    /**
     * Update target route
     * if it is not include in any schedule,
     * we could update what we need.
     * After update route title, we will check correctness of its stationDistances,
     * if it need - updates or create new one.
     *
     * @param route - target route
     * @return - true, if update successful, else return false.
     */
    @SuppressWarnings("unchecked")
    public boolean updateRoute(Route route) {
        List<Schedule> scheduleList = scheduleDao.getScheduleListByRoute(route.getRouteId());
        if (!scheduleList.isEmpty()) {
            if (!scheduleDao.isTicketListEmpty(scheduleList)) {
                return false;                                       // update can't be done, if someone already bought ticket on target route.
            }
            for (Schedule sch : scheduleList) {
                sch.setRoute(route);
                scheduleDao.update(sch);
            }
        }
        route.setStationDistances(distanceDao.getStationsInRoute(route));
        routeDao.update(route);
        List<StationDistance> distanceList = route.getStationDistances();
        for (StationDistance sd : distanceList) {
            if (distanceDao.isExistInRoute(sd.getSequenceNumber(), route)) {
                distanceDao.update(sd);
            } else {
                distanceDao.create(sd);
            }
        }
        return true;
    }

    /**
     * Find target route by its identifier, passed as a parameter
     * @param key - identifier
     * @return - instance of target route.
     */
    @SuppressWarnings("unchecked")
    public Route findRoute(Long key) {
        return (Route) routeDao.findByPK(Route.class, key);
    }

    /**
     * Get list of station distances, in target route
     * @param routeId - identifier of target route
     * @return - list of station distances.
     */
    @SuppressWarnings("unchecked")
    public List<StationDistanceDto> getStationDistances(Long routeId) {
        Route route = routeDao.findByPK(Route.class, routeId);
        List<StationDistance> sdList = distanceDao.getStationsInRoute(route);
        List<StationDistanceDto> dtoList = new ArrayList<>();
        for (StationDistance sd : sdList) {
            dtoList.add(new StationDistanceDto(sd));
        }
        return dtoList;
    }

    public List<StationDistance> getAllStationDistances() {
        return distanceDao.findAll(StationDistance.class);
    }

    /**
     * Adding station distances of target route, passed as a parameter.
     * @param route - target route.
     * @param distanceList - list of stationDistances to add in the database.
     * @return -  true, if creating stationDistances success. else return false.
     */
    public boolean createRoute(Route route, List<StationDistanceDto> distanceList) {
        // if not exist yet
        if(!routeDao.isRouteExist(route.getTitle())) {

            //Route insertedRoute = routeDao.findByPK(Route.class, route.getRouteId());
            List<StationDistance> stationDistances = new ArrayList<>();
            // creating stationDistances
            Long sequenceNumber = (long) 1;
            for (StationDistanceDto sdDto : distanceList) {
                StationDistance stationDistance = new StationDistance();
                stationDistance.setRoute(route);
                stationDistance.setSequenceNumber(sequenceNumber);
                stationDistance.setStation(stationDao.findByName(sdDto.getStationName()));
                String[] time = sdDto.getAppearenceTime().split(":");
                stationDistance.setAppearTime(new Time(Integer.parseInt(time[0]), Integer.parseInt(time[1]), 0));
                stationDistances.add(stationDistance);
                sequenceNumber++;
            }
            route.setStationDistances(stationDistances);
            routeDao.create(route);
            for(StationDistance sd: stationDistances){
                distanceDao.create(sd);
            }
            //route.setStationDistances(stationDistances);
            //routeDao.update(route);
            return true;
        }
        return false;
    }
}
