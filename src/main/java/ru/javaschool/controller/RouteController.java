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
import ru.javaschool.services.RouteService;
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

    /**
     * Get route list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/routes")
    public String getRouteList(Model model) {
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
    public String index(Model model) {
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
    public String getStationDistances(@PathVariable("routeId") Long routeId,
                                      Model model) {
        model.addAttribute("stationDistanceDto", new StationDistanceDto());
        model.addAttribute("stationDistances", routeService.getStationDistances(routeId));
        return "routeView/details";
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
            distanceList = new ArrayList<StationDistanceDto>();
            session.setAttribute("distanceList", distanceList);
        }
        model.addAttribute("distanceList", distanceList);
        model.addAttribute("stationList", stationService.getAllStations());
        model.addAttribute("route", new Route());
        return "routeView/createRoute";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/addStation", method = RequestMethod.POST)
    public String addStation(HttpSession session, @ModelAttribute("stationName") String stationName,
                             @ModelAttribute("appearenceTime") String appearenceTime) {
        StationDistanceDto stationDistanceDto = new StationDistanceDto();
        stationDistanceDto.setStationName(stationName);
        stationDistanceDto.setAppearenceTime(appearenceTime);

        List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
        distanceList.add(stationDistanceDto);
        session.setAttribute("distanceList", distanceList);
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
    public String addRoute(HttpSession session,@Valid @ModelAttribute("route") Route route, BindingResult result) {
        if (result.hasErrors()) {
            session.setAttribute("message", "Wrong data");
        }
        if (session.getAttribute("distanceList") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
            if(distanceList.size() < 2){
                session.setAttribute("message", "there is only 1 station distance. Must be 2 as minimum");
                return "redirect:/routeView/createRoute";
            }
            if(!routeService.createRoute(route, distanceList)){
                session.setAttribute("message", "Wrong data!");
                return "redirect:/routeView/createRoute";
            }
            distanceList.clear();
            session.setAttribute("distanceList", distanceList);
            session.setAttribute("message", "Success!");
            return "redirect:/routeView/routes";
        }
        session.setAttribute("message", "Station distance list is empty!");
        return "redirect:/routeView/createRoute";
    }

    /**
     * Delete route from the database
     *
     * @param routeId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{routeId}")
    public String deleteRoute(@PathVariable("routeId") Long routeId) {
        Route route = routeService.findRoute(routeId);
        if (route != null) {
            if(!routeService.deleteRoute(routeId)) {
                // TODO message error
                return "redirect:routeView/routes";
            }
            // TODO message success
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
    public String updateRoute(@PathVariable("routeId") Long routeId,
                              Model model) {
        model.addAttribute("route", routeService.findRoute(routeId));
        return "routeView/updateRoute";
    }

    /**
     * Update route
     *
     * @param route - target route to update
     * @return - redirect url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshRoute(@Valid @ModelAttribute("route") Route route, BindingResult result) {
        if (result.hasErrors()) {

            return "redirect:/updateRoute";
        }
        if (!routeService.updateRoute(route)) {

            return "redirect:/updateRoute";
        }
        return "redirect:/routeView/routes";
    }
}
