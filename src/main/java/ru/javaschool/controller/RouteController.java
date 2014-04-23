package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javaschool.dto.StationDistanceDto;
import ru.javaschool.model.entities.Route;
import ru.javaschool.services.RouteService;
import ru.javaschool.services.StationService;

import javax.servlet.http.HttpSession;
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
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/routes")
    public String getRouteList(Model model) {
        model.addAttribute("route", new Route());
        model.addAttribute("routeList", routeService.getAllRoutes());
        return "routeView/routes";
    }

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
        if(session.getAttribute("distanceList") != null){
            distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
        }
        else {
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
    public String addRoute(HttpSession session, @ModelAttribute("route") Route route) {
        if(session.getAttribute("distanceList") != null) {
            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
            routeService.createRoute(route, distanceList);
            distanceList.clear();
            session.setAttribute("distanceList", distanceList);
        }
        return "redirect:/routeView/routes";
    }

    /**
     * Delete route from the database
     *
     * @param routeId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{routeId}")
    public String deleteRoute(@PathVariable("routeId") Long routeId) {
        routeService.deleteRoute(routeId);
        return "redirect:/routeView/routes";
    }

//    /**
//     * Throw us to the update form
//     *
//     * @param stationId - targets identifier
//     * @param model     - model of view.
//     * @return - target url.
//     */
//    @RequestMapping("/updateRoute/{stationId}")
//    public String updateStation(@PathVariable("stationId") Long stationId,
//                                Model model) {
//        model.addAttribute("station", stationService.findStation(stationId));
//        return "stationView/updateRoute";
//    }
//
//    /**
//     * Update station
//     *
//     * @param station - target station to update
//     * @return - redirect url.
//     */
//    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
//    public String refreshStation(@ModelAttribute("station") Station station) {
//        stationService.updateStation(station);
//        return "redirect:/stationView/stations";
//    }

}



