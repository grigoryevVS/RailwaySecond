package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.services.RouteService;
import ru.javaschool.services.ScheduleService;
import ru.javaschool.services.TrainService;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/scheduleView")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private RouteService routeService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    /**
     * Get schedule list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/schedule")
    public String getScheduleList(Model model) {
        model.addAttribute("scheduler", new ScheduleDto());
        model.addAttribute("scheduleList", scheduleService.getAllSchedule());
        return "scheduleView/schedule";
    }

    /**
     * Get schedule list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/scheduleIndex")
    public String index(Model model) {
        model.addAttribute("scheduler", new ScheduleDto());
        model.addAttribute("scheduleList", scheduleService.getAllSchedule());
        return "scheduleView/scheduleIndex";
    }

    /**
     * Apply new url, which will be the form,
     * to set needed fields, to create schedule.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @RequestMapping(value = "/createSchedule", method = RequestMethod.POST)
    public String createSchedule(Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("trainList", trainService.getAllTrains());
        model.addAttribute("routeList", routeService.getAllRoutes());
        return "scheduleView/createSchedule";
    }

    /**
     * Create new schedule and redirect us to central view.
     *
     * @param schedule - target schedule to be created
     * @return - redirect url.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSchedule(@ModelAttribute("schedule") Schedule schedule,
                              @ModelAttribute("trainName") String trainName,
                              @ModelAttribute("routeName") String routeName,
                              @ModelAttribute("dateTrip") String dateTrip) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTrainName(trainName);
        scheduleDto.setRouteName(routeName);
        scheduleDto.setDate(dateTrip);
        if (scheduleService.wrapAndCreateSchedule(scheduleDto)) {
            return "redirect:/scheduleView/schedule";
        } else
            return "redirect:/scheduleView/schedule"; // TODO error hanDle.
    }

    /**
     * Delete schedule from the database
     *
     * @param scheduleId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{scheduleId}")
    public String deleteSchedule(@PathVariable("scheduleId") Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return "redirect:/scheduleView/schedule";
    }

    /**
     * Throw us to the update form
     *
     * @param scheduleId - targets identifier
     * @param model      - model of view.
     * @return - target url.
     */
    @RequestMapping("/updateSchedule/{scheduleId}")
    public String updateSchedule(@PathVariable("scheduleId") Long scheduleId,
                                 Model model) {
        model.addAttribute("schedule", scheduleService.findSchedule(scheduleId));
        return "scheduleView/updateSchedule";
    }

    /**
     * Update station
     *
     * @param schedule - target schedule to update
     * @return - redirect url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshSchedule(@ModelAttribute("schedule") Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return "redirect:/scheduleView/schedule";
    }
}

