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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.dto.ScheduleFilterDto;
import ru.javaschool.dto.TicketDto;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Ticket;
import ru.javaschool.model.entities.User;
import ru.javaschool.services.ScheduleService;
import ru.javaschool.services.TicketService;
import ru.javaschool.services.UserService;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    /**
     * Buy ticket on target schedule
     * after all validation checks.
     *
     * @param scheduleId - schedule identifier
     * @param model      - model view
     * @return - view of successfully bought ticket, or validation message.
     */
    @RequestMapping("buyTicket/{scheduleId}")
    public String buyTicket(@PathVariable("scheduleId") Long scheduleId, Model model, RedirectAttributes redAttr, HttpSession session) {

        User user = userService.getUserByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        Schedule schedule = scheduleService.findSchedule(scheduleId);
        if (schedule != null) {
            ScheduleFilterDto filter = (ScheduleFilterDto) session.getAttribute("filter");
            String validateResult;
            if (filter != null) {
                validateResult = ticketService.buyTicket(user, schedule, filter.getStationFromName(), filter.getStationToName());
            } else {
                validateResult = ticketService.buyTicket(user, schedule, "", "");
            }
            if (validateResult.equals("Buy ticket success!")) {
                List<ScheduleDto> schedList = scheduleService.getFilteredSchedule((ScheduleFilterDto) session.getAttribute("filter"));
                session.setAttribute("scheduleList", schedList);
                TicketDto ticketDto;
                if (filter != null) {
                    ticketDto = new TicketDto(user, schedule, filter.getStationFromName(), filter.getStationToName());
                } else {
                    ticketDto = new TicketDto(user, schedule, "", "");
                }
                model.addAttribute("ticket", ticketDto);
                return "scheduleView/buyTicket";
            } else {
                redAttr.addFlashAttribute("msgf", validateResult);
                return "redirect:/scheduleView/scheduleFilter";
            }
        } else {
            return "error404";
        }
    }

    /**
     * Getting all registered passengers on target train
     *
     * @param scheduleId - identifier of target schedule
     * @param model      - view
     * @return - view of list passengers.
     */
    @RequestMapping("passengers/{scheduleId}")
    public String passengers(@PathVariable("scheduleId") Long scheduleId, Model model) {

        Schedule schedule = scheduleService.findSchedule(scheduleId);
        if (schedule != null) {
            model.addAttribute("userList", ticketService.getAllRegisteredOnTrain(schedule));
            model.addAttribute("schedule", schedule);
            return "scheduleView/passengers";
        } else {
            return "error404";
        }
    }

    /**
     * Administrator can delete tickets of passengers if they can't use there ticket
     * caused by alimony as example.
     *
     * @param userId     - target user identifier
     * @param scheduleId - target schedule identifier
     * @return - view of registered passengers on train, after deleting.
     */
    @RequestMapping(value = "passengers/deletePassenger/{userId}/{scheduleId}")
    public String deletePassengerFromTrain(@PathVariable("userId") Long userId,
                                           @PathVariable("scheduleId") Long scheduleId, HttpSession session) {
        Ticket ticket = ticketService.getTicket(userId, scheduleId);
        if (ticket != null) {
            ticketService.deleteTicket(ticket);
            if (session.getAttribute("filter") == null) {
                session.setAttribute("filter", new ScheduleFilterDto());
            }
            List<ScheduleDto> schedList = scheduleService.getFilteredSchedule((ScheduleFilterDto) session.getAttribute("filter"));
            if (session.getAttribute("schedList") == null) {
                session.setAttribute("schedList", new ArrayList<ScheduleDto>());
            }
            session.setAttribute("scheduleList", schedList);
            return "redirect:/scheduleView/passengers/{scheduleId}";
        } else {
            return "error404";
        }
    }
}
