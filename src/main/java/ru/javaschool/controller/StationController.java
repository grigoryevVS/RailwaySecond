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
    public String getList(Model model) {
        model.addAttribute("station", new Station());
        model.addAttribute("stationList", stationService.getAllStations());
        return "stations";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model) {
        model.addAttribute("station", new Station());
        return "create";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("station")Station station) {
        stationService.createStation(station);
        return "redirect:/stations";
    }

    @RequestMapping("/delete/{stationId}")
    public String delete(@PathVariable("stationId") Long stationId){
        stationService.deleteStation(stationId);
        return "redirect:/stations";
    }

    @RequestMapping("/update/{stationId}")
    public String update(@PathVariable("stationId") Long id,
                         Model model) {
        model.addAttribute("station", stationService.findStation(id));
        return "update";
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public String refresh(@ModelAttribute("station")Station station) {
        stationService.updateStation(station);
        return "redirect:/stations";
    }
}
