package ru.javaschool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.javaschool.dto.TicketDto;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.User;
import ru.javaschool.services.ScheduleService;
import ru.javaschool.services.TicketService;
import ru.javaschool.services.UserService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/scheduleView")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("buyTicket/{scheduleId}")
    public String buyTicket(@PathVariable("scheduleId") Long scheduleId, Model model) {
        User user = userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Schedule schedule = scheduleService.findSchedule(scheduleId);
        if(ticketService.buyTicket(user, schedule)) {
            TicketDto ticketDto = new TicketDto(user, schedule);
            model.addAttribute("ticket", ticketDto);
            return "scheduleView/buyTicket";
        } else {
            return "redirect:/scheduleView/scheduleIndex";  // TODO validation msg
        }
    }
}
