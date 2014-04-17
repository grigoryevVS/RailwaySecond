package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaschool.services.AuthorizationService;

@Controller
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;
}
