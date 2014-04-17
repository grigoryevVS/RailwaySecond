package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaschool.services.RouteService;

/**
 * Controls route service.
 */
@Controller
@RequestMapping("/routeView")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @RequestMapping("/routes")
    public String getRouteList(Model model) {

        return "routeView/routes";
    }

}
