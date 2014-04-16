package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaschool.services.RouteService;

/**
 * Controls route service.
 */
@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;
}
