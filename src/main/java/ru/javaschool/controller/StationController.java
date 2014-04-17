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

    @RequestMapping("/stations")
    public String getStationList(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationList", stationService.getAllStations());
        return "stationView/stations";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model) {
        model.addAttribute("station", new Station());
        return "stationView/create";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("station") Station station) {
        if(stationService.createStation(station)){
        return "redirect:/stationView/stations";
        } else {
            return "redirect:/stationView/stations";    // TODO  create error page, will be include info about double entty and button home.
        }
    }

    @RequestMapping("/delete/{stationId}")
    public String delete(@PathVariable("stationId") Long stationId) {
        stationService.deleteStation(stationId);
        return "redirect:/stationView/stations";
    }

    @RequestMapping("/update/{stationId}")
    public String update(@PathVariable("stationId") Long stationId,
                         Model model) {
        model.addAttribute("station", stationService.findStation(stationId));
        return "stationView/update";
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refresh(@ModelAttribute("station") Station station) {
        stationService.updateStation(station);
        return "redirect:/stationView/stations";
    }
}
