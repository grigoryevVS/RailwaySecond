package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.dto.ScheduleFilterDto;
import ru.javaschool.model.entities.Schedule;
import ru.javaschool.model.entities.Station;
import ru.javaschool.services.RouteService;
import ru.javaschool.services.ScheduleService;
import ru.javaschool.services.StationService;
import ru.javaschool.services.TrainService;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/scheduleView")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private RouteService routeService;

    @Autowired
    private StationService stationService;

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
        model.addAttribute("scheduleList", scheduleService.getFullSchedule());
        return "scheduleView/schedule";
    }

    /**
     * Get schedule list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/scheduleIndex")
    public String index(ModelMap model) {
        model.put("scheduler", new ScheduleDto());
        model.put("scheduleList", scheduleService.getAllSchedule());
        return "scheduleView/scheduleIndex";
    }

    /**
     * Apply new url, which will be the form,
     * to set needed fields, to create schedule.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @RequestMapping(value = "/createSchedule")
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
    public String addSchedule(@ModelAttribute("schedule") Schedule schedule, BindingResult result,
                              @ModelAttribute("trainName") String trainName,
                              @ModelAttribute("routeName") String routeName,
                              @ModelAttribute("dateTrip") String dateTrip,  RedirectAttributes redAttr) {
        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msg", "Wrong data!");
            return "redirect:/scheduleView/createSchedule";
        }
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTrainName(trainName);
        scheduleDto.setRouteName(routeName);
        scheduleDto.setDate(dateTrip);
        String validateResult = scheduleService.unWrapAndCreateSchedule(scheduleDto);
        if (validateResult.equals("Success!")) {
            return "redirect:/scheduleView/schedule";
        } else {
            redAttr.addFlashAttribute("msg", validateResult);
            return "redirect:/scheduleView/createSchedule";
        }
    }

    /**
     * Delete schedule from the database
     *
     * @param scheduleId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{scheduleId}")
    public String deleteSchedule(@PathVariable("scheduleId") Long scheduleId, RedirectAttributes redAttr) {
        Schedule schedule = scheduleService.findSchedule(scheduleId);
        if (schedule != null) {
            if (!scheduleService.deleteSchedule(schedule)) {
                redAttr.addFlashAttribute("msg", "There are some tickets on this schedule, you can't delete it!");
            }
            return "redirect:/scheduleView/schedule";
        }
        return "error404";
    }

    /**
     * Throw us to the update form
     *
     * @param scheduleId - targets identifier
     * @param model      - model of view.
     * @return - target url.
     */
    @RequestMapping("/updateSchedule/{scheduleId}")
    public String updateSchedule(@PathVariable("scheduleId") Long scheduleId, Model model) {
        Schedule schedule = scheduleService.findSchedule(scheduleId);
        if (schedule != null) {
            model.addAttribute("schedule", schedule);
            model.addAttribute("trainList", trainService.getAllTrains());
            model.addAttribute("routeList", routeService.getAllRoutes());
            return "scheduleView/updateSchedule";
        }
        return "error404";
    }

    /**
     * Update station
     *
     * @param schedule - target schedule to update
     * @return - redirect url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshSchedule(@ModelAttribute("schedule") Schedule schedule,
                                  @ModelAttribute("scheduleId") Long scheduleId,
                                  @ModelAttribute("trainName") String trainName,
                                  @ModelAttribute("routeName") String routeName,
                                  @ModelAttribute("dateTrip") String dateTrip, RedirectAttributes redAttr) {

        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setTrainName(trainName);
        scheduleDto.setRouteName(routeName);
        scheduleDto.setDate(dateTrip);
        scheduleDto.setId(scheduleId);
        String validateResult = scheduleService.updateSchedule(scheduleDto);
        if (validateResult.equals("Success!")) {
            redAttr.addFlashAttribute("msg", "Update successful!");
            return "redirect:/scheduleView/schedule";
        } else {
            redAttr.addFlashAttribute("msg", validateResult);
            return "redirect:/scheduleView/updateSchedule/" + schedule.getScheduleId();
        }
    }

    /**
     * Schedule filter.
     *
     * @param model - view
     * @return - view
     */
    @RequestMapping(value = "/scheduleFilter")
    public String createFilter( Model model) {


        ScheduleFilterDto filter = new ScheduleFilterDto();
        model.addAttribute("filter", filter);
        model.addAttribute("stationListFrom", stationService.getAllStations());
        model.addAttribute("stationListTo", stationService.getAllStations());

        return "scheduleView/scheduleFilter";
    }

    /**
     * Get schedule list in accordance with the filter
     *
     * @param filter  - target filter of schedule
     * @param model   - model of view
     * @param redAttr - parameters to get in redirect pages( validation messages)
     * @return - view
     */
    @RequestMapping(value = "/filteredSchedule")
    public String filteredSchedule(@Valid @ModelAttribute("filter") ScheduleFilterDto filter, BindingResult result, ModelMap model,
                                   RedirectAttributes redAttr) {


        if (result.hasErrors()) {
            return "redirect:/scheduleView/scheduleIndex";
        }

        List<ScheduleDto> schedList = scheduleService.getFilteredSchedule(filter);

        if (schedList.isEmpty()) {
            redAttr.addFlashAttribute("msg", "There are no trains with such filter!");
            return "redirect:/scheduleView/scheduleFilter";
        }
        model.put("scheduler", new ScheduleDto());
        model.put("scheduleList", schedList);
        return "scheduleView/filteredSchedule";
    }

    /**
     * Get stations to the js autocomplete method
     *
     * @param stationName Name of station
     * @return List of station
     */
    @RequestMapping(value = "/getStations", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Station> getStationsForJS(@RequestParam String stationName) {
        List<Station> result = new ArrayList<Station>();
        List<Station> stations = stationService.getAllStations();
        if (!stations.isEmpty()) {
            for (Station station : stations) {
                if (station.getName().contains(stationName)) {
                    result.add(station);
                }
            }
        }
        return result;
    }
}

