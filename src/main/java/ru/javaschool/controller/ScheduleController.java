package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.services.ScheduleService;

@Controller
@RequestMapping("/scheduleView")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

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
     * Apply new url, which will be the form,
     * to set needed fields, to create schedule.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @RequestMapping(value = "/createSchedule", method = RequestMethod.POST)
    public String createSchedule(Model model) {
        model.addAttribute("schedule", new Schedule());
        return "scheduleView/createSchedule";
    }

    /**
     * Create new schedule and redirect us to central view.
     *
     * @param schedule - target schedule to be created
     * @return - redirect url.
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addSchedule(@ModelAttribute("schedule") Schedule schedule) {
        scheduleService.createSchedule(schedule);
        return "redirect:/scheduleView/schedule";
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

