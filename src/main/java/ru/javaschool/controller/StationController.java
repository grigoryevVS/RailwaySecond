package ru.javaschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javaschool.model.entities.Station;
import ru.javaschool.services.StationService;

@Controller
@RequestMapping("/stationView")
public class StationController {

    @Autowired
    private StationService stationService;

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
     * Apply new url, which will be the form,
     * to set needed fields, to create station.
     *
     * @param model - - model of view.
     * @return - target url
     */
    @RequestMapping(value = "/createStation", method = RequestMethod.POST)
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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addStation(@ModelAttribute("station") Station station) {
        if (stationService.createStation(station)) {
            return "redirect:/stationView/stations";
        } else {
            return "redirect:/stationView/stations";    // TODO  create error page, will be include info about double entty and button home.
        }
    }

    /**
     * Delete station from the database
     *
     * @param stationId - targets identifier
     * @return - redirect url.
     */
    @RequestMapping("/delete/{stationId}")
    public String deleteStation(@PathVariable("stationId") Long stationId) {
        stationService.deleteStation(stationId);
        return "redirect:/stationView/stations";
    }

    /**
     * Throw us to the update form
     *
     * @param stationId - targets identifier
     * @param model     - model of view.
     * @return - target url.
     */
    @RequestMapping("/updateStation/{stationId}")
    public String updateStation(@PathVariable("stationId") Long stationId,
                                Model model) {
        model.addAttribute("station", stationService.findStation(stationId));
        return "stationView/updateStation";
    }

    /**
     * Update station
     *
     * @param station - target station to update
     * @return - redirect url.
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refreshStation(@ModelAttribute("station") Station station) {
        stationService.updateStation(station);
        return "redirect:/stationView/stations";
    }
}
