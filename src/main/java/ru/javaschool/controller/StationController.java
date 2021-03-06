package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.javaschool.dto.ScheduleDto;
import ru.javaschool.dto.ScheduleFilterDto;
import ru.javaschool.model.entities.Station;
import ru.javaschool.services.ScheduleService;
import ru.javaschool.services.StationService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/stationView")
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Get station list.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/stations")
    public String getStationList(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationList", stationService.getAllStations());
        return "stationView/stations";
    }

    /**
     * Get station list for custom user.
     *
     * @param model - model of view.
     * @return - target url
     */
    @RequestMapping("/stationIndex")
    public String index(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationList", stationService.getAllStations());
        return "stationView/stationIndex";
    }

    /**
     * This method implements working with stations.
     * If user click on the station name, we give him
     * schedule for this target station.
     *
     * @param session     - http session
     * @param stationName - target station name
     * @return - result url
     */
    @RequestMapping("/stationFilter/{stationName}")
    public String stationFilter(HttpSession session, @PathVariable("stationName") String stationName) {
        Station station = stationService.getStationByName(stationName);
        if (station != null) {
            if (session.getAttribute("filter") == null) {
                session.setAttribute("filter", new ScheduleFilterDto());
            }
            ScheduleFilterDto filter = (ScheduleFilterDto) session.getAttribute("filter");
            filter.setStationFromName(stationName);
            filter.setDate("");
            filter.setStationToName("");
            session.setAttribute("filter", filter);
            List<ScheduleDto> schedList = scheduleService.getFilteredSchedule((ScheduleFilterDto) session.getAttribute("filter"));
            session.setAttribute("scheduleList", schedList);
            if (schedList.isEmpty()) {
                // to get this message on the filteredSchedule view!
                session.setAttribute("msgf", "There are no trains from station " + stationName + " !");
            }
            return "redirect:/scheduleView/scheduleFilter";
        }
        return "error404";
    }

    /**
     * Apply new url, which will be the form,
     * to set needed fields, to create station.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @RequestMapping(value = "/createStation")
    public String createStation(Model model) {
        model.addAttribute("station", new Station());
        return "stationView/createStation";
    }

    /**
     * Create new station and redirect us to central view.
     *
     * @param station - target station to be created
     * @return - redirect url.
     */
    @RequestMapping(value = "/add")
    public String addStation(@ModelAttribute("station") Station station, RedirectAttributes redAttr) {

        if (station == null || station.getName().equals("")) {
            redAttr.addFlashAttribute("msgf", "Name can't be empty!");
            return "redirect:/stationView/createStation";
        }
        if (stationService.createStation(station)) {
            redAttr.addFlashAttribute("msgg", "Create station " + station.getName() + " successful!");
            return "redirect:/stationView/stations";
        } else {
            redAttr.addFlashAttribute("msgf", "Such station is already exist!");
            return "redirect:/stationView/createStation";
        }
    }

    /**
     * Delete station from the database
     *
     * @param stationId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{stationId}")
    public String deleteStation(@PathVariable("stationId") Long stationId, RedirectAttributes redAttr) {
        Station station = stationService.findStation(stationId);
        if (station != null) {
            if (!stationService.deleteStation(station)) {
                redAttr.addFlashAttribute("msgf", "Station " + station.getName() + " exist in some route, you can't delete it!");
                return "redirect:/stationView/stations";
            }
            redAttr.addFlashAttribute("msgg", "Delete station " + station.getName() + " successful!");
            return "redirect:/stationView/stations";
        }
        return "error404";
    }

    /**
     * Throw us to the update form
     *
     * @param stationId - targets identifier
     * @param model     - model of view.
     * @return - target url.
     */
    @RequestMapping("/updateStation/{stationId}")
    public String updateStation(@PathVariable("stationId") Long stationId, Model model) {
        Station station = stationService.findStation(stationId);
        if (station != null) {
            model.addAttribute("station", station);
            return "stationView/updateStation";
        }
        return "error404";
    }

    /**
     * Update station
     *
     * @param station - target station to update
     * @return - redirect url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshStation(@Valid @ModelAttribute("station") Station station, BindingResult result,
                                 RedirectAttributes redAttr) {
        if (result.hasErrors()) {
            redAttr.addFlashAttribute("msgf", "Wrong data!");
            return "redirect:/stationView/updateStation/" + station.getStationId();
        }
        if (stationService.getStationByName(station.getName()) != null) {
            redAttr.addFlashAttribute("msgf", "Such station name is already exist!");
            return "redirect:/stationView/updateStation/" + station.getStationId();
        }
        stationService.updateStation(station);
        redAttr.addFlashAttribute("msgg", "Update station " + station.getName() + " successful!");
        return "redirect:/stationView/stations";
    }
}