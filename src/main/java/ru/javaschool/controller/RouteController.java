package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javaschool.dto.StationDistanceDto;
import ru.javaschool.model.entities.Route;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.services.RouteService;
import ru.javaschool.services.ScheduleService;
import ru.javaschool.services.StationService;
import ru.javaschool.services.TicketService;

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

    @Autowired
    private TicketService ticketService;

    /**
     * Get route list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/routes")
    public String getRouteList(HttpSession session, Model model) {
        session.removeAttribute("distanceListUpdate");
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
                             @ModelAttribute("appearenceTime") String appearenceTime, RedirectAttributes redAttr) {

        StationDistanceDto stationDistanceDto = new StationDistanceDto();
        stationDistanceDto.setStationName(stationName);
        stationDistanceDto.setAppearenceTime(appearenceTime);
        ArrayList<StationDistanceDto> distanceList = (ArrayList<StationDistanceDto>) session.getAttribute("distanceList");
        String msgArrivalCheck = routeService.isCorrectArrivalStation(distanceList, stationDistanceDto);
        String msgDepartureCheck = routeService.isCorrectDepartureStation(distanceList, stationDistanceDto);
        if (msgArrivalCheck.equals("Success!")) {
            distanceList.add(stationDistanceDto);
            redAttr.addFlashAttribute("msgg", "Success!");
            session.setAttribute("distanceList", distanceList);
        } else {
            if (msgDepartureCheck.equals("Success!")) {
                distanceList.add(0, stationDistanceDto);
                redAttr.addFlashAttribute("msgg", "Success!");
                session.setAttribute("distanceList", distanceList);
            } else {
                redAttr.addFlashAttribute("msgf", (msgArrivalCheck + " and " + msgDepartureCheck));
            }
        }
        return "redirect:/routeView/createRoute";
    }

    /**
     * Delete station distance from the view
     *
     * @return - redirect url.
     */
    @RequestMapping("/deleteSD/{stationName}")
    public String deleteStationDistance(@PathVariable("stationName") String stationName, HttpSession session, RedirectAttributes redAttr) {
        @SuppressWarnings("unchecked")
        ArrayList<StationDistanceDto> distanceDtoList = (ArrayList<StationDistanceDto>) session.getAttribute("distanceList");
        for (StationDistanceDto sd : distanceDtoList) {
            if (sd.getStationName().equals(stationName)) {
                distanceDtoList.remove(sd);
                distanceDtoList.trimToSize();
                break;
            }
        }
        session.setAttribute("distanceList", distanceDtoList);
        redAttr.addFlashAttribute("msgg", "Delete successful!");
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
    public String addRoute(HttpSession session, @Valid @ModelAttribute("route") Route route, BindingResult result, RedirectAttributes redAttr) {

        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msgf", "Wrong data");
        }
        if (session.getAttribute("distanceList") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
            if (distanceList.size() < 2) {
                redAttr.addFlashAttribute("msgf", "there is only 1 station distance. Must be 2 as minimum");
                return "redirect:/routeView/createRoute";
            }
            if (!routeService.createRoute(route, distanceList)) {
                redAttr.addFlashAttribute("msgf", "Wrong data!");
                return "redirect:/routeView/createRoute";
            }
            session.removeAttribute("distanceList");
            redAttr.addFlashAttribute("msgg", "Success!");
            return "redirect:/routeView/routes";
        }
        redAttr.addFlashAttribute("msgf", "Station distance list is empty!");
        return "redirect:/routeView/createRoute";
    }

    /**
     * Delete route from the database
     *
     * @param routeId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{routeId}")
    public String deleteRoute(@PathVariable("routeId") Long routeId, RedirectAttributes redAttr) {
        Route route = routeService.findRoute(routeId);
        if (route != null) {
            if (!routeService.deleteRoute(routeId)) {
                redAttr.addFlashAttribute("msgf", "There are some tickets! You can't delete it!");
            } else {
                redAttr.addFlashAttribute("msgg", "Delete route " + route.getTitle() + " successful!");
            }
            return "redirect:/routeView/routes";
        }
        return "error404";
    }

    /**
     * Clear distance list in the view, if something gone wrong,
     * and we need to clean it.
     *
     * @param session - http session.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/clearDistanceList")
    public String clearDistanceList(HttpSession session) {
        if (session.getAttribute("distanceList") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
            distanceList.clear();
            session.setAttribute("distanceList", distanceList);
        }
        return "redirect:/routeView/createRoute";
    }


    /**
     * Apply new url, which will be the form,
     * to set needed fields, to create route.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/updateRoute/{routeId}")
    public String updateRoute(HttpSession session, @PathVariable("routeId") Long routeId, Model model, RedirectAttributes redAttr) {
        Route route = routeService.findRoute(routeId);
        if (route != null) {
            if (ticketService.isSoldTicketsOnSchedule(routeId)) {
                redAttr.addFlashAttribute("msgf", "You can't update route " + route.getTitle() + " now, because there are some tickets on it!");
                return "redirect:/routeView/routes";
            }
            session.setAttribute("routeId", routeId);
            List<StationDistanceDto> distanceList;
            if (session.getAttribute("distanceListUpdate") == null) {
                distanceList = routeService.getStationDistances(routeId);
            } else {
                distanceList = (List<StationDistanceDto>) session.getAttribute("distanceListUpdate");
            }
            if (distanceList != null) {
                session.setAttribute("distanceListUpdate", distanceList);
            } else {
                return "error404";
            }
            model.addAttribute("distanceList", distanceList);
            model.addAttribute("stationList", stationService.getAllStations());
            model.addAttribute("route", route);
            return "routeView/updateRoute";
        }
        return "error404";
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
    @RequestMapping(value = "/addStationUpdate", method = RequestMethod.POST)
    public String addStationForUpdate(HttpSession session, @ModelAttribute("stationName") String stationName,
                                      @ModelAttribute("appearenceTime") String appearenceTime, RedirectAttributes redAttr) {

        StationDistanceDto stationDistanceDto = new StationDistanceDto();
        stationDistanceDto.setStationName(stationName);
        stationDistanceDto.setAppearenceTime(appearenceTime);
        ArrayList<StationDistanceDto> distanceList = (ArrayList<StationDistanceDto>) session.getAttribute("distanceListUpdate");
        String msgArrivalCheck = routeService.isCorrectArrivalStation(distanceList, stationDistanceDto);
        String msgDepartureCheck = routeService.isCorrectDepartureStation(distanceList, stationDistanceDto);
        if (msgArrivalCheck.equals("Success!")) {
            distanceList.add(stationDistanceDto);
            redAttr.addFlashAttribute("msgg", "Success!");
            session.setAttribute("distanceListUpdate", distanceList);
        } else {
            if (msgDepartureCheck.equals("Success!")) {
                distanceList.add(0, stationDistanceDto);
                redAttr.addFlashAttribute("msgg", "Success!");
                session.setAttribute("distanceListUpdate", distanceList);
            } else {
                redAttr.addFlashAttribute("msgf", (msgArrivalCheck + " and " + msgDepartureCheck));
            }
        }
        if (session.getAttribute("routeId") == null) {
            return "error404";
        }
        return "redirect:/routeView/updateRoute/" + session.getAttribute("routeId");
    }

    /**
     * Delete station distance from the view
     *
     * @return - redirect url.
     */
    @RequestMapping("/deleteSDUpdate/{stationName}")
    public String deleteStationDistanceForUpdate(@PathVariable("stationName") String stationName, HttpSession session, RedirectAttributes redAttr) {
        @SuppressWarnings("unchecked")
        ArrayList<StationDistanceDto> distanceDtoList = (ArrayList<StationDistanceDto>) session.getAttribute("distanceListUpdate");
        for (StationDistanceDto sd : distanceDtoList) {
            if (sd.getStationName().equals(stationName)) {
                distanceDtoList.remove(sd);
                distanceDtoList.trimToSize();
                break;
            }
        }
        session.setAttribute("distanceListUpdate", distanceDtoList);
        redAttr.addFlashAttribute("msgg", "Delete successful!");
        if (session.getAttribute("routeId") == null) {
            return "error404";
        }
        return "redirect:/routeView/updateRoute/" + session.getAttribute("routeId");
    }

    /**
     * Create new route and redirect us to central view.
     *
     * @param route - target route to create
     * @return - redirect url.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshRoute(HttpSession session, @Valid @ModelAttribute("route") Route route, BindingResult result, RedirectAttributes redAttr) {

        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msgf", "Wrong data");
        }
        if (session.getAttribute("distanceListUpdate") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceListUpdate");
            if (distanceList.size() < 2) {
                redAttr.addFlashAttribute("msgf", "there is only 1 station distance. Must be 2 as minimum");
                return "redirect:/routeView/updateRoute/" + route.getRouteId();
            }
            if (!routeService.updateRoute(route, distanceList)) {
                redAttr.addFlashAttribute("msgf", "Wrong data!");
                return "redirect:/routeView/updateRoute/" + route.getRouteId();
            }
            redAttr.addFlashAttribute("msgg", "Success!");
            session.removeAttribute("routeId");
            session.removeAttribute("distanceListUpdate");
            return "redirect:/routeView/routes";
        }
        redAttr.addFlashAttribute("msgf", "Station distance list is empty!");
        return "redirect:/routeView/updateRoute/" + route.getRouteId();
    }

    /**
     * Clear distance list in the view, if something gone wrong,
     * and we need to clean it.
     *
     * @param session - http session.
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/clearDistanceListUpdate")
    public String clearDistanceListForUpdate(HttpSession session) {
        if (session.getAttribute("distanceListUpdate") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceListUpdate");
            distanceList.clear();
            session.setAttribute("distanceListUpdate", distanceList);
        }
        if (session.getAttribute("routeId") == null) {
            return "error404";
        }
        return "redirect:/routeView/updateRoute/" + session.getAttribute("routeId");
    }


}