//package ru.javaschool.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import ru.javaschool.dto.StationDistanceDto;
//import ru.javaschool.model.entities.Route;
//import ru.javaschool.services.RouteService;
//import ru.javaschool.services.StationService;
//
//import javax.servlet.http.HttpSession;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Controls route service.
// */
//@Controller
//@RequestMapping("/routeView")
//public class RouteController {
//
//    @Autowired
//    private RouteService routeService;
//
//    @Autowired
//    private StationService stationService;
//
//    /**
//     * Get route list.
//     * @param model - model of view.
//     * @return - target url
//     */
//    @RequestMapping("/routes")
//    public String getRouteList(Model model) {
//        model.addAttribute("route", new Route());
//        model.addAttribute("routeList", routeService.getAllRoutes());
//        return "routeView/routes";
//    }
//
//    @RequestMapping("/details/{routeId}")
//    public String getStationDistances(@PathVariable("routeId") Long routeId,
//                                      Model model) {
//        model.addAttribute("stationDistanceDto", new StationDistanceDto());
//        model.addAttribute("stationDistances", routeService.getStationDistances(routeId));
//        return "routeView/details";
//    }
//
//    /**
//     * Apply new url, which will be the form,
//     * to set needed fields, to create route.
//     *
//     * @param model - - model of view.
//     * @return - target url
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "/createRoute", method = RequestMethod.GET)
//    public String createRoute(HttpSession session, Model model) {
//        List<StationDistanceDto> distanceList;
//        if(session.getAttribute("distanceList") != null){
//            distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
//        }
//        else {
//            distanceList = new ArrayList<StationDistanceDto>();
//            session.setAttribute("distanceList", distanceList);
//        }
//        model.addAttribute("distanceList", distanceList);
//        model.addAttribute("stationList", stationService.getAllStations());
//        model.addAttribute("route", new Route());
//        return "routeView/createRoute";
//    }
//
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "/addStation", method = RequestMethod.POST)
//    public String addStation(HttpSession session, @ModelAttribute("stationName") String stationName,
//                             @ModelAttribute("appearenceTime") String appearenceTime) {
//        StationDistanceDto stationDistanceDto = new StationDistanceDto();
//        stationDistanceDto.setStationName(stationName);
//        stationDistanceDto.setAppearenceTime(appearenceTime);
//
//        List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
//        distanceList.add(stationDistanceDto);
//        session.setAttribute("distanceList", distanceList);
//        return "redirect:/routeView/createRoute";
//    }
//
//    /**
//     * Create new route and redirect us to central view.
//     *
//     * @param route - target route to create
//     * @return - redirect url.
//     */
//    @SuppressWarnings("unchecked")
//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public String addRoute(HttpSession session, @ModelAttribute("route") Route route) {
//        if(session.getAttribute("distanceList") != null) {
//            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
//            distanceList.clear();
//            session.setAttribute("distanceList", distanceList);
//            routeService.createRoute(route, distanceList);
//        }
//        return "redirect:/routeView/routes";
//    }
//
//    /**
//     * Delete route from the database
//     *
//     * @param routeId - targets identifier
//     * @return - redirect url.
//     */
//    @RequestMapping("/delete/{routeId}")
//    public String deleteRoute(@PathVariable("routeId") Long routeId) {
//        routeService.deleteRoute(routeId);
//        return "redirect:/routeView/routes";
//    }
//
////    /**
////     * Throw us to the update form
////     *
////     * @param stationId - targets identifier
////     * @param model     - model of view.
////     * @return - target url.
////     */
////    @RequestMapping("/updateRoute/{stationId}")
////    public String updateStation(@PathVariable("stationId") Long stationId,
////                                Model model) {
////        model.addAttribute("station", stationService.findStation(stationId));
////        return "stationView/updateRoute";
////    }
////
////    /**
////     * Update station
////     *
////     * @param station - target station to update
////     * @return - redirect url.
////     */
////    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
////    public String refreshStation(@ModelAttribute("station") Station station) {
////        stationService.updateStation(station);
////        return "redirect:/stationView/stations";
////    }
//
//}
//
//
//
//
////package ru.javaschool.controller;
////
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Controller;
////import org.springframework.ui.Model;
////import org.springframework.web.bind.annotation.ModelAttribute;
////import org.springframework.web.bind.annotation.PathVariable;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RequestMethod;
////import ru.javaschool.dto.StationDistanceDto;
////import ru.javaschool.model.entities.Route;
////import ru.javaschool.model.entities.Station;
////import ru.javaschool.services.RouteService;
////import ru.javaschool.services.StationService;
////
////import javax.servlet.http.HttpSession;
////import java.util.ArrayList;
////import java.util.List;
////
/////**
//// * Controls route service.
//// */
////@Controller
////@RequestMapping("/routeView")
////public class RouteController {
////
////    @Autowired
////    private RouteService routeService;
////
////    @Autowired
////    private StationService stationService;
////
////    /**
////     * Get route list.
////     * @param model - model of view.
////     * @return - target url
////     */
////    @RequestMapping("/routes")
////    public String getRouteList(Model model) {
////        model.addAttribute("route", new Route());
////        model.addAttribute("routeList", routeService.getAllRoutes());
////        return "routeView/routes";
////    }
////
////    @RequestMapping("/details/{routeId}")
////    public String getStationDistances(@PathVariable("routeId") Long routeId,
////                                      Model model) {
////        model.addAttribute("stationDistanceDto", new StationDistanceDto());
////        model.addAttribute("stationDistances", routeService.getStationDistances(routeId));
////        return "routeView/details";
////    }
////
////    /**
////     * Apply new url, which will be the form,
////     * to set needed fields, to create route.
////     *
////     * @param model - - model of view.
////     * @return - target url
////     */
////    @SuppressWarnings("unchecked")
////    @RequestMapping(value = "/createRoute", method = RequestMethod.POST)
////    public String createRoute(HttpSession session, Model model) {
////        model.addAttribute("distanceList", session.getAttribute("distanceList"));
////        if(session.getAttribute("distanceList") != null){
////            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
////            distanceList.clear();
////            session.setAttribute("distanceList", distanceList);
////        }
////        model.addAttribute("stationList", stationService.getAllStations());
////        model.addAttribute("route", new Route());
////        return "routeView/createRoute";
////    }
////
////    @SuppressWarnings("unchecked")
////    @RequestMapping(value = "/addStation", method = RequestMethod.POST)
////    public String addStation(HttpSession session, @ModelAttribute("stationDistanceDto") StationDistanceDto stationDistanceDto,
////                             @ModelAttribute("stationList") ArrayList<Station> stationList) {
////        if(session.getAttribute("distanceList") != null) {
////            List<StationDistanceDto> distanceList = (List<StationDistanceDto>) session.getAttribute("distanceList");
////            distanceList.add(stationDistanceDto);
////            session.setAttribute("distanceList", distanceList);
////        }
////        return "redirect:/routeView/createRoute";
////    }
////
////    /**
////     * Create new route and redirect us to central view.
////     *
////     * @param route - target route to create
////     * @return - redirect url.
////     */
////    @SuppressWarnings("unchecked")
////    @RequestMapping(value = "/add", method = RequestMethod.POST)
////    public String addRoute(HttpSession session, @ModelAttribute("route") Route route) {
////        if(session.getAttribute("distanceList") != null) {
////            List<StationDistanceDto> distanceList = (ArrayList<StationDistanceDto>) session.getAttribute("distanceList");
////            routeService.createRoute(route, distanceList);
////        }
////            return "redirect:/routeView/routes";
////    }
////
////    /**
////     * Delete route from the database
////     *
////     * @param routeId - targets identifier
////     * @return - redirect url.
////     */
////    @RequestMapping("/delete/{routeId}")
////    public String deleteRoute(@PathVariable("routeId") Long routeId) {
////        routeService.deleteRoute(routeId);
////        return "redirect:/routeView/routes";
////    }
////
//////    /**
//////     * Throw us to the update form
//////     *
//////     * @param stationId - targets identifier
//////     * @param model     - model of view.
//////     * @return - target url.
//////     */
//////    @RequestMapping("/updateRoute/{stationId}")
//////    public String updateStation(@PathVariable("stationId") Long stationId,
//////                                Model model) {
//////        model.addAttribute("station", stationService.findStation(stationId));
//////        return "stationView/updateRoute";
//////    }
//////
//////    /**
//////     * Update station
//////     *
//////     * @param station - target station to update
//////     * @return - redirect url.
//////     */
//////    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
//////    public String refreshStation(@ModelAttribute("station") Station station) {
//////        stationService.updateStation(station);
//////        return "redirect:/stationView/stations";
//////    }
////
////}
