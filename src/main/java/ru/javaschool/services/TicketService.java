package ru.javaschool.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaschool.dao.TicketDao;

@Service
public class TicketService {

    @Autowired
    private TicketDao ticketDao;
}
