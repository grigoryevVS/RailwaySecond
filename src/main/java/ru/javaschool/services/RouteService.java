package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.RouteDao;

@Service
public class RouteService {

    @Autowired
    private RouteDao routeDao;
}
