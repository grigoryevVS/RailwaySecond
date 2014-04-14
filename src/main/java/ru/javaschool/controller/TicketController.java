package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaschool.services.TicketService;

@Controller
public class TicketController {

    @Autowired
    private TicketService ticketService;
}
