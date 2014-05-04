package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javaschool.dto.StationDistanceDto;
import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.services.RouteService;
import ru.javaschool.services.ScheduleService;
import ru.javaschool.services.StationService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Controls route service.
 */
@Controller
@RequestMapping("/routeView")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Get route list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/routes")
    public String getRouteList(Model model, HttpSession session) {
        if (session.getAttribute("msgg") != null) {
            session.removeAttribute("msgf");
            model.addAttribute("msgg", session.getAttribute("msgg"));
        }
        if (session.getAttribute("msgf") != null) {
            session.removeAttribute("msgg");
            model.addAttribute("msgf", session.getAttribute("msgf"));
        }
        model.addAttribute("route", new Route());
        model.addAttribute("routeList", routeService.getAllRoutes());
        return "routeView/routes";
    }

    /**
     * Get route list for standard user or anonymous.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/routeIndex")
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("msgg") != null) {
            model.addAttribute("msgg", session.getAttribute("msgg"));
        }
        if (session.getAttribute("msgf") != null) {
            model.addAttribute("msgf", session.getAttribute("msgf"));
        }
        model.addAttribute("route", new Route());
        model.addAttribute("routeList", routeService.getAllRoutes());
        return "routeView/routeIndex";
    }

    /**
     * Show concrete stationDistances of target route
     *
     * @param routeId - route identifier
     * @param model   - model of view.
     * @return - url
     */
    @RequestMapping("/details/{routeId}")
    public String getStationDistances(@PathVariable("routeId") Long routeId, Model model) {

        List<StationDistanceDto> distanceList = routeService.getStationDistances(routeId);
        if (!distanceList.isEmpty()) {
            model.addAttribute("stationDistanceDto", new StationDistanceDto());
            model.addAttribute("stationDistances", distanceList);
            return "routeView/details";
        } else {
            return "error404";
        }
    }

    @RequestMapping("/detailsFromSchedule/{id}")
    public String getStationsBySchedule(@PathVariable("id") Long id, Model model) {
        Schedule schedule = scheduleService.findSchedule(id);
        if (schedule != null) {
            Long routeId = schedule.getRoute().getRouteId();
            List<StationDistanceDto> distanceList = routeService.getStationDistances(routeId);
            if (!distanceList.isEmpty()) {
                model.addAttribute("stationDistanceDto", new StationDistanceDto());
                model.addAttribute("stationDistances", distanceList);
                return "routeView/detailsForSchedule";
            }
        }
        return "error404";
    }

    /**
     * Apply new url, which will be the form,
     * to set needed fields, to create route.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/createRoute", method = RequestMethod.GET)
    public String createRoute(HttpSession session, Model model) {

        List<StationDistanceDto> distanceList;
        if (session.getAttribute("distanceList") != null) {
            distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
        } else {
            distanceList = new ArrayList<>();
            session.setAttribute("distanceList", distanceList);
        }
        model.addAttribute("distanceList", distanceList);
        model.addAttribute("stationList", stationService.getAllStations());
        model.addAttribute("route", new Route());
        model.addAttribute("msgg", session.getAttribute("msgg"));
        model.addAttribute("msgf", session.getAttribute("msgf"));
        return "routeView/createRoute";
    }

    /**
     * On each push button add station, add stationDistance to the distanceList
     * after validation.
     *
     * @param session        - http session
     * @param stationName    - name of station distance
     * @param appearenceTime - appearance time of train on target station
     * @return - view.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/addStation", method = RequestMethod.POST)
    public String addStation(HttpSession session, @ModelAttribute("stationName") String stationName,
                             @ModelAttribute("appearenceTime") String appearenceTime) {

        StationDistanceDto stationDistanceDto = new StationDistanceDto();
        stationDistanceDto.setStationName(stationName);
        stationDistanceDto.setAppearenceTime(appearenceTime);

        List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
        String msg = routeService.isCorrectStation(distanceList, stationDistanceDto);
        if (msg.equals("Success!")) {
            distanceList.add(stationDistanceDto);
            session.removeAttribute("msgf");
            session.setAttribute("msgg", msg);
            session.setAttribute("distanceList", distanceList);
            return "redirect:/routeView/createRoute";
        } else {
            session.removeAttribute("msgg");
            session.setAttribute("msgf", msg);
            return "redirect:/routeView/createRoute";
        }
    }

    /**
     * Delete station distance from the view
     *
     * @return - redirect url.
     */
    @RequestMapping("/deleteSD/{stationName}")
    public String deleteStationDistance(@PathVariable("stationName") String stationName, HttpSession session) {

        @SuppressWarnings("unchecked")
        List<StationDistanceDto> distanceDtoList = (List<StationDistanceDto>) session.getAttribute("distanceList");
        List<StationDistanceDto> result = new ArrayList<>();
        for (StationDistanceDto sd : distanceDtoList) {
            if (!sd.getStationName().equals(stationName)) {
                result.add(sd);
            }
        }
        session.removeAttribute("msgf");
        session.setAttribute("distanceList", result);
        session.setAttribute("msgg", "Delete successful!");
        return "redirect:/routeView/createRoute";
    }

    /**
     * Create new route and redirect us to central view.
     *
     * @param route - target route to create
     * @return - redirect url.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addRoute(HttpSession session, @Valid @ModelAttribute("route") Route route, BindingResult result) {

        session.removeAttribute("msgg");
        session.removeAttribute("msgf");
        if (result.hasErrors()) {
            session.setAttribute("msgf", "Wrong data");
        }
        if (session.getAttribute("distanceList") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
            if (distanceList.size() < 2) {
                session.setAttribute("msgf", "there is only 1 station distance. Must be 2 as minimum");
                return "redirect:/routeView/createRoute";
            }
            if (!routeService.createRoute(route, distanceList)) {
                session.setAttribute("msgf", "Wrong data!");
                return "redirect:/routeView/createRoute";
            }
            distanceList.clear();
            session.setAttribute("distanceList", distanceList);
            session.setAttribute("msgg", "Success!");
            return "redirect:/routeView/routes";
        }
        session.setAttribute("msgf", "Station distance list is empty!");
        return "redirect:/routeView/createRoute";
    }

    /**
     * Delete route from the database
     *
     * @param routeId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{routeId}")
    public String deleteRoute(@PathVariable("routeId") Long routeId, HttpSession session) {
        Route route = routeService.findRoute(routeId);
        if (route != null) {
            if (!routeService.deleteRoute(routeId)) {
                session.removeAttribute("msgg");
                session.setAttribute("msgf", "There are some tickets! You can't delete it!");
            } else {
                session.removeAttribute("msgf");
                session.setAttribute("msgg", "Delete route " + route.getTitle() + " successful!");
            }
            return "redirect:/routeView/routes";
        }
        return "error404";
    }

    /**
     * Throw us to the update form
     *
     * @param routeId - targets identifier
     * @param model   - model of view.
     * @return - target url.
     */
    @RequestMapping("/updateRoute/{routeId}")
    public String updateRoute(HttpSession session, @PathVariable("routeId") Long routeId, Model model) {
        //session.removeAttribute("msgg");
        //session.removeAttribute("msgf");
        Route route = routeService.findRoute(routeId);
        if (route != null) {
            model.addAttribute("route", route);
            return "routeView/updateRoute";
        }
        return "error404";
    }

    /**
     * Update route
     *
     * @param route - target route to update
     * @return - redirect url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshRoute(@Valid @ModelAttribute("route") Route route, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            session.setAttribute("msgf", "Wrong data!");
            return "redirect:/routeView/updateRoute/" + route.getRouteId();
        }
        if (!routeService.updateRoute(route)) {
            session.removeAttribute("msgg");
            session.setAttribute("msgf", "There are some tickets!");
            return "redirect:/routeView/updateRoute/" + route.getRouteId();
        }
        session.removeAttribute("msgf");
        session.setAttribute("msgg", "Update route " + route.getTitle() + " successful!");
        return "redirect:/routeView/routes";
    }

    /**
     * Clear distance list in the view, if something gone wrong,
     * and we need to clean it.
     *
     * @param session - http session.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/clearDistanceList", method = RequestMethod.POST)
    public String clearDistanceList(HttpSession session) {
        if (session.getAttribute("distanceList") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
            distanceList.clear();
            session.setAttribute("distanceList", distanceList);
        }
        return "redirect:/routeView/createRoute";
    }
}
