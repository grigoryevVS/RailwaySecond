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
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping("/stations")
    public String getStationList(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationList", stationService.getAllStations());
        return "/stations";
    }

    @RequestMapping(value = "/createStation", method = RequestMethod.POST)
    public String createStation(@ModelAttribute("station")Station station) {
        stationService.createStation(station);
        return "redirect:/stations";
    }

    @RequestMapping(value = "/deleteStation/{stationId}")
    public String deleteStation(@PathVariable("stationId") Long stationId){
        stationService.deleteStation(stationId);
        return "redirect:/stations";
    }
}
