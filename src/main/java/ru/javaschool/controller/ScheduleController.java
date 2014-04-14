package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaschool.services.ScheduleService;

@Controller
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;
}
